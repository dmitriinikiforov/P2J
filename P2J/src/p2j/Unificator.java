
package p2j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class Unificator {
     HashMap<String, Argument> map;
     static ArrayList<Statement> program;
     Structure query;
     
     public Unificator() {
        map = new HashMap<String, Argument>(); 
     }
     
     public Unificator(Structure query) {
         map = new HashMap<String, Argument>();
         
         this.query = query;
     }

    public static void setProgram(ArrayList<Statement> nprogram) {
        program = nprogram;
    }
     
    public boolean unifyProgram() {
        for (Statement rule : program) {
//            System.out.println("*** "+map+" *** "+rule+" *** "+query);            
            if (unifyStatement(rule, query)) {
//                System.out.println("Unified statement success: "+toString());
                return true;
            } else {
                map.clear();
            }
        }
        return false;
    } 
     
    public boolean unifyStatement(Statement rule, Structure query) {
        
        if (unify(rule.left, query)) {
            HashMap<String, Argument> tempMap = new HashMap<String, Argument>();
            boolean ok = true;
            if (rule.right==null) return true;
//            pullUpVariables();
            LinkedList<Structure> list = new LinkedList<Structure>();
            for (Structure rightStructure : rule.right) {
                list.add(replaceVariables(rightStructure));
            }
            for (Structure rightStructure : list) {
                Unificator newUnificator = new Unificator(rightStructure);
                ok = newUnificator.unifyProgram();
//                System.out.println("======  Un   "+newUnificator.map);
                if (!ok) return false;
                tempMap = resolve(tempMap, newUnificator.getMap());
                if (tempMap==null) return false;
                
            }
            
            map =resolveUp(map,tempMap);
//            pullUpVariables();
            //map = resolve(map, resolveUp(map,tempMap));
            if (map==null) return false;
            //filterMap();
            for (String key : tempMap.keySet()) {
                if (!map.containsKey(key)) {
                    map.put(key, tempMap.get(key));
                }
            }
           //pullUpVariables();
           return ok;   
        }
        return false;
    }
    
    public void pullUpVariables() {
        
        HashMap<String, Argument> tempMap = (HashMap<String, Argument>) map.clone();
        for (String string : map.keySet()) {
            
            Argument arg = map.get(string);
            if (arg.getClass().getSimpleName().equals("Variable")) {

                tempMap.put(string, pullUpVariable((Variable) arg));
            }
            
        }
        map = tempMap;
    }
    
    public Argument pullUpVariable(Variable var) {
        if (!map.containsKey(var.name)) return var;
        Argument arg = map.get(var.name);
        if (arg.getClass().getSimpleName().equals("Variable")) {
            return pullUpVariable((Variable) arg);
        } else if (arg.getClass().getSimpleName().equals("List")) {
            return pullUpList((List)arg);
        } else if (arg.getClass().getSimpleName().equals("Structure")) {
            return pullUpStructure((Structure) arg);
        } else  {
            return arg;
        }
    }
    
    public List pullUpList(List list) {
        
        if (list.args.isEmpty()) {
            return list;
        }
        List newList = new List();
        for (Argument arg : list.args) {
            Argument newArg;
            switch (arg.getClass().getSimpleName()) {
                case "Variable" :
                    newArg = pullUpVariable((Variable) arg);
                    break;
                case "Structure" :
                    newArg = pullUpStructure((Structure) arg);
                    break;
                case "List" :
                    newArg = pullUpList((List) arg);
                    break;
                default :
                    newArg = arg;
            }
            newList.addArgument(newArg);
        }
        return newList;
    }
    
    public Structure pullUpStructure(Structure structure) {
        
        if (structure.args.isEmpty()) {
            return structure;
        }
        Structure newStructure = new Structure(structure.functor);
        for (Argument arg : structure.args) {
            Argument newArg;
            switch (arg.getClass().getSimpleName()) {
                case "Variable" :
//                    System.out.println(structure+" "+arg);
                    newArg = pullUpVariable((Variable) arg);
                    
                    break;
                case "Structure" :
                    newArg = pullUpStructure((Structure) arg);
                    break;
                case "List" :
                    newArg = pullUpList((List) arg);
                    break;
                default :
                    newArg = arg;
            }
            newStructure.addArgument(newArg);
        }
        return newStructure;
    }
    
    
    public static HashMap<String, Argument> resolve(HashMap<String, Argument> prevMap, HashMap<String, Argument> laterMap) {
////        System.out.println("*** prev: "+prevMap+" later: "+laterMap+" ***");
        HashMap<String, Argument> newMap = (HashMap<String, Argument>) prevMap.clone();
        for (String s : laterMap.keySet()) {
            if (newMap.containsKey(s)) {
                if (newMap.get(s)!=laterMap.get(s)) {                    
//                    System.out.println(s+": "+newMap.get(s)+" vs "+laterMap.get(s)); // fucking conflict
                    return null;
                }
            } else {
                newMap.put(s, laterMap.get(s));
            }
        }
        prevMap = newMap;
//        System.out.println("*** prev: "+prevMap+" later: "+laterMap+" *** end method");
        return prevMap;
    }
    
    static boolean pulled;
    public HashMap<String, Argument> resolveUp(HashMap<String, Argument> upMap, HashMap<String, Argument> downMap) {
        
        HashMap<String, Argument> tempMap = (HashMap<String, Argument>) upMap.clone();
        do {
            pulled = false;
            for (String key : upMap.keySet()) {
                Argument arg = pull(tempMap, downMap, key);
                tempMap.put(key, arg);
            }
        } while (pulled);
        upMap = tempMap;
        return upMap;
   }
 
        
    public Argument pull(HashMap<String, Argument> upMap, HashMap<String, Argument> downMap, String key) {
//        System.out.println("==== upMap: "+upMap+" downMap: "+downMap+" var: "+key);
        //if (!upMap.containsKey(key)) return pull(downMap, downMap, key);
        switch (upMap.get(key).getClass().getSimpleName()) {
            
            case "Variable" :
                Variable var = (Variable) upMap.get(key);
                if (!upMap.containsKey(var.name)) {
//                    System.out.println("==== upMap: "+upMap+" downMap: "+downMap+" var: "+var.name);
                    if (downMap.containsKey(var.name)) {
                        
                        Argument arg = pull(downMap, downMap, var.name);
                        pulled = true;
                        upMap.put(var.name, arg);
                        return arg;
                    }
                } else {
                    //return pullUpVariable(var);
                }                   
                return var;
            case "List" :
                List list = (List) upMap.get(key);
                List newList = new List();
                for (Argument arg : list.args) {
                    if (arg.getClass().getSimpleName().equals("Variable")) {
                        Variable var1 = (Variable) arg;
                        Argument arg1;
                        if (upMap.containsKey(var1.name)) {
                            arg1 = pull(upMap, downMap, var1.name);
//                            arg1 = var1;
                        } else if (downMap.containsKey(var1.name)) {
                            arg1 = pull(downMap, downMap, var1.name);
                        } else {
                            arg1 = var1;
                        }
                        newList.addArgument(arg1);
                    } else {
                        newList.addArgument(arg);
                    }
                }
                return newList;
            case "Structure" :   
                Structure structure = (Structure) upMap.get(key);
                Structure newStructure = new Structure(structure.functor);
                for (Argument arg : structure.args) {
                    if (arg.getClass().getSimpleName().equals("Variable")) {
                        Variable var1 = (Variable) arg;
                        Argument arg1;
                        if (upMap.containsKey(var1.name)) {
//                            if (upMap.get(var1.name).getClass().getSimpleName().equals("Structure")) {
//                                if (upMap.containsValue((Structure)upMap.get(var1.name))) {
//                                    //arg1 = upMap.get(var1.name);
//                                    arg1 = var1;
//                                } else {
//                                    arg1 = pull(upMap, downMap, var1.name);
//                                    //arg1 = var1;
//                                }
//                            }
//                            else {
                                arg1 = pull(upMap, downMap, var1.name);
//                            }
                        } else if (downMap.containsKey(var1.name)) {
                            arg1 = pull(downMap, downMap, var1.name);
                        } else {
                            arg1 = var1;
                        }
                        newStructure.addArgument(arg1);
                    } else {
                        newStructure.addArgument(arg);
                    }
                }
                return newStructure;
            
             default :
                return upMap.get(key);
        }
    }
    
//    public void filterMap() {
//        Argument res;
//        for (String s : map.keySet()) { //ходим по всеч ключам, раскручиваем
//            String str=s;
//            Argument arg=map.get(str);
//            if (arg.getClass().equals("Variable"))
//            do {
//                str=((Variable) arg).name;
//                arg=map.get(str);
//            }
//            while (arg.getClass().equals("Variable"));
//        }
//        
//        
//        
//        
//    }
        
        
    public HashMap<String, Argument> getMap() {
        return map;
    }
    
    public Structure replaceVariables(Structure right) {
        Structure newStructure=new Structure(right.functor);
        for (Argument arg:right.args) {
            String aClassName=arg.getClass().getSimpleName();
            Argument newArg=arg;
            switch (aClassName) {
                case "Structure":
                    Structure argStr=(Structure) arg;
                    newArg=replaceVariables(argStr);
                    break;
                case "List":
                    List argList=(List) arg;
                    newArg=replaceVariables(argList);
                    break;
                case "Variable":
                    Variable var=(Variable) arg;
                    newArg = map.get(var.name);
                    if (newArg==null) {
                        String newName="_"+var.name;
                        while (map.get(newName)!=null) {
                            newName="_"+newName;
                        }
                        newArg = new Variable(newName);
                        map.put(var.name, newArg);
                    } else {
                        
                    }
            }
            newStructure.addArgument(newArg);
        }
        return newStructure;
    }
    
    public boolean unify(Argument rule, Argument query) {
        String ruleClassName = rule.getClass().getSimpleName();
        switch (ruleClassName) {
            case "Number":
                return unifyNumber((Number) rule, query);
            case "Structure":
                return unifyStructure((Structure) rule, query);
            case "Variable":
                return unifyVariable((Variable) rule, query); 
            case "List":
                return unifyList((List) rule, query);
            case "ArgString":
                return unifyArgString((ArgString) rule, query);
        }
//        System.out.println("Unexpectively!");
        return false;
    }
    
    
    boolean unifyVariable(Variable rule, Argument query) {
//        String queryClassName=query.getClass().getSimpleName();
//        switch (queryClassName) {
//            case "Structure":
//                Structure qStr=(Structure) query;
//                Variable newV=new Variable("_"+rule.name);
//                currentHashMap.put(newV, query);
//                currentHashMap.put(rule, newV);
//                return true;
//        }
//        System.out.println(map+" ======= "+rule+" -> "+query);
        Argument arg=map.get(rule.name);
        
        //
                        if (query.getClass().getSimpleName().equals("Variable")) {
                    String qVarName=((Variable) query).name;
                    if (rule.name.equals(qVarName)) {
                        return true;
                    }
                        }
        //
        
        if (arg==null) {
            map.put(rule.name, query);
            
            return true;
        } else {
            if (arg.getClass().getSimpleName().equals("Variable")) {
//                map.put(((Variable) arg).name,query);
                //return true;
                return unifyVariable(((Variable) arg),query);
            } else {
                if (query.getClass().getSimpleName().equals("Variable")) {
                    String qVarName=((Variable) query).name;
                    if (!rule.name.equals(qVarName)) {
                        map.put(qVarName, rule);
                    }
                } else {
                    return unify(arg, query);
                }
            }
        }
        return true;
    }
    
    boolean unifyNumber(Number rule, Argument query) {
        String queryClassName=query.getClass().getSimpleName();
        switch (queryClassName) {
            case "Number":
                Number qNum=(Number) query;
                return qNum.value==rule.value;
            case "Variable":
                map.put(((Variable) query).name,rule);
                return true;
        }
        return false;
    }
    
    boolean unifyArgString(ArgString rule, Argument query) {
        String queryClassName=query.getClass().getSimpleName();
        switch (queryClassName) {
            case "ArgString":
                ArgString qArgString=(ArgString) query;
                return qArgString.string.equals(rule.string);
            case "Variable":
                map.put(((Variable) query).name, rule);
                return true; 
        }
        return false;
    }
    
    boolean unifyList(List rule, Argument query) {
        String queryClassName = query.getClass().getSimpleName();
        switch (queryClassName) {
            case "List":
                List qList=(List) query;
//                System.out.println("= = = = mapa "+map+" unifying"+rule+qList);
                int qSize=qList.args.size();
                int rSize=rule.args.size();
                boolean ok=true;
                if (qSize==rSize) {
                    for (int i=0; i<qSize; i++) {
                        ok=ok&&unify(rule.args.get(i),qList.args.get(i));
                    }
                    return ok;
                }
                if (rSize==0) return false;
                int min=Math.min(qSize,rSize);
                
                if ((qSize>rSize)&&(rule.args.get(min-1).getClass().getSimpleName().equals("Variable"))) { 
                    if (rSize==1) return false;
//                    System.out.println(rule.args.get(min-1)+"*");                   
                    List tail=new List(new LinkedList<Argument>(qList.args.subList(min-1, qSize))); 
                    ok=unifyVariable((Variable) rule.args.get(min-1), tail);
                    if (!ok) return false;
                } else if ((qSize<rSize)&&(qList.args.get(min-1).getClass().getSimpleName().equals("Variable"))) {
                    if (qSize==1) return false;
                    List tail=new List(new LinkedList<Argument>( rule.args.subList(min-1, rSize-1)));
                    ok=unifyList(tail, (Variable) qList.args.get(min-1));
//                    System.out.println("**"+ok+" unifying "+tail+" and "+qList.args.get(min-1));
                    if (!ok) return false;
                } else { 
                    return false;
                }
                for (int i=0; i<min-1; i++) {
//                    System.out.println(rule.args.get(i)+" - - - "+qList.args.get(i));
                    ok=ok&&unify(rule.args.get(i),qList.args.get(i));
                }                
                return ok;
            case "Variable":
                map.put(((Variable) query).name,rule);
                return true;
        }
        return false;
    }
    
    boolean unifyStructure(Structure rule, Argument query) {
        String queryClassName = query.getClass().getSimpleName();
        switch (queryClassName) {
            case "Structure":
                Structure qStr=(Structure)query;
                if (!qStr.functor.equals(rule.functor)) return false;                
                int size=qStr.args.size();
                if (rule.args.size()!=size) return false;
                boolean ok=true;
                for (int i=0; i<size; i++) {
                    ok=ok&&unify(rule.args.get(i),qStr.args.get(i));
                }
                return ok;
            case "Variable":
                map.put(((Variable) query).name, rule);
                return true;
        }
        return false;
    }
        
     @Override
    public String toString() {     
         return this.hashCode()+" query: "+query+" map: "+map;
    }

    private Argument replaceVariables(List list) {
        
        List newList=new List();
        for (Argument arg:  list.args) {
            String aClassName=arg.getClass().getSimpleName();
            Argument newArg=arg;
            switch (aClassName) {
                case "Structure":
                    Structure argStr=(Structure) arg;
                    newArg=replaceVariables(argStr);
                    break;
                case "List":
                    List argList=(List) arg;
                    newArg=replaceVariables(argList); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    break;
                case "Variable":
                    Variable var=(Variable) arg;
                    newArg = map.get(var.name);
                    if (newArg==null) {
                        String newName="_"+var.name;
                        while (map.get(newName)!=null) {
                            newName="_"+newName;
                        }
                        newArg = new Variable(newName);
                        map.put(var.name, newArg);
                    } else {
                        
                    }
            }
            newList.addArgument(newArg);
        }
        return newList;
    }
}


package p2j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;


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
            System.out.println(rule+" *** "+query);            
            if (unifyStatement(rule, query)) {
                System.out.println("Unified statement success: "+toString());
                return true;
            }
        }
        return false;
    } 
     
    public boolean unifyStatement(Statement rule, Structure query) {
        
        if (unify(rule.left, query)) {
            HashMap<String, Argument> tempMap = new HashMap<String, Argument>();
            boolean ok = true;
            if (rule.right==null) return true;
            LinkedList<Structure> list = new LinkedList<Structure>();
            for (Structure rightStructure : rule.right) {
                list.add(replaceVariables(rightStructure));
            }
//            System.out.println("before clearinig "+map);
//            Collection<String> keySet=map.keySet();
//            HashMap<String,Argument> myMap=(HashMap<String,Argument>) map.clone();
//            for (String key:keySet) {
//                if (!map.get(key).getClass().getSimpleName().equals("Variable")) myMap.remove(key);
//                
//            }
//            map=myMap;
//            System.out.println("after clearinig "+map);
            for (Structure rightStructure : list) {
                Unificator newUnificator = new Unificator(rightStructure);
                ok = newUnificator.unifyProgram();
                System.out.println("======     "+newUnificator.map);
                if (!ok) return false;
                tempMap = resolve(tempMap, newUnificator.getMap());
                if (tempMap==null) return false;
            }
            map =resolve(map,tempMap);
            if (map==null) return false;
            //filterMap();
            
            return ok;   
        }
        return false;
    }
    
    public static HashMap<String, Argument> resolve(HashMap<String, Argument> prevMap, HashMap<String, Argument> laterMap) {
        System.out.println("*** prev: "+prevMap+" later: "+laterMap+" ***");
        HashMap<String, Argument> newMap = (HashMap<String, Argument>) prevMap.clone();
        for (String s : laterMap.keySet()) {
            if (newMap.containsKey(s)) {
                if (newMap.get(s)!=laterMap.get(s)) {                    
                    System.out.println(s+": "+newMap.get(s)+" vs "+laterMap.get(s)); // fucking conflict
                    return null;
                }
            } else {
                newMap.put(s, laterMap.get(s));
            }
        }
        prevMap = newMap;
        System.out.println("*** prev: "+prevMap+" later: "+laterMap+" *** end method");
        return prevMap;
    }
    
    public static boolean resolveUp(HashMap<String, Argument> upMap, HashMap<String, Argument> downMap) {
        
        
        return true;
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
//        System.out.println(toString());
//        System.out.println("new structure ---------- "+newStructure);
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
        System.out.println("Unexpectively!");
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
        System.out.println(map+" ======= "+rule+" -> "+query);
        Argument arg=map.get(rule.name);
        if (arg==null) {
            map.put(rule.name, query);
            return true;
        } else {
            if (arg.getClass().getSimpleName().equals("Variable")) {
                map.put(((Variable) arg).name,query);
                map.put(rule.name,query);
                return true;
            } else {
                if (query.getClass().getSimpleName().equals("Variable")) {
                    map.put(((Variable) query).name, rule);
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
                //Добавить переменную в стек
                return true; 
        }
        return false;
    }
    
    boolean unifyList(List rule, Argument query) {
        String queryClassName = query.getClass().getSimpleName();
        switch (queryClassName) {
            case "List":
                List qList=(List) query;
                System.out.println("= == = mapa "+map+" unifying"+rule+qList);
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
                boolean more=qSize>rSize; //true for bigger query list size
                int min=Math.min(qSize,rSize);
                
                if (more&&(rule.args.get(min-1).getClass().getSimpleName().equals("Variable"))) { 
                    System.out.println(rule.args.get(min-1)+"*");                   
                    List tail=new List(new LinkedList<Argument>(qList.args.subList(min-1, qSize))); 
                    ok=unifyVariable((Variable) rule.args.get(min-1), tail);
                    if (!ok) return false;
                } else if (!more&&(qList.args.get(min-1).getClass().getSimpleName().equals("Variable"))) {
                    System.out.println("**");
                    List tail=new List((LinkedList<Argument>) rule.args.subList(min-1, rSize-1));
                    ok=unifyList(tail, (Variable) qList.args.get(min-1));
                    if (!ok) return false;
                } else { 
                    return false;
                }
                for (int i=0; i<min-1; i++) {
                    System.out.println(rule.args.get(i)+" - - - "+qList.args.get(i));
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

    private Argument replaceVariables(List argList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

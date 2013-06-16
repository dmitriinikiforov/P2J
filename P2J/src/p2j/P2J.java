package p2j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class P2J {
        static ArrayList<Statement> program;
//        static Statement curStatement;
        public static Structure query;
        public static HashMap<String, Variable> queryVars;
        public static StringBuffer textSB = new StringBuffer();
        
    public static void main(String[] args) throws IOException {
        if (args==null||args.length==0) {  //GUI
            P2JFrame frame = new P2JFrame();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        if (args.length==1) { //compile
            String sourceName=args[0];
            File source=new File(sourceName);
            if (!source.exists()) {
                System.out.println("File \""+sourceName+"\" doesn't exist");
                return;
            }
            if (!source.canRead()) {
                System.out.println("File \""+sourceName+"\" cannot be read");
                return;
            }
            try {
                
                Compiler.compile(args[0]);
            } catch (Exception e) {
                System.out.println("Something got wrong");
                e.printStackTrace();
            } finally {
                return;
            }             
        }
        if (args.length>1) { //interpret
            String programName=args[0];
            String queryName=args[1];
            File programFile=new File(programName);
            File queryFile=new File(queryName);
            if (!programFile.exists()) {
                System.out.println("File \""+programName+"\" doesn't exist");
                return;
            }
            if (!programFile.canRead()) {
                System.out.println("File \""+programName+"\" cannot be read");
                return;
            }
             if (!queryFile.exists()) {
                System.out.println("File \""+queryName+"\" doesn't exist");
                return;
            }
            if (!queryFile.canRead()) {
                System.out.println("File \""+queryName+"\" cannot be read");
                return;
            }
            try {
            //interpreter
            PrologLexer pl=new PrologLexer((CharStream) new ANTLRFileStream(args[0])); 
            PrologParser pp=new PrologParser(new CommonTokenStream(pl));
            Listener listener=new Listener();
            pp.addParseListener(listener);
            pp.program();

            pl=new PrologLexer((CharStream) new ANTLRFileStream(args[1])); 
            pp=new PrologParser(new CommonTokenStream(pl));
            QueryListener qListener=new QueryListener();
            pp.addParseListener(qListener);
            pp.program();
            System.out.println(query);
            textSB.append(query).append("\n").append("\n");
            
            queryVars = new HashMap<String, Variable>();
            addAndRenameVarsFrom(query);
            //output vars
            Unificator unificator = new Unificator(query);
            Unificator.setProgram(program);
            boolean result = unificator.unifyProgram();
//            System.out.println(unificator.getMap());
            outPutToString(unificator.getMap(), result);
            } catch (Exception e) {
                System.out.println("Something got wrong");
                e.printStackTrace();
            } finally {
                //return;
            }      
        }
    }

    public static void addAndRenameVarsFrom(Structure structure) {
    
        for (int i=0; i< structure.args.size(); i++) {
            Argument arg = structure.args.get(i);
            switch (arg.getClass().getSimpleName()) {
                case "Variable":
                    Variable var = new Variable(((Variable) arg).name+"#");
                    structure.args.set(i, var);
                    if (!queryVars.containsKey(var.name)) {
                        queryVars.put(var.name ,var);
                    }
                    break;
                case "List":
                    addAndRenameVarsFrom((List) arg);
                    break;
                case "Structure": 
                    addAndRenameVarsFrom((Structure) arg);
                    break;
            }
        }
    }
    
    
    private static void addAndRenameVarsFrom(List list) {
        
        for (int i=0; i< list.args.size(); i++) {
            Argument arg = list.args.get(i);
            switch (arg.getClass().getSimpleName()) {
                case "Variable":
                    Variable var = new Variable(((Variable) arg).name+"#");
                    list.args.set(i, var);
                    if (!queryVars.containsKey(var.name)) {
                        queryVars.put(var.name,var);
                    }
                    break;
                case "List":
                    addAndRenameVarsFrom((List) arg);
                    break;
                case "Structure": 
                    addAndRenameVarsFrom((Structure) arg);
                    break;
            }
        }
    }
    
    public static void outPutToString(HashMap<String, Argument> map, boolean result) {
        
        
        if (result) {
            if (!queryVars.isEmpty()) {

                map = backRenameInMap(pullUpVariables(map));
//                System.out.println(map);
//                System.out.println(queryVars);
                for (String s : queryVars.keySet()) {
                    Variable var = queryVars.get(s);
                    if (map.containsKey(var.name)) {
                        textSB.append("Variable ").append(var.name.substring(0, var.name.length()-1)).append(" = ").append(map.get(var.name)).append("\n");
                        System.out.println("Variable "+var.name.substring(0, var.name.length()-1)+" = "+map.get(var.name));
                    } else {
                        textSB.append("Variable ").append(var.name.substring(0, var.name.length()-1)).append(" is free"+"\n");
                        System.out.println("Variable "+var.name.substring(0, var.name.length()-1)+" is free");
                    }

                }
            }     
        }
        textSB.append(result);
        System.out.println(result);
    }
    
    public static HashMap<String, Argument> backRenameInMap(HashMap<String, Argument> map) {
        
        HashMap<String, Argument> tempMap = (HashMap<String, Argument>) map.clone();
        for (String s : map.keySet()) {
            Argument arg = map.get(s);
            tempMap.put(s, backRenameArgument(arg));
        }
        return tempMap;
    }
    
    public static Argument backRenameArgument(Argument arg) {
        
        switch (arg.getClass().getSimpleName()) {
            case "Variable" :
                Variable var = (Variable) arg;
                if (var.name.contains("#")) {
                    var = new Variable(var.name.substring(0, var.name.length()-1));
                    //var.name = var.name.substring(0, var.name.length()-1);
                }
                return var;
            case "Structure" :
                return backRenameInStructure((Structure) arg);
            case "List" :
                return backRenameInList((List) arg);
            default :
                return arg;
        }
    }
    
    public static List backRenameInList(List list) {
        
        for (int i = 0; i < list.args.size(); i++) {
            list.args.set(i, backRenameArgument(list.args.get(i)));
        }
        return list;
    }
    
    public static Structure backRenameInStructure(Structure structure) {
        
        for (int i = 0; i < structure.args.size(); i++) {
            structure.args.set(i, backRenameArgument(structure.args.get(i)));
        }
        return structure;
    }
    
    public static HashMap<String, Argument> pullUpVariables(HashMap<String, Argument> map) {
        
        HashMap<String, Argument> tempMap = (HashMap<String, Argument>) map.clone();
        for (String s : map.keySet()) {
            if (queryVars.containsKey(s)) {
                Variable var = (Variable) queryVars.get(s);
                if (map.get(var.name).getClass().getSimpleName().equals("Variable")) {
                    Variable newVar = (Variable) map.get(var.name);
                    if ((!queryVars.containsKey(newVar.name))&&(map.containsKey(newVar.name))) {
                        tempMap.put(s, map.get(newVar.name));
                    }
                }
            }
        }
        return tempMap;
    }
}

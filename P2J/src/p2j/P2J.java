package p2j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class P2J {
        static ArrayList<Statement> program;
        static Statement curStatement;
        static Structure query;
        static HashMap<String, Variable> queryVars;
        
    public static void main(String[] args) throws IOException {
        PrologLexer pl=new PrologLexer((CharStream) new ANTLRFileStream(args[0])); 
        PrologParser pp=new PrologParser(new CommonTokenStream(pl));
        Listener listener=new Listener();
        pp.addParseListener(listener);
        pp.program();
        
        for (Statement st:program) {
            System.out.println(st.toString());
        }
        System.out.println();
        
        pl=new PrologLexer((CharStream) new ANTLRFileStream(args[1])); 
        pp=new PrologParser(new CommonTokenStream(pl));
        QueryListener qListener=new QueryListener();
        pp.addParseListener(qListener);
        pp.program();
        System.out.println(query+"\n");
        queryVars = new HashMap<String, Variable>();
        addAndRenameVarsFrom(query);
        //output vars
        Unificator unificator = new Unificator(query);
        Unificator.setProgram(program);
        boolean result = unificator.unifyProgram();
        System.out.println(unificator.getMap());
        
        outPutToString(unificator.getMap(), result);
    }

    private static void addAndRenameVarsFrom(Structure structure) {
    
        for (int i=0; i< structure.args.size(); i++) {
            Argument arg = structure.args.get(i);
            if (arg.getClass().getSimpleName().equals("Variable")) {
                Variable var = new Variable(((Variable) arg).name+"#");
                structure.args.set(i, var);
                if (!queryVars.containsKey(var.name)) {
                    queryVars.put(var.name ,var);
                }
            } else if (arg.getClass().getSimpleName().equals("List")){
                addAndRenameVarsFrom((List) arg);
            } else if (arg.getClass().getSimpleName().equals("Structure")) {
                addAndRenameVarsFrom((Structure) arg);
            } 
        }
    }
    
    
    private static void addAndRenameVarsFrom(List list) {
        
        
        for (int i=0; i< list.args.size(); i++) {
            Argument arg = list.args.get(i);
            if (arg.getClass().getSimpleName().equals("Variable")) {
                Variable var = new Variable(((Variable) arg).name+"#");
                list.args.set(i, var);
                if (!queryVars.containsKey(var.name)) {
                    queryVars.put(var.name,var);
                }
                
            } else if (arg.getClass().getSimpleName().equals("List")){
                addAndRenameVarsFrom((List) arg);
            } else if (arg.getClass().getSimpleName().equals("Structure")) {
                addAndRenameVarsFrom((Structure) arg);
            } 
        }
    }
    
    public static void outPutToString(HashMap<String, Argument> map, boolean result) {
        
        System.out.println();
        if (result) {
            
            System.out.println(query);
            for (String s : queryVars.keySet()) {
                Variable var = queryVars.get(s);
                System.out.println("Variable "+var.name.substring(0, var.name.length()-1)+" = "+map.get(var.name));
            }
            for (Argument arg : query.args) {
                if (arg.getClass().getSimpleName().equals("Variable")) {
                    Variable var = (Variable) arg;
                    if (!queryVars.containsKey(var.name)) {
                        System.out.println("Variable "+var.name+" is free");
                    }
                }
            }
        }
        System.out.println(result);
    }
}
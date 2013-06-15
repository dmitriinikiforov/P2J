package p2j;

import java.io.IOException;
import java.util.ArrayList;
//import org.antlr.v4.runtime.ANTLRFileStream;
//import org.antlr.v4.runtime.CharStream;
//import org.antlr.v4.runtime.CommonTokenStream;

public class P2J {
        static ArrayList<Statement> program;
        static Statement curStatement;
        static Structure query;
        
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
        //output vars
        Unificator unificator = new Unificator(query);
        Unificator.setProgram(program);
        boolean result = unificator.unifyProgram();
        System.out.println(unificator.getMap());
        System.out.println();
        if (result) {
            System.out.println(query);
            for (Argument arg : query.args) {
                if (arg.getClass().getSimpleName().equals("Variable")) {
                    Variable var = (Variable) arg;
                    if (unificator.getMap().containsKey(var.name)) {
                        Variable newVar = var;
                        Argument newArg = unificator.getMap().get(newVar.name);
                        while ((unificator.getMap().containsKey(newVar.name))
                                &&(unificator.getMap().get(newVar.name).getClass().getSimpleName().equals("Variable"))) {
                            
                            newArg = unificator.getMap().get(newVar.name);
                            newVar = (Variable) newArg;
                        }
                        unificator.getMap().put(var.name, newArg);
                        System.out.println(var.name+" = "+newArg.toString());
                    }
                }
            }    
        }
        System.out.println(result);
    }
}

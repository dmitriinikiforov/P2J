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
//        PrologLexer pl=new PrologLexer((CharStream) new ANTLRFileStream(args[0])); 
//        PrologParser pp=new PrologParser(new CommonTokenStream(pl));
//        Listener listener=new Listener();
//        pp.addParseListener(listener);
//        pp.program();
//        
//        for (Statement st:program) {
//            System.out.println(st.toString());
//        }
//        System.out.println();
//        
//        pl=new PrologLexer((CharStream) new ANTLRFileStream(args[1])); 
//        pp=new PrologParser(new CommonTokenStream(pl));
//        QueryListener qListener=new QueryListener();
//        pp.addParseListener(qListener);
//        pp.program();
//        System.out.println(query+"\n");
//        //output vars
//        Unificator unificator = new Unificator(query);
//        Unificator.setProgram(program);
//        boolean result = unificator.unifyProgram();
//        if (result) {
//            System.out.println("***\n"+unificator.getMap());
//        }
//        System.out.println(result);
        System.out.println("*");
        Compiler.compile(args[0]);
    }
}

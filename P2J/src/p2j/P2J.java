package p2j;

import java.io.IOException;
import java.util.ArrayList;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class P2J {
        static ArrayList<Statement> program;
        static Statement curStatement;
        static Statement query;
        
    public static void main(String[] args) throws IOException {
        PrologLexer pl=new PrologLexer((CharStream) new ANTLRFileStream(args[0])); 
        PrologParser pp=new PrologParser(new CommonTokenStream(pl));
        Listener listener=new Listener();
        pp.addParseListener(listener);
        pp.program();
        
        for (Statement st:program) {
            System.out.println(st.toString());
        }
//        pl=new PrologLexer((CharStream) new ANTLRFileStream(args[1])); 
//        pp=new PrologParser(new CommonTokenStream(pl));
//        listener=new Listener();
//        pp.addParseListener(listener);
//        pp.program();
//        System.out.println(Statement.unify(program,query));
        //output vars
    }
}

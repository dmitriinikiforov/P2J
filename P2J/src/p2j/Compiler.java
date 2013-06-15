package p2j;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Compiler {
    public static void compile(String filename) throws IOException {
        System.out.println("*");
        PrologLexer pl=new PrologLexer((CharStream) new ANTLRFileStream(filename)); 
        System.out.println("*");
        PrologParser pp=new PrologParser(new CommonTokenStream(pl));
        System.out.println("*");
        Listener listener=new Listener();
        pp.addParseListener(listener);
        
        pp.program();
        try (BufferedWriter out = new BufferedWriter(new FileWriter("C:\\compiled\\Main.java"))) {
            out.write("import p2j.*;\n");
            out.write("import org.antlr.v4.runtime.*;\n");
            out.write("import java.util.ArrayList;\n");
//            out.write("import java.util.LinkedList;\n");
            out.write("import java.io.IOException;\n");
            out.write("public class Main {\n");
            out.write("public static void main(String[] args) throws IOException {\n");
            out.write("ArrayList<Statement> program=new ArrayList<>();");
            out.write("\n");
            for (Statement st: P2J.program) {
                out.write("program.add("+st.javaCode()+");\n");
            }
            out.write("PrologLexer pl=new PrologLexer((CharStream) new ANTLRFileStream(args[0]));\n");
            out.write("PrologParser pp=new PrologParser(new CommonTokenStream(pl));\n");
            out.write("CompileListener qListener=new CompileListener();\n");
            out.write("pp.addParseListener(qListener);\n");
            out.write("pp.program();\n");
            out.write("Unificator unificator=new Unificator(qListener.getQuery());\n");
            out.write("Unificator.setProgram(program);\n");
            out.write("unificator.unifyProgram();\n");
            out.write("}\n");
            out.write("}\n");
        } catch (IOException ex) {
            Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

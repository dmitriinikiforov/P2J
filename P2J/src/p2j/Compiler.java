package p2j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Compiler {
    public static void compile(CharStream cs) throws IOException {
        PrologLexer pl=new PrologLexer(cs); 
        PrologParser pp=new PrologParser(new CommonTokenStream(pl));
        Listener listener=new Listener();
        pp.addParseListener(listener);
        pp.program();
        String compiledFileName="Main.java";
        File compiledFile=new File(compiledFileName);
        if (compiledFile.exists()) {
            if (!compiledFile.delete()) {
                System.out.println("Cannot delete "+compiledFileName);
                return;
            }
        } 
        if (!compiledFile.createNewFile())  {
            System.out.println("Cannot create "+compiledFileName);
            return;
        }
        if (!compiledFile.canWrite()) {
            System.out.println("Cannot write to "+compiledFileName);
            return;
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter(compiledFile))) {
            out.write("import p2j.*;\n");
            out.write("import org.antlr.v4.runtime.*;\n");
            out.write("import java.util.ArrayList;\n");
            out.write("import java.util.HashMap;\n");
            out.write("import java.io.IOException;\n");
            out.write("public class Main {\n");
            out.write("public static void main(String[] args) throws IOException {\n");

            out.write("if (args.length==0) {\n");
            out.write("System.out.println(\"normal usage: java \"+Main.class.getSimpleName()+\" [prolog file]\");\n");
            out.write("return;");
            out.write("}\n");
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
            out.write("P2J.queryVars = new HashMap<String, Variable>();\n");
            out.write("P2J.query=qListener.getQuery();\n");
            out.write("System.out.println(P2J.query);\n");
            out.write("P2J.addAndRenameVarsFrom(P2J.query);\n");
            out.write("Unificator unificator=new Unificator(P2J.query);\n");
            out.write("Unificator.setProgram(program);\n");
            out.write("boolean ok=unificator.unifyProgram();\n");
            out.write("P2J.outPutToString(unificator.getMap(), ok);\n");
            out.write("}\n");
            out.write("}\n");
            System.out.println("Class "+compiledFileName+" compiled successfully"+" "+compiledFile.getAbsolutePath());
            P2J.textSB.append("Class ").append(compiledFileName).append(" compiled successfully ").append(compiledFile.getAbsolutePath()).append("\n");
        } catch (IOException ex) {
            System.out.println("There is a problem with compiling "+compiledFileName);
            Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

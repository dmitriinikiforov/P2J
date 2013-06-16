import p2j.*;
import org.antlr.v4.runtime.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
public class Main {
public static void main(String[] args) throws IOException {
if (args.length==0) {
System.out.println("normal usage: java "+Main.class.getSimpleName()+" [prolog file]");
return;}
ArrayList<Statement> program=new ArrayList<>();
program.add(new Statement().update(new Structure("sum").update(new p2j.Number(0.0)).update(new Variable("T")).update(new Variable("T"))));
program.add(new Statement().update(new Structure("sum").update(new Structure("s").update(new Variable("A"))).update(new Variable("B")).update(new Structure("s").update(new Variable("C")))).update(new Structure("sum").update(new Variable("A")).update(new Variable("B")).update(new Variable("C"))));
program.add(new Statement().update(new Structure("hello").update(new ArgString("hello"))));
program.add(new Statement().update(new Structure("hello").update(new ArgString("hi"))));
program.add(new Statement().update(new Structure("hello").update(new ArgString("bonjour"))));
program.add(new Statement().update(new Structure("ishello").update(new Variable("A"))).update(new Structure("hello").update(new Variable("A"))));
program.add(new Statement().update(new Structure("ishello").update(new List())));
program.add(new Statement().update(new Structure("ishello").update(new List().update(new Variable("A")).update(new Variable("T")))).update(new Structure("hello").update(new Variable("A"))).update(new Structure("ishello").update(new Variable("T"))));
program.add(new Statement().update(new Structure("test")).update(new Structure("ishello").update(new List().update(new ArgString("hello")).update(new ArgString("hi")))));
PrologLexer pl=new PrologLexer((CharStream) new ANTLRFileStream(args[0]));
PrologParser pp=new PrologParser(new CommonTokenStream(pl));
CompileListener qListener=new CompileListener();
pp.addParseListener(qListener);
pp.program();
P2J.queryVars = new HashMap<String, Variable>();
P2J.query=qListener.getQuery();
System.out.println(P2J.query);
P2J.addAndRenameVarsFrom(P2J.query);
Unificator unificator=new Unificator(P2J.query);
Unificator.setProgram(program);
boolean ok=unificator.unifyProgram();
P2J.outPutToString(unificator.getMap(), ok);
}
}

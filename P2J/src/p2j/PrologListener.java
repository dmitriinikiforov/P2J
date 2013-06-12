// Generated from C:\Users\Dima\Documents\NetBeansProjects\P2J\src\p2j\Prolog.g4 by ANTLR 4.0
package p2j;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface PrologListener extends ParseTreeListener {
	void enterArgument(PrologParser.ArgumentContext ctx);
	void exitArgument(PrologParser.ArgumentContext ctx);

	void enterStatement(PrologParser.StatementContext ctx);
	void exitStatement(PrologParser.StatementContext ctx);

	void enterStructure(PrologParser.StructureContext ctx);
	void exitStructure(PrologParser.StructureContext ctx);

	void enterProgram(PrologParser.ProgramContext ctx);
	void exitProgram(PrologParser.ProgramContext ctx);

	void enterList(PrologParser.ListContext ctx);
	void exitList(PrologParser.ListContext ctx);

	void enterTail(PrologParser.TailContext ctx);
	void exitTail(PrologParser.TailContext ctx);
}
// Generated from C:\Users\Dima\Documents\NetBeansProjects\P2J\src\p2j\Prolog.g4 by ANTLR 4.0
package p2j;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface PrologVisitor<T> extends ParseTreeVisitor<T> {
	T visitArgument(PrologParser.ArgumentContext ctx);

	T visitStatement(PrologParser.StatementContext ctx);

	T visitStructure(PrologParser.StructureContext ctx);

	T visitProgram(PrologParser.ProgramContext ctx);

	T visitList(PrologParser.ListContext ctx);

	T visitTail(PrologParser.TailContext ctx);
}
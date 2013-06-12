// Generated from C:\Users\Dima\Documents\NetBeansProjects\P2J\src\p2j\Prolog.g4 by ANTLR 4.0
package p2j;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ParserRuleContext;

public class PrologBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements PrologVisitor<T> {
	@Override public T visitArgument(PrologParser.ArgumentContext ctx) { return visitChildren(ctx); }

	@Override public T visitStatement(PrologParser.StatementContext ctx) { return visitChildren(ctx); }

	@Override public T visitStructure(PrologParser.StructureContext ctx) { return visitChildren(ctx); }

	@Override public T visitProgram(PrologParser.ProgramContext ctx) { return visitChildren(ctx); }

	@Override public T visitList(PrologParser.ListContext ctx) { return visitChildren(ctx); }

	@Override public T visitTail(PrologParser.TailContext ctx) { return visitChildren(ctx); }
}
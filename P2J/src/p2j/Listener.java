package p2j;

import java.util.ArrayList;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Listener implements PrologListener {

    @Override
    public void enterProgram(PrologParser.ProgramContext ctx) {
        P2J.program=new ArrayList<Statement>();
    }

    @Override
    public void exitProgram(PrologParser.ProgramContext ctx) {
        
    }
    
    @Override
    public void enterStatement(PrologParser.StatementContext ctx) {
        
    }

    @Override
    public void exitStatement(PrologParser.StatementContext ctx) {
        P2J.program.add(new Statement(ctx));
    }
    
    @Override
    public void enterStructure(PrologParser.StructureContext ctx) {
        
    }

    @Override
    public void exitStructure(PrologParser.StructureContext ctx) {
        
    }
    
    @Override
    public void enterArgument(PrologParser.ArgumentContext ctx) {
        
    }

    @Override
    public void exitArgument(PrologParser.ArgumentContext ctx) {
//        System.out.println(ctx.getText());
    }

    @Override
    public void enterList(PrologParser.ListContext ctx) {
        
    }

    @Override
    public void exitList(PrologParser.ListContext ctx) {
        
    }

    @Override
    public void enterTail(PrologParser.TailContext ctx) {
        
    }

    @Override
    public void exitTail(PrologParser.TailContext ctx) {
        
    }

    @Override
    public void visitTerminal(TerminalNode tn) {
//        System.out.println(tn.getSymbol().getType()+" "+tn.getText());
    }

    @Override
    public void visitErrorNode(ErrorNode en) {
        
    }

    @Override
    public void enterEveryRule(ParserRuleContext prc) {
        
    }

    @Override
    public void exitEveryRule(ParserRuleContext prc) {
        
    }
    
}

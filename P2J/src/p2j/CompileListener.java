package p2j;

public class CompileListener extends Listener{
    Structure query;
    @Override
    public void enterProgram(PrologParser.ProgramContext ctx) {
        
    }

    @Override
    public void exitProgram(PrologParser.ProgramContext ctx) {
        
    }
    
    @Override
    public void enterStatement(PrologParser.StatementContext ctx) {
        
    }

    @Override
    public void exitStatement(PrologParser.StatementContext ctx) {
        query=(new Statement(ctx)).left;
    }
    public Structure getQuery() {
        return query;
    }
}

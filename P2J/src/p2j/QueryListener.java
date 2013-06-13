/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2j;

import java.util.ArrayList;

/**
 *
 * @author Dima
 */
public class QueryListener extends Listener {
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
        P2J.query=(new Statement(ctx)).left;
    }
}

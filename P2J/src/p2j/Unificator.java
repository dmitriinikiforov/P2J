
package p2j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


public class Unificator {
     Stack<HashMap<Variable,Argument>> stack;
     
     public Unificator() {
         
         stack = new Stack<HashMap<Variable, Argument>>();
     }
     
     public boolean unify(ArrayList<Statement> program, Statement query) {
            boolean ok=false;
            for (Statement rule: program) {
                ok=unify(rule,query);
                if (ok) {
                    System.out.println(toString());
                }
            }
            return ok;
    }
    
    public boolean unify(Statement rule, Statement query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public boolean unify(Argument rule, Argument query) {
        String classNameRule = rule.getClass().getSimpleName();
        if (classNameRule.equals("Structure")) {
            return unifyStructure((Structure) rule, query);
        } else if (classNameRule.equals("Number")) {
            return unifyNumber((Number) rule, query);
        } else if (classNameRule.equals("Variable")) {
            return unifyVariable((Variable) rule, query); 
        } else if (classNameRule.equals("List")) {
            return unifyList((List) rule, query);
        } else if (classNameRule.equals("ArgString")) {
            return unifyArgString((ArgString) rule, query);
        } else {
            return false;
        }
    }
    
    
    boolean unifyVariable(Variable rule, Argument query) {
        return false;
    }
    
    boolean unifyNumber(Number rule, Argument query) {
        String queryClassName=query.getClass().getSimpleName();
        //switch (queryClassName) {
          //  case "Number":
        //}
        return false;
    }
    
    boolean unifyArgString(ArgString rule, Argument query) {
        return false;
    }
    
    boolean unifyList(List rule, Argument query) {
        return false;
    }
    
    boolean unifyStructure(Structure rule, Argument query) {
        return false;
    }
        
     @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (HashMap<Variable, Argument> map : stack) {
            
            for (Variable key : map.keySet()) {
                sb.append(key.toString()).append("=").append(map.get(key).toString()).append("\n");
                
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

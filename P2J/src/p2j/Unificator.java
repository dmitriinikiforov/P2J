
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
        //самое интересное здесь
        return false;
    }
    public boolean unify(Argument rule, Argument query) {
        String ruleClassName = rule.getClass().getSimpleName();
        switch (ruleClassName) {
            case "Number":
                return unifyNumber((Number) rule, query);
            case "Structure":
                return unifyStructure((Structure) rule, query);
            case "Variable":
                return unifyVariable((Variable) rule, query); 
            case "List":
                return unifyList((List) rule, query);
            case "ArgString":
                return unifyArgString((ArgString) rule, query);
        }
        System.out.println("Unexpectively!");
        return false;
    }
    
    
    boolean unifyVariable(Variable rule, Argument query) {
        return false;
    }
    
    boolean unifyNumber(Number rule, Argument query) {
        String queryClassName=query.getClass().getSimpleName();
        switch (queryClassName) {
            case "Number":
                Number qNum=(Number) query;
                return qNum.value==rule.value;
            case "Variable":
                //Добавить переменную в стэк
                return true;
        }
        return false;
    }
    
    boolean unifyArgString(ArgString rule, Argument query) {
        String queryClassName=query.getClass().getSimpleName();
        switch (queryClassName) {
            case "ArgString":
                ArgString qArgString=(ArgString) query;
                return qArgString.string.equals(rule.string);
            case "Variable":
                //Добавить переменную в стек
                return true; 
        }
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

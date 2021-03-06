package p2j;

import java.util.ArrayList;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import p2j.PrologParser.ListContext;
import p2j.PrologParser.StructureContext;

public class Structure extends Argument {
    String functor;
    ArrayList<Argument> args;
    
    public Structure(String functor) {
        this.functor=functor;
    }
    void addArgument (Argument arg) {
        if (args==null) {
            args=new ArrayList<Argument>();            
        }
        args.add(arg);
    }
    public Structure update (Argument arg) {
        if (arg==null) return this;
        if (this.args==null) {
            this.args=new ArrayList<Argument>();            
        }
        this.args.add(arg);
        return this;
    }
    
    Structure (StructureContext ctx) {
        functor=ctx.FUNCTOR().getText();
        args=new ArrayList<Argument>();
            int chNum=ctx.getChildCount();
            for (int i=0; i<chNum; i++) {
                ParseTree ch=ctx.getChild(i);
                String className=ch.getClass().getSimpleName();
                if (!className.equals("TerminalNodeImpl")) {
                    ch=ch.getChild(0);
                    className=ch.getClass().getSimpleName();
//                    System.out.println("* "+ctx.getText()+" "+className);
                    if (className.equals("TerminalNodeImpl")) {
                        TerminalNode td=(TerminalNode) ch;
                        Token t=td.getSymbol();
//                                System.out.println("*** "+t.getType()+" "+t.getText());
                        switch (t.getType()) {
                            case 1: 
                                args.add(new Variable(t.getText()));
                                break;
                            case 3: 
                                args.add(new Number(t.getText()));
                                break;
                            case 13:
                                args.add(new ArgString(t.getText()));
                            default: continue;
                        }
                    }
                    if (className.equals("ListContext")) {
                        ListContext lc=(ListContext) ch;
                        args.add(new List(lc));
                    }
                    if (className.equals("StructureContext")) {                    
                        StructureContext ac=(StructureContext) ch;
                        args.add(new Structure(ac));
                    }
                }
            }
    }
    
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(functor);
        if (args==null) return sb.append("()").toString();
        sb.append("( ");
        for (Argument arg: args) {
            sb.append(arg.toString());
            sb.append(" ");
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public String javaCode() {        
        StringBuilder sb=new StringBuilder();
        sb.append("new Structure(\"").append(functor).append("\")");
        for (Argument arg:args) {
            sb.append(".update(").append(arg.javaCode()).append(")");
}
        return sb.toString();
    }
}

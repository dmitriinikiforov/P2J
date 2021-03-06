package p2j;

import java.util.LinkedList;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import p2j.PrologParser.ListContext;
import p2j.PrologParser.StructureContext;
import p2j.PrologParser.TailContext;

public class List extends Argument {
    LinkedList<Argument> args;
    public List(LinkedList<Argument> list) {
        args= (LinkedList<Argument>) list.clone();
    }
    public List() {
        args=new LinkedList<>();
    }
    public List update(Argument arg) {
        if (arg==null) return this;
        this.args.add(arg);
        return this;
    }
     public void addArgument(Argument arg) {
        args.add(arg);
    }
    List(ListContext ctx) {
        args=new LinkedList<Argument>();
            int chNum=ctx.getChildCount();
            for (int i=0; i<chNum; i++) {
                ParseTree ch=ctx.getChild(i);
                String className=ch.getClass().getSimpleName();
                if (!className.equals("TerminalNodeImpl")) {
                    if (className.equals("TailContext")) {
                        ParseTree pt = ch.getChild(1);
                        className = pt.getClass().getSimpleName();   
//                                System.out.println("* * *");
                        if (className.equals("TerminalNodeImpl")) {
                            TerminalNode td=(TerminalNode) pt;
                            Token t=td.getSymbol();
                            args.add(new Variable(t.getText()));
                        } else {
                            ListContext lc=(ListContext) pt;
                            List list = new List(lc);
                            args.addAll(list.args);
                        }
                        continue;
                    }
                    ch=ch.getChild(0);
                    className=ch.getClass().getSimpleName();
//                    System.out.println("* "+ch.getText()+" "+className);
                    if (className.equals("TerminalNodeImpl")) {
                        TerminalNode td=(TerminalNode) ch;
                        Token t=td.getSymbol();
                        switch (t.getType()) {
                            case 1:                             
                                args.add(new Variable(t.getText()));
                                break;
                            case 3: 
                                args.add(new Number(t.getText()));
                                break;
                            case 13:                               
//                                System.out.println("* * *");
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
        sb.append('[');
        sb.append(' ');
        for (Argument arg:args) {
            sb.append(arg.toString());
            sb.append(' ');
        }
        sb.append("]");
        return sb.toString();
    }        

    @Override
    public String javaCode() {
        StringBuilder sb=new StringBuilder();
        sb.append("new List()");
        if (args==null) return sb.toString();
        for (Argument arg:args) {
            sb.append(".update(").append(arg.javaCode()).append(")");
}
        return sb.toString();
    }
}

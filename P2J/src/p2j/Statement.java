package p2j;

import java.util.ArrayList;
import org.antlr.v4.runtime.tree.ParseTree;
import p2j.PrologParser.StatementContext;
import p2j.PrologParser.StructureContext;

public class Statement {
    Structure left;
    ArrayList<Structure> right;
    final void addStructure(Structure s) {
        if (left==null) {
            left=s;            
        } else {
            if (right==null) {
                right=new ArrayList<Structure>();
            }
            right.add(s); 
        }
    }
    public Statement () {
    }
    
    public Statement update(Structure s) {
        if (s==null) return this;
        this.addStructure(s);
        return this;
    }
    public Statement (StatementContext sc) {
        int chNum=sc.getChildCount();
        for (int i=0; i<chNum; i++) {
            ParseTree ch=sc.getChild(i);
            String className=ch.getClass().getSimpleName();
            if (!className.equals("TerminalNodeImpl")) {
                addStructure(new Structure((StructureContext) ch));
            }            
        }
    }
    public Statement (Structure left, ArrayList<Structure> right) {
        this.left=left;
        this.right=right;
    }
    
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(left.toString());
        if (right==null) return sb.append(".").toString();
        sb.append(":-");
        for (Structure str: right) {
            sb.append(" ");
            sb.append(str.toString());
        }
        sb.append(".");
        return sb.toString();
    }
    
    public String javaCode() {
        StringBuilder sb=new StringBuilder();
        sb.append("new Statement()").append(".update(").append(left.javaCode()).append(")");
        if (right==null) return sb.toString();
        for (Structure str:right) {
            sb.append(".update(").append(str.javaCode()).append(")");
}
        return sb.toString();
    }
}

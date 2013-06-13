package p2j;

import java.util.ArrayList;
import org.antlr.v4.runtime.tree.ParseTree;
import p2j.PrologParser.StatementContext;
import p2j.PrologParser.StructureContext;

class Statement {
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
    Statement (StatementContext sc) {
        int chNum=sc.getChildCount();
        for (int i=0; i<chNum; i++) {
            ParseTree ch=sc.getChild(i);
            String className=ch.getClass().getSimpleName();
            if (!className.equals("TerminalNodeImpl")) {
                addStructure(new Structure((StructureContext) ch));
            }            
        }
    }
    Statement (Structure left, ArrayList<Structure> right) {
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
    
}

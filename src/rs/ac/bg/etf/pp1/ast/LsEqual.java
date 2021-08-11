// generated with ast extension for cup
// version 0.8
// 11/7/2021 10:43:7


package rs.ac.bg.etf.pp1.ast;

public class LsEqual extends Relop {

    public LsEqual () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("LsEqual(\n");

        buffer.append(tab);
        buffer.append(") [LsEqual]");
        return buffer.toString();
    }
}

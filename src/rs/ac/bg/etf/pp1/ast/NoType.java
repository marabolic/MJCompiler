// generated with ast extension for cup
// version 0.8
// 29/5/2021 9:50:0


package rs.ac.bg.etf.pp1.ast;

public class NoType extends RetType {

    public NoType () {
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
        buffer.append("NoType(\n");

        buffer.append(tab);
        buffer.append(") [NoType]");
        return buffer.toString();
    }
}

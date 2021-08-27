// generated with ast extension for cup
// version 0.8
// 26/7/2021 15:31:41


package rs.ac.bg.etf.pp1.ast;

public class WhileEnd implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public WhileEnd () {
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
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
        buffer.append("WhileEnd(\n");

        buffer.append(tab);
        buffer.append(") [WhileEnd]");
        return buffer.toString();
    }
}

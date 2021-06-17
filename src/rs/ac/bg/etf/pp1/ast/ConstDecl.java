// generated with ast extension for cup
// version 0.8
// 17/5/2021 18:49:41


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String I1;
    private BasicTypes BasicTypes;

    public ConstDecl (String I1, BasicTypes BasicTypes) {
        this.I1=I1;
        this.BasicTypes=BasicTypes;
        if(BasicTypes!=null) BasicTypes.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public BasicTypes getBasicTypes() {
        return BasicTypes;
    }

    public void setBasicTypes(BasicTypes BasicTypes) {
        this.BasicTypes=BasicTypes;
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
        if(BasicTypes!=null) BasicTypes.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(BasicTypes!=null) BasicTypes.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(BasicTypes!=null) BasicTypes.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(BasicTypes!=null)
            buffer.append(BasicTypes.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl]");
        return buffer.toString();
    }
}

// generated with ast extension for cup
// version 0.8
// 26/5/2021 17:34:43


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String var;
    private BasicTypes BasicTypes;

    public ConstDecl (String var, BasicTypes BasicTypes) {
        this.var=var;
        this.BasicTypes=BasicTypes;
        if(BasicTypes!=null) BasicTypes.setParent(this);
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var=var;
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

        buffer.append(" "+tab+var);
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

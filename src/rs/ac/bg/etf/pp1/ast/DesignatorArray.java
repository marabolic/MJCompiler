// generated with ast extension for cup
// version 0.8
// 26/7/2021 15:31:41


package rs.ac.bg.etf.pp1.ast;

public class DesignatorArray extends Designator {

    private DesArrayName DesArrayName;
    private Expr Expr;

    public DesignatorArray (DesArrayName DesArrayName, Expr Expr) {
        this.DesArrayName=DesArrayName;
        if(DesArrayName!=null) DesArrayName.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public DesArrayName getDesArrayName() {
        return DesArrayName;
    }

    public void setDesArrayName(DesArrayName DesArrayName) {
        this.DesArrayName=DesArrayName;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesArrayName!=null) DesArrayName.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesArrayName!=null) DesArrayName.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesArrayName!=null) DesArrayName.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorArray(\n");

        if(DesArrayName!=null)
            buffer.append(DesArrayName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorArray]");
        return buffer.toString();
    }
}

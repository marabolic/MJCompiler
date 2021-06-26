// generated with ast extension for cup
// version 0.8
// 26/5/2021 17:34:43


package rs.ac.bg.etf.pp1.ast;

public class DesignatorArray extends Designator {

    private String arr;
    private Expr Expr;

    public DesignatorArray (String arr, Expr Expr) {
        this.arr=arr;
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public String getArr() {
        return arr;
    }

    public void setArr(String arr) {
        this.arr=arr;
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
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorArray(\n");

        buffer.append(" "+tab+arr);
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

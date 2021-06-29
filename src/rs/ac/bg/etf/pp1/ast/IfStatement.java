// generated with ast extension for cup
// version 0.8
// 29/5/2021 23:26:31


package rs.ac.bg.etf.pp1.ast;

public class IfStatement extends Statement {

    private CondInParens CondInParens;
    private IfPrepare IfPrepare;
    private IfInsideStmt IfInsideStmt;

    public IfStatement (CondInParens CondInParens, IfPrepare IfPrepare, IfInsideStmt IfInsideStmt) {
        this.CondInParens=CondInParens;
        if(CondInParens!=null) CondInParens.setParent(this);
        this.IfPrepare=IfPrepare;
        if(IfPrepare!=null) IfPrepare.setParent(this);
        this.IfInsideStmt=IfInsideStmt;
        if(IfInsideStmt!=null) IfInsideStmt.setParent(this);
    }

    public CondInParens getCondInParens() {
        return CondInParens;
    }

    public void setCondInParens(CondInParens CondInParens) {
        this.CondInParens=CondInParens;
    }

    public IfPrepare getIfPrepare() {
        return IfPrepare;
    }

    public void setIfPrepare(IfPrepare IfPrepare) {
        this.IfPrepare=IfPrepare;
    }

    public IfInsideStmt getIfInsideStmt() {
        return IfInsideStmt;
    }

    public void setIfInsideStmt(IfInsideStmt IfInsideStmt) {
        this.IfInsideStmt=IfInsideStmt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondInParens!=null) CondInParens.accept(visitor);
        if(IfPrepare!=null) IfPrepare.accept(visitor);
        if(IfInsideStmt!=null) IfInsideStmt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondInParens!=null) CondInParens.traverseTopDown(visitor);
        if(IfPrepare!=null) IfPrepare.traverseTopDown(visitor);
        if(IfInsideStmt!=null) IfInsideStmt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondInParens!=null) CondInParens.traverseBottomUp(visitor);
        if(IfPrepare!=null) IfPrepare.traverseBottomUp(visitor);
        if(IfInsideStmt!=null) IfInsideStmt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfStatement(\n");

        if(CondInParens!=null)
            buffer.append(CondInParens.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfPrepare!=null)
            buffer.append(IfPrepare.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfInsideStmt!=null)
            buffer.append(IfInsideStmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfStatement]");
        return buffer.toString();
    }
}

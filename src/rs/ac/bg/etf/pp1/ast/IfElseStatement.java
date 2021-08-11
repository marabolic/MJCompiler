// generated with ast extension for cup
// version 0.8
// 11/7/2021 10:43:7


package rs.ac.bg.etf.pp1.ast;

public class IfElseStatement extends Statement {

    private CondInParens CondInParens;
    private IfPrepare IfPrepare;
    private IfInsideStmt IfInsideStmt;
    private Else Else;
    private ElseInsideStmt ElseInsideStmt;

    public IfElseStatement (CondInParens CondInParens, IfPrepare IfPrepare, IfInsideStmt IfInsideStmt, Else Else, ElseInsideStmt ElseInsideStmt) {
        this.CondInParens=CondInParens;
        if(CondInParens!=null) CondInParens.setParent(this);
        this.IfPrepare=IfPrepare;
        if(IfPrepare!=null) IfPrepare.setParent(this);
        this.IfInsideStmt=IfInsideStmt;
        if(IfInsideStmt!=null) IfInsideStmt.setParent(this);
        this.Else=Else;
        if(Else!=null) Else.setParent(this);
        this.ElseInsideStmt=ElseInsideStmt;
        if(ElseInsideStmt!=null) ElseInsideStmt.setParent(this);
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

    public Else getElse() {
        return Else;
    }

    public void setElse(Else Else) {
        this.Else=Else;
    }

    public ElseInsideStmt getElseInsideStmt() {
        return ElseInsideStmt;
    }

    public void setElseInsideStmt(ElseInsideStmt ElseInsideStmt) {
        this.ElseInsideStmt=ElseInsideStmt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondInParens!=null) CondInParens.accept(visitor);
        if(IfPrepare!=null) IfPrepare.accept(visitor);
        if(IfInsideStmt!=null) IfInsideStmt.accept(visitor);
        if(Else!=null) Else.accept(visitor);
        if(ElseInsideStmt!=null) ElseInsideStmt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondInParens!=null) CondInParens.traverseTopDown(visitor);
        if(IfPrepare!=null) IfPrepare.traverseTopDown(visitor);
        if(IfInsideStmt!=null) IfInsideStmt.traverseTopDown(visitor);
        if(Else!=null) Else.traverseTopDown(visitor);
        if(ElseInsideStmt!=null) ElseInsideStmt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondInParens!=null) CondInParens.traverseBottomUp(visitor);
        if(IfPrepare!=null) IfPrepare.traverseBottomUp(visitor);
        if(IfInsideStmt!=null) IfInsideStmt.traverseBottomUp(visitor);
        if(Else!=null) Else.traverseBottomUp(visitor);
        if(ElseInsideStmt!=null) ElseInsideStmt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfElseStatement(\n");

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

        if(Else!=null)
            buffer.append(Else.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseInsideStmt!=null)
            buffer.append(ElseInsideStmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfElseStatement]");
        return buffer.toString();
    }
}

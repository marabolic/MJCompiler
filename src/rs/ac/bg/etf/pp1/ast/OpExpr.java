// generated with ast extension for cup
// version 0.8
// 29/5/2021 23:26:31


package rs.ac.bg.etf.pp1.ast;

public class OpExpr extends Expr {

    private TermSum TermSum;

    public OpExpr (TermSum TermSum) {
        this.TermSum=TermSum;
        if(TermSum!=null) TermSum.setParent(this);
    }

    public TermSum getTermSum() {
        return TermSum;
    }

    public void setTermSum(TermSum TermSum) {
        this.TermSum=TermSum;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TermSum!=null) TermSum.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TermSum!=null) TermSum.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TermSum!=null) TermSum.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OpExpr(\n");

        if(TermSum!=null)
            buffer.append(TermSum.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OpExpr]");
        return buffer.toString();
    }
}

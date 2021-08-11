// generated with ast extension for cup
// version 0.8
// 11/7/2021 10:43:7


package rs.ac.bg.etf.pp1.ast;

public class NegOpExpr extends Expr {

    private Minus Minus;
    private TermSum TermSum;

    public NegOpExpr (Minus Minus, TermSum TermSum) {
        this.Minus=Minus;
        if(Minus!=null) Minus.setParent(this);
        this.TermSum=TermSum;
        if(TermSum!=null) TermSum.setParent(this);
    }

    public Minus getMinus() {
        return Minus;
    }

    public void setMinus(Minus Minus) {
        this.Minus=Minus;
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
        if(Minus!=null) Minus.accept(visitor);
        if(TermSum!=null) TermSum.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Minus!=null) Minus.traverseTopDown(visitor);
        if(TermSum!=null) TermSum.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Minus!=null) Minus.traverseBottomUp(visitor);
        if(TermSum!=null) TermSum.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NegOpExpr(\n");

        if(Minus!=null)
            buffer.append(Minus.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TermSum!=null)
            buffer.append(TermSum.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NegOpExpr]");
        return buffer.toString();
    }
}

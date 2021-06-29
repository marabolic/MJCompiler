// generated with ast extension for cup
// version 0.8
// 29/5/2021 16:38:50


package rs.ac.bg.etf.pp1.ast;

public class MulFactors extends Term {

    private Term Term;
    private Muloper Muloper;
    private Factor Factor;

    public MulFactors (Term Term, Muloper Muloper, Factor Factor) {
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.Muloper=Muloper;
        if(Muloper!=null) Muloper.setParent(this);
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public Muloper getMuloper() {
        return Muloper;
    }

    public void setMuloper(Muloper Muloper) {
        this.Muloper=Muloper;
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Term!=null) Term.accept(visitor);
        if(Muloper!=null) Muloper.accept(visitor);
        if(Factor!=null) Factor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(Muloper!=null) Muloper.traverseTopDown(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(Muloper!=null) Muloper.traverseBottomUp(visitor);
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MulFactors(\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Muloper!=null)
            buffer.append(Muloper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MulFactors]");
        return buffer.toString();
    }
}

// generated with ast extension for cup
// version 0.8
// 29/5/2021 9:50:0


package rs.ac.bg.etf.pp1.ast;

public class ASTBoolean extends BasicTypes {

    private String boolConst;

    public ASTBoolean (String boolConst) {
        this.boolConst=boolConst;
    }

    public String getBoolConst() {
        return boolConst;
    }

    public void setBoolConst(String boolConst) {
        this.boolConst=boolConst;
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
        buffer.append("ASTBoolean(\n");

        buffer.append(" "+tab+boolConst);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ASTBoolean]");
        return buffer.toString();
    }
}

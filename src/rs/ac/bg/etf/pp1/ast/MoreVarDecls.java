// generated with ast extension for cup
// version 0.8
// 11/7/2021 10:43:7


package rs.ac.bg.etf.pp1.ast;

public class MoreVarDecls extends VarDeclList {

    private VarDeclComma VarDeclComma;
    private VarDeclList VarDeclList;

    public MoreVarDecls (VarDeclComma VarDeclComma, VarDeclList VarDeclList) {
        this.VarDeclComma=VarDeclComma;
        if(VarDeclComma!=null) VarDeclComma.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
    }

    public VarDeclComma getVarDeclComma() {
        return VarDeclComma;
    }

    public void setVarDeclComma(VarDeclComma VarDeclComma) {
        this.VarDeclComma=VarDeclComma;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclComma!=null) VarDeclComma.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclComma!=null) VarDeclComma.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclComma!=null) VarDeclComma.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreVarDecls(\n");

        if(VarDeclComma!=null)
            buffer.append(VarDeclComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreVarDecls]");
        return buffer.toString();
    }
}

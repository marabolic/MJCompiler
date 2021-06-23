package rs.ac.bg.etf.pp1;


import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;


public class SemanticPass extends VisitorAdaptor {

	int varDeclCount = 0;
	int printCallCount = 0;
	int nVars;
	boolean errorDetected;
	
	

	Logger log = Logger.getLogger(getClass());
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public void visit(Program program){
    	nVars = Tab.currentScope.getnVars();
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    }
	
	
	 //public void visit(PrintStmt print) {
		//printCallCount++;
	//}
	
	/*public void visit(SingleVarDecl singleVarDecl){
		varDeclCount++;
		report_info("Deklarisana promenljiva "+ singleVarDecl.getVarDecl(), singleVarDecl.getVarDecl());
		//Obj varNode = Tab.insert(Obj.Var, singleVarDecl.getVarDecl(), singleVarDecl.struct);
	}*/
	
	public void visit(DeclList declList) {
		report_info("Usao u DeclList "+ declList, declList);
	}
	
	
	public void visit(ASTVarDeclList declList) {
		report_info("Usao u ASTVarDeclList "+ declList, declList);
	}
	
	
	public void visit(ASTConstDeclList declList) {
		report_info("Usao u ASTConstDeclList "+ declList, declList);
	}
	
	
	public void visit(MoreVarDecls declList) {
		report_info("Usao u MoreVarDecls "+ declList, declList);
	}
	
	
	public void visit(SingleVarDecl declList) {
		report_info("Usao u SingleVarDecl "+ declList, declList);
	}
	
	public void visit(ASTVarDecl declList) {
		report_info("Usao u ASTVarDecl "+ declList, declList);
	}
	
	
	public void visit(ArrayDecl declList) {
		report_info("Usao u ArrayDecl "+ declList, declList);
	}
	
	public void visit(Type type){
    	Obj typeNode = Tab.find(type.getTypeName());
    	if(typeNode == Tab.noObj){
    		report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
    		type.struct = Tab.noType;
    	}else{
    		if(Obj.Type == typeNode.getKind()){
    			type.struct = typeNode.getType();
    		}else{
    			report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
    			type.struct = Tab.noType;
    		}
    	}
    }
	 
	 public void visit(ASTDesignator designator){
    	Obj obj = Tab.find(designator.getName());
    	if(obj == Tab.noObj){
			report_error("Greska na liniji " + designator.getLine()+ " : ime "+designator.getName()+" nije deklarisano! ", null);
    	}
    	designator.obj = obj;
    }
	 
	
}


package rs.ac.bg.etf.pp1;


import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;


public class SemanticAnalyzer extends VisitorAdaptor {

	int varDeclCount = 0;
	int printCallCount = 0;
	int nVars = 0;
	boolean errorDetected;
	Type currType;
	

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
	
	
	public void visit(Increment increment) {
		
	}
	
	public void visit(Decrement decrement) {
		
	}
	
	public void visit(AssignStmt assignStmt) {
		if (assignStmt.getExpr().struct.assignableTo(assignStmt.getDesignator().obj.getType())) {
			report_error("Greska na liniji " + assignStmt.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
		}
		else {
			report_info("Dodeljena vrednost " + assignStmt.getExpr() +  " promenljivoj " + assignStmt.getDesignator(), null);
		}
	}
	
	public void visit(NumConst numConst){
		numConst.struct = Tab.intType;
	}
	
	public void visit(CharConst charConst){
		charConst.struct = Tab.charType;
	}
	
	public void visit(BoolConst boolConst){
		boolConst.struct = 
	}
	
	public void visit(ConstDecl constDecl){
		report_info("Deklarisana konstanta "+ constDecl.getVar(), constDecl);
		Obj varNode = Tab.insert(Obj.Var, constDecl.getVar(), currType.struct);
	}
	

	public void visit(ASTVarDecl astVarDecl) {
		report_info("Deklarisana promenljiva "+ astVarDecl.getVar(), astVarDecl);
		Obj varNode = Tab.insert(Obj.Var, astVarDecl.getVar(), currType.struct);
	}
	
	public void visit(ArrayDecl arrayDecl) {
		report_info("Deklarisan niz "+ arrayDecl.getVar(), arrayDecl);
		Obj varNode = Tab.insert(Obj.Var, arrayDecl.getVar(), currType.struct);
	}
	
	
	
	public void visit(Type type){
    	Obj typeNode = Tab.find(type.getTypeName());
    	currType = new Type(type.getTypeName());
    	if(typeNode == Tab.noObj){
    		report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
    		type.struct = Tab.noType;
    		currType.struct = Tab.noType;
    	}else{
    		if(Obj.Type == typeNode.getKind()){
    			type.struct = typeNode.getType();
    			this.currType.struct = type.struct;
    		}else{
    			report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
    			type.struct = Tab.noType;
    			currType.struct = Tab.noType;
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



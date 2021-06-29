
package rs.ac.bg.etf.pp1;


import org.apache.log4j.Logger;


import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;




public class SemanticAnalyzer extends VisitorAdaptor {

	int varDeclCount = 0;
	int printCallCount = 0;
	int nVars = 0;
	boolean errorDetected;
	String constName = null;
	Obj currentMethod = null;
	Type currType;
	boolean mainExists = false;
	

	Logger log = Logger.getLogger(getClass());
	
	
	//report functions
	
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
	
	
	
	
	//program functions
	
	public void visit(Program program){
    	nVars = Tab.currentScope.getnVars();
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    	
    	if (!mainExists)
    		report_error("Greska: Main funkcija mora da postojie", null);
    }
	
	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
    	Tab.openScope();
	}
	
	
	
	
	// VAR DECLATATIONS

	public void visit(ASTVarDecl astVarDecl) {
		report_info("Deklarisana promenljiva "+ astVarDecl.getVar(), astVarDecl);
		Obj varNode = Tab.insert(Obj.Var, astVarDecl.getVar(), currType.struct);
	}
	
	public void visit(ArrayDecl arrayDecl) {
		report_info("Deklarisan niz "+ arrayDecl.getVar(), arrayDecl);
		Obj varNode = Tab.insert(Obj.Var, arrayDecl.getVar(), new Struct(Struct.Array,currType.struct));
	}
	
	
	
	// METHOD DECLARATION
	
	public void visit(MethodTypeName methodTypeName){
		if("main".equalsIgnoreCase(methodTypeName.getMethName())){
			mainExists = true;
		}
		
		if (methodTypeName.getRetType() instanceof ActualType) {
			ActualType type = (ActualType) methodTypeName.getRetType();
			currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethName(), type.getType().struct);
		}
		else {
			NoType type = (NoType) methodTypeName.getRetType();
			currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethName(), Tab.noType);
		}
    	
    	methodTypeName.obj = currentMethod;
    	Tab.openScope();
		report_info("Obradjuje se funkcija " + methodTypeName.getMethName(), methodTypeName);
	}
	 
	public void visit(MethodDecl methodDecl) {
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
	}
	
	
	
	// IF STATEMENTS
	
	public void visit(IfStatement ifStatement) {
		if (ifStatement.getCondition().struct==MyStatic.boolType) {
			report_info("Condition je tipa bool", null);
		}
		else {
			report_error("Greska u liniji " + ifStatement.getLine() + " condition nije tipa bool", ifStatement);
		}
	}
	
	
	public void visit(IfElseStatement ifElseStatement) {
		if (ifElseStatement.getCondition().struct==MyStatic.boolType) {
			report_info("Condition je tipa bool", null);
		}
		else {
			report_error("Greska u liniji " + ifElseStatement.getLine() + " condition nije tipa bool", ifElseStatement);
		}
	}
	
	
	// READ STATEMENT
	
	public void visit(ReadStmt readStmt) {
		Obj obj =  readStmt.getDesignator().obj;
		if ((obj.getKind() == Obj.Var || obj.getKind() == Obj.Elem) 
				&& (obj.getType() == Tab.charType || obj.getType() == Tab.intType || obj.getType() == MyStatic.boolType)) {
			report_info("ReadStmt je ispravan ", null);
		}
		else {
			report_error("Greska u liniji " + readStmt.getLine() + " Designator nije dobrog tipa", readStmt);
		}
	}
	
	
	
	//PRINT STATEMENT
	
	 public void visit(PrintStmt print) {
		 if (print.getExpr().struct == Tab.intType || print.getExpr().struct == Tab.charType || print.getExpr().struct == MyStatic.boolType) {
			 report_info("Print pozvan ", print);
			 printCallCount++;
		 }
		 else {
			 report_error("Greska u liniji " + print.getLine() + " Expr nije dobrog tipa", print);
		 }
	}
	 
	public void visit(PrintStmtNum print) {
		if (print.getExpr().struct == Tab.intType || print.getExpr().struct == Tab.charType || print.getExpr().struct == MyStatic.boolType) {
			 report_info("Print pozvan ", print);
			 printCallCount++;
		 }
		 else {
			 report_error("Greska u liniji " + print.getLine() + " Expr nije dobrog tipa", print);
		 }
	}
	
	
	
	// CONDITIONS
	
	public void visit(MulCondTerms mulCondTerms) {
		if (mulCondTerms.getCondition().struct == MyStatic.boolType && mulCondTerms.getCondTerm().struct == MyStatic.boolType) {
			report_info("MulCondTerm je tipa bool", mulCondTerms);
			mulCondTerms.struct = MyStatic.boolType;
		}
		else {
			report_error("Greska u liniji " + mulCondTerms.getLine() +" MulCondTerm nije tipa bool", mulCondTerms);
		}
	}
	
	public void visit(SingleCondTerm singleCondTerm) {
		singleCondTerm.struct = singleCondTerm.getCondTerm().struct;
	}
	
	public void visit(MulCondFacts mulCondFacts) {
		if (mulCondFacts.getCondFact().struct == MyStatic.boolType && mulCondFacts.getCondTerm().struct == MyStatic.boolType) {
			report_info("MulCondFact je tipa bool", mulCondFacts);
			mulCondFacts.struct = MyStatic.boolType;
		}
		else {
			report_error("Greska u liniji " + mulCondFacts.getLine() +" MulCondFact nije tipa bool", mulCondFacts);
		}
	}
	
	public void visit(SingleCondFact singleCondFact) {
		singleCondFact.struct = singleCondFact.getCondFact().struct;
	}
	
	
	public void visit(CondFactRel condFactRel) {
		if (condFactRel.getExpr().struct.compatibleWith(condFactRel.getExpr1().struct)) {
			report_info("CondFactRel je tipa bool", condFactRel);
			condFactRel.struct = MyStatic.boolType;
		}
	}
	
	public void visit(ASTCondFact astCondFact) {
		if (astCondFact.getExpr().struct == MyStatic.boolType) {
			astCondFact.struct = astCondFact.getExpr().struct;
		}
		else {
			report_error("Greska u liniji " + astCondFact.getLine() + " Expr nije tipa bool",  astCondFact);
		}
	}
	
	
	
	
	
	
	// DESIGNATOR STATEMENTS
	
	public void visit(AssignStmt assignStmt) {
		Obj assignObj = assignStmt.getDesignator().obj;
		
		if (assignObj.getKind() == Obj.Var) {
			//report_info("Dodeljena vrednost " + assignStmt.getExpr() +  " promenljivoj " + assignStmt.getDesignator(), null);
			report_info("Dodeljena vrednost promenljivoj ", assignStmt);

		}
		else if(assignObj.getKind() == Obj.Elem ){
			//report_info("Napravljen niz " + assignStmt.getDesignator(), null);
			report_info("Dodeljena vrednost elementu niza ", assignStmt);
			}
			else {
				report_error("Greska na liniji " + assignStmt.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", assignStmt);
			}
	}
	
	public void visit(Increment increment) {
		Obj obj = increment.getDesignator().obj;
		if ( obj.getType() == Tab.intType && (obj.getKind() == obj.Var || obj.getKind() == obj.Elem)) {
			//report_info("Inkrementirana vrednost " + increment.getDesignator() +  " promenljive ", null);
			report_info("Inkrementirana vrednost promenljive ", increment);
		}
		else {
			report_error("Greska na liniji " + increment.getLine() + " promenljiva nije tipa int ", increment);
		}
	}
	
	public void visit(Decrement decrement) {
		Obj obj = decrement.getDesignator().obj;
		if (obj.getType() == Tab.intType && (obj.getKind() == obj.Var || obj.getKind() == obj.Elem)) {
			//report_info("Dekrementirana vrednost " + decrement.getDesignator() +  " promenljive ", null);
			report_info("Dekrementirana vrednost promenljive ", decrement);
		}
		else {
			report_error("Greska na liniji " + decrement.getLine() + " promenljiva nije tipa int ", decrement);
		}
	}
	
	
	
	// EXPRESSION
	
	public void visit(OpExpr opExpr) {
		opExpr.struct = opExpr.getTermSum().struct;
	}
	
	public void visit(NegOpExpr negOpExpr) {
		negOpExpr.struct = negOpExpr.getTermSum().struct;
	}
	
	public void visit(SumTerm sumTerm) {
		if(sumTerm.getTermSum().struct == Tab.intType && sumTerm.getTerm().struct == Tab.intType) {
    		sumTerm.struct=Tab.intType;
    	}
    	else {
    		report_error("Greska na liniji " + sumTerm.getLine() + " nisu svi clanovi SumTerm tipa int ", sumTerm);
    		sumTerm.struct=Tab.noType;
    	}   	
	}
	
	public void visit(SingleTerm singleTerm) {
		singleTerm.struct = singleTerm.getTerm().struct;
	}
	
	
	public void visit(MulFactors mulFactors) {
		if(mulFactors.getTerm().struct == Tab.intType && mulFactors.getFactor().struct == Tab.intType) {
			mulFactors.struct = Tab.intType;
    	}
    	else {
    		report_error("Greska na liniji " + mulFactors.getLine() + " nisu svi clanovi mulFactor tipa int ", mulFactors);
    		mulFactors.struct = Tab.noType;
    	}   	
	}
	
	public void visit(SingleFactor singleFactor) {
		singleFactor.struct = singleFactor.getFactor().struct;
	}
	
	
	
	// FACTOR
	
	public void visit(Var var){
		var.struct = var.getDesignator().obj.getType();
	}
	
	public void visit(ExprInParens exprInParens){
		exprInParens.struct = exprInParens.getExpr().struct;
	}
	
	public void visit(NumConst numConst){
		numConst.struct = Tab.intType;
	}
	
	public void visit(CharConst charConst){
		charConst.struct = Tab.charType;
	}
	
	public void visit(BoolConst boolConst){
		boolConst.struct = MyStatic.boolType;
	}
	
	public void visit(NewArray newArray) {
		if (newArray.getExpr().struct == Tab.intType) {
			report_info("Novi niz ", newArray);
		}
		else {
			report_error("Greska na liniji " + newArray.getLine() + " Expr nije tipa int ", newArray);
		}
	}
	
	
	
	
	// CONSTANTS
	
	public void visit(ConstName cName){
		constName = cName.getVar();
	}
	
	public void visit(ASTNumber astNumber) {
		Obj obj = Tab.find(constName);
		if (obj != Tab.noObj)
			report_error("Konstanta " + constName  +" je vec deklarisana", astNumber);
		if (!currType.struct.equals(Tab.intType))
			report_error("Konstanta " + constName  +" nije dobrog tipa", astNumber);
		
		Obj varNode = Tab.insert(Obj.Con, constName, currType.struct);
		varNode.setAdr(astNumber.getNumConst());
		report_info("Deklarisana konstanta "+ constName + " vrednost " + astNumber.getNumConst(), astNumber);
	}
	
	public void visit(ASTBoolean astBoolean) {
		Obj obj = Tab.find(constName);
		if (obj != Tab.noObj)
			report_error("Konstanta " + constName  +" je vec deklarisana", astBoolean);
		if (!currType.equals(MyStatic.boolType))
			report_error("Konstanta " + constName  +" nije dobrog tipa", astBoolean);
		
		Obj varNode = Tab.insert(Obj.Con, constName, currType.struct);
		varNode.setAdr(astBoolean.getBoolConst().equals("true")?1:0);
		report_info("Deklarisana konstanta "+ constName, astBoolean);
	}

	public void visit(ASTCharacter astCharacter) {
		Obj obj = Tab.find(constName);
		if (obj != Tab.noObj)
			report_error("Konstanta " + constName  +" je vec deklarisana", astCharacter);
		if (!currType.equals(Tab.charType))
			report_error("Konstanta " + constName  +" nije dobrog tipa", astCharacter);
		
		Obj varNode = Tab.insert(Obj.Con, constName, currType.struct);
		varNode.setAdr(astCharacter.getCharConst());
		report_info("Deklarisana konstanta "+ constName, astCharacter);
	}
	
	
	
	

	
	
	
	public void visit(Type type){
    	Obj typeNode = Tab.find(type.getTypeName());
    	currType = new Type(type.getTypeName());
    	if(typeNode == Tab.noObj){
    		report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", type);
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
	 
	
	
	// DESIGNATOR
	
	 public void visit(ASTDesignator designator){
    	Obj obj = Tab.find(designator.getName());
    	
    	if(obj == Tab.noObj){
			report_error("Greska na liniji " + designator.getLine()+ " : ime "+designator.getName()+" nije deklarisano! ", null);
    	}
    	designator.obj = obj;
    	
    }
	 
	 public void visit(DesignatorArray designator){
		String name = designator.getDesArrayName().getName();
    	Obj obj = Tab.find(name);
    	
    	if(obj == Tab.noObj){
			report_error("Greska na liniji " + designator.getLine()+ " : ime "+name+" nije deklarisano! ", null);
			designator.obj = Tab.noObj;
    	}
    	else {
    		if (obj.getType().getKind() != Struct.Array) {
				report_error("Greska na liniji " + designator.getLine() + " Designator " + name + " nije niz ", designator);
				designator.obj = Tab.noObj;
    		}
	    	else {
	    		if (designator.getExpr().struct != Tab.intType) {
		    		report_error("Greska na liniji " + designator.getLine() + " Expr nije tipa int ", designator);
		    		designator.obj = Tab.noObj;
	    		}
	    		else {
	    			designator.obj=new Obj(Obj.Elem,"Elem:"+obj.getName(),obj.getType().getElemType());
	    		}
	    	}
    	}
	 }
	 
	 public void visit(DesArrayName desArrayName) {
		 desArrayName.obj = Tab.find(desArrayName.getName());
	 }
	 
	 
	 public boolean passed(){
	    	return !errorDetected;
	    }
	 
	
}



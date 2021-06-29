package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.CounterVisitor;
import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
import rs.etf.pp1.mj.runtime.Code;

public class CodeGenerator extends VisitorAdaptor{
	
	private int mainPc;
	
	private boolean minusSet = false;
	
	public int getMainPc(){
		return mainPc;
	}
	
	public void visit(Program program){
		
	}
	
	public void visit(ProgName progName) {
		
	}
	
public void visit(MethodTypeName methodTypeName){
		
		if("main".equalsIgnoreCase(methodTypeName.getMethName())){
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode methodNode = methodTypeName.getParent();
	
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	
	}
	
	
	public void visit(PrintStmt printStmt){
		if((printStmt.getExpr().struct == Tab.charType)){
			Code.loadConst(1);
			Code.put(Code.bprint);
		}else{
			Code.loadConst(5);
			Code.put(Code.print);
			
		}
	}
	
	public void visit(PrintStmtNum printStmt) {
		Code.loadConst(printStmt.getWidth());
		if((printStmt.getExpr().struct == Tab.charType)){
			Code.put(Code.bprint);
		}else{
			Code.put(Code.print);
		}
	}
	
	public void visit(AssignStmt assignStmt) {
		Code.store(assignStmt.getDesignator().obj);
	}
	
	public void visit(ReadStmt readStmt) {
		if (readStmt.getDesignator().obj.getType().equals(Tab.charType))
			Code.put(Code.bread);
		else
			Code.put(Code.read);
		Code.store(readStmt.getDesignator().obj);
	}
	
	public void visit(ASTDesignator des) {
		
		if (des.getParent() instanceof Factor || des.getParent() instanceof Increment || des.getParent() instanceof Decrement  ) {
			Code.load(des.obj);
		}
		if (des.getParent() instanceof DesignatorStatement){
			
		}
	}
	
	
	public void visit(DesignatorArray des) {
		if (des.getParent() instanceof Factor || des.getParent() instanceof Increment || des.getParent() instanceof Decrement  ) {
			Code.load(des.obj);
		}
		if (des.getParent() instanceof DesignatorStatement){
			
		}
	}
	
	
	
	// CONDITIONS
	
	public void visit(MulCondTerms cond) {
		Code.put(Code.add);
	}

	public void visit(SingleCondTerm cond) {
		
	}
	
	public void visit (MulCondFacts cond) {
		Code.put(Code.mul); 
	}
	
	public void visit (SingleCondFact cond) {
		
	}
	
	public void visit (CondFactRel cond) {
		if (cond.getRelop() instanceof CompEqual) {
			Code.put(Code.eq);
		}
		if (cond.getRelop() instanceof NotEqual) {
			Code.put(Code.ne);
		}
		if (cond.getRelop() instanceof GreaterThan) {
			Code.put(Code.gt);
		}
		if (cond.getRelop() instanceof GtEqual) {
			Code.put(Code.ge);
		}
		if (cond.getRelop() instanceof LessThan) {
			Code.put(Code.lt);
		}
		if (cond.getRelop() instanceof LsEqual) {
			Code.put(Code.le);
		}
		
	}
	
	public void visit (ASTCondFact cond) {
		
	}
	
	
	
	
	// TERMS
	
	public void visit(OpExpr term) {
		
	}

	public void visit(NegOpExpr term) {
		
	}
	
	public void visit (Minus minus) {
		minusSet = true;
	}
	
	public void visit (SumTerm term) {
		if (term.getAddop() instanceof PlusOp) {
			Code.put(Code.add);
		}
		else {
			Code.put(Code.sub);
		}
	}
	
	public void visit(SingleTerm term) {
		if (minusSet) {
			Code.put(Code.neg);
			minusSet = false;
		}
	}

	public void visit(MulFactors term) {
		if (term.getMuloper() instanceof Mulop) {
			Code.put(Code.mul);
		}
		else if (term.getMuloper() instanceof Divop) {
			Code.put(Code.div);
			}
		else { //Modop
			Code.put(Code.rem);
		}
	}
	
	
	
	// FACTOR

	public void visit(Var factor) {
		
	}
	
	public void visit(NumConst factor) {
		Code.loadConst(factor.getN1());
	}
	
	public void visit(CharConst factor) {
		Code.loadConst((int)factor.getC1());
	}
	
	public void visit(BoolConst factor) {
		Code.loadConst(factor.getB1()=="true"?1:0);
	}
	
	public void visit(NewArray factor) {
		Code.put(Code.newarray);
		if (factor.getType().struct == Tab.charType) {
			Code.put(0);
		}
		else {
			Code.put(1);
		}
	}
	
	
	// OTHER

	
}

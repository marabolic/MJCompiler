package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;

import java.util.Stack;

import jdk.nashorn.internal.ir.WhileNode;
import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
import rs.etf.pp1.mj.runtime.Code;


public class CodeGenerator extends VisitorAdaptor{
	
	private int mainPc;
	Stack<Integer> savedPC = new Stack<>();
	
	private boolean minusSet = false;
	private int beginDoWhile;
	private int breakAddr;
	private int i = 0;
	private boolean isFactorArrSize = false;
	
	public int getMainPc(){
		return mainPc;
	}
	
	public void visit(Program program){
		
	}
	
	public void visit(ProgName progName) {
		Tab.lenObj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n);
		Code.put(Code.arraylength);
		Code.put(Code.exit);
		Code.put(Code.return_);
		
		//CHR METHOD
		Tab.chrObj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n);
		Code.loadConst(256);
		Code.put(Code.rem);
		Code.put(Code.exit);
		Code.put(Code.return_);
		
		//ORD METHOD
		Tab.ordObj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);		
	}
	
	public void visit(MethodTypeName methodTypeName){
		
		if("main".equalsIgnoreCase(methodTypeName.getMethName())){
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);

		SyntaxNode methodNode = methodTypeName.getParent();
	
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		


		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	
	}
	
	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	
	//METH PARAMS
	
	public void visit(FuncCall funcCall) {
		int adr = funcCall.getDesignator().obj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(adr);
		
	}
	
	public void visit(ActualParam actualParam) {
		
	}
	
	
	
	
	// STATEMENTS
	
	public void visit(BreakStmt breakStmt) {
		Code.putJump(0);
		breakAddr = Code.pc - 2;
	}
	
	public void visit(ContinueStmt continueStmt) {
		Code.putJump(beginDoWhile);
	}
	
	public void visit(DoStart doStart) {
		beginDoWhile = Code.pc;
		
	}
	
	public void visit(WhileEnd whileEnd) {
		Code.loadConst(0);
		Code.putFalseJump(Code.le, beginDoWhile);
		Code.fixup(breakAddr);
	}
	
	public void visit(PrintStmt printStmt){
		if((printStmt.getExpr().obj.getType() == Tab.charType)){
			Code.loadConst(1);
			Code.put(Code.bprint);
		}else{
			Code.loadConst(5);
			Code.put(Code.print);
		}
	}
	
	public void visit(PrintStmtNum printStmt) {
		Code.loadConst(printStmt.getWidth());
		if((printStmt.getExpr().obj.getType() == Tab.charType)){
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
	
	
	public void visit(Increment inc) {
		
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(inc.getDesignator().obj);
	}
	
	public void visit(Decrement dec) {
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(dec.getDesignator().obj);
	}
	
	public void visit(IfStatement ifStatement) {
		Code.fixup(savedPC.pop());
	}
	
	public void visit(IfElseStatement ifElseStatement) {
		
	}
	
	
	public void visit(IfInsideStmt ifStatement) {
		
	}
	
	public void visit(Else elseS) {
		Code.putJump(0);
		int temp = Code.pc - 2;
		Code.fixup(savedPC.pop());
		savedPC.push(temp);
	}
	
	public void visit(ElseInsideStmt ifElseStatement) {
		Code.fixup(savedPC.pop());
	}
	
	
	public void visit(IfPrepare ifprep) {
		Code.loadConst(0);
		Code.putFalseJump(Code.gt, 0);
		savedPC.push(Code.pc - 2);
		
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
	
	public void visit (CondFactRel cond) {
		if (cond.getRelop() instanceof CompEqual) {
			Code.putFalseJump(Code.eq, Code.pc+7);
		}
		if (cond.getRelop() instanceof NotEqual) {
			Code.putFalseJump(Code.ne, Code.pc+7);
		}
		if (cond.getRelop() instanceof GreaterThan) {
			Code.putFalseJump(Code.gt, Code.pc+7);
		}
		if (cond.getRelop() instanceof GtEqual) {
			Code.putFalseJump(Code.ge, Code.pc+7);
		}
		if (cond.getRelop() instanceof LessThan) {
			Code.putFalseJump(Code.lt, Code.pc+7);
		}
		if (cond.getRelop() instanceof LsEqual) {
			Code.putFalseJump(Code.le, Code.pc+7);
		}
		Code.loadConst(1);
		Code.putJump(Code.pc+4);
		Code.loadConst(0);
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
	
	
	
	public void visit(Mulop mulop) {
		
	}
	

	public void visit(MulFactors term) {
		if (term.getMuloper() instanceof Mulop) {
			if (term.getTerm().obj.getKind() == Struct.Array) {
				if (term.getFactor().struct.getKind() == Struct.Int) {
					System.out.println("jedan array");
					
					int origArr = Code.dataSize - 1, scalar = Code.dataSize - 2, counter = Code.dataSize - 3, newArr = Code.dataSize - 4;
					
					//global init
					
					Code.put(Code.putstatic);
					Code.put2(scalar);
					Code.put(Code.putstatic);
					Code.put2(origArr);
					Code.put(Code.getstatic);
					Code.put2(origArr);
					
					Code.put(Code.arraylength);
					Code.put(Code.dup);
					Code.put(Code.putstatic);
					Code.put2(counter);
					Code.put(Code.newarray);
					Code.put(1);
					Code.put(Code.putstatic);
					Code.put2(newArr);
					
					
					//counter decrement
					Code.put(Code.getstatic);
					Code.put2(counter);
					Code.loadConst(1);
					Code.put(Code.sub);
					Code.put(Code.putstatic);
					Code.put2(counter);
					
					//get elem
					
					Code.put(Code.getstatic);
					Code.put2(newArr);
					Code.put(Code.getstatic);
					Code.put2(counter);
					Code.put(Code.getstatic);
					Code.put2(origArr);
					Code.put(Code.getstatic);
					Code.put2(counter);
					Code.put(Code.aload);
					Code.put(Code.getstatic);
					Code.put2(scalar);
					Code.put(Code.mul);
					
					
					Code.put(Code.astore);
					
					Code.put(Code.getstatic);
					Code.put2(counter);
					Code.loadConst(0);
					Code.putFalseJump(Code.eq, Code.pc -30);
					
					Code.put(Code.getstatic);
					Code.put2(newArr);
					
				}
				
				
				if (term.getFactor().struct.getKind() == Struct.Array) {
						System.out.println(" oba array");
						int arr1 = Code.dataSize - 1, arr2 = Code.dataSize - 2, counter = Code.dataSize - 3, len2 = Code.dataSize - 4;
						
						//global init
						
						Code.put(Code.putstatic);
						Code.put2(arr1);
						Code.put(Code.putstatic);
						Code.put2(arr2);
						
						Code.put(Code.getstatic);
						Code.put2(arr1);
						Code.put(Code.arraylength);
						Code.put(Code.dup);
						Code.put(Code.putstatic);
						Code.put2(counter);
						
						Code.put(Code.getstatic);
						Code.put2(arr2);
						Code.put(Code.arraylength);
						Code.putFalseJump(Code.ne, Code.pc + 5);
						Code.put(Code.trap);
						Code.put(1);
						
						Code.loadConst(0);
						
						//counter decrement
						Code.put(Code.getstatic);
						Code.put2(counter);
						Code.loadConst(1);
						Code.put(Code.sub);
						Code.put(Code.putstatic);
						Code.put2(counter);
						
						//get elem
						
						
						Code.put(Code.getstatic);
						Code.put2(arr1);
						Code.put(Code.getstatic);
						Code.put2(counter);
						Code.put(Code.aload);
						Code.put(Code.getstatic);
						Code.put2(arr2);
						Code.put(Code.getstatic);
						Code.put2(counter);
						Code.put(Code.aload);
						Code.put(Code.mul);
						Code.put(Code.add);
						
						
						
						Code.put(Code.getstatic);
						Code.put2(counter);
						Code.loadConst(0);
						Code.putFalseJump(Code.eq, Code.pc -28);
						
					
				}
			}else {

				if (term.getFactor().struct.getKind() == Struct.Array) {
					int origArr = Code.dataSize - 1, scalar = Code.dataSize - 2, counter = Code.dataSize - 3, newArr = Code.dataSize - 4;
					
					//global init
										
					Code.put(Code.putstatic);
					Code.put2(origArr);
					Code.put(Code.putstatic);
					Code.put2(scalar);
					Code.put(Code.getstatic);
					Code.put2(origArr);
					
					Code.put(Code.arraylength);
					Code.put(Code.dup);
					Code.put(Code.putstatic);
					Code.put2(counter);
					Code.put(Code.newarray);
					Code.put(1);
					Code.put(Code.putstatic);
					Code.put2(newArr);
					
					
					//counter decrement
					Code.put(Code.getstatic);
					Code.put2(counter);
					Code.loadConst(1);
					Code.put(Code.sub);
					Code.put(Code.putstatic);
					Code.put2(counter);
					
					//get elem
					
					Code.put(Code.getstatic);
					Code.put2(newArr);
					Code.put(Code.getstatic);
					Code.put2(counter);
					Code.put(Code.getstatic);
					Code.put2(origArr);
					Code.put(Code.getstatic);
					Code.put2(counter);
					Code.put(Code.aload);
					Code.put(Code.getstatic);
					Code.put2(scalar);
					Code.put(Code.mul);
					
					
					Code.put(Code.astore);
					
					Code.put(Code.getstatic);
					Code.put2(counter);
					Code.loadConst(0);
					Code.putFalseJump(Code.eq, Code.pc -30);
					
					Code.put(Code.getstatic);
					Code.put2(newArr);
						
				}
				else {
					Code.put(Code.mul);
				}
				
			}
			
		}
		else if (term.getMuloper() instanceof Divop) {
			Code.put(Code.div);
			}
		else { //Modop
			Code.put(Code.rem);
		}
	}
	
	
	
	// FACTOR
	
	public void visit(ArrSize arrSize) {
		isFactorArrSize = true;
	}

	public void visit(Var factor) {
		
	}
	
	public void visit(NumConst factor) {
		Code.loadConst(factor.getN1());
	}
	
	public void visit(CharConst factor) {
		Code.loadConst((int)factor.getC1());
	}
	
	public void visit(BoolConst factor) {
		Code.loadConst(factor.getB1().equals("true")?1:0);
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
	
	
	
	// DESIGNATOR
	
	public void visit(ASTDesignator des) {
		
		if (des.getParent() instanceof Factor || des.getParent() instanceof Increment || des.getParent() instanceof Decrement  ) {
			Code.load(des.obj);
		}
		if (des.getParent() instanceof DesignatorStatement){
			
		}
	}
	
	
	public void visit(DesignatorArray des) {
		if (des.getParent() instanceof Factor) {
			Code.load(des.obj);
		}
		
		
		if (des.getParent() instanceof Increment || des.getParent() instanceof Decrement ) {
			Code.put(Code.dup2);
			Code.load(des.obj);
		}
		
	}
	
	public void visit(DesArrayName des) {
		//System.out.println("Adresa: " + des.obj.getAdr() + " level: " + des.obj.getLevel() + " kind: " + des.obj.getKind() + " name: " + des.getName());
		Code.load(des.obj);
	}
	
	// OTHER

	
}

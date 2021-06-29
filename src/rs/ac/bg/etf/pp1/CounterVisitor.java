package rs.ac.bg.etf.pp1;

import java_cup.astext.AstParser;
import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor {
	
protected int count;
	
	public int getCount(){
		return count;
	}
	
	public static class FormParamCounter extends CounterVisitor{
	
		public void visit(ASTFormalParamDecl formParamDecl){
			count++;
		}
		public void visit(ArrParamDecl formParamDecl){
			count++;
		}
		
	}
	
	public static class VarCounter extends CounterVisitor{
		
		public void visit(ASTVarDecl varDecl){
			count++;
		}
		
		public void visit(ArrayDecl varDecl){
			count++;
		}
	}

}

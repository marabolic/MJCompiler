// generated with ast extension for cup
// version 0.8
// 29/5/2021 9:50:0


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Relop Relop);
    public void visit(FormalParamDecl FormalParamDecl);
    public void visit(StatementList StatementList);
    public void visit(Addop Addop);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(DeclList DeclList);
    public void visit(Designator Designator);
    public void visit(Term Term);
    public void visit(RetType RetType);
    public void visit(Condition Condition);
    public void visit(Muloper Muloper);
    public void visit(ConstDeclList ConstDeclList);
    public void visit(ActualParamList ActualParamList);
    public void visit(VarDeclList VarDeclList);
    public void visit(FormalParamList FormalParamList);
    public void visit(Expr Expr);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(ActualPars ActualPars);
    public void visit(Statement Statement);
    public void visit(VarDecl VarDecl);
    public void visit(CondFact CondFact);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(BasicTypes BasicTypes);
    public void visit(TermSum TermSum);
    public void visit(FormPars FormPars);
    public void visit(ASTCharacter ASTCharacter);
    public void visit(ASTBoolean ASTBoolean);
    public void visit(ASTNumber ASTNumber);
    public void visit(Modop Modop);
    public void visit(Divop Divop);
    public void visit(Mulop Mulop);
    public void visit(MinusOp MinusOp);
    public void visit(PlusOp PlusOp);
    public void visit(LsEqual LsEqual);
    public void visit(LessThan LessThan);
    public void visit(GtEqual GtEqual);
    public void visit(GreaterThan GreaterThan);
    public void visit(NotEqual NotEqual);
    public void visit(CompEqual CompEqual);
    public void visit(Assignop Assignop);
    public void visit(DesignatorArray DesignatorArray);
    public void visit(ASTDesignator ASTDesignator);
    public void visit(ExprInParens ExprInParens);
    public void visit(NewArray NewArray);
    public void visit(BoolConst BoolConst);
    public void visit(CharConst CharConst);
    public void visit(NumConst NumConst);
    public void visit(FuncCallPars FuncCallPars);
    public void visit(Var Var);
    public void visit(SingleFactor SingleFactor);
    public void visit(MulFactors MulFactors);
    public void visit(SingleTerm SingleTerm);
    public void visit(SumTerm SumTerm);
    public void visit(Minus Minus);
    public void visit(NegOpExpr NegOpExpr);
    public void visit(OpExpr OpExpr);
    public void visit(ASTCondFact ASTCondFact);
    public void visit(CondFactRel CondFactRel);
    public void visit(SingleCondFact SingleCondFact);
    public void visit(MulCondFacts MulCondFacts);
    public void visit(SingleCondTerm SingleCondTerm);
    public void visit(MulCondTerms MulCondTerms);
    public void visit(ActualParam ActualParam);
    public void visit(ActualParList ActualParList);
    public void visit(NoActuals NoActuals);
    public void visit(Actuals Actuals);
    public void visit(Decrement Decrement);
    public void visit(Increment Increment);
    public void visit(FuncCall FuncCall);
    public void visit(AssignStmt AssignStmt);
    public void visit(SurroundStmt SurroundStmt);
    public void visit(PrintStmtNum PrintStmtNum);
    public void visit(PrintStmt PrintStmt);
    public void visit(ReadStmt ReadStmt);
    public void visit(ReturnNoExpr ReturnNoExpr);
    public void visit(ReturnExpr ReturnExpr);
    public void visit(IfElseStatement IfElseStatement);
    public void visit(IfStatement IfStatement);
    public void visit(DesignatorStmt DesignatorStmt);
    public void visit(NoStmt NoStmt);
    public void visit(StmtList StmtList);
    public void visit(Type Type);
    public void visit(ArrParamDecl ArrParamDecl);
    public void visit(ASTFormalParamDecl ASTFormalParamDecl);
    public void visit(SingleFormalParamDecl SingleFormalParamDecl);
    public void visit(FormalParamDecls FormalParamDecls);
    public void visit(NoFormParams NoFormParams);
    public void visit(FormParams FormParams);
    public void visit(NoType NoType);
    public void visit(ActualType ActualType);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(MethodDecl MethodDecl);
    public void visit(NoMethodDecl NoMethodDecl);
    public void visit(ASTMethodDecList ASTMethodDecList);
    public void visit(ConstName ConstName);
    public void visit(ConstDecl ConstDecl);
    public void visit(SingleConstDecl SingleConstDecl);
    public void visit(MoreConstDecls MoreConstDecls);
    public void visit(ArrayDecl ArrayDecl);
    public void visit(ASTVarDecl ASTVarDecl);
    public void visit(SingleVarDecl SingleVarDecl);
    public void visit(MoreVarDecls MoreVarDecls);
    public void visit(NoSpecDecl NoSpecDecl);
    public void visit(ASTConstDeclList ASTConstDeclList);
    public void visit(ASTVarDeclList ASTVarDeclList);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}

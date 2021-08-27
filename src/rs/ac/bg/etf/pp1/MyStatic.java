package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;

public class MyStatic {
	public static final int Bool = 5;
	public static final Struct boolType = new Struct(MyStatic.Bool);
	
	public static void init() {
		Tab.init();
		Scope universe=Tab.currentScope;
		universe.addToLocals(new Obj(Obj.Type, "bool", boolType));
		
		Obj obj=universe.findSymbol("chr");
		Obj par=obj.getLocalSymbols().iterator().next();
		par.setFpPos(1);
		
		obj=universe.findSymbol("ord");
		par=obj.getLocalSymbols().iterator().next();
		par.setFpPos(1);	
		
		obj=universe.findSymbol("len");
		par=obj.getLocalSymbols().iterator().next();
		par.setFpPos(1);			
		
	}
	
	
	
	
}

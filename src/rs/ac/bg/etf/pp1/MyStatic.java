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
	}
	
	public static Obj find(String name) {
		Obj resultObj = null;
		for (Scope s = Tab.currentScope; s != null; s = s.getOuter()) {
			if (s.getLocals() != null) {
				resultObj = s.getLocals().searchKey(name);
				if (resultObj != null) break;
			}
		}
		return (resultObj != null) ? resultObj : Tab.noObj;
	}
	
	
}

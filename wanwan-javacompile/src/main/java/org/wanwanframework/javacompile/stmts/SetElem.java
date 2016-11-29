package org.wanwanframework.javacompile.stmts;

import org.wanwanframework.javacompile.expresses.Express;
import org.wanwanframework.javacompile.expresses.Id;
import org.wanwanframework.javacompile.lexer.Type;
import org.wanwanframework.javacompile.ops.Access;
import org.wanwanframework.javacompile.symbols.Array;

public class SetElem extends Stmt {

	public Id array;
	public Express index;
	public Express expr;
	
	public SetElem(Access x, Express y) {
		array = x.array;
		index = x.index;
		expr = y;
		
		if(check(x.type, expr.type) == null)
			error("type error");
	}
	
	public Type check(Type p1, Type p2) {
		if (p1 instanceof Array || p2 instanceof Array) {
			return null;
		} else if (p1 == p2) {
			return p2;
		} else if (Type.numberic(p1) && Type.numberic(p2)) {
			return p2;
		} else {
			return null;
		}
	}
	
	public void gen(int b, int a) {
		String s1 = index.reduce().toString();
		String s2 = expr.reduce().toString();
		emit(array.toString() + " [ "+s1+" ] = " + s2);
	}
}

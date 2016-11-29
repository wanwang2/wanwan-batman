package org.wanwanframework.javacompile.stmts;

import org.wanwanframework.javacompile.expresses.Express;
import org.wanwanframework.javacompile.expresses.Id;
import org.wanwanframework.javacompile.lexer.Type;

/**
 * 赋值三段式
 * @author coco
 *
 */
public class Set extends Stmt {

	public Id id; 
	public Express expr;
	public Set(Id i, Express x) {
		this.id = i;
		this.expr = x;
		if(check(id.type, expr.type) == null) {
			error("type error");
		}
	}
	
	public Type check(Type p1, Type p2) {
		if(Type.numberic(p1) && Type.numberic(p2)) {
			return p2;
		}else if(p1 == Type.BOOL && p2 == Type.BOOL) {
			return p2;
		}
		return null;
	}
	
	public void gen(int b, int a) {
		emit(id.toString() + " = " + expr.gen().toString());
	}
}

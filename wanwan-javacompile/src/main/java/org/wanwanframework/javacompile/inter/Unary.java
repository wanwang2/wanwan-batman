package org.wanwanframework.javacompile.inter;

import org.wanwanframework.javacompile.lexer.Token;
import org.wanwanframework.javacompile.lexer.Type;

/**
 * 单目运算
 * @author coco
 *
 */
public class Unary extends Op {

	public Expr expr;
	public Unary(Token tok, Expr x) {
		super(tok, null);
		this.expr = x;
		type = Type.max(Type.INT, expr.type);
		if(type == null) error("type error");
	}
	
	public Expr gen() {
		return new Unary(op, expr.reduce());
	}
	
	public String toString() {
		return op.toString() + " " + expr.toString();
	}

}

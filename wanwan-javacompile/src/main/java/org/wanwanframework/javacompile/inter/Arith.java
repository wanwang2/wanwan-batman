package org.wanwanframework.javacompile.inter;

import org.wanwanframework.javacompile.lexer.Token;
import org.wanwanframework.javacompile.lexer.Type;

/**
 * 双目运算
 * @author coco
 *
 */
public class Arith extends Op {

	public Expr expr1, expr2;
	
	public Arith(Token token, Expr i, Expr w) {
		super(token, null);
		expr1 = i;
		expr2 = w;
		type = Type.max(expr1.type, expr2.type);
		if(type == null) error("type error");
	}

	public Expr gen() {
		return new Arith(op, expr1.reduce(), expr2.reduce());
	}

	@Override
	public String toString() {
		return expr1.toString() + " " + op.toString() + " " + expr2.toString();
	}
	
	
}

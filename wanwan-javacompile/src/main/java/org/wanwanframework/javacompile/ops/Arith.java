package org.wanwanframework.javacompile.ops;

import org.wanwanframework.javacompile.expresses.Express;
import org.wanwanframework.javacompile.lexer.Token;
import org.wanwanframework.javacompile.lexer.Type;

/**
 * 双目运算
 * @author coco
 *
 */
public class Arith extends Op {

	public Express expr1, expr2;
	
	public Arith(Token token, Express i, Express w) {
		super(token, null);
		expr1 = i;
		expr2 = w;
		type = Type.max(expr1.type, expr2.type);
		if(type == null) error("type error");
	}

	public Express gen() {
		return new Arith(op, expr1.reduce(), expr2.reduce());
	}

	@Override
	public String toString() {
		return expr1.toString() + " " + op.toString() + " " + expr2.toString();
	}
	
	
}

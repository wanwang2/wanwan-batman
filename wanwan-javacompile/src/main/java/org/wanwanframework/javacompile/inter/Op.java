package org.wanwanframework.javacompile.inter;

import org.wanwanframework.javacompile.lexer.Token;
import org.wanwanframework.javacompile.lexer.Type;

public class Op extends Expr {

	public Op(Token tok, Type p) {
		super(tok, p);
	}

	public Expr reduce() {
		Expr x = gen();
		Temp t = new Temp(type);
		emit(t.toString() + " = " + x.toString());
		return t;
	}
	
}

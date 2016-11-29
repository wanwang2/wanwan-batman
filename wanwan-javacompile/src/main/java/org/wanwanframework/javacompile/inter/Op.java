package org.wanwanframework.javacompile.inter;

import org.wanwanframework.javacompile.lexer.Token;
import org.wanwanframework.javacompile.lexer.Type;

public class Op extends Express {

	public Op(Token tok, Type p) {
		super(tok, p);
	}

	public Express reduce() {
		Express x = gen();
		Temp t = new Temp(type);
		emit(t.toString() + " = " + x.toString());
		return t;
	}
	
}

package org.wanwanframework.javacompile.expresses;

import org.wanwanframework.javacompile.lexer.Num;
import org.wanwanframework.javacompile.lexer.Token;
import org.wanwanframework.javacompile.lexer.Type;
import org.wanwanframework.javacompile.lexer.Word;

public class Constant extends Express {

	public Constant(Token tok, Type p) {
		super(tok, p);
	}

	public Constant(int width) {
		super(new Num(width), Type.INT);
	}

	public static final Constant 
		True = new Constant(Word.TRUE, 		Type.BOOL),
		False = new Constant(Word.FALSE, 	Type.BOOL);
	
	public void jumping(int t, int f) {
		if(this == True && t != 0) emit("goto L" + t);
		else if(this == False && f!= 0) emit("goto L" + f);
	}
}

package org.wanwanframework.javacompile.inter;

import org.wanwanframework.javacompile.lexer.Token;
import org.wanwanframework.javacompile.lexer.Type;

/**
 * 表达式
 * @author coco
 *
 */
public class Express extends Node {

	public Token op;
	public Type type;
	Express(Token tok, Type p) {
		op = tok;
		type = p;
	}
	
	public Express gen() {
		return this;
	}
	
	public Express reduce() {
		return this;
	}
	
	/**
	 * 跳转
	 * @param t
	 * @param f
	 */
	public void jumping(int t, int f) {
		emitjumps(toString(), t, f);
	}
	
	public void emitjumps(String test, int t, int f) {
		if(t != 0 && f != 0) {
			emit("if " + test + " goto L" + t);
			emit("goto L" + f);
		}
		else if(t != 0) emit("if " + test + " goto L" + t);
		else if(f != 0) emit("iffalse " + test + " goto L" + f);
		else;
	}
	
	public String toString() {
		return op.toString();
	}
}

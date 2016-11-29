package org.wanwanframework.javacompile.inter;

import org.wanwanframework.javacompile.lexer.Token;
import org.wanwanframework.javacompile.lexer.Type;

public class Logical extends Expr {

	public Expr expr1, expr2;
	
	Logical(Token tok, Expr x1, Expr x2) {
		super(tok, null);
		expr1 = x1; 
		expr2 = x2;
		type = check(expr1.type, expr2.type);
	}
	
	public Type check(Type p1, Type p2) {
		if(p1 == Type.BOOL && p2 == Type.BOOL) {
			return Type.BOOL;
		}
		return null;
	}

	public Expr gen() {
		int f = newlabel(); int a = newlabel();
		Temp temp = new Temp(type);
		this.jumping(0, f);
		emit(temp.toString() + " = true ");
		emit("goto L" + a);
		emitlabel(f); emit(temp.toString() + " = false");
		emitlabel(a);
		return temp;
	}

	@Override
	public String toString() {
		return "Logical [expr1=" + expr1 + " " + op.toString() + ", expr2=" + expr2 + "]";
	}
	
	
}

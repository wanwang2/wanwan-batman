package org.wanwanframework.javacompile.inter;

import org.wanwanframework.javacompile.lexer.Type;

public class Do extends Stmt {

	Expr expr;
	Stmt stmt;
	public Do() {
		this.expr = null;
		this.stmt = null;
	}
	
	public void init(Stmt s, Expr x) {
		expr = x; stmt = s;
		if(expr.type != Type.BOOL){
			expr.error("boolean required in do");
		}
	}
	
	public void gen(int b, int a) {
		after = a;
		int label = newlabel();
		stmt.gen(b, label);
		emitlabel(label);
		expr.jumping(b, 0);
	}
}

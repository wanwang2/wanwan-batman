package org.wanwanframework.javacompile.inter;

public class Do extends Stmt {

	Expr expr;
	Stmt stmt;
	public Do() {
		this.expr = null;
		this.stmt = null;
	}
	
	public void init() {
		
	}
	
	public void gen(int b, int a) {
		after = a;
		int label = newlabel();
		stmt.gen(b, label);
		emitlabel(label);
		expr.jumping(b, 0);
	}
}

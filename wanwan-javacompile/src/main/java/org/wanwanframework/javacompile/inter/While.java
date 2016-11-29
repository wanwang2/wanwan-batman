package org.wanwanframework.javacompile.inter;

import org.wanwanframework.javacompile.lexer.Type;

/**
 * while:complite
 * @author coco
 *
 */
public class While extends Stmt {

	Express expr; Stmt stmt;
	public While() { expr = null; stmt = null; }
	public void init(Express x, Stmt s) {
		expr = x;
		stmt = s;
		if(expr.type != Type.BOOL)
			expr.error("boolean required in while");
	}
	
	public void gen(int b, int a) {
		after = a;
		expr.jumping(0, a);
		int label = newlabel();
		emitlabel(label);
		stmt.gen(label, b);
		emit("goto L" + b);
	}
}

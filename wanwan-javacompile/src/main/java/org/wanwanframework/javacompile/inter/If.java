package org.wanwanframework.javacompile.inter;

import org.wanwanframework.javacompile.lexer.Type;

/**
 * If:complite
 * @author coco
 *
 */
public class If extends Stmt {

	Express expr; Stmt stmt;
	public If(Express x, Stmt s) {
		expr = x; stmt = s;
		if(expr.type != Type.BOOL) {
			expr.error("boolean required in if");
		}
	}
	
	public void gen(int b, int a) {
		int label = newlabel();
		expr.jumping(0, a);
		emitlabel(label); stmt.gen(label, a);
	}
}

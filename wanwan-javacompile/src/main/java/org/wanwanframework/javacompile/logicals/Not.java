package org.wanwanframework.javacompile.logicals;

import org.wanwanframework.javacompile.expresses.Express;
import org.wanwanframework.javacompile.expresses.Logical;
import org.wanwanframework.javacompile.lexer.Token;

/**
 * Not:complite
 * @author coco
 *
 */
public class Not extends Logical{

	public Not(Token tok, Express x2) {
		super(tok, x2, x2);
	}

	public void jumping(int t, int f) {
		expr2.jumping(f, t);
	}
	
	public String toString() {
		return op.toString() + " " + expr2.toString();
	}
}

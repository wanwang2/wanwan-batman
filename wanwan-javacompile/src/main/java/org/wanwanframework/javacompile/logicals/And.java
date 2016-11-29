package org.wanwanframework.javacompile.logicals;

import org.wanwanframework.javacompile.expresses.Express;
import org.wanwanframework.javacompile.expresses.Logical;
import org.wanwanframework.javacompile.lexer.Token;

/**
 * And:complite
 * @author coco
 *
 */
public class And extends Logical{

	public And(Token tok, Express x1, Express x2) {
		super(tok, x1, x2);
	}
	
	public void jumping(int t, int f) {
		int label = f != 0 ? f : newlabel();
		expr1.jumping(0, label);
		expr2.jumping(t, f);
		if(f == 0) {
			emitlabel(label);
		}
	}

}

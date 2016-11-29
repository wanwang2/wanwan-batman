package org.wanwanframework.javacompile.inter;

import org.wanwanframework.javacompile.lexer.Token;

/**
 * 或跳转代码：complite 
 * @author coco
 *
 */
public class Or extends Logical{

	public Or(Token tok, Express x1, Express x2) {
		super(tok, x1, x2);
	}

	public void jumping(int t, int f) {
		int label = t != 0 ? t : newlabel();
		expr1.jumping(label, 0);
		expr2.jumping(t, f);
		if(t == 0) emitlabel(label);
	}
	
}

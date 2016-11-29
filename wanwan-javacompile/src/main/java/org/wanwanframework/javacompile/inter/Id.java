package org.wanwanframework.javacompile.inter;

import org.wanwanframework.javacompile.lexer.Type;
import org.wanwanframework.javacompile.lexer.Word;

public class Id extends Expr {

	public int offset;
	
	public Id(Word id, Type p, int b) {
		super(id, p);
		this.offset = b;
	}

}

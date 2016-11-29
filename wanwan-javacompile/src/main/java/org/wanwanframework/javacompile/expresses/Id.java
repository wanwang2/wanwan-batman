package org.wanwanframework.javacompile.expresses;

import org.wanwanframework.javacompile.lexer.Type;
import org.wanwanframework.javacompile.lexer.Word;

public class Id extends Express {

	public int offset;
	
	public Id(Word id, Type p, int b) {
		super(id, p);
		this.offset = b;
	}

}

package org.wanwanframework.javacompile.inter;

import org.wanwanframework.javacompile.lexer.Token;
import org.wanwanframework.javacompile.lexer.Type;

public class Array extends Type{

	public Type of;
	public int size = 1;
	public Array(int sz, Type p) {
		super("", Token.TagKey.INDEX.ordinal(), sz * p.width);
		size = sz; 
		of = p;
	}
	
	@Override
	public String toString() {
		return "Array [of=" + of + ", size=" + size + "]";
	}
	
}

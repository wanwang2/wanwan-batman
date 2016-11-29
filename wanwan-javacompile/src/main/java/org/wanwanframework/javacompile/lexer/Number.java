package org.wanwanframework.javacompile.lexer;

public class Number extends Token {

	public Number(int v) {
		super(Token.TagKey.NUM.ordinal());
		value = v;
	}

	public final int value;

	@Override
	public String toString() {
		return "Number [value=" + value + "]";
	}
	
}

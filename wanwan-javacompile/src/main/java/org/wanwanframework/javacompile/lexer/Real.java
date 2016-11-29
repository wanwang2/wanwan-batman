package org.wanwanframework.javacompile.lexer;

public class Real extends Token {

	public float value;
	
	public Real(float v) {
		super(Token.TagKey.REAL.ordinal());
		this.value = v;
	}

	@Override
	public String toString() {
		return "Real [value=" + value + "]";
	}
	
}

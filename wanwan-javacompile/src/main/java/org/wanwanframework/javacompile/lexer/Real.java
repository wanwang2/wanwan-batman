package org.wanwanframework.javacompile.lexer;

public class Real extends Token {

	public float value;
	
	public Real(float v) {
		super(Tag.REAL);
		this.value = v;
	}

	@Override
	public String toString() {
		return "Real [value=" + value + "]";
	}
	
}

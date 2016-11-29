package org.wanwanframework.javacompile.lexer;

/**
 * Num
 * @author coco
 *
 */
public class Num extends Token {

	public Num(int v) {
		super(Tag.NUM);
		value = v;
	}

	public final int value;

	@Override
	public String toString() {
		return "Number [value=" + value + "]";
	}
	
}

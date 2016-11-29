package org.wanwanframework.javacompile.lexer;

/**
 * Token只包含一个词
 * @author coco
 *
 */
public class Token {

	public int tag;

	public Token(int tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "Token [tag=" + tag + "]";
	}

}

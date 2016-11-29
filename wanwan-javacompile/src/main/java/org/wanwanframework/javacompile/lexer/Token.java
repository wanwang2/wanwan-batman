package org.wanwanframework.javacompile.lexer;

/**
 * Token只包含一个词
 * @author coco
 *
 */
public class Token {

	public enum TagKey {
		AND, BASIC, BREAK, DO, ELSE, 
		EQ, FALSE, GE, ID, IF,
		INDEX, LE, MINUS, NE, NUM,
		OR, REAL, TEMP, TRUE, WHILE,
		INT, CHAR, FLOAT, BOOL;
	};

	public int tag;

	public Token(int tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "Token [tag=" + tag + "]";
	}

}

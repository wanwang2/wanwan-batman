package org.wanwanframework.javacompile.lexer;

public class Token {

	public enum TagKey {
		AND, BASIC, BREAK, DO, ELSE, 
		EQ, FALSE, GE, ID, IF,
		INDEX, LE, MINUS, NE, NUM,
		OR, REAL, TEMP, TRUE, WHILE,
		INT, CHAR, FLOAT, BOOL;
	};

	int tag;

	public Token(int tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "Token [tag=" + tag + "]";
	}

}

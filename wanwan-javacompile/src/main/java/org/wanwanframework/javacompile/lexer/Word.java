package org.wanwanframework.javacompile.lexer;

public class Word extends Token {

	String lexeme = "";
	
	public Word(String value, int tag) {
		super(tag);
		this.lexeme = value;
	}

	@Override
	public String toString() {
		return "Word [lexeme=" + lexeme + "]";
	}
	
	public static final Word and = new Word("&&", TagKey.AND.ordinal());
	public static final Word or = new Word("||", TagKey.OR.ordinal());
	public static final Word eq = new Word("==", TagKey.EQ.ordinal());
	public static final Word ne = new Word("!=", TagKey.NE.ordinal());
	
	public static final Word le = new Word("<=", TagKey.LE.ordinal());
	public static final Word ge = new Word(">=", TagKey.GE.ordinal());
	public static final Word minus = new Word("minus", TagKey.MINUS.ordinal());
	
	public static final Word TRUE = new Word("true", TagKey.TRUE.ordinal());
	public static final Word FALSE = new Word("false", TagKey.FALSE.ordinal());
	public static final Word temp = new Word("t", TagKey.TEMP.ordinal());
}

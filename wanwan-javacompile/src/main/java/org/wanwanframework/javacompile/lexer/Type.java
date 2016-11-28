package org.wanwanframework.javacompile.lexer;

public class Type extends Word {

	public int width = 0;
	
	public Type(String value, int tag, int width) {
		super(value, tag);
		this.width = width;
	}

	public static final Type INT = new Type("int", TagKey.INT.ordinal(), 4);
	public static final Type FLOAT = new Type("int", TagKey.FLOAT.ordinal(), 8);
	
	public static final Type BOOL = new Type("bool", TagKey.BOOL.ordinal(), 1);
	public static final Type CHAR = new Type("char", TagKey.CHAR.ordinal(), 1);
	
}

package org.wanwanframework.javacompile.lexer;

/**
 * Type类型
 * @author coco
 *
 */
public class Type extends Word {

	public int width = 0;
	
	public Type(String value, int t, int width) {
		super(value, t);
		this.width = width;
	}

	public static final Type INT = new Type("int", Tag.BASIC , 4);
	public static final Type FLOAT = new Type("float", Tag.BASIC , 8);
	public static final Type BOOL = new Type("bool", Tag.BASIC , 1);
	public static final Type CHAR = new Type("char", Tag.BASIC , 1);
	
	public static boolean numberic(Type p) {
		if(p == Type.CHAR || p == Type.INT || p == Type.FLOAT) {
			return true;
		}else {
			return false;
		}
	}
	
	public static Type max(Type p1, Type p2) {
		if (!numberic(p1) || !numberic(p2)) {
			return null;
		} else if (p1 == Type.FLOAT || p2 == Type.FLOAT) {
			return Type.FLOAT;
		} else if (p1 == Type.INT || p2 == Type.INT) {
			return Type.INT;
		} else {
			return Type.CHAR;
		}
	}
}

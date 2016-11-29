package org.wanwanframework.javacompile.expresses;

import org.wanwanframework.javacompile.lexer.Type;
import org.wanwanframework.javacompile.lexer.Word;

/**
 * 临时变量
 * @author coco
 *
 */
public class Temp extends Express {
	public static int count = 0;
	int number = 0;
	Temp(Type p) {
		super(Word.temp, p);
		number = ++count;
	}

	public String toString() {
		return "t" + number;
	}
}

package org.wanwanframework.javacompile.inter;

import org.wanwanframework.javacompile.lexer.Token;
import org.wanwanframework.javacompile.lexer.Type;
import org.wanwanframework.javacompile.lexer.Word;

/**
 * 数组类型检查 
 * @author coco
 *
 */
public class Access extends Op {

	public Id array;
	public Expr index;
	
	public Access(Id a, Expr i, Type p) {
		super(new Word("[]", Token.TagKey.INDEX.ordinal()), p);
		array = a;
		index = i;
	}

	public Expr gen() {
		return new Access(array, index.reduce(), type);
	}
	
	public void jumping(int t, int f) {
		emitjumps(reduce().toString(), t, f);
	}
	
	public String toString() {
		return array.toString() + " [ "+index.toString()+" ] ";
	}
}

package org.wanwanframework.javacompile.ops;

import org.wanwanframework.javacompile.expresses.Express;
import org.wanwanframework.javacompile.expresses.Id;
import org.wanwanframework.javacompile.lexer.Tag;
import org.wanwanframework.javacompile.lexer.Type;
import org.wanwanframework.javacompile.lexer.Word;

/**
 * 数组类型检查 
 * @author coco
 *
 */
public class Access extends Op {

	public Id array;
	public Express index;
	
	public Access(Id a, Express i, Type p) {
		super(new Word("[]", Tag.INDEX), p);
		array = a;
		index = i;
	}

	public Express gen() {
		return new Access(array, index.reduce(), type);
	}
	
	public void jumping(int t, int f) {
		emitjumps(reduce().toString(), t, f);
	}
	
	public String toString() {
		return array.toString() + " [ "+index.toString()+" ] ";
	}
}

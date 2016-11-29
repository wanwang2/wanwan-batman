package org.wanwanframework.javacompile.inter;

/**
 * 语句构造：complite
 * @author coco
 *
 */
public class Stmt extends Node {

	public Stmt() {
		
	}
	
	public static Stmt Null = new Stmt();
	
	/**
	 * 生成三地址
	 * @param b 开始时的标号
	 * @param a 结束时的标号
	 */
	public void gen(int b, int a) { // the number begin and end
		
	}
	
	int after = 0; // the mark to save next language
	
	public static Stmt Enclosing = Stmt.Null; // for break
}

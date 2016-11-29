package org.wanwanframework.javacompile.core;

import org.wanwanframework.javacompile.lexer.Lexer;

/**
 * Node {Expr表达式节点, Stmt语句节点}
 * @author coco
 *
 */
public class Node {

	int lexline = 0;
	
	public Node() { 
		lexline = Lexer.line;
	}
	
	public void error(String s) {
		throw new Error("new line :" + lexline + ",error:" + s);
	}
	
	public static int labels = 0;
	
	public int newlabel(){return ++labels;}
	
	public void emitlabel(int i) {
		System.out.print("L" + i + ":");
	}
	
	public void emit(String s) {
		System.out.println("\t" + s);
	}
}

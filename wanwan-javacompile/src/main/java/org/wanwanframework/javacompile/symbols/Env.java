package org.wanwanframework.javacompile.symbols;

import java.util.Hashtable;

import org.wanwanframework.javacompile.inter.Id;
import org.wanwanframework.javacompile.lexer.Token;

public class Env {

	private Hashtable<Object, Object> table;
	protected Env pre;
	public Env(Env n) { table = new Hashtable<>(); pre = n;}
	public void put(Token w, Id i) {table.put(w, i);}
	public Id get(Token w) {
		return null;
	}
}

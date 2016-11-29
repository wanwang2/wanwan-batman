package org.wanwanframework.javacompile.symbols;

import java.util.Hashtable;

import org.wanwanframework.javacompile.inter.Id;
import org.wanwanframework.javacompile.lexer.Token;

/**
 * 符号表
 * @author coco
 *
 */
public class Env {

	private Hashtable<Object, Object> table;
	protected Env prev;
	
	public Env(Env n) { table = new Hashtable<>(); prev = n;}
	
	public void put(Token w, Id i) {table.put(w, i);}
	
	public Id get(Token w) {
		for (Env e = this ; e != null; e = e.prev) {
			Id found = (Id)(e.table.get(w));
			if(found != null) return found;
		}
		return null;
	}
}

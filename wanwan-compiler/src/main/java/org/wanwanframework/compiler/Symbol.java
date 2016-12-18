package org.wanwanframework.compiler;

public class Symbol {

	private static Symbol instance;

	public static Symbol getInstance() {
		if (instance == null)
			instance = new Symbol();
		return instance;
	}

	int currentClassNumber = 0;
	int static_index = 0;
	int field_index = 0;
	int arg_index = 0;
	int var_index = 0;
	int errorNum = 0;

	public Symbol() {

	}
}

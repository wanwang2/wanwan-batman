package org.wanwanframework.compiler;

public class Analyzer {

	Symbol symbol;
	Parser parser;
	
	public Analyzer(Parser parser)
	{
		symbol = Symbol.getInstance();
		this.parser = parser;
	}

}

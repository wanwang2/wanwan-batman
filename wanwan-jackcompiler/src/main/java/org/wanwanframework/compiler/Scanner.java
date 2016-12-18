package org.wanwanframework.compiler;

import java.util.HashSet;
import java.util.Set;

public class Scanner {

	Set<String> keyWords = new HashSet<String>();;
	Set<String> symbols = new HashSet<String>();
	private String lineContent;
	private int lineIndex;
	protected int row;

	public void initKeyWords() {
		keyWords.add("class");
	    keyWords.add("constructor");
	    keyWords.add("function");
	    keyWords.add("method");
	    keyWords.add("field");
	    keyWords.add("static");
	    keyWords.add("int");
	    keyWords.add("char");
	    keyWords.add("boolean");
	    keyWords.add("void");
	    keyWords.add("true");
	    keyWords.add("false");
//	    keyWords.add("null");
	    keyWords.add("this");
	    keyWords.add("if");
	    keyWords.add("else");
	    keyWords.add("while");
	    keyWords.add("return");
	}
	
	public void initSymbols() {
		symbols.add("{");
		symbols.add("}");
		symbols.add("(");
		symbols.add(")");
		symbols.add("[");
		symbols.add("]");
		symbols.add(".");
		symbols.add(",");
		symbols.add(";");
		symbols.add("+");
		symbols.add("-");
		symbols.add("*");
		symbols.add("/");
		symbols.add("&");
		symbols.add("|");
		symbols.add("~");
		symbols.add("<");
		symbols.add(">");
		symbols.add("=");
		symbols.add(">=");
		symbols.add("<=");
		symbols.add("==");
		symbols.add("!=");
		// symbols.add("!");
		// symbols.add("&&");
		// symbols.add("||");
	}

	private void getLine() {
		
	}
	
	@SuppressWarnings("unused")
	private char nextChar()
	{
	    if (lineIndex >= lineContent.length())
	    {
	        row++;
	        getLine();
	        lineContent += '\n';
	        lineIndex = 0;
            return lineContent.charAt(lineIndex);
	    }
	    else
	    {
	        return lineContent.charAt(lineIndex);
	    }
	}
}

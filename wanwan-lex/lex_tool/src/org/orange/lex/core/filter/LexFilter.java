package org.orange.lex.core.filter;

public class LexFilter extends Filter{

	private int index = 0;
	
	public int getIndex(){
		return index++;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	 
	public String toIndex(String word){ 
		return  replaceAll(word, to("index"), "" + getIndex());
	}
	
	public String toField(String word, String field){
		return replaceAll(word, to("field"), field);
	}
	
	public String toType(String word, String field){
		return replaceAll(word, to("type"), field);
	}
	
	private String replaceAll(String word, String express, String field){
		word = word.replaceAll(express, field);
		return word;
	}
}

package org.wanwanframework.language.model;

public class Node {

	private String content; // 输出结果
	private String[] lex; //结构数据
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getLex() {
		return lex;
	}

	public void setLex(String[] lex) {
		this.lex = lex;
	}
	
}

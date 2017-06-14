package org.wanwanframework.lextool;

public class LexFilter {

	private int index = 0;

	public int getIndex() {
		return index++;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	protected String to(String param) {
		return "@" + param;
	}

	public String toIndex(String word) {
		return replaceAll(word, to("index"), "" + getIndex());
	}

	public String toField(String word, String field) {
		return replaceAll(word, to("field"), field);
	}

	public String toType(String word, String field) {
		return replaceAll(word, to("type"), field);
	}

	private String replaceAll(String word, String express, String field) {
		word = word.replaceAll(express, field);
		return word;
	}
}

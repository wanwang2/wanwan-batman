package org.wanwanframework.language.core;

public class Parser {

	/**
	 * 根据配置翻译句子
	 * @param sentenceId
	 * @param flex
	 * @return
	 */
	public String parser(String[] flex) {
		String content = "";
		if(flex.length >= 3 && flex[0].indexOf("1") > -1) {
			content = flex[1] + " is " + flex[2];
		} 
		else if(flex.length >= 4 && flex[0].indexOf("2") > -1) {
			content = flex[1] + " " + flex[2] + " [了] " + flex[3];
		}
		else if(flex.length >= 4 && flex[0].indexOf("3") > -1) {
			content = flex[1] + " [年] " + flex[2] + " [全面] " + flex[3];
		}
		return content;
	}
}

package org.wanwanframework.javacompile.util;

import org.wanwanframework.log.Log;

public class ReadUtil {

	public static String content = "";
	public static int currentIndex = 0;
	
	/**
	 * 获得读取的字符
	 * @return
	 */
	public static char readchar() {
		char r = (char)content.charAt(currentIndex++);
		return r;
	}
	
	public static void main(String[] args) {
		ReadUtil.content = "abcdefg...abcdefg...";
		for(int i = 0; i < 10; i++) {
			Log.log(ReadUtil.readchar());
		}
		
	}
}

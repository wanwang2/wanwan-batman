package org.wanwanframework.javacompile.util;

import org.wanwanframework.log.Log;

public class ReadUtil {

	public static String content = "";
	
	private static int currentIndex = 0;

	/**
	 * 获得读取的字符
	 * 
	 * @return
	 */
	public static char readchar() {
		char r = (char) content.charAt(currentIndex);
		if (currentIndex < content.length()) {
			currentIndex++;
		}
		return r;
	}

	/**
	 * 是否读取到字符末尾
	 * 
	 * @return
	 */
	public static boolean isEnd() {
		if (currentIndex < content.length()) {
			return false;
		} else {
			return true;
		}
	}

	public static void main(String[] args) {
		ReadUtil.content = "abcdefg...abcdefg...";
		for (int i = 0; i < 10; i++) {
			Log.log(ReadUtil.readchar());
		}

	}
}

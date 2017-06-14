package org.wanwanframework.language;

import java.util.Map;

import org.wanwanframework.language.core.Parser;
import org.wanwanframework.log.Log;
import org.wanwanframwork.file.LineTool;

/**
 * 启动程序
 * @author coco
 *
 */
public class LanguageController {

	private Parser parser = new Parser();

	private void read(Map<String, String> flexMap) {
		for(String id: flexMap.keySet()) {
			String content  = parser.parser(LineTool.getWords(id, flexMap, ","));
			Log.log(content);
		}
	}
	
	public static void main(String[] args) {
		Log.log("LanguageController...");
		LanguageController controller = new LanguageController(); 
		Map<String, String> flexMap = LineTool.getSimpleLine(LanguageController.class.getResource("/flex.txt").getPath());
		controller.read(flexMap);
	}
}

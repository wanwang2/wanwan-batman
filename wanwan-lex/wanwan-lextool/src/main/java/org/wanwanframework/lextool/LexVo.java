package org.wanwanframework.lextool;

/**
 * 词法模型
 * @author coco
 *
 */
public class LexVo {

	public NodeVo[] fields;
	
	public String[] types;
	
	public String template;
	
	public char[] split;
	
	/**
	 * 计数器
	 */
	public int index;
	
	/**
	 * 输出路径
	 */
	public String path;
	
	public String file;
}

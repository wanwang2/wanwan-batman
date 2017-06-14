package org.wanwanframework.lextool;

import java.io.IOException;
import java.util.Properties;

import org.wanwanframework.log.Log;
import org.wanwanframwork.file.FileUtil;
import org.wanwanframwork.file.PropertyUtil;
import org.wanwanframwork.file.core.ConfigProperties;

public class LexController {

	private static final String RESOURCE = LexController.class.getResource("/").getPath();

	protected LexVo lexVo;
	protected Properties properties;
	protected LexFilter filter = new LexFilter();
 
	public void load(){ 
		properties = PropertyUtil.load(RESOURCE + "@.properties");
		String expressFile = (String) properties.get( "express");
		String express = FileUtil.readFile(RESOURCE + expressFile);
		String types = (String) properties.get( "type");
		initLexVo(express, types);	
		Log.log("lexVo:", lexVo.fields);
	}
	
	private void initLexVo(String express, String types){
		lexVo = new LexVo();
		lexVo.split = ((String) properties.get( "split")).toCharArray();
		String[] nodes = express.split(lexVo.split[0] + "");
		lexVo.fields = new NodeVo[nodes.length];
		for (int i = 0; i < nodes.length; i++) {
			initNodeVo(lexVo, nodes[i], i);
		}
		lexVo.types = types.split(lexVo.split[0] + "");
		lexVo.template = (String) properties.get( "template");
		lexVo.index = Integer.parseInt((String) properties.get( "index"));
		lexVo.path = RESOURCE + properties.get( "path");
		lexVo.file = (String) properties.get( "file");
	}
	
	private void initNodeVo(LexVo lex, String nodeString, int i){
		NodeVo vo = new NodeVo();
		String[] nodes = nodeString.split(ConfigProperties.middle_circle); 
		if(nodes != null && nodes.length > 0){
			vo.node = nodes[0]; 
		}
		if(nodes != null && nodes.length > 1){
			vo.childs = nodes[1].split(lex.split[1] + "");	
		}
		lex.fields[i] =vo;
	}
	
	public void write(){
		NodeVo[] fields = lexVo.fields;
		String[] types = lexVo.types;
		String content = "";
		filter.setIndex(lexVo.index);
		String[] childs;
		for (int i = 0; i < fields.length; i++) {	 
			content += filter(lexVo.template, fields[i].node, types[0]) + "\r\n";
			childs = fields[i].childs; 
			int lastIndex = filter.getIndex();
			filter.setIndex(1);
			content = writeNode(childs, types[1], content);
			filter.setIndex(lastIndex);
		}
		Log.log("content\r\n" + content);
		writeFile(content, lexVo); 
	}
	
	private String writeNode(String[] childs, String type, String content){
		if(childs != null){
			for(String child : childs){
				content += "\t" + filter(lexVo.template, child, type) + "\r\n";
			} 
		} 
		return content;
	}
	
	public void writeFile(String content, LexVo vo){
		try {
			FileUtil.createFileUion(vo.file, vo.path, content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String filter(String word, String field, String type){
		word = filter.toIndex(word);
		word = filter.toField(word, field);
		word = filter.toType(word, type);
		return word;
	}
	
	public static void main(String[] args) {
		LexController controller = new LexController();
		controller.load();
		controller.write();
	}
}

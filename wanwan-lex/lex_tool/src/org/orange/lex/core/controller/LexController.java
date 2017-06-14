package org.orange.lex.core.controller;

import java.util.Properties;

import org.orange.lex.core.filter.LexFilter;
import org.orange.lex.core.filter.Smbol;
import org.orange.lex.core.model.LexVo;
import org.orange.lex.core.model.NodeVo;
import org.orange.lex.util.file.FileUtil;
import org.orange.lex.util.file.Log;
import org.orange.lex.util.file.Path;
import org.orange.lex.util.file.PropertyUtil;

public class LexController {

	protected LexVo lexVo;
	protected Properties properties;
	protected LexFilter filter = new LexFilter();
 
	public void load(){ 
		properties = PropertyUtil.loadProperty(Path.RESOURCE + "@.properties");
		String expressFile = PropertyUtil.getProperty(properties, "express");
		String express = FileUtil.readFile(Path.RESOURCE + expressFile);
		String types = PropertyUtil.getProperty(properties, "type");
		initLexVo(express, types);	
		Log.log("lexVo:", lexVo.fields);
	}
	
	private void initLexVo(String express, String types){
		lexVo = new LexVo();
		lexVo.split = PropertyUtil.getProperty(properties, "split").toCharArray();
		String[] nodes = express.split(lexVo.split[0] + "");
		lexVo.fields = new NodeVo[nodes.length];
		for (int i = 0; i < nodes.length; i++) {
			initNodeVo(lexVo, nodes[i], i);
		}
		lexVo.types = types.split(lexVo.split[0] + "");
		lexVo.template = PropertyUtil.getProperty(properties, "template");
		lexVo.index = Integer.parseInt(PropertyUtil.getProperty(properties, "index"));
		lexVo.path = Path.RESOURCE + PropertyUtil.getProperty(properties, "path");
		lexVo.file = PropertyUtil.getProperty(properties, "file");
	}
	
	private void initNodeVo(LexVo lex, String nodeString, int i){
		NodeVo vo = new NodeVo(); 
		String[] nodes = nodeString.split(Smbol.middle_circle); 
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
			content += filter(lexVo.template, fields[i].node, types[0]) + Smbol.tr;
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
				content += "\t" + filter(lexVo.template, child, type) + Smbol.tr;
			} 
		} 
		return content;
	}
	
	public void writeFile(String content, LexVo vo){
		FileUtil.createFile2(vo.file, vo.path, content);
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

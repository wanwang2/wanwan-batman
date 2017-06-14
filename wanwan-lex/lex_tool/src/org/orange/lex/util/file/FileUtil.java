package org.orange.lex.util.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 文件操作工具类：包括文件读取、文件写、文件复制，等操作
 * @author coco
 *
 */
public class FileUtil{
	
	/**
	 * 拷贝一个文件
	 * @param source
	 * @param path
	 * @throws IOException
	 */
	public static void copy(String source, String path) throws IOException{
		FileInputStream fileInputStream = new FileInputStream(source);
		FileOutputStream fileOutputStream = new FileOutputStream(path);
		int readIndex = -1;
		while((readIndex = fileInputStream.read()) != -1){
			fileOutputStream.write(readIndex);
		}
		fileInputStream.close();
		fileOutputStream.close();
	}
	
	/**
	 * 读取一个文件的目录并返回这个目录的内容列表
	 * @param source
	 */
	public static String[] readDir(String source){
		File fileDir = new File(source);
		String[] list = null;
		if(fileDir.exists()){
			list = fileDir.list();
		}
		return list;
	}
	
	/**
	 * 读取一个文件的目录并返回这个目录的内容列表
	 * @param source
	 */
	public static File[] readFileList(String source){
		File fileDir = new File(source);
		File[] list = null;
		if(fileDir.exists()){
			list = fileDir.listFiles();
		}
		return list;
	} 
	
	/**
	 * 读取一个文件的目录并返回这个目录的内容列表
	 * @param source
	 */
	public static File[] readFileList(String source, boolean isDir){
		File fileDir = new File(source);
		File[] list = null;
		if(fileDir.exists()){
			final boolean isDirection = isDir;
			list = fileDir.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File file) {
					if(isDirection && file.isDirectory()){
						return true;
					}else if(!isDirection && !file.isDirectory()){
						return true;
					}
					return false;
				}
			});
		}
		return list;
	} 
	
	/**
	 * 创建一个文件，如果没有目录则创建一个目录:dir和file没有连接关系
	 * @param dir
	 * @param content，创建文件的内容
	 */
	public static void createFile(String file, String dir, String content){
		try { 
			File f = new File(dir);
			f.mkdirs();
		
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			output.write(content);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建一个文件，如果没有目录则创建一个目录:dir和file没有连接关系
	 * @param dir
	 * @param content，创建文件的内容
	 */
	public static void createFile2(String fileName, String dir, String content){
		try { 
			File f = new File(dir);
			f.mkdirs();
		    String file = dir + "/" + fileName;
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			output.write(content);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据文件路径读取文件内容
	 * @param templateType
	 * @param filePath
	 * @param models
	 * @return
	 */
	public static String readFile(String filePath){ 
		String content = "";
		try { 
			Scanner in = new Scanner(new File(filePath));
			content = readText(in);
			in.close();
			return content; 
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		}
		return content;
	}
	 
	/**
	 * 将字节流中的字符组织成String
	 * @param in
	 * @param module
	 * @param file
	 * @return
	 */
	public static String readText(Scanner in){ 
		String content = ""; 
		while (in.hasNextLine()) {
			content = (content + in.nextLine() + "\r\n");
		}
		if(content.length() > 2){//当content读取到结果并长度大于2时，用于删除文件中的最后一个换行
			content = content.substring(0, content.length() - 2);	
		}
		return content; 	
	}
	 
	/**
	 * 获取文件数组字符串
	 * @param file
	 * @return
	 */
	public static String getFiles(String file){
		String[] list = FileUtil.readDir(file);
		String listFiles = ""; 
	 
		for (int i = 1; i < list.length; i++) {
			listFiles += (list[i] + "/");
		}
		return listFiles;
	}
	 
}

package org.wanwanframework.compiler;

import org.wanwanframework.log.Log;

public class Error {

	public int errorNumber;
	
	public boolean hasError()
	{
	    return errorNumber > 0;
	}
	
	public void error1(String currentParserFilename) {
		Log.log("Error in file " + currentParserFilename + ".jack: " + "classname should be same as filename");
		errorNumber++;
	}

	public void error2(String currentClass, int row, String type, String name) {
		Log.log("Error in class " + currentClass + " in line " + row
		        + ": redeclaration of '" + type + " " + name + "'");
		errorNumber++;
	}

	public void error3() {
		errorNumber++;
	}
	
	public void error4() {
		errorNumber++;
	}

	public void error5() {
		errorNumber++;
	}

	public void error6() {
		errorNumber++;
	}
	
	public void error7() {
		errorNumber++;
	}

	public void error8() {
		errorNumber++;
	}

	public void error9() {
		errorNumber++;
	}
	
}

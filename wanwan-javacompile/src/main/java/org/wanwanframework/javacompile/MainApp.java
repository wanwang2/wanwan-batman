package org.wanwanframework.javacompile;

import java.io.IOException;

import org.wanwanframework.javacompile.core.Parser;
import org.wanwanframework.javacompile.lexer.Lexer;

public class MainApp {

	public static void main(String[] args) throws IOException {
		Lexer lex = new Lexer();
		Parser parse = new Parser(lex);
		parse.start();
		System.out.println();
		System.out.println("end...");
	}
}

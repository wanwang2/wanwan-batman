package org.wanwanframework.javacompile;

import java.io.IOException;

import org.wanwanframework.javacompile.lexer.Lexer;
import org.wanwanframework.javacompile.parser.Parser;

public class MainApp {

	public static void main(String[] args) throws IOException {
		Lexer lex = new Lexer();
		Parser parse = new Parser(lex);
		parse.start();
		System.out.println();
		System.out.println("end...");
	}
}

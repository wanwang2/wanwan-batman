package org.wanwanframework.javacompile;

import java.io.IOException;

import org.wanwanframework.javacompile.lexer.Lexer;
import org.wanwanframework.javacompile.parser.Parser;

public class Main {

	public Main() throws IOException {
		Lexer lex = new Lexer();
		Parser parse = new Parser(lex);
		parse.start();
		System.out.write('\n');
	}
}

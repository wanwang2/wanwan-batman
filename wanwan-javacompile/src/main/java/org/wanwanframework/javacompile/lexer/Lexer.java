package org.wanwanframework.javacompile.lexer;

import java.io.IOException;
import java.util.Hashtable;

/**
 * 词法扫描
 * @author coco
 *
 */
public class Lexer {

	public static int line = 1;
	
	private Hashtable<String, Word> words = new Hashtable<String, Word>();
	private char peekCurrentChar = ' ';

	private void add(Word word) {
		words.put(word.value, word);
	}
	
	public Lexer() {
		add(new Word("if", Tag.IF));
		add(new Word("else", Tag.ELSE));
		add(new Word("while", Tag.WHILE));
		add(new Word("do", Tag.DO));
		add(new Word("break", Tag.BREAK));

		add(Word.TRUE);
		add(Word.FALSE);
		add(Type.INT);
		add(Type.CHAR);
		add(Type.BOOL);
		add(Type.FLOAT);
	}

	public void readch() throws IOException {
		peekCurrentChar = (char) System.in.read();
	}

	public boolean readch(char c) throws IOException {
		readch();
		if (peekCurrentChar != c) { // 将peek缓存起来
			return false;
		}
		peekCurrentChar = ' '; // 默认取到了peek eq c 的字符，然后清除缓存
		return true;
	}

	public Token scan() throws IOException {
		for (;; readch()) {
			if (peekCurrentChar == ' ' || peekCurrentChar == '\t')
				continue;
			else if (peekCurrentChar == '\n')
				line = line + 1;
			else
				break;
		}
		switch (peekCurrentChar) {
		case '&':
			if (readch('&'))
				return Word.and;
			else
				return new Token('&');
		case '|':
			if (readch('|'))
				return Word.or;
			else
				return new Token('|');
		case '=':
			if (readch('='))
				return Word.eq;
			else
				return new Token('=');
		case '!':
			if (readch('='))
				return Word.ne;
			else
				return new Token('!');
		case '<':
			if (readch('='))
				return Word.le;
			else
				return new Token('<');
		case '>':
			if (readch('='))
				return Word.ge;
			else
				return new Token('>');
		}

		if (Character.isDigit(peekCurrentChar)) {
			int v = 0;
			do {
				v = 10 * v + Character.digit(peekCurrentChar, 10);
				readch();
			} while (Character.isDigit(peekCurrentChar));
			if (peekCurrentChar != '.')
				return new Num(v);
			float x = v;
			float d = 10;
			for (;;) {
				readch();
				if (!Character.isDigit(peekCurrentChar))
					break;
				x = x + Character.digit(peekCurrentChar, 10) / d;
				d = d * 10;
			}
			return new Real(x);
		}

		if (Character.isLetter(peekCurrentChar)) {
			StringBuffer b = new StringBuffer();
			do {
				b.append(peekCurrentChar);
				readch();

			} while (Character.isLetterOrDigit(peekCurrentChar));
			String s = b.toString();
			Word w = (Word) words.get(s);
			if (w != null)
				return w;
			w = new Word(s, Tag.ID);
			words.put(s, w);
			return w;
		}
		Token tok = new Token(peekCurrentChar);
		peekCurrentChar = ' ';
		return tok;
	}
}

package org.wanwanframework.javacompile.lexer;

import java.io.IOException;
import java.util.Hashtable;

public class Lexer {

	Hashtable<String, Word> words = new Hashtable<String, Word>();
	
	public void reverse(Word word) {
		words.put(word.lexeme, word);
	}
	
	public Lexer() {
		reverse(new Word("if", Tag.IF ));
		reverse(new Word("else", Tag.ELSE ));
		reverse(new Word("while", Tag.WHILE ));
		reverse(new Word("do", Tag.DO ));
		reverse(new Word("break", Tag.BREAK ));
		
		reverse(Word.TRUE);
		reverse(Word.FALSE);
		reverse(Type.INT);
		reverse(Type.FLOAT);
	}
	
	char peek = ' ';
	
	public void readch() throws IOException {
		peek = (char) System.in.read();
	}
	
	public boolean readch(char c) throws IOException {
		readch();
		if(peek != c) { // 将peek缓存起来
			return false;
		}
		peek = ' '; // 默认取到了peek eq c 的字符，然后清除缓存
		return true;
	}
	
	public static int line = 1;
	
	public Token scan() throws IOException {
		for (;; readch()) {
			if(peek == ' ' || peek == '\t' ) continue;
			else if(peek == '\n') line = line + 1;
			else break;
		}
		switch (peek) {
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
		
		if(Character.isDigit(peek)) {
			int v = 0;
			do {
				v = 10 * v + Character.digit(peek, 10);readch();
			} while(Character.isDigit(peek));
			if(peek != '.') return new Num(v);
			float x = v; float d = 10;
			for(;;) {
				readch();
				if(! Character.isDigit(peek)) break;
				x = x + Character.digit(peek, 10) / d; d = d * 10;
			}
			return new Real(x);
		}
		
		if(Character.isLetter(peek)) {
			StringBuffer b = new StringBuffer();
			do {
				b.append(peek); readch();
				
			} while(Character.isLetterOrDigit(peek));
			String s = b.toString();
			Word w = (Word) words.get(s);
			if(w != null) return w;
			w = new Word(s, Tag.ID );
			words.put(s, w);
			return w;
		}
		Token tok = new Token(peek); peek = ' ';
		return tok;
	}
}

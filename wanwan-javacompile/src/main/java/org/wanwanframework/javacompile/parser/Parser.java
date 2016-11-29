package org.wanwanframework.javacompile.parser;

import java.io.IOException;

import org.wanwanframework.javacompile.inter.Access;
import org.wanwanframework.javacompile.inter.And;
import org.wanwanframework.javacompile.inter.Arith;
import org.wanwanframework.javacompile.inter.Array;
import org.wanwanframework.javacompile.inter.Break;
import org.wanwanframework.javacompile.inter.Constant;
import org.wanwanframework.javacompile.inter.Else;
import org.wanwanframework.javacompile.inter.Expr;
import org.wanwanframework.javacompile.inter.Id;
import org.wanwanframework.javacompile.inter.If;
import org.wanwanframework.javacompile.inter.Not;
import org.wanwanframework.javacompile.inter.Or;
import org.wanwanframework.javacompile.inter.Rel;
import org.wanwanframework.javacompile.inter.Seq;
import org.wanwanframework.javacompile.inter.Set;
import org.wanwanframework.javacompile.inter.SetElem;
import org.wanwanframework.javacompile.inter.Stmt;
import org.wanwanframework.javacompile.inter.Unary;
import org.wanwanframework.javacompile.inter.While;
import org.wanwanframework.javacompile.lexer.Lexer;
import org.wanwanframework.javacompile.lexer.Number;
import org.wanwanframework.javacompile.lexer.Token;
import org.wanwanframework.javacompile.lexer.Type;
import org.wanwanframework.javacompile.lexer.Word;
import org.wanwanframework.javacompile.symbols.Env;

/**
 * 语法分析器
 * @author coco
 *
 */
public class Parser {

	private Lexer lex;
	private Token look;
	Env top = null;
	public int used; // 使用空间
	
	public Parser(Lexer l) throws IOException {
		lex = l; 
		moveToScan();
	}
	
	/**
	 * 读取一个词
	 * @throws IOException
	 */
	private void moveToScan() throws IOException {
		look = lex.scan();
	}
	
	private void error(String s) { 
		throw new Error("near line " + Lexer.line + ":" + s);
	}
	
	/**
	 * 如果读取到一个词的类型是t
	 * @param t
	 * @throws IOException
	 */
	private void matchAndScan(int t) throws IOException {
		if(look.tag == t) {
			moveToScan();
		}
		else {
			error("syntax error");
		}
	}
	
	public void start() throws IOException {
		Stmt s = readBlock();
		int begin = s.newlabel(); 
		int after = s.newlabel();
		s.emitlabel(begin); 
		s.gen(begin, after);
		s.emitlabel(after);
	}
	
	/**
	 * 大括号
	 * @return
	 * @throws IOException
	 */
	public Stmt readBlock() throws IOException {
		matchAndScan('{');
		Env savedEnv = top;
		top = new Env(top);
		decls(); 
		Stmt s = stmts();
		matchAndScan('}');
		top = savedEnv;
		return s;
	}
	
	public void decls() throws IOException {
		while(look.tag == Token.TagKey.BASIC.ordinal()) {
			Type p = type(); 
			Token tok = look; 
			matchAndScan(Token.TagKey.ID.ordinal());
			matchAndScan(';');
			Id id = new Id((Word) tok, p, used);
			top.put(tok, id);
			used = used + p.width;
		}
	}
	
	public Type type() throws IOException {
		Type p = (Type)look;
		matchAndScan(Token.TagKey.BASIC.ordinal());
		if(look.tag != '[') return p;
		else return dims(p);
	}
	
	public Type dims(Type p) throws IOException {
		matchAndScan('[');
		Token tok = look;
		matchAndScan(Token.TagKey.NUM.ordinal());
		matchAndScan(']');
		if(look.tag == '[')
			p = dims(p);
		return new Array(((Number)tok).value, p);
	}
	
	public Stmt stmts() throws IOException {
		if(look.tag == '}') {
			return Stmt.Null;
		} else {
			return new Seq(stmt(), stmts());
		}
	}
	
	public Stmt stmt() throws IOException {
		Expr x; Stmt s, s1, s2;
		Stmt savedStmt;
		if(look.tag == ';') {
			moveToScan();
			return Stmt.Null;
		} else if(look.tag == Token.TagKey.IF.ordinal()) {
			matchAndScan(Token.TagKey.IF.ordinal());
			matchAndScan('(');
			x = bool();
			matchAndScan(')');
			s1 = stmt();
			if(look.tag != Token.TagKey.ELSE.ordinal()) {
				return new If(x, s1);
			}
			matchAndScan(Token.TagKey.ELSE.ordinal());
			s2 = stmt();
			return new Else(x, s1, s2);
		} else if(look.tag == Token.TagKey.WHILE.ordinal()) {
			While whilenode = new While();
			savedStmt = Stmt.Enclosing;
			Stmt.Enclosing = whilenode;
			matchAndScan(Token.TagKey.WHILE.ordinal());
			matchAndScan('(');
			x = bool();
			matchAndScan(')');
			s1 = stmt();
			whilenode.init(x, s1);
			Stmt.Enclosing = savedStmt;
			return whilenode;
		} else if(look.tag == Token.TagKey.BREAK.ordinal()) {
			matchAndScan(Token.TagKey.BREAK.ordinal());
			matchAndScan(';');
			return new Break();
		} else if(look.tag == '{') {
			return readBlock();
		}
		return assign();
	}
	
	/**
	 * 赋值语句
	 * @return
	 * @throws IOException
	 */
	public Stmt assign() throws IOException {
		Stmt stmt;
		Token t = look;
		matchAndScan(Token.TagKey.ID.ordinal());
		Id id = top.get(t);
		if(id == null) error(t.toString() + " undeclared");
		if(look.tag == '=') {
			moveToScan();
			stmt = new Set(id, bool());
		} else {
			Access x = offset(id);
			matchAndScan('=');
			stmt = new SetElem(x, bool());
		}
		matchAndScan(';');
		return stmt;
	}
	
	Expr bool() throws IOException {
		Expr x = join();
		while(look.tag == Token.TagKey.OR.ordinal()) {
			Token tok = look;
			moveToScan();
			x = new Or(tok, x, join());
		}
		return x;
	}
	
	Expr join() throws IOException {
		Expr x = equality();
		while(look.tag == Token.TagKey.AND.ordinal()) {
			Token tok = look;
			moveToScan();
			x = new And(tok, x, equality());
		}
		return x;
	}
	
	Expr equality() throws IOException {
		Expr x = equality();
		while(look.tag == Token.TagKey.EQ.ordinal()) {
			Token tok = look;
			moveToScan();
			x = new Rel(tok, x, rel());
		}
		return x;
	}
	
	Expr rel() throws IOException {
		Expr x = expr();
		if(look.tag == '<' ||
				look.tag == Token.TagKey.LE.ordinal() ||
				look.tag == Token.TagKey.GE.ordinal() ||
				look.tag == '>') {
			Token tok = look;
			moveToScan();
			return new Rel(tok, x, expr());
		}
		return x;
	}
	
	Expr expr() throws IOException {
		Expr x = term();
		while(look.tag == '+' || look.tag == '-') {
			Token tok = look; 
			moveToScan(); 
			x = new Arith(tok, x, term());
		}
		return x;
	}
	
	Expr term() throws IOException {
		Expr x = unary();
		while(look.tag == '*' || look.tag == '/') {
			Token tok = look;
			moveToScan();
			x = new Arith(tok, x, unary());
		}
		return x;
	}
	
	Expr unary() throws IOException {
		if(look.tag == '-') {
			moveToScan();
			return new Unary(Word.minus, unary());
		}
		else if(look.tag == '!') {
			Token tok = look;
			moveToScan();
			return new Not(tok, unary());
		}
		return factor();
	}
	
	/**
	 * 数组地址计算
	 * @return
	 * @throws IOException
	 */
	public Expr factor() throws IOException {
		Expr x = null;
		if (look.tag == '(') {
			moveToScan();
			x = bool();
			matchAndScan(')');
			return x;
		} else if (look.tag == Token.TagKey.NUM.ordinal()) {
			x = new Constant(look, Type.INT);
			moveToScan();
			return x;
		} else if (look.tag == Token.TagKey.REAL.ordinal()) {
			x = new Constant(look, Type.FLOAT);
			moveToScan();
			return x;
		} else if (look.tag == Token.TagKey.TRUE.ordinal()) {
			x = Constant.True;
			moveToScan();
			return x;
		} else if (look.tag == Token.TagKey.FALSE.ordinal()) {
			x = Constant.False;
			moveToScan();
			return x;
		} else if (look.tag == Token.TagKey.ID.ordinal()) {
			//String s = look.toString();
			Id id = top.get(look);
			if (id == null)
				error(look.toString() + " undeclared");
			moveToScan();
			if (look.tag != '[') {
				return id;
			} else {
				return offset(id);
			}
		} else {
			error("systax error");
			return x;
		}
	}
	
	public Access offset(Id a) throws IOException {
		Expr i; Expr w; Expr t1, t2; Expr loc;
		Type type = a.type;
		matchAndScan('[');
		i = bool(); 
		matchAndScan(']');
		type = ((Array)type).of;
		w = new Constant(type.width);
		t1 = new Arith(new Token('*'), i, w);
		loc = t1;
		while(look.tag == '[') {
			matchAndScan('['); 
			i = bool(); 
			matchAndScan(']');
			type = ((Array)type).of;
			w = new Constant(type.width);
			t1 = new Arith(new Token('*'), i, w);
			t2 = new Arith(new Token('+'), loc, t1); 
			loc = t2;
		}
		return new Access(a, loc, type);
	}
}

package lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class Lexer {
	
	private static final char EOF_CHAR = (char) -1;
	private static int line = 1;
	private BufferedReader reader;
	private char peek;
	private Hashtable<String, Tag> keywords;
	
	public Lexer(File file) {
		try {
			this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.peek = ' ';
		keywords = new Hashtable<String,Tag>();
		keywords.put("programa", Tag.PROGRAM);
		keywords.put("inicio", Tag.BEGIN);
		keywords.put("fim", Tag.END);
		keywords.put("inteiro", Tag.INT);
		keywords.put("real", Tag.REAL);
		keywords.put("booleano", Tag.BOOL);
		keywords.put("verdadeiro", Tag.TRUE);
		keywords.put("falso", Tag.FALSE);
		keywords.put("leia", Tag.READ);
		keywords.put("escreva", Tag.WRITE);
	}
	
	public static int line() {
		return line;
	}
	
	private char nextChar() {
		if (peek == '\n') line++;
		try {
			peek = (char) reader.read();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return peek;
	}
	
	private static boolean isWhitespace(int c) {
		
		switch (c) {
		case ' ': case '\t': case '\n' : case '\r':
			return true;
		default:	
			return false;
		}
	}
	
	private static boolean isIdStart(int c) {
		return (Character.isAlphabetic(c) || c == '_');
	}
	private static boolean isIdPart(int c) {
		return (isIdStart(c) || Character.isDigit(c));
	}
	
	public Token nextToken() {
		while (isWhitespace(peek))
			nextChar();
		switch (peek) {
		case '=':
			nextChar();
			return new Token(Tag.ASSIGN);
		case '+':
			nextChar();
			return new Token(Tag.SUM);
		case '*':
			nextChar();
			return new Token(Tag.MUL);
		case '|':
			nextChar();
			return new Token(Tag.OR);
		case '<':
			nextChar();
			if (peek == '=') {
				nextChar();
				return new Token(Tag.LE);
			}
			return new Token(Tag.LT);
		case '>':
			nextChar();
			if (peek == '=') {
				nextChar();
				return new Token(Tag.GE);
			}
			return new Token(Tag.GT);
		case '&':
			nextChar();
			return new Token(Tag.LAND);
		case EOF_CHAR:
			return new Token(Tag.EOF);
		default:
			if (Character.isDigit(peek)) {
				String num = "";
				do {
					num+= peek;
					nextChar();
				} while (Character.isDigit(peek));
				if(peek == '.') {
					do {
						num+= peek;
						nextChar();
					} while (Character.isDigit(peek));
					return new Token(Tag.LIT_REAL, num);
				}
				return new Token(Tag.LIT_INT,num);
			} else if (isIdStart(peek)) {
				String id = "";
				do {
					id += peek;
					nextChar();
				} while (isIdPart(peek));
				if (keywords.containsKey(id)) 
					return new Token(keywords.get(id));
				
				return new Token(Tag.ID, id);
			}
		}
		
		String unk = String.valueOf(peek);
		nextChar();
		return new Token(Tag.UNK, unk);
		
	}
}

package lexer;

public class Token {
	private Tag tag;
	private String lexeme;
	
	public Token(Tag tag, String lexeme) {

		this.tag = tag;
		this.lexeme = lexeme;
	}

	public Token(Tag tag) {
		this(tag, null);
	}
	
	public Tag tag() {
		return tag;
	}
	
	public String lexeme() {
		return lexeme;
	}

	@Override
	public String toString() {
		String s = "<" + tag;
		if(lexeme != null)
			s+= ", '" + lexeme + "'";
		s += ">";
		return s;
	}

	
	
}

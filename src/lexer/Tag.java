package lexer;

public enum Tag {

	PROGRAM("PROGRAM"),
	BEGIN("BEGIN"), END("END"),
	INT("INT"), REAL("REAL"), BOOL ("BOOL"),
	TRUE("TRUE"), FALSE ("FALSE"),
	READ("READ"), WRITE("WRITE"),
	ASSIGN("ASSIGN"),
	SUM("SUM"),
	MUL("MUL"),
	OR("OR"),
	LT("LT"), LE("LE"), GT("GT"), GE("GE"),
	LAND("&"),
	LIT_INT("LIT_INT"),	LIT_REAL("LIT_REAL"),
	ID("ID"),
	EOF("EOF"), UNK("UNK");
	
	
	private String name;

	private Tag(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

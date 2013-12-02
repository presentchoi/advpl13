package codemodel.structure.type;

public enum UnaryOperatorType {
	PLUS_PLUS, MINUS_MINUS, NOT, REFER, PLUS, MINUS, BIT_REVERS;
	
	public String toString() {
		switch (this) {
		case PLUS_PLUS:
			return "++";
		case MINUS_MINUS:
			return "--";
		case NOT:
			return "!";
		case REFER:
			return "&";
		case PLUS:
			return "+";
		case MINUS:
			return "-";
		case BIT_REVERS:
			return "~";
		}
		return null;
	}
}

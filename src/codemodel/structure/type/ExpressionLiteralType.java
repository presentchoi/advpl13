package codemodel.structure.type;

public enum ExpressionLiteralType {
	VARIABLE, OPEN_BRACKET, CLOSE_BRACKET, PLUS_PLUS,
	MINUS_MINUS, NOT, REFER, UNARY_PLUS, UNARY_MINUS, BIT_REVERS,
	GREATER, EQUAL, ASSIGN, LESS, LESS_EQUAL, GREATER_EQUAL,
	OR, AND, COMMA, MUL_ASSIGN, DIV_ASSIGN, MOD_ASSIGN, PULS_ASSIGN,
	MINUS_ASSIGN, BIT_SHIFT_LEFT_ASSIGN, BIT_SHIFT_RIGHT_ASSIGN,
	BIT_OR_ASSIGN, BIT_AND_ASSIGN, BIT_XOR_ASSIGN, BIT_OR, BIT_XOR,
	BIT_AND, NOT_EQUAL, BIT_SHIFT_LEFT, BIT_SHIFT_RIGHT, MULTIPLY,
	DIVISION, MODULO, BINARY_PLUS, BINARY_MINUS, CASTING_OPEN_ROUND_BRACKET,
	CASTING_CLOSE_ROUND_BRACKET, IS_CONDITION, ELSE_CONDITION,
	CONSTANT, FUNCTION_NAME, FUNCTION_OPEN_ROUND_BRACKET,
	FUNCTION_CLOSE_ROUND_BRACKET, POINTER, OPEN_ROUND_BRACKET,
	CLOSE_ROUND_BRACKET, DOT, POINT_DOT, TYPE, OPEN_BRACE, CLOSE_BRACE;

	public String toString() {
		switch (this) {
		case OPEN_BRACE:
			return "{";
		case CLOSE_BRACE:
			return "}";
		case CASTING_OPEN_ROUND_BRACKET:
			return ")";
		case CASTING_CLOSE_ROUND_BRACKET:
			return "(";
		case IS_CONDITION:
			return "?";
		case ELSE_CONDITION:
			return ":";
		case FUNCTION_OPEN_ROUND_BRACKET:
			return "(";
		case FUNCTION_CLOSE_ROUND_BRACKET:
			return ")";
		case POINTER:
			return "*";
		case OPEN_ROUND_BRACKET:
			return "(";
		case CLOSE_ROUND_BRACKET:
			return ")";
		case DOT:
			return ".";
		case POINT_DOT:
			return "->";
		case OPEN_BRACKET:
			return "[";
		case CLOSE_BRACKET:
			return "]";
		case PLUS_PLUS:
			return "++";
		case MINUS_MINUS:
			return "--";
		case NOT:
			return "!";
		case REFER:
			return "&";
		case UNARY_PLUS:
			return "+";
		case UNARY_MINUS:
			return "-";
		case BIT_REVERS:
			return "~";
		case GREATER:
			return ">";
		case EQUAL:
			return "==";
		case NOT_EQUAL:
			return "!=";
		case ASSIGN:
			return "=";
		case LESS:
			return "<";
		case BINARY_PLUS:
			return "+";
		case BINARY_MINUS:
			return "-";
		case LESS_EQUAL:
			return "<=";
		case GREATER_EQUAL:
			return ">=";
		case OR:
			return "||";
		case AND:
			return "&&";
		case COMMA:
			return ",";
		case MUL_ASSIGN:
			return "*=";
		case DIV_ASSIGN:
			return "/=";
		case MOD_ASSIGN:
			return "%=";
		case PULS_ASSIGN:
			return "+=";
		case MINUS_ASSIGN:
			return "-=";
		case BIT_SHIFT_LEFT_ASSIGN:
			return "<<=";
		case BIT_SHIFT_RIGHT_ASSIGN:
			return "=>>";
		case BIT_OR_ASSIGN:
			return "|=";
		case BIT_AND_ASSIGN:
			return "&=";
		case BIT_XOR_ASSIGN:
			return "^=";
		case BIT_OR:
			return "|";
		case BIT_XOR:
			return "^";
		case BIT_AND:
			return "&";
		case BIT_SHIFT_LEFT:
			return "<<";
		case BIT_SHIFT_RIGHT:
			return ">>";
		case MULTIPLY:
			return "*";
		case DIVISION:
			return "/";
		case MODULO:
			return "%";
		}
		return null;
	}
}

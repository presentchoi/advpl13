package codemodel.structure.type;

public enum ExpressionType {
	ARRAY, BINARY, CONDITION, CONSTANT, FUNCTION_CALL, ROUND_BRACKET, UNARY, VARIABLE, CAST, NULL, BLOCK, STRUCT, POINTER, ALL;

	public String toString() {
		switch (this) {
		case ARRAY:
			return "ARRAY";
		case BINARY:
			return "BINARY";
		case CONDITION:
			return "CONDITION";
		case CONSTANT:
			return "CONSTANT";
		case FUNCTION_CALL:
			return "FUNCTION_CALL";
		case ROUND_BRACKET:
			return "ROUND_BRACKET";
		case UNARY:
			return "UNARY";
		case VARIABLE:
			return "VARIABLE";
		case CAST:
			return "CAST";
		case BLOCK:
			return "BLOCK";
		case STRUCT:
			return "STRUCT";
		case NULL:
			return "";
		case POINTER:
			return "*";
		case ALL:
			return "ALL";
		}
		return null;
	}
}

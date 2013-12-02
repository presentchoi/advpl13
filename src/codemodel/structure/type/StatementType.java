package codemodel.structure.type;

public enum StatementType {
	BLOCK, NONCONDITION, CONDITION, IF, FOR, WHILE, SWITCH, LABLE, JUMP, EXPRESSION, INSTRUMENT, INSTRUMENT_BLOCK, DO_WHILE;

	public String toString() {
		String typeString = null;
		switch (this) {
		case BLOCK:
			typeString = "BLOCK";
			break;
		case NONCONDITION:
			typeString = "NONCONDITION";
			break;
		case CONDITION:
			typeString = "CONDITION";
			break;
		case IF:
			typeString = "if";
			break;
		case FOR:
			typeString = "for";
			break;
		case WHILE:
			typeString = "while";
			break;
		case DO_WHILE:
			typeString = "do";
			break;
		case SWITCH:
			typeString = "switch";
			break;
		case LABLE:
			typeString = "LABLE";
			break;
		case JUMP:
			typeString = "JUMP";
			break;
		case EXPRESSION:
			typeString = "EXPRESSION";
			break;
		case INSTRUMENT:
			typeString = "INSTRUMENT";
			break;
		case INSTRUMENT_BLOCK:
			typeString = "INSTRUMENT";
			break;
		}
		return typeString;
	}
}

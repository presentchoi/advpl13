package codemodel.structure.type;

public enum BinaryOperatorType {
	GREATER, EQUAL, ASSIGN, LESS, PLUS, MINUS, LESS_EQUAL, GREATER_EQUAL, OR, AND, COMMA, MUL_ASSIGN, DIV_ASSIGN, MOD_ASSIGN, PULS_ASSIGN, MINUS_ASSIGN, BIT_SHIFT_LEFT_ASSIGN, BIT_SHIFT_RIGHT_ASSIGN, BIT_OR_ASSIGN, BIT_AND_ASSIGN, BIT_XOR_ASSIGN, BIT_OR, BIT_XOR, BIT_AND, NOT_EQUAL, BIT_SHIFT_LEFT, BIT_SHIFT_RIGHT, MULTIPLY, DIVISION, MODULO;

	public String toString() {
		switch (this) {
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
		case PLUS:
			return "+";
		case MINUS:
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

	public boolean isAssignOperator() {
		switch (this) {
		case MUL_ASSIGN:
			return true;
		case DIV_ASSIGN:
			return true;
		case MOD_ASSIGN:
			return true;
		case PULS_ASSIGN:
			return true;
		case MINUS_ASSIGN:
			return true;
		case BIT_SHIFT_LEFT_ASSIGN:
			return true;
		case BIT_SHIFT_RIGHT_ASSIGN:
			return true;
		case BIT_OR_ASSIGN:
			return true;
		case BIT_AND_ASSIGN:
			return true;
		case BIT_XOR_ASSIGN:
			return true;
		}
		return false;
	}

	public boolean isAdditiveOperator() {
		switch (this) {
		case PLUS:
			return true;
		case MINUS:
			return true;
		}
		return false;
	}

	public boolean isMultiplicativeOperator() {
		switch (this) {
		case DIVISION:
			return true;
		case MULTIPLY:
			return true;
		case MODULO:
			return true;
		}
		return false;
	}

	public boolean isCalculativeOperator() {
		if (isAdditiveOperator() || isMultiplicativeOperator()) {
			return true;
		}
		return false;
	}

	public boolean isComparativeOperator() {
		switch (this) {
		case EQUAL:
			return true;
		case NOT_EQUAL:
			return true;
		case GREATER:
			return true;
		case GREATER_EQUAL:
			return true;
		case LESS:
			return true;
		case LESS_EQUAL:
			return true;
		}
		return false;
	}

	public boolean isLogicalOperator() {
		switch (this) {
		case AND:
			return true;
		case OR:
			return true;
		}
		return false;
	}

	public String getName() {
		switch (this) {
		case GREATER:
			return "GREATER";
		case EQUAL:
			return "EQUAL";
		case NOT_EQUAL:
			return "NOT_EQUAL";
		case ASSIGN:
			return "ASSIGN";
		case LESS:
			return "LESS";
		case PLUS:
			return "PLUS";
		case MINUS:
			return "MINUS";
		case LESS_EQUAL:
			return "LESS_EQUAL";
		case GREATER_EQUAL:
			return "GREATER_EQUAL";
		case OR:
			return "OR";
		case AND:
			return "AND";
		case COMMA:
			return "COMMA";
		case MUL_ASSIGN:
			return "MUL_ASSIGN";
		case DIV_ASSIGN:
			return "DIV_ASSIGN";
		case MOD_ASSIGN:
			return "MOD_ASSIGN";
		case PULS_ASSIGN:
			return "PULS_ASSIGN";
		case MINUS_ASSIGN:
			return "MINUS_ASSIGN";
		case BIT_SHIFT_LEFT_ASSIGN:
			return "BIT_SHIFT_LEFT_ASSIGN";
		case BIT_SHIFT_RIGHT_ASSIGN:
			return "BIT_SHIFT_RIGHT_ASSIGN";
		case BIT_OR_ASSIGN:
			return "BIT_OR_ASSIGN";
		case BIT_AND_ASSIGN:
			return "BIT_AND_ASSIGN";
		case BIT_XOR_ASSIGN:
			return "BIT_XOR_ASSIGN";
		case BIT_OR:
			return "BIT_OR";
		case BIT_XOR:
			return "BIT_XOR";
		case BIT_AND:
			return "BIT_AND";
		case BIT_SHIFT_LEFT:
			return "BIT_SHIFT_LEFT";
		case BIT_SHIFT_RIGHT:
			return "BIT_SHIFT_RIGHT";
		case MULTIPLY:
			return "MULTIPLY";
		case DIVISION:
			return "DIVISION";
		case MODULO:
			return "MODULO";
		}
		return "";
	}
}

package codemodel.structure.exp;

import codemodel.structure.type.ExpressionLiteralType;

public class ExpressionLiteral {
	private String literal;
	private ExpressionLiteralType type;

	public void setLiteral(String literal) {
		this.literal = literal;
	}

	public String getLiteral() {
		return literal;
	}

	public void setType(ExpressionLiteralType type) {
		this.type = type;
	}

	public ExpressionLiteralType getType() {
		return type;
	}
}

package codemodel.structure.dcl;

import codemodel.structure.exp.Expression;
import codemodel.structure.exp.NullExpression;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;

public class Array {
	private Array nextArray;
	private Expression arrayExpression;
	private static final Array emptyArray = new Array(NullExpression.getInstance());

	public Array(Expression exp) {
		// TODO Auto-generated constructor stub
		arrayExpression = exp;
	}

	public int hasArrayNum() {
		// TODO Auto-generated method stub
		int result = 0;
		Array checkArray = this;
		while (checkArray != null) {
			result++;
			checkArray = checkArray.getNextArray();
		}
		return result;
	}

	public void setArrayExpression(Expression arrayExpression) {
		this.arrayExpression = arrayExpression;
	}

	public Expression getArrayExpression() {
		return arrayExpression;
	}

	public String toString() {
		if (getNextArray() != null) {
			return "[" + arrayExpression.toString() + "]"
					+ getNextArray().toString();
		}
		return "[" + arrayExpression.toString() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Array) {
			return ((Array) obj).getArrayExpression().toString().equals(
					arrayExpression.toString());
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;
		result = result * 31 + arrayExpression.hashCode();
		return result;
	}

	public Array makeClone() {
		// TODO Auto-generated method stub
		Array newInstance = new Array(arrayExpression.makeClone());
		if (getNextArray() != null) {
			newInstance.setNextArray(getNextArray().makeClone());
		}
		return newInstance;
	}

	public void setNextArray(Array nextArray) {
		this.nextArray = nextArray;
	}

	public Array getNextArray() {
		return nextArray;
	}

	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	public <T> T accept(CodeModelToTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	public void accept(CodeModelNoParamVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	public static Array getEmptyArray() {
		return emptyArray;
	}
}

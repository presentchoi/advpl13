package codemodel.structure.exp;

import java.util.ArrayList;
import java.util.List;

import codemodel.structure.dcl.StructType;
import codemodel.structure.dcl.VariableDeclaration;
import codemodel.structure.def.StructDefinition;
import codemodel.structure.type.ExpressionLiteralType;
import codemodel.structure.type.ExpressionType;
import codemodel.structure.type.UnaryOperatorType;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.ExpressionNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.ExpressionOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.ExpressionToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.ExpressionToTOneParamVisitor;


public class UnaryExpression extends Expression {
	private Expression operand;
	private UnaryOperatorType operator;
	private boolean isPostOperator;
	private String id;
	private boolean needUpdateId = true;

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof UnaryExpression) {
			return operand.equals(((UnaryExpression) obj).getOperand())
					&& operator.equals(((UnaryExpression) obj).getOperator())
					&& isPostOperator == (((UnaryExpression) obj)
							.isPostOperator());
		}
		return false;
	}

	@Override
	public List<StructDefinition> getStructDefinition() {
		// TODO Auto-generated method stub
		List<VariableDeclaration> temp = getVariableDeclaration();
		List<StructDefinition> result = new ArrayList<StructDefinition>();
		for (VariableDeclaration v : temp) {
			if (v.getType() instanceof StructType) {
				result.add(((StructType) v.getType()).getStruct());
			}
		}
		return result;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;
		result = result * 31 + operand.hashCode();
		result = result * 31 + operator.hashCode();
		if (isPostOperator) {
			result = result * 31 + 1;
		} else {
			result = result * 31 + 0;
		}
		return result;
	}

	@Override
	public ExpressionType getType() {
		// TODO Auto-generated method stub
		return ExpressionType.UNARY;
	}

	public void setOperand(Expression operand) {
		this.operand = operand;
		operand.setParentExpression(this);
		needUpdateId = true;
	}

	public Expression getOperand() {
		return operand;
	}

	public void setOperator(UnaryOperatorType operator) {
		this.operator = operator;
		needUpdateId = true;
	}

	public UnaryOperatorType getOperator() {
		return operator;
	}

	public void setPostOperator(boolean isPostOperator) {
		this.isPostOperator = isPostOperator;
		needUpdateId = true;
	}

	public boolean isPostOperator() {
		return isPostOperator;
	}

	public boolean isPreOperator() {
		return !isPostOperator;
	}

	public String toString() {
		String result = "";
		if (isPostOperator) {
			result = operand.toString() + operator.toString();
		} else {
			result = operator.toString() + operand.toString();
		}
		return result;
	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	@Override
	public List<String> toStringList() {
		// TODO Auto-generated method stub
		List<String> temp = new ArrayList<String>();
		if (isPostOperator) {
			temp.addAll(operand.toStringList());
			temp.add(operator.toString());
		} else {
			temp.add(operator.toString());
			temp.addAll(operand.toStringList());
		}
		return temp;
	}

	@Override
	public Expression makeClone() {
		// TODO Auto-generated method stub
		UnaryExpression exp = new UnaryExpression();
		exp.setOperand(operand.makeClone());
		exp.setOperator(operator);
		exp.setPostOperator(isPostOperator);
		return exp;
	}

	@Override
	public int getExpressionNum(ExpressionType type) {
		// TODO Auto-generated method stub
		int result = 0;
		if (type == getType()) {
			result++;
		}
		result += operand.getExpressionNum(type);
		return result;
	}

	@Override
	public List<Expression> getExpression(ExpressionType type) {
		// TODO Auto-generated method stub
		List<Expression> temp = new ArrayList<Expression>();
		if (type == getType() || type == ExpressionType.ALL) {
			temp.add(this);
		}
		temp.addAll(operand.getExpression(type));
		return temp;
	}

	@Override
	public Expression getAlternativeExpression() {
		// TODO Auto-generated method stub
		if (this.operator == UnaryOperatorType.NOT
				&& this.operand instanceof RoundBracketExpression) {
			return ((RoundBracketExpression) this.operand).getInnerExpression()
					.makeClone();
		}
		UnaryExpression notExp = new UnaryExpression();
		RoundBracketExpression bracketExp = new RoundBracketExpression();

		notExp.setOperator(UnaryOperatorType.NOT);
		notExp.setPostOperator(false);
		notExp.setOperand(bracketExp);

		bracketExp.setInnerExpression(this.makeClone());
		return notExp;
	}

	@Override
	public List<VariableDeclaration> getVariableDeclaration() {
		// TODO Auto-generated method stub
		List<VariableDeclaration> temp = new ArrayList<VariableDeclaration>();
		temp.addAll(operand.getVariableDeclaration());
		return temp;
	}

	@Override
	public List<ExpressionLiteral> toExpressionLiteralList() {
		// TODO Auto-generated method stub
		List<ExpressionLiteral> temp = new ArrayList<ExpressionLiteral>();
		if (isPostOperator) {
			temp.addAll(operand.toExpressionLiteralList());
			temp.add(toExpressionLiteral(operator));
		} else {
			temp.add(toExpressionLiteral(operator));
			temp.addAll(operand.toExpressionLiteralList());
		}
		return temp;
	}

	private ExpressionLiteral toExpressionLiteral(UnaryOperatorType operator) {
		// TODO Auto-generated method stub
		ExpressionLiteral newInstance = new ExpressionLiteral();
		switch (operator) {
		case PLUS_PLUS:
			newInstance.setType(ExpressionLiteralType.PLUS_PLUS);
			break;
		case MINUS_MINUS:
			newInstance.setType(ExpressionLiteralType.MINUS_MINUS);
			break;
		case NOT:
			newInstance.setType(ExpressionLiteralType.NOT);
			break;
		case REFER:
			newInstance.setType(ExpressionLiteralType.REFER);
			break;
		case PLUS:
			newInstance.setType(ExpressionLiteralType.UNARY_PLUS);
			break;
		case MINUS:
			newInstance.setType(ExpressionLiteralType.UNARY_MINUS);
			break;
		case BIT_REVERS:
			newInstance.setType(ExpressionLiteralType.BIT_REVERS);
			break;
		}
		newInstance.setLiteral(newInstance.getType().toString());
		return newInstance;
	}

	@Override
	public List<VariableDeclaration> getDeclareRequiredVariableDeclaration() {
		// TODO Auto-generated method stub
		List<VariableDeclaration> temp = new ArrayList<VariableDeclaration>();
		temp.addAll(operand.getDeclareRequiredVariableDeclaration());
		return temp;
	}

	@Override
	public <T> T accept(CodeModelToTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	@Override
	public void accept(CodeModelNoParamVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	@Override
	public <T, T1> T accept(CodeModelToTOneParamVisitor<T, T1> visitor, T1 data) {
		// TODO Auto-generated method stub
		return visitor.visit(this, data);
	}

	@Override
	public <T> T accept(ExpressionToTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	@Override
	public void accept(ExpressionNoParamVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	@Override
	public <T, T1> T accept(ExpressionToTOneParamVisitor<T, T1> visitor, T1 data) {
		// TODO Auto-generated method stub
		return visitor.visit(this, data);
	}

	@Override
	public <T> void accept(ExpressionOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		if (needUpdateId == true) {
			updateID();
			needUpdateId = false;
		}
		return id;
	}

	private void updateID() {
		int hashCode = hashCode();
		if (hashCode < 0) {
			id = "U_" + -hashCode;
		} else {
			id = "U" + hashCode;
		}
	}

	@Override
	public List<String> getIDs() {
		// TODO Auto-generated method stub
		List<String> newInstance = new ArrayList<String>();
		newInstance.add(getID());
		newInstance.addAll(getOperand().getIDs());
		return newInstance;
	}
}

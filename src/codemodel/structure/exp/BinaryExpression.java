package codemodel.structure.exp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import codemodel.structure.dcl.StructType;
import codemodel.structure.dcl.VariableDeclaration;
import codemodel.structure.def.StructDefinition;
import codemodel.structure.type.BinaryOperatorType;
import codemodel.structure.type.ExpressionLiteralType;
import codemodel.structure.type.ExpressionType;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.ExpressionNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.ExpressionOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.ExpressionToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.ExpressionToTOneParamVisitor;


public class BinaryExpression extends Expression {
	private Expression lhsOperand;
	private Expression rhsOperand;
	private BinaryOperatorType operator;
	private String id;
	private boolean needUpdateId = true;

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof BinaryExpression) {
			return lhsOperand.equals(((BinaryExpression) obj).getLhsOperand())
					&& rhsOperand.equals(((BinaryExpression) obj)
							.getRhsOperand())
					&& operator.equals(((BinaryExpression) obj).getOperator());
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;
		result = result * 31 + rhsOperand.hashCode();
		result = result * 31 + rhsOperand.hashCode();
		result = result * 31 + operator.hashCode();
		return result;
	}

	@Override
	public ExpressionType getType() {
		// TODO Auto-generated method stub
		return ExpressionType.BINARY;
	}

	public void setLhsOperand(Expression lhsOperand) {
		this.lhsOperand = lhsOperand;
		lhsOperand.setParentExpression(this);
		needUpdateId = true;
	}

	public Expression getLhsOperand() {
		return lhsOperand;
	}

	public void setRhsOperand(Expression rhsOperand) {
		this.rhsOperand = rhsOperand;
		rhsOperand.setParentExpression(this);
		needUpdateId = true;
	}

	public Expression getRhsOperand() {
		return rhsOperand;
	}

	public void setOperator(BinaryOperatorType operator) {
		this.operator = operator;
		needUpdateId = true;
	}

	public BinaryOperatorType getOperator() {
		return operator;
	}

	public String toString() {
		return lhsOperand.toString() + operator.toString()
				+ rhsOperand.toString();
	}

	@Override
	public List<String> toStringList() {
		// TODO Auto-generated method stub
		List<String> temp = lhsOperand.toStringList();
		temp.add(operator.toString());
		temp.addAll(rhsOperand.toStringList());
		return temp;
	}

	@Override
	public Expression makeClone() {
		// TODO Auto-generated method stub
		BinaryExpression exp = new BinaryExpression();
		exp.setOperator(operator);
		exp.setLhsOperand(lhsOperand.makeClone());
		exp.setRhsOperand(rhsOperand.makeClone());
		return exp;
	}

	@Override
	public int getExpressionNum(ExpressionType type) {
		// TODO Auto-generated method stub
		int result = 0;
		if (type == getType()) {
			result++;
		}
		result += lhsOperand.getExpressionNum(type);
		result += rhsOperand.getExpressionNum(type);
		return result;
	}

	@Override
	public List<Expression> getExpression(ExpressionType type) {
		// TODO Auto-generated method stub
		List<Expression> temp = new ArrayList<Expression>();
		if (type == getType() || type == ExpressionType.ALL) {
			temp.add(this);
		}
		temp.addAll(lhsOperand.getExpression(type));
		temp.addAll(rhsOperand.getExpression(type));
		return temp;
	}

	@Override
	public Expression getAlternativeExpression() {
		// TODO Auto-generated method stub
		Expression result = null;
		if (this.operator == BinaryOperatorType.GREATER) {
			result = getBinaryExpression(BinaryOperatorType.LESS_EQUAL);
		} else if (this.operator == BinaryOperatorType.GREATER_EQUAL) {
			result = getBinaryExpression(BinaryOperatorType.LESS);
		} else if (this.operator == BinaryOperatorType.LESS) {
			result = getBinaryExpression(BinaryOperatorType.GREATER_EQUAL);
		} else if (this.operator == BinaryOperatorType.LESS_EQUAL) {
			result = getBinaryExpression(BinaryOperatorType.GREATER);
		} else if (this.operator == BinaryOperatorType.EQUAL) {
			result = getBinaryExpression(BinaryOperatorType.NOT_EQUAL);
		} else if (this.operator == BinaryOperatorType.NOT_EQUAL) {
			result = getBinaryExpression(BinaryOperatorType.EQUAL);
		} else if (this.operator == BinaryOperatorType.AND) {
			result = getBinaryAlternativeExpression(BinaryOperatorType.OR);
		} else if (this.operator == BinaryOperatorType.OR) {
			result = getBinaryAlternativeExpression(BinaryOperatorType.AND);
		} else {
			result = this;
		}
		return result;
	}

	private Expression getBinaryExpression(BinaryOperatorType type) {
		BinaryExpression exp = new BinaryExpression();
		exp.setOperator(type);
		exp.setLhsOperand(lhsOperand.makeClone());
		exp.setRhsOperand(rhsOperand.makeClone());
		return exp;
	}

	private Expression getBinaryAlternativeExpression(BinaryOperatorType type) {
		BinaryExpression exp = new BinaryExpression();
		exp.setOperator(type);
		exp.setLhsOperand(lhsOperand.getAlternativeExpression());
		exp.setRhsOperand(rhsOperand.getAlternativeExpression());
		return exp;
	}

	// private Expression getNotExpression(Expression targetExpression) {
	// UnaryExpression notExp = new UnaryExpression();
	// RoundBracketExpression bracketExp = new RoundBracketExpression();
	//
	// notExp.setOperator(UnaryOperatorType.NOT);
	// notExp.setPostOperator(false);
	// notExp.setOperand(bracketExp);
	//
	// bracketExp.setInnerExpression(targetExpression);
	// return notExp;
	// }

	@Override
	public List<VariableDeclaration> getVariableDeclaration() {
		// TODO Auto-generated method stub
		List<VariableDeclaration> temp = new ArrayList<VariableDeclaration>();
		temp.addAll(lhsOperand.getVariableDeclaration());
		for (VariableDeclaration e : rhsOperand.getVariableDeclaration()) {
			if (!temp.contains(e)) {
				temp.add(e);
			}
		}
		return temp;
	}

	@Override
	public List<ExpressionLiteral> toExpressionLiteralList() {
		// TODO Auto-generated method stub
		List<ExpressionLiteral> temp = lhsOperand.toExpressionLiteralList();
		temp.add(toExpressionLiteral(operator));
		temp.addAll(rhsOperand.toExpressionLiteralList());
		return temp;
	}

	private ExpressionLiteral toExpressionLiteral(BinaryOperatorType operator) {
		// TODO Auto-generated method stub
		ExpressionLiteral newInstance = new ExpressionLiteral();
		switch (operator) {
		case GREATER:
			newInstance.setType(ExpressionLiteralType.GREATER);
			break;
		case EQUAL:
			newInstance.setType(ExpressionLiteralType.EQUAL);
			break;
		case NOT_EQUAL:
			newInstance.setType(ExpressionLiteralType.NOT_EQUAL);
			break;
		case ASSIGN:
			newInstance.setType(ExpressionLiteralType.ASSIGN);
			break;
		case LESS:
			newInstance.setType(ExpressionLiteralType.LESS);
			break;
		case PLUS:
			newInstance.setType(ExpressionLiteralType.BINARY_PLUS);
			break;
		case MINUS:
			newInstance.setType(ExpressionLiteralType.BINARY_MINUS);
			break;
		case LESS_EQUAL:
			newInstance.setType(ExpressionLiteralType.LESS_EQUAL);
			break;
		case GREATER_EQUAL:
			newInstance.setType(ExpressionLiteralType.GREATER_EQUAL);
			break;
		case OR:
			newInstance.setType(ExpressionLiteralType.OR);
			break;
		case AND:
			newInstance.setType(ExpressionLiteralType.AND);
			break;
		case COMMA:
			newInstance.setType(ExpressionLiteralType.COMMA);
			break;
		case MUL_ASSIGN:
			newInstance.setType(ExpressionLiteralType.MUL_ASSIGN);
			break;
		case DIV_ASSIGN:
			newInstance.setType(ExpressionLiteralType.DIV_ASSIGN);
			break;
		case MOD_ASSIGN:
			newInstance.setType(ExpressionLiteralType.MOD_ASSIGN);
			break;
		case PULS_ASSIGN:
			newInstance.setType(ExpressionLiteralType.PULS_ASSIGN);
			break;
		case MINUS_ASSIGN:
			newInstance.setType(ExpressionLiteralType.MINUS_ASSIGN);
			break;
		case BIT_SHIFT_LEFT_ASSIGN:
			newInstance.setType(ExpressionLiteralType.BIT_SHIFT_LEFT_ASSIGN);
			break;
		case BIT_SHIFT_RIGHT_ASSIGN:
			newInstance.setType(ExpressionLiteralType.BIT_SHIFT_RIGHT_ASSIGN);
			break;
		case BIT_OR_ASSIGN:
			newInstance.setType(ExpressionLiteralType.BIT_OR_ASSIGN);
			break;
		case BIT_AND_ASSIGN:
			newInstance.setType(ExpressionLiteralType.BIT_AND_ASSIGN);
			break;
		case BIT_XOR_ASSIGN:
			newInstance.setType(ExpressionLiteralType.BIT_XOR_ASSIGN);
			break;
		case BIT_OR:
			newInstance.setType(ExpressionLiteralType.BIT_OR);
			break;
		case BIT_XOR:
			newInstance.setType(ExpressionLiteralType.BIT_XOR);
			break;
		case BIT_AND:
			newInstance.setType(ExpressionLiteralType.BIT_AND);
			break;
		case BIT_SHIFT_LEFT:
			newInstance.setType(ExpressionLiteralType.BIT_SHIFT_LEFT);
			break;
		case BIT_SHIFT_RIGHT:
			newInstance.setType(ExpressionLiteralType.BIT_SHIFT_RIGHT);
			break;
		case MULTIPLY:
			newInstance.setType(ExpressionLiteralType.MULTIPLY);
			break;
		case DIVISION:
			newInstance.setType(ExpressionLiteralType.DIVISION);
			break;
		case MODULO:
			newInstance.setType(ExpressionLiteralType.MODULO);
			break;
		}
		newInstance.setLiteral(newInstance.getType().toString());
		return newInstance;
	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
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
	public List<VariableDeclaration> getDeclareRequiredVariableDeclaration() {
		// TODO Auto-generated method stub
		List<VariableDeclaration> temp = new ArrayList<VariableDeclaration>();

		temp.addAll(lhsOperand.getDeclareRequiredVariableDeclaration());
		for (VariableDeclaration e : rhsOperand
				.getDeclareRequiredVariableDeclaration()) {
			if (e == null) break;
			if (!temp.contains(e)) {
				temp.add(e);
			}
		}
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
			id = "Bo_" + -hashCode;
		} else {
			id = "Bo" + hashCode;
		}
	}

	@Override
	public List<String> getIDs() {
		// TODO Auto-generated method stub
		List<String> newInstance = new ArrayList<String>();
		newInstance.add(getID());
		newInstance.addAll(getLhsOperand().getIDs());
		newInstance.addAll(getRhsOperand().getIDs());
		return newInstance;
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
}

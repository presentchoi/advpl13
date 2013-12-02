package codemodel.structure.exp;

import java.util.ArrayList;
import java.util.List;

import codemodel.structure.dcl.StructType;
import codemodel.structure.dcl.VariableDeclaration;
import codemodel.structure.def.StructDefinition;
import codemodel.structure.type.ExpressionLiteralType;
import codemodel.structure.type.ExpressionType;
import codemodel.structure.type.StructOperatorType;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.ExpressionNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.ExpressionOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.ExpressionToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.ExpressionToTOneParamVisitor;


public class StructExpression extends Expression {
	private Expression firstVariable;
	private Expression secondVariable;
	private StructOperatorType operator;
	private String id;
	private boolean needUpdateId = true;

	public void setFirstVariable(Expression firstVariable) {
		firstVariable.setParentExpression(this);
		this.firstVariable = firstVariable;
		needUpdateId = true;
	}

	public Expression getFirstVariable() {
		return firstVariable;
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

	public void setSecondVariable(Expression secondVariable) {
		secondVariable.setParentExpression(this);
		this.secondVariable = secondVariable;
		needUpdateId = true;
	}

	public Expression getSecondVariable() {
		return secondVariable;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof StructExpression) {
			return getFirstVariable().equals(
					((StructExpression) obj).getFirstVariable())
					&& getSecondVariable().equals(
							((StructExpression) obj).getSecondVariable());
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;
		result = result * 31 + getFirstVariable().hashCode();
		result = result * 31 + getOperator().hashCode();
		result = result * 31 + getSecondVariable().hashCode();
		return result;
	}

	@Override
	public ExpressionType getType() {
		// TODO Auto-generated method stub
		return ExpressionType.STRUCT;
	}

	public String toString() {
		return getFirstVariable().toString() + getOperator().toString()
				+ getSecondVariable().toString();
	}

	@Override
	public List<String> toStringList() {
		// TODO Auto-generated method stub
		List<String> temp = getFirstVariable().toStringList();
		temp.add(getOperator().toString());
		temp.addAll(getSecondVariable().toStringList());
		return temp;
	}

	@Override
	public Expression makeClone() {
		// TODO Auto-generated method stub
		StructExpression exp = new StructExpression();
		exp.setFirstVariable(firstVariable.makeClone());
		exp.setSecondVariable(secondVariable.makeClone());
		exp.setOperator(operator);
		return exp;
	}

	@Override
	public int getExpressionNum(ExpressionType type) {
		// TODO Auto-generated method stub
		int result = 0;
		if (type == getType()) {
			result++;
		}
		result += firstVariable.getExpressionNum(type);
		result += secondVariable.getExpressionNum(type);
		return result;
	}

	@Override
	public List<Expression> getExpression(ExpressionType type) {
		// TODO Auto-generated method stub
		List<Expression> temp = new ArrayList<Expression>();
		if (type == getType() || type == ExpressionType.ALL) {
			temp.add(this);
		}
		temp.addAll(firstVariable.getExpression(type));
		temp.addAll(secondVariable.getExpression(type));
		return temp;
	}

	@Override
	public Expression getAlternativeExpression() {
		// TODO Auto-generated method stub
		return makeClone();
	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	@Override
	public List<VariableDeclaration> getVariableDeclaration() {
		// TODO Auto-generated method stub
		List<VariableDeclaration> temp = new ArrayList<VariableDeclaration>();
		temp.addAll(firstVariable.getVariableDeclaration());
		temp.addAll(secondVariable.getVariableDeclaration());
		return temp;
	}

	public void setOperator(StructOperatorType operator) {
		this.operator = operator;
	}

	public StructOperatorType getOperator() {
		return operator;
	}

	@Override
	public List<ExpressionLiteral> toExpressionLiteralList() {
		// TODO Auto-generated method stub
		List<ExpressionLiteral> temp = getFirstVariable()
				.toExpressionLiteralList();
		temp.add(toExpressionLiteral(operator));
		temp.addAll(getSecondVariable().toExpressionLiteralList());
		return temp;
	}

	private ExpressionLiteral toExpressionLiteral(StructOperatorType operator) {
		// TODO Auto-generated method stub
		ExpressionLiteral newInstance = new ExpressionLiteral();
		switch (operator) {
		case DOT:
			newInstance.setType(ExpressionLiteralType.DOT);
			break;
		case POINT_DOT:
			newInstance.setType(ExpressionLiteralType.POINT_DOT);
			break;
		}
		newInstance.setLiteral(newInstance.getType().toString());
		return newInstance;
	}

	@Override
	public List<VariableDeclaration> getDeclareRequiredVariableDeclaration() {
		// TODO Auto-generated method stub
		List<VariableDeclaration> temp = new ArrayList<VariableDeclaration>();
		temp.addAll(firstVariable.getDeclareRequiredVariableDeclaration());
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
			id = "S_" + -hashCode;
		} else {
			id = "S" + hashCode;
		}
	}

	@Override
	public List<String> getIDs() {
		// TODO Auto-generated method stub
		List<String> newInstance = new ArrayList<String>();
		newInstance.add(getID());
		newInstance.addAll(getSecondVariable().getIDs());
		return newInstance;
	}
}

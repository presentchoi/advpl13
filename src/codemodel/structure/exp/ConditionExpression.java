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


public class ConditionExpression extends Expression {

	public static final String ELSE_CONDITION = ":";
	public static final String IS_CONDITION = "?";
	private Expression trueConditionExpression;
	private Expression falseConditionExpression;
	private Expression checkConditionExpression;
	private String id;
	private boolean needUpdateId = true;

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof ConditionExpression) {
			return trueConditionExpression.equals(((ConditionExpression) obj)
					.getTrueConditionExpression())
					&& falseConditionExpression
							.equals(((ConditionExpression) obj)
									.getFalseConditionExpression())
					&& checkConditionExpression
							.equals(((ConditionExpression) obj)
									.getCheckConditionExpression());
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
		result = result * 31 + trueConditionExpression.hashCode();
		result = result * 31 + falseConditionExpression.hashCode();
		result = result * 31 + checkConditionExpression.hashCode();
		return result;
	}

	@Override
	public ExpressionType getType() {
		// TODO Auto-generated method stub
		return ExpressionType.CONDITION;
	}

	public void setTrueConditionExpression(Expression trueConditionExpression) {
		this.trueConditionExpression = trueConditionExpression;
		needUpdateId = true;
	}

	public Expression getTrueConditionExpression() {
		return trueConditionExpression;
	}

	public void setFalseConditionExpression(Expression falseConditionExpression) {
		this.falseConditionExpression = falseConditionExpression;
		needUpdateId = true;
	}

	public Expression getFalseConditionExpression() {
		return falseConditionExpression;
	}

	public void setCheckConditionExpression(Expression checkConditionExpression) {
		this.checkConditionExpression = checkConditionExpression;
		needUpdateId = true;
	}

	public Expression getCheckConditionExpression() {
		return checkConditionExpression;
	}

	public String toString() {
		return checkConditionExpression.toString() + " " + IS_CONDITION + " "
				+ trueConditionExpression.toString() + " " + ELSE_CONDITION
				+ " " + falseConditionExpression.toString();
	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
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
	public List<String> toStringList() {
		// TODO Auto-generated method stub
		List<String> temp = checkConditionExpression.toStringList();
		temp.add(IS_CONDITION);
		temp.addAll(trueConditionExpression.toStringList());
		temp.add(ELSE_CONDITION);
		temp.addAll(falseConditionExpression.toStringList());
		return temp;
	}

	@Override
	public Expression makeClone() {
		// TODO Auto-generated method stub
		ConditionExpression exp = new ConditionExpression();
		exp.setCheckConditionExpression(checkConditionExpression.makeClone());
		exp.setFalseConditionExpression(falseConditionExpression.makeClone());
		exp.setTrueConditionExpression(trueConditionExpression.makeClone());
		return exp;
	}

	@Override
	public int getExpressionNum(ExpressionType type) {
		// TODO Auto-generated method stub
		int result = 0;
		if (type == getType()) {
			result++;
		}
		result += checkConditionExpression.getExpressionNum(type);
		result += trueConditionExpression.getExpressionNum(type);
		result += falseConditionExpression.getExpressionNum(type);
		return result;
	}

	@Override
	public List<Expression> getExpression(ExpressionType type) {
		// TODO Auto-generated method stub
		List<Expression> temp = new ArrayList<Expression>();
		if (type == getType() || type == ExpressionType.ALL) {
			temp.add(this);
		}
		temp.addAll(checkConditionExpression.getExpression(type));
		temp.addAll(trueConditionExpression.getExpression(type));
		temp.addAll(falseConditionExpression.getExpression(type));
		return temp;
	}

	@Override
	public Expression getAlternativeExpression() {
		// TODO Auto-generated method stub
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
		temp.addAll(checkConditionExpression.getVariableDeclaration());
		for (VariableDeclaration e : trueConditionExpression
				.getVariableDeclaration()) {
			if (!temp.contains(e)) {
				temp.add(e);
			}
		}
		for (VariableDeclaration e : falseConditionExpression
				.getVariableDeclaration()) {
			if (!temp.contains(e)) {
				temp.add(e);
			}
		}
		return temp;
	}

	@Override
	public List<ExpressionLiteral> toExpressionLiteralList() {
		// TODO Auto-generated method stub
		List<ExpressionLiteral> temp = checkConditionExpression
				.toExpressionLiteralList();

		ExpressionLiteral isCondition = new ExpressionLiteral();
		isCondition.setType(ExpressionLiteralType.IS_CONDITION);
		isCondition.setLiteral(ExpressionLiteralType.IS_CONDITION.toString());

		temp.addAll(trueConditionExpression.toExpressionLiteralList());

		ExpressionLiteral elseCondition = new ExpressionLiteral();
		elseCondition.setType(ExpressionLiteralType.ELSE_CONDITION);
		elseCondition.setLiteral(ExpressionLiteralType.ELSE_CONDITION
				.toString());

		temp.addAll(falseConditionExpression.toExpressionLiteralList());

		return temp;
	}

	@Override
	public List<VariableDeclaration> getDeclareRequiredVariableDeclaration() {
		// TODO Auto-generated method stub
		List<VariableDeclaration> temp = new ArrayList<VariableDeclaration>();
		temp.addAll(checkConditionExpression
				.getDeclareRequiredVariableDeclaration());
		for (VariableDeclaration e : trueConditionExpression
				.getDeclareRequiredVariableDeclaration()) {
			if (!temp.contains(e)) {
				temp.add(e);
			}
		}
		for (VariableDeclaration e : falseConditionExpression
				.getDeclareRequiredVariableDeclaration()) {
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
			id = "Cn_" + -hashCode;
		} else {
			id = "Cn" + hashCode;
		}
	}

	@Override
	public List<String> getIDs() {
		// TODO Auto-generated method stub
		List<String> newInstance = new ArrayList<String>();
		newInstance.add(getID());
		newInstance.addAll(getCheckConditionExpression().getIDs());
		newInstance.addAll(getTrueConditionExpression().getIDs());
		newInstance.addAll(getFalseConditionExpression().getIDs());
		return newInstance;
	}
}

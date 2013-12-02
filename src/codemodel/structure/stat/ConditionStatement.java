package codemodel.structure.stat;

import codemodel.structure.exp.Expression;
import codemodel.structure.exp.NullExpression;

public abstract class ConditionStatement implements Statement {
	private Block conditionBlock;
	private Expression conditionExpression = NullExpression.getInstance();

	public void setConditionBlock(Block conditionBlock) {
		this.conditionBlock = conditionBlock;
	}

	public Block getConditionBlock() {
		return conditionBlock;
	}

	public void setConditionExpression(Expression conditionExpression) {
		this.conditionExpression = conditionExpression;
	}

	public Expression getConditionExpression() {
		return conditionExpression;
	}
}

package codemodel.structure.stat;

import java.io.PrintStream;

import codemodel.structure.type.StatementType;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.StatementNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.StatementOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.StatementToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.StatementToTOneParamVisitor;


public class WhileStatement extends ConditionStatement {
	@Override
	public StatementType getType() {
		// TODO Auto-generated method stub
		return StatementType.WHILE;
	}

	@Override
	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		StringBuffer form = new StringBuffer();
		form.append("while (");
		form.append(getConditionExpression().toString());
		form.append(")");
		out.println(prefix + form.toString());
		getConditionBlock().print(out, prefix);
	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor,
			T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
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
	public <T> T accept(StatementToTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	@Override
	public void accept(StatementNoParamVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	@Override
	public <T, T1> T accept(StatementToTOneParamVisitor<T, T1> visitor, T1 data) {
		// TODO Auto-generated method stub
		return visitor.visit(this, data);
	}

	@Override
	public <T> void accept(StatementOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	@Override
	public int getStatementNum(StatementType type) {
		// TODO Auto-generated method stub
		int result = 0;
		if (type == getType()) {
			result++;
		}
		result += getConditionBlock().getStatementNum(type);
		return result;
	}

	@Override
	public WhileStatement makeClone() {
		// TODO Auto-generated method stub
		WhileStatement newInstance = new WhileStatement();
		newInstance.setConditionBlock(getConditionBlock().makeClone());
		newInstance.setConditionExpression(getConditionExpression().makeClone());
		return newInstance;
	}

}

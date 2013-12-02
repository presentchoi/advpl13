package codemodel.structure.stat;

import java.io.PrintStream;

import codemodel.structure.type.StatementType;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;


public class DoWhileStatement extends WhileStatement {

	@Override
	public StatementType getType() {
		// TODO Auto-generated method stub
		return StatementType.DO_WHILE;
	}

	@Override
	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		StringBuffer form = new StringBuffer();
		out.println(prefix + "do");
		getConditionBlock().print(out, prefix);
		form.append("while (");
		form.append(getConditionExpression().toString());
		form.append(");");
		out.println(prefix + form.toString());
	}

	@Override
	public void accept(CodeModelNoParamVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
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
	public DoWhileStatement makeClone() {
		// TODO Auto-generated method stub
		DoWhileStatement newInstance = new DoWhileStatement();
		newInstance.setConditionBlock(getConditionBlock().makeClone());
		newInstance.setConditionExpression(getConditionExpression().makeClone());
		return newInstance;
	}
}

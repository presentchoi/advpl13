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


public class LabeledStatement extends NonconditionStatement {
	private String labelString;
	private Statement labeledStatement;

	@Override
	public StatementType getType() {
		// TODO Auto-generated method stub
		return StatementType.LABLE;
	}

	public void setLabeledStatement(Statement labeledStatement) {
		this.labeledStatement = labeledStatement;
	}

	public Statement getLabeledStatement() {
		return labeledStatement;
	}

	public void setLabelString(String labelString) {
		this.labelString = labelString;
	}

	public String getLabelString() {
		return labelString;
	}

	@Override
	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		StringBuffer form = new StringBuffer();
		form.append(prefix);
		form.append(labelString);
		form.append(" :");
		out.println(form.toString());
		labeledStatement.print(out, "\t" + prefix);
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
		result += labeledStatement.getStatementNum(type);
		return result;
	}

	@Override
	public LabeledStatement makeClone() {
		// TODO Auto-generated method stub
		LabeledStatement newInstance = new LabeledStatement();
		newInstance.setLabeledStatement(labeledStatement.makeClone());
		newInstance.setLabelString(labelString);
		return newInstance;
	}

    @Override
    public void printWithLineNum(PrintStream out, String prefix) {
        // TODO Auto-generated method stub
        StringBuffer form = new StringBuffer();
        form.append(getLineNum());
        form.append(prefix);
        form.append(labelString);
        form.append(" :");
        out.println(form.toString());
        labeledStatement.printWithLineNum(out, "\t" + prefix);
    }

}

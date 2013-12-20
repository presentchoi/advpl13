package codemodel.structure.stat;

import java.io.PrintStream;

import codemodel.structure.stat.NonconditionStatement;
import codemodel.structure.type.StatementType;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.StatementNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.StatementOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.StatementToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.StatementToTOneParamVisitor;


public class InstrumentStatement extends NonconditionStatement {
	private String statement;

	public InstrumentStatement(String statement) {
		// TODO Auto-generated constructor stub
		this.statement = statement;
	}

	public InstrumentStatement() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public StatementType getType() {
		// TODO Auto-generated method stub
		return StatementType.INSTRUMENT;
	}

	@Override
	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		out.println(prefix + getStatement());
	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
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

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getStatement() {
		return statement;
	}

	@Override
	public int getStatementNum(StatementType type) {
		// TODO Auto-generated method stub
		if (type == getType()) {
			return 1;
		}
		return 0;
	}

	@Override
	public InstrumentStatement makeClone() {
		// TODO Auto-generated method stub
		InstrumentStatement newInstance = new InstrumentStatement();
		newInstance.setStatement(statement);
		return newInstance;
	}

    @Override
    public void printWithLineNum(PrintStream out, String prefix) {
        // TODO Auto-generated method stub
        out.println(getLineNum() + prefix + getStatement());
    }
}

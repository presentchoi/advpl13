package codemodel.structure.stat;

import java.io.PrintStream;
import java.util.Vector;

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


public class InstrumentBlock implements NonconditionStatement {
	private Vector<InstrumentStatement> statements;
	private int nodeNum;

	public void setNodeNum(int nodeNum) {
		this.nodeNum = nodeNum;
	}

	public int getNodeNum() {
		return nodeNum;
	}

	public void setStatements(Vector<InstrumentStatement> statements) {
		this.statements = statements;
	}

	public void addStatement(InstrumentStatement statement) {
		this.statements.add(statement);
	}

	public Vector<InstrumentStatement> getStatements() {
		return statements;
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
	public StatementType getType() {
		// TODO Auto-generated method stub
		return StatementType.INSTRUMENT_BLOCK;
	}

	@Override
	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		for (InstrumentStatement s : statements) {
			s.print(out, prefix);
		}
	}

	@Override
	public int getStatementNum(StatementType type) {
		// TODO Auto-generated method stub
		if (type == getType()) {
			return 1;
		} else if (type == StatementType.INSTRUMENT) {
			return statements.size();
		}
		return 0;
	}

	@Override
	public InstrumentBlock makeClone() {
		// TODO Auto-generated method stub
		InstrumentBlock newInstance = new InstrumentBlock();
		newInstance.setNodeNum(nodeNum);
		newInstance.setStatements(new Vector<InstrumentStatement>(statements));
		return newInstance;
	}
}

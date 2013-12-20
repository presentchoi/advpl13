package codemodel.structure.stat;

import java.io.PrintStream;
import java.util.Vector;

import codemodel.structure.exp.Expression;
import codemodel.structure.exp.NullExpression;
import codemodel.structure.type.StatementType;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.StatementNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.StatementOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.StatementToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.StatementToTOneParamVisitor;


public class JumpStatement extends NonconditionStatement {
	private Vector<String> jumpLiteral = new Vector<String>();
	private Expression jumpExpression = NullExpression.getInstance();

	@Override
	public StatementType getType() {
		// TODO Auto-generated method stub
		return StatementType.JUMP;
	}

	public void setJumpLiteral(Vector<String> jumpLiteral) {
		this.jumpLiteral = jumpLiteral;
	}

	public Vector<String> getJumpLiteral() {
		return jumpLiteral;
	}

	public void addJumpLiteral(String jumpLiteral) {
		this.jumpLiteral.add(jumpLiteral);
	}

	public void setJumpExpression(Expression jumpExpression) {
		this.jumpExpression = jumpExpression;
	}

	public Expression getJumpExpression() {
		return jumpExpression;
	}

	@Override
	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		StringBuffer form = new StringBuffer();
		form.append(prefix);
		for (String s : jumpLiteral) {
			form.append(s);
		}
		form.append(" ");
		if (jumpExpression != null) {
			form.append(jumpExpression.toString());
		}
		form.append(";");
		out.println(form.toString());
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
		if (type == getType()) {
			return 1;
		}
		return 0;
	}

	@Override
	public JumpStatement makeClone() {
		// TODO Auto-generated method stub
		JumpStatement newInstance = new JumpStatement();
		newInstance.setJumpExpression(jumpExpression.makeClone());
		newInstance.setJumpLiteral(new Vector<String>(jumpLiteral));
		return newInstance;
	}

    @Override
    public void printWithLineNum(PrintStream out, String prefix) {
        // TODO Auto-generated method stub
        out.print(getLineNum());
        print(out, prefix);
    }

}

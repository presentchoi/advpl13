package codemodel.structure.stat;

import java.io.PrintStream;

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


public class IfStatement extends ConditionStatement {
	private IfStatement ifElse;

	@Override
	public StatementType getType() {
		// TODO Auto-generated method stub
		return StatementType.IF;
	}

	public void setIfElse(IfStatement ifElse) {
		this.ifElse = ifElse;
	}

	public IfStatement getIfElse() {
		return ifElse;
	}

	public boolean isIfElseStatement() {
		if (getConditionExpression() != null
				&& !(getConditionExpression() instanceof NullExpression)) {
			return true;
		}
		return false;
	}

	@Override
	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		StringBuffer form = new StringBuffer();
		form.append("if (");
		form.append(getConditionExpression().toString());
		form.append(")");
		out.println(prefix + form.toString());
		if (getConditionBlock() != null) {
			getConditionBlock().print(out, prefix);
		}

		if (ifElse != null) {
			ifElse.ifElsePrint(out, prefix);
		}
	}

	public void ifElsePrint(PrintStream out, String prefix) {
		StringBuffer form = new StringBuffer();
		if (isIfElseStatement()) {
			form.append("else if (");
			form.append(getConditionExpression().toString());
			form.append(")");
		} else {
			form.append("else");
		}
		out.println(prefix + form.toString());
		if (getConditionBlock() != null) {
			getConditionBlock().print(out, prefix);
		}

		if (ifElse != null) {
			ifElse.ifElsePrint(out, prefix);
		}
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

	public int getStatementNum(StatementType type) {
		// TODO Auto-generated method stub
		int result = 0;
		if (type == getType()) {
			result++;
		}
		result += getConditionBlock().getStatementNum(type);
		if (ifElse != null) {
			result += ifElse.getStatementNum(type);
		}
		return result;
	}

	/**
	 * This method makes shallow copied object
	 * 
	 * @return : shallow copied IfStatement object
	 */
	public IfStatement makeClone() {
		// TODO Auto-generated method stub
		IfStatement newData = new IfStatement();
		newData.setConditionBlock(this.getConditionBlock());
		newData.setConditionExpression(this.getConditionExpression());
		newData.setIfElse(this.ifElse);
		return newData;
	}

    @Override
    public void printWithLineNum(PrintStream out, String prefix) {
        // TODO Auto-generated method stub
        StringBuffer form = new StringBuffer();
        form.append(getLineNum());
        form.append("if (");
        form.append(getConditionExpression().toString());
        form.append(")");
        out.println(prefix + form.toString());
        if (getConditionBlock() != null) {
            getConditionBlock().printWithLineNum(out, prefix);
        }

        if (ifElse != null) {
            ifElse.ifElsePrintWithLineNum(out, prefix);
        }
    }

    public void ifElsePrintWithLineNum(PrintStream out, String prefix) {
        StringBuffer form = new StringBuffer();
        form.append(getLineNum());
        if (isIfElseStatement()) {
            form.append("else if (");
            form.append(getConditionExpression().toString());
            form.append(")");
        } else {
            form.append("else");
        }
        out.println(prefix + form.toString());
        if (getConditionBlock() != null) {
            getConditionBlock().printWithLineNum(out, prefix);
        }

        if (ifElse != null) {
            ifElse.ifElsePrintWithLineNum(out, prefix);
        }
    }
}

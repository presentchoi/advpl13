package cfg.structure;

import java.io.PrintStream;

import codemodel.structure.exp.Expression;
import codemodel.structure.exp.NullExpression;

import cfg.structure.transfer.CFGOneParamVisitor;


public abstract class Node {
	private Expression exp = NullExpression.getInstance();
	private int containedStatementCount = 0;

	public Expression getExp() {
		return exp;
	}

	public Node setExp(Expression exp) {
		this.exp = exp;
		return this;
	}

	public abstract void print(PrintStream out, String prefix);

	public abstract <T> void accept(CFGOneParamVisitor<T> visitor, T data);

	public int getContainedStatementCount() {
		return containedStatementCount;
	}

	public void setContainedStatementCount(int containedStatementCount) {
		this.containedStatementCount = containedStatementCount;
	}
}

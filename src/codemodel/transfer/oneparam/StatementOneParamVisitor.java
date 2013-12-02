package codemodel.transfer.oneparam;

import codemodel.structure.stat.Block;
import codemodel.structure.stat.DoWhileStatement;
import codemodel.structure.stat.ExpressionStatement;
import codemodel.structure.stat.ForStatement;
import codemodel.structure.stat.IfStatement;
import codemodel.structure.stat.InstrumentBlock;
import codemodel.structure.stat.InstrumentStatement;
import codemodel.structure.stat.JumpStatement;
import codemodel.structure.stat.LabeledStatement;
import codemodel.structure.stat.WhileStatement;

public interface StatementOneParamVisitor<T> {
	public void visit(Block block, T data);

	public void visit(ExpressionStatement expressionStatement, T data);

	public void visit(ForStatement forStatement, T data);

	public void visit(IfStatement ifStatement, T data);

	public void visit(JumpStatement jumpStatement, T data);

	public void visit(WhileStatement whileStatement, T data);

	public void visit(LabeledStatement labeledStatement, T data);

	public void visit(DoWhileStatement doWhileStatement, T data);

	public void visit(InstrumentStatement instrumentStatement, T data);

	public void visit(InstrumentBlock instrumentBlock, T data);
}

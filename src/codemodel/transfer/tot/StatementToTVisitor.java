package codemodel.transfer.tot;

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

public interface StatementToTVisitor<T> {
	public T visit(Block block);

	public T visit(ExpressionStatement expressionStatement);

	public T visit(ForStatement forStatement);

	public T visit(IfStatement ifStatement);

	public T visit(JumpStatement jumpStatement);

	public T visit(WhileStatement whileStatement);

	public T visit(LabeledStatement labeledStatement);

	public T visit(DoWhileStatement doWhileStatement);

	public T visit(InstrumentStatement instrumentStatement);

	public T visit(InstrumentBlock instrumentBlock);
}

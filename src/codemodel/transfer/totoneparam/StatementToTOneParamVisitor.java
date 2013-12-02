package codemodel.transfer.totoneparam;

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

public interface StatementToTOneParamVisitor<T, T1> {
	public T visit(Block block, T1 data);

	public T visit(ExpressionStatement expressionStatement, T1 data);

	public T visit(ForStatement forStatement, T1 data);

	public T visit(IfStatement ifStatement, T1 data);

	public T visit(JumpStatement jumpStatement, T1 data);

	public T visit(WhileStatement whileStatement, T1 data);

	public T visit(LabeledStatement labeledStatement, T1 data);

	public T visit(DoWhileStatement doWhileStatement, T1 data);

	public T visit(InstrumentStatement instrumentStatement, T1 data);

	public T visit(InstrumentBlock instrumentBlock, T1 data);
}

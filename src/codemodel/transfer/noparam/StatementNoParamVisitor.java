package codemodel.transfer.noparam;

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

public interface StatementNoParamVisitor {
	public void visit(Block block);

	public void visit(ExpressionStatement expressionStatement);

	public void visit(ForStatement forStatement);

	public void visit(IfStatement ifStatement);

	public void visit(JumpStatement jumpStatement);

	public void visit(WhileStatement whileStatement);

	public void visit(LabeledStatement labeledStatement);

	public void visit(DoWhileStatement doWhileStatement);

	public void visit(InstrumentStatement instrumentStatement);

	public void visit(InstrumentBlock instrumentBlock);
}

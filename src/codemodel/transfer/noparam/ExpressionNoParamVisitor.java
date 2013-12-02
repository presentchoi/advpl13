package codemodel.transfer.noparam;

import codemodel.structure.exp.ArrayExpression;
import codemodel.structure.exp.BinaryExpression;
import codemodel.structure.exp.BlockExpression;
import codemodel.structure.exp.CastExpression;
import codemodel.structure.exp.ConditionExpression;
import codemodel.structure.exp.ConstantExpression;
import codemodel.structure.exp.FunctionCallExpression;
import codemodel.structure.exp.NullExpression;
import codemodel.structure.exp.PointerExpression;
import codemodel.structure.exp.RoundBracketExpression;
import codemodel.structure.exp.StructExpression;
import codemodel.structure.exp.UnaryExpression;
import codemodel.structure.exp.VariableExpression;

public interface ExpressionNoParamVisitor {
	public void visit(ArrayExpression arrayExpression);

	public void visit(BinaryExpression binaryExpression);

	public void visit(BlockExpression blockExpression);

	public void visit(CastExpression castExpression);

	public void visit(ConditionExpression conditionExpression);

	public void visit(ConstantExpression constantExpression);

	public void visit(FunctionCallExpression functionCallExpression);

	public void visit(NullExpression nullExpression);

	public void visit(PointerExpression pointerExpression);

	public void visit(RoundBracketExpression roundBracketExpression);

	public void visit(StructExpression structExpression);

	public void visit(UnaryExpression unaryExpression);

	public void visit(VariableExpression variableExpression);
}

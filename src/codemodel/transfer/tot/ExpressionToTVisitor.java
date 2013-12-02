package codemodel.transfer.tot;

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

public interface ExpressionToTVisitor<T> {
	public T visit(ArrayExpression arrayExpression);

	public T visit(BinaryExpression binaryExpression);

	public T visit(BlockExpression blockExpression);

	public T visit(CastExpression castExpression);

	public T visit(ConditionExpression conditionExpression);

	public T visit(ConstantExpression constantExpression);

	public T visit(FunctionCallExpression functionCallExpression);

	public T visit(NullExpression nullExpression);

	public T visit(PointerExpression pointerExpression);

	public T visit(RoundBracketExpression roundBracketExpression);

	public T visit(StructExpression structExpression);

	public T visit(UnaryExpression unaryExpression);

	public T visit(VariableExpression variableExpression);
}

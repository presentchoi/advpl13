package codemodel.transfer.totoneparam;

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

public interface ExpressionToTOneParamVisitor<T, T1> {
	public T visit(ArrayExpression arrayExpression, T1 data);

	public T visit(BinaryExpression binaryExpression, T1 data);

	public T visit(BlockExpression blockExpression, T1 data);

	public T visit(CastExpression castExpression, T1 data);

	public T visit(ConditionExpression conditionExpression, T1 data);

	public T visit(ConstantExpression constantExpression, T1 data);

	public T visit(FunctionCallExpression functionCallExpression, T1 data);

	public T visit(NullExpression nullExpression, T1 data);

	public T visit(PointerExpression pointerExpression, T1 data);

	public T visit(RoundBracketExpression roundBracketExpression, T1 data);

	public T visit(StructExpression structExpression, T1 data);

	public T visit(UnaryExpression unaryExpression, T1 data);

	public T visit(VariableExpression variableExpression, T1 data);
}

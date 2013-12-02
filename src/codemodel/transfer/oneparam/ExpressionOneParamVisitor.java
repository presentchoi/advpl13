package codemodel.transfer.oneparam;

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

public interface ExpressionOneParamVisitor<T> {
	public void visit(ArrayExpression arrayExpression, T data);

	public void visit(BinaryExpression binaryExpression, T data);

	public void visit(BlockExpression blockExpression, T data);

	public void visit(CastExpression castExpression, T data);

	public void visit(ConditionExpression conditionExpression, T data);

	public void visit(ConstantExpression constantExpression, T data);

	public void visit(FunctionCallExpression functionCallExpression, T data);

	public void visit(NullExpression nullExpression, T data);

	public void visit(PointerExpression pointerExpression, T data);

	public void visit(RoundBracketExpression roundBracketExpression, T data);

	public void visit(StructExpression structExpression, T data);

	public void visit(UnaryExpression unaryExpression, T data);

	public void visit(VariableExpression variableExpression, T data);
}

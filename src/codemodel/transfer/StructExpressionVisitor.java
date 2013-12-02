package codemodel.transfer;

import java.util.List;
import java.util.Stack;

import codemodel.structure.dcl.Declaration;
import codemodel.structure.dcl.StructType;
import codemodel.structure.dcl.VariableDeclaration;
import codemodel.structure.def.StructDefinition;
import codemodel.structure.exp.ArrayExpression;
import codemodel.structure.exp.BinaryExpression;
import codemodel.structure.exp.BlockExpression;
import codemodel.structure.exp.CastExpression;
import codemodel.structure.exp.ConditionExpression;
import codemodel.structure.exp.ConstantExpression;
import codemodel.structure.exp.Expression;
import codemodel.structure.exp.FunctionCallExpression;
import codemodel.structure.exp.NullExpression;
import codemodel.structure.exp.PointerExpression;
import codemodel.structure.exp.RoundBracketExpression;
import codemodel.structure.exp.StructExpression;
import codemodel.structure.exp.UnaryExpression;
import codemodel.structure.exp.VariableExpression;
import codemodel.structure.type.DeclarationType;
import codemodel.transfer.noparam.ExpressionNoParamVisitor;


public class StructExpressionVisitor implements ExpressionNoParamVisitor {

	private Stack<List<Declaration>> declarationStack;
	private StructDefinition lhsStructDef = null;

	public void visit(StructExpression structExpression,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		this.declarationStack = declarationStack;
		visit(structExpression);
	}

	@Override
	public void visit(ArrayExpression arrayExpression) {
		// TODO Auto-generated method stub
		Expression exp = arrayExpression.getVariable();
		exp.accept(this);

		exp = arrayExpression.getArrayLocation();
		exp.accept(this);
	}

	@Override
	public void visit(BinaryExpression binaryExpression) {
		// TODO Auto-generated method stub
		Expression exp = binaryExpression.getLhsOperand();
		exp.accept(this);

		exp = binaryExpression.getRhsOperand();
		exp.accept(this);
	}

	@Override
	public void visit(BlockExpression blockExpression) {
		// TODO Auto-generated method stub
		for (Expression exp : blockExpression.getBlockInnerExpressions()) {
			exp.accept(this);
		}
	}

	@Override
	public void visit(CastExpression castExpression) {
		// TODO Auto-generated method stub
		Expression exp = castExpression.getCastedExpression();
		exp.accept(this);
	}

	@Override
	public void visit(ConditionExpression conditionExpression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ConstantExpression constantExpression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FunctionCallExpression functionCallExpression) {
		// TODO Auto-generated method stub
		functionCallExpression.getParameterInstanceExpression().accept(this);
	}

	@Override
	public void visit(NullExpression nullExpression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(PointerExpression pointerExpression) {
		// TODO Auto-generated method stub
		Expression exp = pointerExpression.getPointedExpression();
		exp.accept(this);
	}

	@Override
	public void visit(RoundBracketExpression roundBracketExpression) {
		// TODO Auto-generated method stub
		roundBracketExpression.getInnerExpression().accept(this);
	}

	@Override
	public void visit(StructExpression structExpression) {
		// TODO Auto-generated method stub
		structExpression.getFirstVariable().accept(this);
		structExpression.getSecondVariable().accept(this);
	}

	@Override
	public void visit(UnaryExpression unaryExpression) {
		// TODO Auto-generated method stub
		unaryExpression.getOperand().accept(this);
	}

	@Override
	public void visit(VariableExpression variableExpression) {
		// TODO Auto-generated method stub
		if (variableExpression.getVariable() == null) {
			if (lhsStructDef == null) {
				VariableDeclaration vb = findStructVariableDeclaration(variableExpression);
				variableExpression.setVariable(vb);
				lhsStructDef = ((StructType) vb.getType()).getStruct();
			} else {
				for (VariableDeclaration d : lhsStructDef.getStructVariable()) {
					if (d.getName().equals(variableExpression.getLiteral())) {
						variableExpression.setVariable(d);
						if (d.getType() instanceof StructType) {
							lhsStructDef = ((StructType) d.getType())
									.getStruct();
						}
					}
				}
			}
		} else if ((variableExpression.getVariable().getType() instanceof StructType)
				&& lhsStructDef == null) {
			lhsStructDef = ((StructType) variableExpression.getVariable()
					.getType()).getStruct();
		}

	}

	private VariableDeclaration findStructVariableDeclaration(
			VariableExpression expression) {
		for (int i = declarationStack.size() - 1; i >= 0; i--) {
			for (Declaration temp : declarationStack.get(i)) {
				if (temp.getDeclarationType() == DeclarationType.VARIABLE) {
					VariableDeclaration vb = (VariableDeclaration) temp;
					if (expression.getLiteral().equals(vb.getName())
							&& vb.getType() instanceof StructType) {
						return vb;
					}
				}
			}
		}
		return null;
	}

}

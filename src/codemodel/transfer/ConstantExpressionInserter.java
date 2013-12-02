package codemodel.transfer;

import java.util.List;

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
import codemodel.transfer.noparam.ExpressionNoParamVisitor;


public class ConstantExpressionInserter implements ExpressionNoParamVisitor {
	private static final ConstantExpressionInserter instance = new ConstantExpressionInserter();

	public static ConstantExpressionInserter getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}

	@Override
	public void visit(ArrayExpression arrayExpression) {
		// TODO Auto-generated method stub
		if (arrayExpression.getVariable() instanceof VariableExpression) {
			VariableExpression var = (VariableExpression) arrayExpression
					.getVariable();
			if (var.getVariable() == null) {
				throw new IllegalArgumentException("undefined variable : "
						+ var.getLiteral());
			}
		}
		if (arrayExpression.getArrayLocation() instanceof VariableExpression) {
			VariableExpression var = (VariableExpression) arrayExpression
					.getArrayLocation();
			if (var.getVariable() == null) {
				ConstantExpression con = new ConstantExpression();
				con.setLiteral(var.getLiteral());
				arrayExpression.setArrayLocation(con);
			}
		}
	}

	@Override
	public void visit(BinaryExpression binaryExpression) {
		// TODO Auto-generated method stub
		if (binaryExpression.getLhsOperand() instanceof VariableExpression) {
			VariableExpression var = (VariableExpression) binaryExpression
					.getLhsOperand();
//			System.err.println(var.getVariable());
			if (var.getVariable() == null) {
				ConstantExpression con = new ConstantExpression();
				con.setLiteral(var.getLiteral());
				binaryExpression.setLhsOperand(con);
			}
		}
		if (binaryExpression.getRhsOperand() instanceof VariableExpression) {
			VariableExpression var = (VariableExpression) binaryExpression
					.getRhsOperand();
//			System.err.println(var.getVariable());
			if (var.getVariable() == null) {
				ConstantExpression con = new ConstantExpression();
				con.setLiteral(var.getLiteral());
				binaryExpression.setRhsOperand(con);
			}
		}

	}

	@Override
	public void visit(BlockExpression blockExpression) {
		// TODO Auto-generated method stub
		List<Expression> exps = blockExpression.getBlockInnerExpressions();
		for (int i = 0; i < exps.size(); i++) {
			Expression e = exps.get(i);
			if (e instanceof VariableExpression) {
				VariableExpression var = (VariableExpression) e;
				if (var.getVariable() == null) {
					ConstantExpression con = new ConstantExpression();
					con.setLiteral(var.getLiteral());
					blockExpression.replaceBlockInnerExpression(con, i);
				}
			}
		}
	}

	@Override
	public void visit(CastExpression castExpression) {
		// TODO Auto-generated method stub
		if (castExpression.getCastedExpression() instanceof VariableExpression) {
			VariableExpression var = (VariableExpression) castExpression
					.getCastedExpression();
			if (var.getVariable() == null) {
				ConstantExpression con = new ConstantExpression();
				con.setLiteral(var.getLiteral());
				castExpression.setCastedExpression(con);
			}
		}
	}

	@Override
	public void visit(ConditionExpression conditionExpression) {
		// TODO Auto-generated method stub
		if (conditionExpression.getTrueConditionExpression() instanceof VariableExpression) {
			VariableExpression var = (VariableExpression) conditionExpression
					.getTrueConditionExpression();
			if (var.getVariable() == null) {
				ConstantExpression con = new ConstantExpression();
				con.setLiteral(var.getLiteral());
				conditionExpression.setTrueConditionExpression(con);
			}
		}
		if (conditionExpression.getFalseConditionExpression() instanceof VariableExpression) {
			VariableExpression var = (VariableExpression) conditionExpression
					.getFalseConditionExpression();
			if (var.getVariable() == null) {
				ConstantExpression con = new ConstantExpression();
				con.setLiteral(var.getLiteral());
				conditionExpression.setFalseConditionExpression(con);
			}
		}
		if (conditionExpression.getCheckConditionExpression() instanceof VariableExpression) {
			VariableExpression var = (VariableExpression) conditionExpression
					.getCheckConditionExpression();
			if (var.getVariable() == null) {
				ConstantExpression con = new ConstantExpression();
				con.setLiteral(var.getLiteral());
				conditionExpression.setCheckConditionExpression(con);
			}
		}
	}

	@Override
	public void visit(ConstantExpression constantExpression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FunctionCallExpression functionCallExpression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(NullExpression nullExpression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(PointerExpression pointerExpression) {
		// TODO Auto-generated method stub
		if (pointerExpression.getPointedExpression() instanceof VariableExpression) {
			VariableExpression var = (VariableExpression) pointerExpression
					.getPointedExpression();
			if (var.getVariable() == null) {
				ConstantExpression con = new ConstantExpression();
				con.setLiteral(var.getLiteral());
				pointerExpression.setPointedExpression(con);
			}
		}
	}

	@Override
	public void visit(RoundBracketExpression roundBracketExpression) {
		// TODO Auto-generated method stub
		if (roundBracketExpression.getInnerExpression() instanceof VariableExpression) {
			VariableExpression var = (VariableExpression) roundBracketExpression
					.getInnerExpression();
			if (var.getVariable() == null) {
				ConstantExpression con = new ConstantExpression();
				con.setLiteral(var.getLiteral());
				roundBracketExpression.setInnerExpression(con);
			}
		}
	}

	@Override
	public void visit(StructExpression structExpression) {
		// TODO Auto-generated method stub
		if (structExpression.getFirstVariable() instanceof VariableExpression) {
			VariableExpression var = (VariableExpression) structExpression
					.getFirstVariable();
			if (var.getVariable() == null) {
				ConstantExpression con = new ConstantExpression();
				con.setLiteral(var.getLiteral());
				structExpression.setFirstVariable(con);
			}
		}
		if (structExpression.getSecondVariable() instanceof VariableExpression) {
			VariableExpression var = (VariableExpression) structExpression
					.getSecondVariable();
			if (var.getVariable() == null) {
				ConstantExpression con = new ConstantExpression();
				con.setLiteral(var.getLiteral());
				structExpression.setSecondVariable(con);
			}
		}
	}

	@Override
	public void visit(UnaryExpression unaryExpression) {
		// TODO Auto-generated method stub
		if (unaryExpression.getOperand() instanceof VariableExpression) {
			VariableExpression var = (VariableExpression) unaryExpression
					.getOperand();
			if (var.getVariable() == null) {
				ConstantExpression con = new ConstantExpression();
				con.setLiteral(var.getLiteral());
				unaryExpression.setOperand(con);
			}
		}
	}

	@Override
	public void visit(VariableExpression variableExpression) {
		// TODO Auto-generated method stub

	}

}

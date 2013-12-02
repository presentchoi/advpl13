package codemodel.transfer;

import java.util.Vector;

import codemodel.structure.CSource;
import codemodel.structure.dcl.Array;
import codemodel.structure.dcl.Declaration;
import codemodel.structure.dcl.FunctionDeclaration;
import codemodel.structure.dcl.StructType;
import codemodel.structure.dcl.TypeID;
import codemodel.structure.dcl.VariableDeclaration;
import codemodel.structure.def.EnumDefinition;
import codemodel.structure.def.FunctionDefinition;
import codemodel.structure.def.StructDefinition;
import codemodel.structure.def.TypedefDefinition;
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
import codemodel.structure.stat.Block;
import codemodel.structure.stat.DoWhileStatement;
import codemodel.structure.stat.ExpressionStatement;
import codemodel.structure.stat.ForStatement;
import codemodel.structure.stat.IfStatement;
import codemodel.structure.stat.InstrumentBlock;
import codemodel.structure.stat.InstrumentStatement;
import codemodel.structure.stat.JumpStatement;
import codemodel.structure.stat.LabeledStatement;
import codemodel.structure.stat.Statement;
import codemodel.structure.stat.WhileStatement;
import codemodel.structure.type.VariableScope;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;


public class GlobalVariableVisitor implements
		CodeModelOneParamVisitor<Vector<VariableDeclaration>> {

	@Override
	public void visit(CSource sourceCode, Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		for (Declaration d : sourceCode.getDeclarations()) {
			if (d instanceof VariableDeclaration && !data.contains(d)) {
				if (((VariableDeclaration) d).getScope() == VariableScope.GLOBAL) {
					data.add((VariableDeclaration) d);
				}
			}
		}
		for (Statement s : sourceCode.getStatements()) {
			s.accept(this, data);
		}
	}

	@Override
	public void visit(Block block, Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		for (Statement s : block.getStatements()) {
			s.accept(this, data);
		}
	}

	@Override
	public void visit(ExpressionStatement expressionStatement,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		expressionStatement.getExpression().accept(this, data);
	}

	@Override
	public void visit(ForStatement forStatement,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		forStatement.getInit().accept(this, data);
		forStatement.getConditionExpression().accept(this, data);
		forStatement.getIter().accept(this, data);
		forStatement.getConditionBlock().accept(this, data);
	}

	@Override
	public void visit(IfStatement ifStatement, Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		ifStatement.getConditionExpression().accept(this, data);
		ifStatement.getConditionBlock().accept(this, data);
	}

	@Override
	public void visit(JumpStatement jumpStatement,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		jumpStatement.getJumpExpression().accept(this, data);
	}

	@Override
	public void visit(WhileStatement whileStatement,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		whileStatement.getConditionExpression().accept(this, data);
		whileStatement.getConditionBlock().accept(this, data);
	}

	@Override
	public void visit(LabeledStatement labeledStatement,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		labeledStatement.getLabeledStatement().accept(this, data);
	}

	@Override
	public void visit(DoWhileStatement doWhileStatement,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		doWhileStatement.getConditionExpression().accept(this, data);
		doWhileStatement.getConditionBlock().accept(this, data);
	}

	@Override
	public void visit(FunctionDefinition functionDefinition,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		functionDefinition.getBlock().accept(this, data);
	}

	@Override
	public void visit(ArrayExpression arrayExpression,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		arrayExpression.getVariable().accept(this, data);
		arrayExpression.getArrayLocation().accept(this, data);
	}

	@Override
	public void visit(BinaryExpression binaryExpression,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		binaryExpression.getLhsOperand().accept(this, data);
		binaryExpression.getRhsOperand().accept(this, data);
	}

	@Override
	public void visit(BlockExpression blockExpression,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		for (Expression e : blockExpression.getBlockInnerExpressions()) {
			e.accept(this, data);
		}
	}

	@Override
	public void visit(CastExpression castExpression,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		castExpression.getCastedExpression().accept(this, data);
	}

	@Override
	public void visit(ConditionExpression conditionExpression,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		conditionExpression.getCheckConditionExpression().accept(this, data);
		conditionExpression.getTrueConditionExpression().accept(this, data);
		conditionExpression.getFalseConditionExpression().accept(this, data);
	}

	@Override
	public void visit(ConstantExpression constantExpression,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FunctionCallExpression functionCallExpression,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		functionCallExpression.getParameterInstanceExpression().accept(this,
				data);
	}

	@Override
	public void visit(NullExpression nullExpression,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(PointerExpression pointerExpression,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		pointerExpression.getPointedExpression().accept(this, data);
	}

	@Override
	public void visit(RoundBracketExpression roundBracketExpression,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		roundBracketExpression.getInnerExpression().accept(this, data);
	}

	@Override
	public void visit(StructExpression structExpression,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		structExpression.getFirstVariable().accept(this, data);
		structExpression.getSecondVariable().accept(this, data);
	}

	@Override
	public void visit(UnaryExpression unaryExpression,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		unaryExpression.getOperand().accept(this, data);
	}

	@Override
	public void visit(VariableExpression variableExpression,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		VariableDeclaration d = variableExpression.getVariable();
		if (d.getScope() == VariableScope.GLOBAL && !data.contains(d)) {
			data.add(d);
		}
	}

	@Override
	public void visit(TypeID typeID, Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Array array, Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StructType structType, Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FunctionDeclaration functionDeclaration,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(VariableDeclaration variableDeclaration,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(EnumDefinition enumDefinition,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StructDefinition structDefinition,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(TypedefDefinition typedefDefinition,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(InstrumentStatement instrumentStatement,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(InstrumentBlock instrumentBlock,
			Vector<VariableDeclaration> data) {
		// TODO Auto-generated method stub
		
	}
}

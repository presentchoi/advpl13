package codemodel.transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import codemodel.structure.CSource;
import codemodel.structure.dcl.Array;
import codemodel.structure.dcl.Declaration;
import codemodel.structure.dcl.FunctionDeclaration;
import codemodel.structure.dcl.StructType;
import codemodel.structure.dcl.TypeID;
import codemodel.structure.dcl.VariableDeclaration;
import codemodel.structure.def.Definition;
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
import codemodel.structure.type.DeclarationType;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;


class SecondPathAnalysisVisitor implements
		CodeModelOneParamVisitor<Stack<List<Declaration>>> {
	private CSource sourceCode;

	public void visit(CSource sourceCode,
			Stack<List<Declaration>> declarationStack) {
		this.sourceCode = sourceCode;

		declarationStack.push(sourceCode.getDeclarations());
		for (Definition d : sourceCode.getDefinitions()) {
			d.accept(this, declarationStack);
		}
		for (Declaration d : sourceCode.getDeclarations()) {
			d.accept(this, declarationStack);
		}
		for (Statement s : sourceCode.getStatements()) {
			s.accept(this, declarationStack);
		}
		new StructDeclarationFindVisitor().visit(sourceCode);
		declarationStack.pop();
	}

	public void visit(FunctionDefinition functionDefinition,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		List<Declaration> paramDec = new ArrayList<Declaration>(
				functionDefinition.getFunctionSignature().getParameters());
		declarationStack.push(paramDec);
		functionDefinition.getBlock().accept(this, declarationStack);
		declarationStack.pop();
	}

	public void visit(Block block, Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		declarationStack.push(block.getDeclarations());
		for (Definition d : block.getDefinitions()) {
			d.accept(this, declarationStack);
		}
		for (Declaration d : block.getDeclarations()) {
			d.accept(this, declarationStack);
		}
		for (Statement s : block.getStatements()) {
			s.accept(this, declarationStack);
		}
		declarationStack.pop();
	}

	public void visit(ExpressionStatement expressionStatement,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		Expression exp = expressionStatement.getExpression();
		expAccept(declarationStack, exp);
	}

	private void expAccept(Stack<List<Declaration>> declarationStack,
			Expression exp) {
		exp.accept(this, declarationStack);
		exp.accept(ConstantExpressionInserter.getInstance());
	}

	public void visit(ForStatement forStatement,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		forStatement.getInit().accept(this, declarationStack);

		Expression exp = forStatement.getConditionExpression();
		expAccept(declarationStack, exp);

		forStatement.getIter().accept(this, declarationStack);
		forStatement.getConditionBlock().accept(this, declarationStack);
	}

	public void visit(IfStatement ifStatement,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		if (isNotElseStatement(ifStatement)) {
			Expression exp = ifStatement.getConditionExpression();
			expAccept(declarationStack, exp);
		}
		ifStatement.getConditionBlock().accept(this, declarationStack);
		if (ifStatement.getIfElse() != null) {
			ifStatement.getIfElse().accept(this, declarationStack);
		}
	}

	private boolean isNotElseStatement(IfStatement ifStatement) {
		return ifStatement.getConditionExpression() != null;
	}

	public void visit(JumpStatement jumpStatement,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		Expression exp = jumpStatement.getJumpExpression();
		if (exp != null) {
			expAccept(declarationStack, exp);
		}
	}

	public void visit(LabeledStatement labeledStatement,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		labeledStatement.getLabeledStatement().accept(this, declarationStack);
	}

	public void visit(WhileStatement whileStatement,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		Expression exp = whileStatement.getConditionExpression();
		expAccept(declarationStack, exp);
		whileStatement.getConditionBlock().accept(this, declarationStack);
	}

	public void visit(VariableExpression variableExpression,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		VariableDeclaration vb = findVariableDeclaration(variableExpression,
				declarationStack);
		variableExpression.setVariable(vb);
	}

	private VariableDeclaration findVariableDeclaration(
			VariableExpression expression,
			Stack<List<Declaration>> declarationStack) {
		for (int i = declarationStack.size() - 1; i >= 0; i--) {
			for (Declaration temp : declarationStack.get(i)) {
				if (temp.getDeclarationType() == DeclarationType.VARIABLE) {
					VariableDeclaration vb = (VariableDeclaration) temp;
					if (expression.getLiteral().equals(vb.getName())) {
						return vb;
					}
				}
			}
		}
		return null;
	}

	public void visit(UnaryExpression unaryExpression,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		Expression exp = unaryExpression.getOperand();
		exp.accept(this, declarationStack);
	}

	public void visit(RoundBracketExpression roundBracketExpression,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		Expression exp = roundBracketExpression.getInnerExpression();
		exp.accept(this, declarationStack);
	}

	public void visit(FunctionCallExpression functionCallExpression,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		FunctionDefinition func = findFunctionDefinition(functionCallExpression
				.getFunctionName());
		functionCallExpression.setFunctionDefinition(func);

		Expression exp = functionCallExpression
				.getParameterInstanceExpression();
		exp.accept(this, declarationStack);
	}

	private FunctionDefinition findFunctionDefinition(String functionName) {
		// TODO Auto-generated method stub
		for (Definition d : this.sourceCode.getDefinitions()) {
			if (d instanceof FunctionDefinition) {
				FunctionDefinition f = (FunctionDefinition) d;
				if (f.getFunctionSignature().getFunctionName().equals(
						functionName)) {
					return f;
				}
			}
		}
		return null;
	}

	public void visit(ConditionExpression conditionExpression,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		Expression exp = conditionExpression.getCheckConditionExpression();
		exp.accept(this, declarationStack);

		exp = conditionExpression.getTrueConditionExpression();
		exp.accept(this, declarationStack);

		exp = conditionExpression.getFalseConditionExpression();
		exp.accept(this, declarationStack);
	}

	public void visit(ConstantExpression constantExpression,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub

	}

	public void visit(BinaryExpression binaryExpression,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		Expression exp = binaryExpression.getLhsOperand();
		exp.accept(this, declarationStack);

		exp = binaryExpression.getRhsOperand();
		exp.accept(this, declarationStack);
	}

	public void visit(ArrayExpression arrayExpression,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		Expression exp = arrayExpression.getVariable();
		exp.accept(this, declarationStack);

		exp = arrayExpression.getArrayLocation();
		exp.accept(this, declarationStack);
	}

	public void visit(CastExpression castExpression,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		Expression exp = castExpression.getCastedExpression();
		exp.accept(this, declarationStack);
	}

	public void visit(DoWhileStatement doWhileStatement,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		Expression exp = doWhileStatement.getConditionExpression();
		exp.accept(this, declarationStack);
		doWhileStatement.getConditionBlock().accept(this, declarationStack);
	}

	public void visit(NullExpression nullExpression,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub

	}

	public void visit(StructExpression structExpression,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		Expression exp = structExpression.getFirstVariable();
		if (!(exp instanceof VariableExpression)) {
			exp.accept(this, declarationStack);
		}
		exp = structExpression.getSecondVariable();
		if (!(exp instanceof VariableExpression)) {
			exp.accept(this, declarationStack);
		}
	}

	public void visit(PointerExpression pointerExpression,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		Expression exp = pointerExpression.getPointedExpression();
		exp.accept(this, declarationStack);
	}

	@Override
	public void visit(BlockExpression blockExpression,
			Stack<List<Declaration>> declarationStack) {
		// TODO Auto-generated method stub
		for (Expression exp : blockExpression.getBlockInnerExpressions()) {
			exp.accept(this, declarationStack);
		}
	}

	@Override
	public void visit(TypeID typeID, Stack<List<Declaration>> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Array array, Stack<List<Declaration>> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StructType structType, Stack<List<Declaration>> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FunctionDeclaration functionDeclaration,
			Stack<List<Declaration>> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(VariableDeclaration variableDeclaration,
			Stack<List<Declaration>> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(EnumDefinition enumDefinition,
			Stack<List<Declaration>> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StructDefinition structDefinition,
			Stack<List<Declaration>> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(TypedefDefinition typedefDefinition,
			Stack<List<Declaration>> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(InstrumentStatement instrumentStatement,
			Stack<List<Declaration>> data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(InstrumentBlock instrumentBlock,
			Stack<List<Declaration>> data) {
		// TODO Auto-generated method stub
		
	}

}

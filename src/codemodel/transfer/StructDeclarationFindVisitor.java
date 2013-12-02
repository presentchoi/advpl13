package codemodel.transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import codemodel.structure.CSource;
import codemodel.structure.dcl.Array;
import codemodel.structure.dcl.Declaration;
import codemodel.structure.dcl.FunctionDeclaration;
import codemodel.structure.dcl.StructType;
import codemodel.structure.dcl.Type;
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
import codemodel.structure.stat.InitDeclaratable;
import codemodel.structure.stat.InstrumentBlock;
import codemodel.structure.stat.InstrumentStatement;
import codemodel.structure.stat.JumpStatement;
import codemodel.structure.stat.LabeledStatement;
import codemodel.structure.stat.Statement;
import codemodel.structure.stat.WhileStatement;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;


public class StructDeclarationFindVisitor implements CodeModelNoParamVisitor {
	private Stack<List<Declaration>> declarationStack = new Stack<List<Declaration>>();
	private Stack<List<StructDefinition>> structStack = new Stack<List<StructDefinition>>();

	private List<StructDefinition> getStructDefinitions(
			InitDeclaratable declaratable) {
		// TODO Auto-generated method stub
		List<StructDefinition> temp = new ArrayList<StructDefinition>();
		for (Definition d : declaratable.getDefinitions()) {
			if (d instanceof StructDefinition) {
				temp.add((StructDefinition) d);
			}
		}
		return temp;
	}

	@Override
	public void visit(CSource sourceCode) {
		// TODO Auto-generated method stub
		structStack.push(getStructDefinitions(sourceCode));
		declarationStack.push(sourceCode.getDeclarations());
		for (Definition d : sourceCode.getDefinitions()) {
			d.accept(this);
		}
		for (Declaration d : sourceCode.getDeclarations()) {
			d.accept(this);
		}
		for (Statement s : sourceCode.getStatements()) {
			s.accept(this);
		}
		findStructInnerDeclaration();
		declarationStack.pop();
		structStack.pop();
	}

	private void findStructInnerDeclaration() {
		// TODO Auto-generated method stub
		for (int i = structStack.size() - 1; i >= 0; i--) {
			for (StructDefinition temp : structStack.get(i)) {
				temp.accept(this);
			}
		}
	}

	@Override
	public void visit(Block block) {
		// TODO Auto-generated method stub
		structStack.push(getStructDefinitions(block));
		declarationStack.push(block.getDeclarations());
		for (Definition d : block.getDefinitions()) {
			d.accept(this);
		}
		for (Declaration d : block.getDeclarations()) {
			d.accept(this);
		}
		for (Statement s : block.getStatements()) {
			s.accept(this);
		}
		declarationStack.pop();
		structStack.pop();
	}

	@Override
	public void visit(ExpressionStatement expressionStatement) {
		// TODO Auto-generated method stub
		Expression exp = expressionStatement.getExpression();
		exp.accept(this);
	}

	@Override
	public void visit(ForStatement forStatement) {
		// TODO Auto-generated method stub
		forStatement.getInit().accept(this);

		Expression exp = forStatement.getConditionExpression();
		exp.accept(this);

		forStatement.getIter().accept(this);
		forStatement.getConditionBlock().accept(this);
	}

	@Override
	public void visit(IfStatement ifStatement) {
		// TODO Auto-generated method stub
		if (isNotElseStatement(ifStatement)) {
			Expression exp = ifStatement.getConditionExpression();
			exp.accept(this);
		}
		ifStatement.getConditionBlock().accept(this);
		if (ifStatement.getIfElse() != null) {
			ifStatement.getIfElse().accept(this);
		}
	}

	private boolean isNotElseStatement(IfStatement ifStatement) {
		return ifStatement.getConditionExpression() != null;
	}

	@Override
	public void visit(JumpStatement jumpStatement) {
		// TODO Auto-generated method stub
		Expression exp = jumpStatement.getJumpExpression();
		if (exp != null) {
			exp.accept(this);
		}
	}

	@Override
	public void visit(WhileStatement whileStatement) {
		// TODO Auto-generated method stub
		Expression exp = whileStatement.getConditionExpression();
		exp.accept(this);
		whileStatement.getConditionBlock().accept(this);
	}

	@Override
	public void visit(LabeledStatement labeledStatement) {
		// TODO Auto-generated method stub
		labeledStatement.getLabeledStatement().accept(this);
	}

	@Override
	public void visit(DoWhileStatement doWhileStatement) {
		// TODO Auto-generated method stub
		Expression exp = doWhileStatement.getConditionExpression();
		exp.accept(this);
		doWhileStatement.getConditionBlock().accept(this);
	}

	@Override
	public void visit(FunctionDefinition functionDefinition) {
		// TODO Auto-generated method stub
		List<Declaration> paramDec = new ArrayList<Declaration>(
				functionDefinition.getFunctionSignature().getParameters());
		declarationStack.push(paramDec);
		for (Declaration paramDecs : functionDefinition.getFunctionSignature()
				.getParameters()) {
			paramDecs.accept(this);
		}
		functionDefinition.getBlock().accept(this);
		declarationStack.pop();
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
		Expression exp = conditionExpression.getCheckConditionExpression();
		exp.accept(this);

		exp = conditionExpression.getTrueConditionExpression();
		exp.accept(this);

		exp = conditionExpression.getFalseConditionExpression();
		exp.accept(this);
	}

	@Override
	public void visit(ConstantExpression constantExpression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FunctionCallExpression functionCallExpression) {
		// TODO Auto-generated method stub
		Expression exp = functionCallExpression
				.getParameterInstanceExpression();
		exp.accept(this);
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
		Expression exp = roundBracketExpression.getInnerExpression();
		exp.accept(this);
	}

	@Override
	public void visit(StructExpression structExpression) {
		// TODO Auto-generated method stub
		new StructExpressionVisitor().visit(structExpression, declarationStack);
	}

	@Override
	public void visit(UnaryExpression unaryExpression) {
		// TODO Auto-generated method stub
		Expression exp = unaryExpression.getOperand();
		exp.accept(this);
	}

	@Override
	public void visit(VariableExpression variableExpression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(TypeID typeID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Array array) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StructType structType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FunctionDeclaration functionDeclaration) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(VariableDeclaration variableDeclaration) {
		// TODO Auto-generated method stub
		Type decType = variableDeclaration.getType();
		if (isStructType(decType)) {
			String structName = getStructName(decType);
			StructType structType = findStructType(structName);
			structType.setPointerNum(decType.pointerNum());
			structType.setArray(decType.getArray());
			variableDeclaration.setType(structType);
		}
	}

	private StructType findStructType(String structName) {
		// TODO Auto-generated method stub
		for (int i = structStack.size() - 1; i >= 0; i--) {
			for (StructDefinition temp : structStack.get(i)) {
				if (temp.getName().equals(structName)) {
					StructType type = new StructType();
					type.setStruct(temp);
					return type;
				}
			}
		}
		return null;
	}

	private String getStructName(Type decType) {
		// TODO Auto-generated method stub
		return ((TypeID) decType.getNextType()).getTypeName();
	}

	private boolean isStructType(Type decType) {
		// TODO Auto-generated method stub
		if (decType instanceof TypeID) {
			TypeID temp = (TypeID) decType;
			if (temp.getTypeName().equals("struct")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void visit(EnumDefinition enumDefinition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StructDefinition structDefinition) {
		// TODO Auto-generated method stub
		for (VariableDeclaration temp : structDefinition.getStructVariable()) {
			temp.accept(this);
		}
	}

	@Override
	public void visit(TypedefDefinition typedefDefinition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(InstrumentStatement instrumentStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(InstrumentBlock instrumentBlock) {
		// TODO Auto-generated method stub
		
	}

}

package codemodel.transfer;

import java.util.List;

import codemodel.Configuration;
import codemodel.structure.CSource;
import codemodel.structure.dcl.Array;
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
import codemodel.structure.type.StatementType;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;


class ConditionStatementTransferVisitor implements CodeModelNoParamVisitor {
	public void visit(CSource sourceCode) {
		for (Statement s : sourceCode.getStatements()) {
			s.accept(this);
		}
		for (Definition f : sourceCode.getDefinitions()) {
			f.accept(this);
		}
	}

	public void visit(FunctionDefinition functionDefinition) {
		// TODO Auto-generated method stub
		functionDefinition.getBlock().accept(this);
	}

	public void visit(Block block) {
		// TODO Auto-generated method stub
		for (int i = 0; i < block.getStatements().size(); i++) {
			Statement s = block.getStatements().get(i);
			if (s.getType() == StatementType.FOR) {
				ForStatement f = (ForStatement) s;
				WhileStatement w = new WhileStatement();
				w.setConditionBlock(f.getConditionBlock());
				w.setConditionExpression(f.getConditionExpression());

				ExpressionStatement iterExp = new ExpressionStatement();
				iterExp.setExpression(f.getIter());

				w.getConditionBlock().addStatement(iterExp);

				block.getStatements().remove(i);

				ExpressionStatement initExp = new ExpressionStatement();
				initExp.setExpression(f.getInit());

				block.getStatements().add(i, initExp);
				block.getStatements().add(i + 1, w);

				replaceWhileToIf(w, block, i + 1);
			} else if (s.getType() == StatementType.WHILE) {
				s.accept(this);
				replaceWhileToIf((WhileStatement) s, block, i);
			} else {
				s.accept(this);
			}
		}
	}

	private void replaceWhileToIf(WhileStatement w, Block block, int index) {
		// TODO Auto-generated method stub
		int maxDepth = Configuration.getLimitLoopDepthNum();
		int whileIndex = index;
		Block targetBlock = block;
		int[] replaceStrategy = { 0, 1, 2, maxDepth };

		targetBlock.getStatements().remove(whileIndex);
		for (int i = 0; i < maxDepth - 1; i++) {
			if (contains(replaceStrategy, i)) {
				targetBlock = addIfStatement(w, whileIndex, targetBlock, i);
			} else {
				addStatementsToBlock(targetBlock, w.getConditionBlock()
						.getStatements());
			}
		}
		addIfStatement(w, whileIndex, targetBlock, maxDepth);
	}

	private Block addIfStatement(WhileStatement w, int whileIndex,
			Block targetBlock, int i) {
		IfStatement ifStatement = new IfStatement();
		ifStatement.setConditionExpression(w.getConditionExpression()
				.makeClone());
		Block newInstance = new Block();
		addStatementsToBlock(newInstance, w.getConditionBlock().getStatements());
		ifStatement.setConditionBlock(newInstance);
		IfStatement emptyElse = new IfStatement();
		emptyElse.setConditionBlock(new Block());
		ifStatement.setIfElse(emptyElse);
		if (i == 0) {
			targetBlock.getStatements().add(whileIndex, ifStatement);
		} else {
			targetBlock.addStatement(ifStatement);
		}
		targetBlock = ifStatement.getConditionBlock();
		return targetBlock;
	}

	private void addStatementsToBlock(Block block, List<Statement> statements) {
		// TODO Auto-generated method stub
		for (Statement s : statements) {
			block.addStatement(s.makeClone());
		}
	}

	private boolean contains(int[] valueSet, int value) {
		// TODO Auto-generated method stub
		for (int i = 0; i < valueSet.length; i++) {
			if (valueSet[i] == value) {
				return true;
			}
		}
		return false;
	}

	public void visit(ExpressionStatement expressionStatement) {
		// TODO Auto-generated method stub
	}

	public void visit(ForStatement forStatement) {
		// TODO Auto-generated method stub
	}

	public void visit(IfStatement ifStatement) {
		// TODO Auto-generated method stub
		ifStatement.getConditionBlock().accept(this);
		if (ifStatement.getIfElse() != null) {
			ifStatement.getIfElse().accept(this);
		} else {
			if (ifStatement.getConditionExpression() != null
					&& !(ifStatement.getConditionExpression() instanceof NullExpression)) {
				IfStatement blankElseIf = new IfStatement();
				blankElseIf.setConditionBlock(new Block());
				ifStatement.setIfElse(blankElseIf);
			}
		}
	}

	public void visit(JumpStatement jumpStatement) {
		// TODO Auto-generated method stub
	}

	public void visit(LabeledStatement labeledStatement) {
		// TODO Auto-generated method stub
		labeledStatement.getLabeledStatement().accept(this);
	}

	public void visit(WhileStatement whileStatement) {
		// TODO Auto-generated method stub
		whileStatement.getConditionBlock().accept(this);
	}

	public void visit(DoWhileStatement doWhileStatement) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ArrayExpression arrayExpression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(BinaryExpression binaryExpression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(BlockExpression blockExpression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(CastExpression castExpression) {
		// TODO Auto-generated method stub

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

	}

	@Override
	public void visit(NullExpression nullExpression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(PointerExpression pointerExpression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(RoundBracketExpression roundBracketExpression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StructExpression structExpression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(UnaryExpression unaryExpression) {
		// TODO Auto-generated method stub

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

	}

	@Override
	public void visit(EnumDefinition enumDefinition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StructDefinition structDefinition) {
		// TODO Auto-generated method stub

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

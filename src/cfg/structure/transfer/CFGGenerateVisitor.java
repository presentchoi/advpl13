package cfg.structure.transfer;

import codemodel.structure.CSource;
import codemodel.structure.dcl.Array;
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
import codemodel.transfer.tot.CodeModelToTVisitor;
import cfg.structure.ConditionNode;
import cfg.structure.ConditionType;
import cfg.structure.ElseNode;
import cfg.structure.EndNode;
import cfg.structure.Node;
import cfg.structure.NonconditionNode;

public class CFGGenerateVisitor implements CodeModelToTVisitor<Node> {
	int conditionNodeNum = 2;

	public int getEndNodeNum() {
		return conditionNodeNum;
	}

	public Node visit(Block block) {
		Node preNode = null;
		Node firstNode = null;
		for (Statement s : block.getStatements()) {
			Node currentNode = (Node) s.accept(this);
			if (currentNode != null) {
				if (firstNode == null) {
					firstNode = currentNode;
				} else {
					if (preNode != null) {
						preNode
								.accept(new CFGNodeSettingVisitor(),
										currentNode);
					}
				}
				preNode = currentNode;
			}
		}
		return firstNode;
	}

	public Node visit(ExpressionStatement expressionStatement) {
		// TODO Auto-generated method stub
		NonconditionNode node = new NonconditionNode();
		node.setExp(expressionStatement.getExpression());
		return node;
	}

	public Node visit(ForStatement forStatement) {
		// TODO Auto-generated method stub
		System.err.println("Warning : CFGNodeVisitor ForStatement called");
		return null;
	}

	public Node visit(IfStatement ifStatement) {
		// TODO Auto-generated method stub
		ConditionNode node = new ConditionNode();
		node.setExp(ifStatement.getConditionExpression());
		node.setConditionType(ConditionType.IF);
		node.setConditionNodeNum(conditionNodeNum++);
		node.setTrueNode((Node) ifStatement.getConditionBlock().accept(this));
		if (ifStatement.getIfElse() != null) {
			ElseNode elseNode = new ElseNode();
			elseNode.setExp(ifStatement.getConditionExpression()
					.getAlternativeExpression());
			elseNode.setElseNodeNum(conditionNodeNum++);
			node.setFalseNode(elseNode);
			elseNode.setNextNode((Node) ifStatement.getIfElse()
					.getConditionBlock().accept(this));
		}
		return node;
	}

	public Node visit(JumpStatement jumpStatement) {
		// TODO Auto-generated method stub
		Node node = null;
		if (jumpStatement.getJumpExpression() != null) {
			if (jumpStatement.getJumpLiteral().contains("return")) {
				node = new NonconditionNode();
				// node.setExp(jumpStatement.getJumpExpression());
				((NonconditionNode) node).setNextNode(new EndNode());
			} else {
				node = new NonconditionNode();
			}
			node.setExp(jumpStatement.getJumpExpression());
		} else {
			node = new EndNode();
		}
		return node;
	}

	public Node visit(WhileStatement whileStatement) {
		// TODO Auto-generated method stub
		ConditionNode node = new ConditionNode();
		node.setExp(whileStatement.getConditionExpression());
		node.setConditionNodeNum(conditionNodeNum++);
		node.setConditionType(ConditionType.WHILE);
		node
				.setTrueNode((Node) whileStatement.getConditionBlock().accept(
						this));
		if (node.getTrueNode() == null) {
			node.setTrueNode(node);
		} else {
//			whileStatement.print(System.err, "");
			new CFGNodeSettingVisitor().loopVisit(node, node);
		}
		return node;
	}

	public Node visit(LabeledStatement labeledStatement) {
		// TODO Auto-generated method stub
		return (Node) labeledStatement.getLabeledStatement().accept(this);
	}

	public Node visit(DoWhileStatement doWhileStatement) {
		// TODO Auto-generated method stub
		ConditionNode node = new ConditionNode();
		node.setExp(doWhileStatement.getConditionExpression());
		node.setConditionNodeNum(conditionNodeNum++);
		node.setConditionType(ConditionType.WHILE);
		node.setTrueNode((Node) doWhileStatement.getConditionBlock().accept(
				this));
		if (node.getTrueNode() == null) {
			node.setTrueNode(node);
		} else {
//			doWhileStatement.print(System.err, "");
			new CFGNodeSettingVisitor().loopVisit(node, node);
		}
		return node;
	}

	@Override
	public Node visit(ArrayExpression arrayExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(BinaryExpression binaryExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(BlockExpression blockExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(CastExpression castExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(ConditionExpression conditionExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(ConstantExpression constantExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(FunctionCallExpression functionCallExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(NullExpression nullExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(PointerExpression pointerExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(RoundBracketExpression roundBracketExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(StructExpression structExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(UnaryExpression unaryExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(VariableExpression variableExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(CSource sourceCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(TypeID typeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(Array array) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(StructType structType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(FunctionDeclaration functionDeclaration) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(VariableDeclaration variableDeclaration) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(EnumDefinition enumDefinition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(FunctionDefinition functionDefinition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(StructDefinition structDefinition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(TypedefDefinition typedefDefinition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(InstrumentStatement instrumentStatement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node visit(InstrumentBlock instrumentBlock) {
		// TODO Auto-generated method stub
		return null;
	}
}

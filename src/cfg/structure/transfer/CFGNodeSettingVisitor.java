package cfg.structure.transfer;

import cfg.structure.ConditionNode;
import cfg.structure.ElseNode;
import cfg.structure.EndNode;
import cfg.structure.Node;
import cfg.structure.NonconditionNode;
import cfg.structure.StartNode;

/**
 * 속성을 가지고 있습니다. 따라서 static으로 생성할 경우 제대로 동작하지 않을 수 있습니다.
 */
public class CFGNodeSettingVisitor implements CFGOneParamVisitor<Node> {

	public void visit(NonconditionNode node, Node data) {
		// TODO Auto-generated method stub
		// visitedNode.add(node);
		if (node.getNextNode() == data) {
			return;
		}
		if (node.getNextNode() == null) {
			node.setNextNode(data);
		} else {
			// if (!visitedNode.contains(node.getNextNode())) {
			node.getNextNode().accept(this, data);
			// }
		}
	}

	public void visit(ConditionNode node, Node data) {
		// TODO Auto-generated method stub
		// visitedNode.add(node);
		if (node.getFalseNode() == null) {
			node.setFalseNode(data);
		} else {
			// if (!visitedNode.contains(node.getFalseNode())) {
			node.getFalseNode().accept(this, data);
			// }
		}
		if (node.getTrueNode() == null) {
			node.setTrueNode(data);
		} else {
			// if (!visitedNode.contains(node.getTrueNode())) {
			node.getTrueNode().accept(this, data);
			// }
		}
	}

	public void visit(EndNode node, Node data) {
		// TODO Auto-generated method stub
		// visitedNode.add(node);
		if (data instanceof EndNode) {
			node.setNodeNum(((EndNode) data).getNodeNum());
		}
	}

	public void visit(StartNode node, Node data) {
		// TODO Auto-generated method stub
		// visitedNode.add(node);
	}

	public void loopVisit(ConditionNode loopNode, ConditionNode data) {
		// TODO Auto-generated method stub
		// visitedNode.add(loopNode);
		loopNode.getTrueNode().accept(this, data);
	}

	@Override
	public void visit(ElseNode node, Node data) {
		// TODO Auto-generated method stub
		if (node.getNextNode() == data) {
			return;
		}
		if (node.getNextNode() == null) {
			node.setNextNode(data);
		} else {
			node.getNextNode().accept(this, data);
		}
	}
}

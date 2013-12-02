package cfg.structure;

import java.io.PrintStream;

import cfg.structure.transfer.CFGOneParamVisitor;


public class ConditionNode extends Node {
	private ConditionType conditionType;
	private Node trueNode;
	private Node falseNode;
	private int conditionNodeNum;

	public ConditionType getConditionType() {
		return conditionType;
	}

	public ConditionNode setConditionType(ConditionType conditionType) {
		this.conditionType = conditionType;
		return this;
	}

	public Node getTrueNode() {
		return trueNode;
	}

	public ConditionNode setTrueNode(Node trueNode) {
		this.trueNode = trueNode;
		return this;
	}

	public Node getFalseNode() {
		return falseNode;
	}

	public ConditionNode setFalseNode(Node falseNode) {
		this.falseNode = falseNode;
		return this;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "conditionNode exp : " + getExp().toString()
		+ " conditionNodeNum : " + conditionNodeNum;
	}
	
	/**
	 * loop 문의 경우 infinite loop가 발생하여 정상 동작하지 않을 수 있다.
	 */
	@Override
	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		out.println(prefix + "conditionNode exp : " + getExp().toString()
				+ " conditionNodeNum : " + conditionNodeNum);
		trueNode.print(out, "parent : conditionNode exp : "
				+ getExp().toString() + "\t\tchild : ");
		falseNode.print(out, "parent : conditionNode exp : "
				+ getExp().toString() + "\t\tchild : ");
	}

	@Override
	public <T> void accept(CFGOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	public void setConditionNodeNum(int conditionNodeNum) {
		this.conditionNodeNum = conditionNodeNum;
	}

	public int getConditionNodeNum() {
		return conditionNodeNum;
	}

	/**
	 * loop 문의 경우 true, false 노드를 비교하면 infinite loop가 발생할 수 있다. 따라서 exp만 비교한다.
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;
		result = result * 31 + getConditionNodeNum();
		result = result * 31 + getExp().hashCode();
		result = result * 31 + getConditionType().hashCode();
		return result;
	}

	/**
	 * loop 문의 경우 true, false 노드를 비교하면 infinite loop가 발생할 수 있다. 따라서 exp만 비교한다.
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof ConditionNode) {
			return conditionNodeNum == ((ConditionNode) obj)
					.getConditionNodeNum()
					&& conditionType.equals(((ConditionNode) obj)
							.getConditionType())
					&& getExp().equals(((ConditionNode) obj)
							.getExp());
		}
		return false;
	}
}

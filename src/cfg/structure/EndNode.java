package cfg.structure;

import java.io.PrintStream;

import cfg.structure.transfer.CFGOneParamVisitor;


public class EndNode extends Node {
	private int nodeNum;

	public EndNode(int endNodeNum) {
		// TODO Auto-generated constructor stub
		nodeNum = endNodeNum;
	}

	public EndNode() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		out.println(prefix + "end node, nodeNum : " + nodeNum);
	}

	@Override
	public <T> void accept(CFGOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	public void setNodeNum(int nodeNum) {
		this.nodeNum = nodeNum;
	}

	public int getNodeNum() {
		return nodeNum;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof EndNode) {
			return (nodeNum == ((EndNode) obj).getNodeNum()) && isEqualExp(obj);
		}
		return false;
	}

	private boolean isEqualExp(Object obj) {
		return getExp() == null ? ((EndNode) obj).getExp() == null : getExp()
				.equals(((EndNode) obj).getExp());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;
		result = result * 31 + nodeNum;
		if (getExp() == null) {
			result = result * 31 + 0;
		} else {
			result = result * 31 + getExp().hashCode();
		}
		return result;
	}
}

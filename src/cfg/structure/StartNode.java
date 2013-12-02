package cfg.structure;

import java.io.PrintStream;

import cfg.structure.transfer.CFGOneParamVisitor;


public class StartNode extends Node {
	private Node firstNode;

	@Override
	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		out.println(prefix + "start node");
		firstNode.print(out, "parent : StartNode " + "\t\tchild : ");
	}

	@Override
	public <T> void accept(CFGOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	public void setFirstNode(Node firstNode) {
		this.firstNode = firstNode;
	}

	public Node getFirstNode() {
		return firstNode;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof StartNode) {
			return firstNode.equals(((StartNode)obj).getFirstNode());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;
		return result * 31 + firstNode.hashCode();
	}
}

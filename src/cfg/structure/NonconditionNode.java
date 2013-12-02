package cfg.structure;

import java.io.PrintStream;

import cfg.structure.transfer.CFGOneParamVisitor;


public class NonconditionNode extends Node {
	private Node nextNode;

	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "NonconditionNode : exp " + getExp().toString();
	}

	@Override
	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		out.println(prefix + "NonconditionNode : exp " + getExp().toString());
		nextNode.print(out, "parent : NonconditionNode : exp "
				+ getExp().toString() + "\t\tchild : ");
	}

	@Override
	public <T> void accept(CFGOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;
		if (getNextNode() == null) {
			result = result * 31 + 0;
		} else {
			result = result * 31 + getNextNode().hashCode();
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof NonconditionNode) {
			NonconditionNode temp = (NonconditionNode) obj;
			return isEqualNextNode(temp)
					&& getNextNode().getExp().equals(
							temp.getNextNode().getExp());
		}
		return false;
	}

	private boolean isEqualNextNode(NonconditionNode temp) {
		boolean isEqual = true;
		isEqual = getNextNode() == null ? (temp.getNextNode()) == null
				: getNextNode().equals(temp.getNextNode());
		return isEqual;
	}
}

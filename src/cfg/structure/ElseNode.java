package cfg.structure;

import java.io.PrintStream;

import cfg.structure.transfer.CFGOneParamVisitor;


public class ElseNode extends NonconditionNode {
	private int elseNodeNum;

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode() * 31 + elseNodeNum;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof ElseNode) {
			return getExp().equals(((ElseNode) obj).getExp())
					&& isEqualNextNode(obj)
					&& getElseNodeNum() == ((ElseNode) obj).getElseNodeNum();
		}
		return false;
	}

	private boolean isEqualNextNode(Object obj) {
		boolean isEqual = true;
		isEqual = getNextNode() == null ? (((NonconditionNode) obj)
				.getNextNode()) == null : getNextNode().equals(
				((NonconditionNode) obj).getNextNode());
		return isEqual;
	}

	@Override
	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		out.println(prefix + "ElseNode : exp " + getExp().toString());
		getNextNode().print(
				out,
				"parent : ElseNode : exp " + getExp().toString()
						+ "\t\tchild : ");
	}

	@Override
	public <T> void accept(CFGOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	public void setElseNodeNum(int elseNodeNum) {
		this.elseNodeNum = elseNodeNum;
	}

	public int getElseNodeNum() {
		return elseNodeNum;
	}
}

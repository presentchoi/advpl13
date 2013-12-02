package cfg.structure.transfer;

import cfg.structure.ConditionNode;
import cfg.structure.ElseNode;
import cfg.structure.EndNode;
import cfg.structure.NonconditionNode;
import cfg.structure.StartNode;

public interface CFGOneParamVisitor<T> {
	public void visit(NonconditionNode node, T data);

	public void visit(ConditionNode node, T data);

	public void visit(EndNode node, T data);

	public void visit(StartNode node, T data);

	public void visit(ElseNode node, T data);
}

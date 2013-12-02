package codemodel.transfer.oneparam;

import codemodel.structure.dcl.FunctionDeclaration;
import codemodel.structure.dcl.VariableDeclaration;

public interface DeclarationOneParamVisitor<T> {
	public void visit(FunctionDeclaration functionDeclaration, T data);

	public void visit(VariableDeclaration variableDeclaration, T data);
}

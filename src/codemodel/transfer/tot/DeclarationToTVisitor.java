package codemodel.transfer.tot;

import codemodel.structure.dcl.FunctionDeclaration;
import codemodel.structure.dcl.VariableDeclaration;

public interface DeclarationToTVisitor<T> {
	public T visit(FunctionDeclaration functionDeclaration);

	public T visit(VariableDeclaration variableDeclaration);
}

package codemodel.transfer.totoneparam;

import codemodel.structure.dcl.FunctionDeclaration;
import codemodel.structure.dcl.VariableDeclaration;

public interface DeclarationToTOneParamVisitor<T, T1> {
	public T visit(FunctionDeclaration functionDeclaration, T1 data);

	public T visit(VariableDeclaration variableDeclaration, T1 data);
}

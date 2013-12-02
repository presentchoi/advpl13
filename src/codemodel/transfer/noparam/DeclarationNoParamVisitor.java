package codemodel.transfer.noparam;

import codemodel.structure.dcl.FunctionDeclaration;
import codemodel.structure.dcl.VariableDeclaration;

public interface DeclarationNoParamVisitor {
	public void visit(FunctionDeclaration functionDeclaration);

	public void visit(VariableDeclaration variableDeclaration);
}

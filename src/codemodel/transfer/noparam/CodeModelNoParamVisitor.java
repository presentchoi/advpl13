package codemodel.transfer.noparam;

import codemodel.structure.CSource;

public interface CodeModelNoParamVisitor extends DeclarationNoParamVisitor,
		DefinitionNoParamVisitor, ExpressionNoParamVisitor,
		StatementNoParamVisitor, TypeNoParamVisitor {
	public void visit(CSource sourceCode);
}

package codemodel.transfer.oneparam;

import codemodel.structure.CSource;

public interface CodeModelOneParamVisitor<T> extends
		DeclarationOneParamVisitor<T>, DefinitionOneParamVisitor<T>,
		ExpressionOneParamVisitor<T>, StatementOneParamVisitor<T>,
		TypeOneParamVisitor<T> {
	public void visit(CSource sourceCode, T data);

}

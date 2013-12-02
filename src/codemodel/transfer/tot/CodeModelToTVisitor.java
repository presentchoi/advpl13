package codemodel.transfer.tot;

import codemodel.structure.CSource;

public interface CodeModelToTVisitor<T> extends DeclarationToTVisitor<T>,
		DefinitionToTVisitor<T>, ExpressionToTVisitor<T>,
		StatementToTVisitor<T>, TypeToTVisitor<T> {
	public T visit(CSource courceCode);

}

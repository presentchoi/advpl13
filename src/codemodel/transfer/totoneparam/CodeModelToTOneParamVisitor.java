package codemodel.transfer.totoneparam;

import codemodel.structure.CSource;

public interface CodeModelToTOneParamVisitor<T, T1> extends
		DeclarationToTOneParamVisitor<T, T1>,
		DefinitionToTOneParamVisitor<T, T1>,
		ExpressionToTOneParamVisitor<T, T1>,
		StatementToTOneParamVisitor<T, T1>, TypeToTOneParamVisitor<T, T1> {
	public T visit(CSource courceCode, T1 data);

}

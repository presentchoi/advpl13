package codemodel.transfer.oneparam;

import codemodel.structure.def.EnumDefinition;
import codemodel.structure.def.FunctionDefinition;
import codemodel.structure.def.StructDefinition;
import codemodel.structure.def.TypedefDefinition;

public interface DefinitionOneParamVisitor<T> {
	public void visit(FunctionDefinition functionDefinition, T data);

	public void visit(EnumDefinition enumDefinition, T data);

	public void visit(StructDefinition structDefinition, T data);

	public void visit(TypedefDefinition typedefDefinition, T data);
}

package codemodel.transfer.tot;

import codemodel.structure.def.EnumDefinition;
import codemodel.structure.def.FunctionDefinition;
import codemodel.structure.def.StructDefinition;
import codemodel.structure.def.TypedefDefinition;

public interface DefinitionToTVisitor<T> {
	public T visit(EnumDefinition enumDefinition);

	public T visit(FunctionDefinition functionDefinition);

	public T visit(StructDefinition structDefinition);

	public T visit(TypedefDefinition typedefDefinition);
}

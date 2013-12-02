
package codemodel.transfer.totoneparam;

import codemodel.structure.def.EnumDefinition;
import codemodel.structure.def.FunctionDefinition;
import codemodel.structure.def.StructDefinition;
import codemodel.structure.def.TypedefDefinition;

public interface DefinitionToTOneParamVisitor<T, T1> {
	public T visit(EnumDefinition enumDefinition, T1 data);

	public T visit(FunctionDefinition functionDefinition, T1 data);

	public T visit(StructDefinition structDefinition, T1 data);

	public T visit(TypedefDefinition typedefDefinition, T1 data);
}

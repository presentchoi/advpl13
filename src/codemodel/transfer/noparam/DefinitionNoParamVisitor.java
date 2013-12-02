package codemodel.transfer.noparam;

import codemodel.structure.def.EnumDefinition;
import codemodel.structure.def.FunctionDefinition;
import codemodel.structure.def.StructDefinition;
import codemodel.structure.def.TypedefDefinition;

public interface DefinitionNoParamVisitor {
	public void visit(EnumDefinition enumDefinition);

	public void visit(StructDefinition structDefinition);

	public void visit(TypedefDefinition typedefDefinition);

	public void visit(FunctionDefinition functionDefinition);
}

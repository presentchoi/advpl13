package codemodel.transfer.noparam;

import codemodel.structure.dcl.Array;
import codemodel.structure.dcl.StructType;
import codemodel.structure.dcl.TypeID;

public interface TypeNoParamVisitor {
	public void visit(TypeID typeID);

	public void visit(Array array);

	public void visit(StructType structType);
}

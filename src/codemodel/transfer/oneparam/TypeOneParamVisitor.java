package codemodel.transfer.oneparam;

import codemodel.structure.dcl.Array;
import codemodel.structure.dcl.StructType;
import codemodel.structure.dcl.TypeID;

public interface TypeOneParamVisitor<T> {
	public void visit(TypeID typeID, T data);

	public void visit(Array array, T data);

	public void visit(StructType structType, T data);
}

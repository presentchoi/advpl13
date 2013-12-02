package codemodel.transfer.tot;

import codemodel.structure.dcl.Array;
import codemodel.structure.dcl.StructType;
import codemodel.structure.dcl.TypeID;

public interface TypeToTVisitor<T> {
	public T visit(TypeID typeID);

	public T visit(Array array);

	public T visit(StructType structType);
}

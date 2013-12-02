package codemodel.transfer.totoneparam;

import codemodel.structure.dcl.Array;
import codemodel.structure.dcl.StructType;
import codemodel.structure.dcl.TypeID;

public interface TypeToTOneParamVisitor<T, T1> {
	public T visit(TypeID typeID, T1 data);

	public T visit(Array array, T1 data);

	public T visit(StructType structType, T1 data);
}

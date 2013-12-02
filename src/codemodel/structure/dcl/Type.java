package codemodel.structure.dcl;

import codemodel.structure.type.VariableType;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.TypeNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.TypeOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.TypeToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.TypeToTOneParamVisitor;

public abstract class Type {
	private Type nextType = null;
	private int pointerNum = 0;
	private Array array = null;
	public static final Type VOID_TYPE = new TypeID("void");
	public static final Type DOUBLE_TYPE = new TypeID("double");
	public static final Type FILE_POINT_TYPE = new TypeID("FILE", 1);
	public static final Type INT_TYPE = new TypeID("int");
	public static final Type LONG_TYPE = new TypeID("long");
	public static final Type CHAR_TYPE = new TypeID("char");
	public static final Type SHORT_TYPE = new TypeID("short");
	public static final Type CHAR_POINT_TYPE = new TypeID("char", 1);

	public Type() {

	}

	public Type(Type t) {
		nextType = t;
	}

	public abstract VariableType getType();

	public void addNextType(Type nextType) {
		if (this.nextType != null) {
			Type temp = null;
			temp = this.nextType;
			while (this.nextType.getNextType() != null) {
				temp = this.nextType.getNextType();
			}
			temp.setNextType(nextType);
		} else {
			this.nextType = nextType;
		}
	}

	public Type setNextType(Type nextType) {
		this.nextType = nextType;
		return this.nextType;
	}

	public Type getNextType() {
		return nextType;
	}

	public abstract Type makeClone();

	public abstract String toString();

	public int pointerNum() {
		// TODO Auto-generated method stub
		return pointerNum;
	}

	public int addPointer() {
		// TODO Auto-generated method stub
		return pointerNum++;
	}

	public void setPointerNum(int hasPointerNum) {
		this.pointerNum = hasPointerNum;
	}

	public void setArray(Array array) {
		this.array = array;
	}

	public boolean hasPointer() {
		if (pointerNum == 0) {
			return false;
		}
		return true;
	}

	public boolean hasArray() {
		if (array == null) {
			return false;
		}
		return true;
	}

	public Array getArray() {
		return array;
	}

	public abstract <T, T1> T accept(
			CodeModelToTOneParamVisitor<T, T1> visitor, T1 data);

	public abstract <T, T1> T accept(TypeToTOneParamVisitor<T, T1> visitor,
			T1 data);

	public abstract <T> void accept(CodeModelOneParamVisitor<T> visitor, T data);

	public abstract <T> void accept(TypeOneParamVisitor<T> visitor, T data);

	public abstract <T> T accept(CodeModelToTVisitor<T> visitor);

	public abstract <T> T accept(TypeToTVisitor<T> visitor);

	public abstract void accept(CodeModelNoParamVisitor visitor);

	public abstract void accept(TypeNoParamVisitor visitor);
}

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

public class TypeID extends Type {
	private String typeName;

	public TypeID(String type) {
		// TODO Auto-generated constructor stub
		setTypeName(type);
	}

	public TypeID(String type, int pointerNum) {
		// TODO Auto-generated constructor stub
		setTypeName(type);
		setPointerNum(pointerNum);
	}

	@Override
	public VariableType getType() {
		// TODO Auto-generated method stub
		return VariableType.TypeID;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public String toString() {
		String result = typeName;
		if (getNextType() != null) {
			result = typeName + " " + getNextType().toString();
		}
		for (int i = pointerNum(); i > 0; i--) {
			result += " *";
		}
		if (getArray() != null) {
			result += getArray().toString();
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof TypeID) {
			return ((TypeID) obj).getTypeName().equals(typeName);
		} else if (obj instanceof String) {
			return ((String) obj).equals(typeName);
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + typeName.hashCode();
		return result;
	}

	@Override
	public TypeID makeClone() {
		// TODO Auto-generated method stub
		TypeID newInstance = new TypeID(typeName);
		if (getNextType() != null) {
			newInstance.setNextType(getNextType().makeClone());
		}
		newInstance.setPointerNum(pointerNum());
		if (getArray() != null) {
			newInstance.setArray(getArray().makeClone());
		}
		return newInstance;
	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	@Override
	public <T> T accept(CodeModelToTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	@Override
	public void accept(CodeModelNoParamVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	@Override
	public <T, T1> T accept(CodeModelToTOneParamVisitor<T, T1> visitor, T1 data) {
		// TODO Auto-generated method stub
		return visitor.visit(this, data);
	}

	@Override
	public <T, T1> T accept(TypeToTOneParamVisitor<T, T1> visitor, T1 data) {
		// TODO Auto-generated method stub
		return visitor.visit(this, data);
	}

	@Override
	public <T> void accept(TypeOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	@Override
	public <T> T accept(TypeToTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	@Override
	public void accept(TypeNoParamVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}
}

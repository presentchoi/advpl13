package codemodel.structure.dcl;

import codemodel.structure.def.StructDefinition;
import codemodel.structure.type.VariableType;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.TypeNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.TypeOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.TypeToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.TypeToTOneParamVisitor;

public class StructType extends Type {
	private StructDefinition struct;

	@Override
	public VariableType getType() {
		// TODO Auto-generated method stub
		return VariableType.STRUCT;
	}

	public String toString() {
		if (struct == null) {
			struct = new StructDefinition();
		}
		String result = struct.getName();
		if (!struct.getName().equals("")) {
			result = "struct " + result;
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
		if (obj instanceof StructType) {
			StructType temp = (StructType) obj;
			if (temp.getStruct() == null && struct == null) {
				return true;
			}
			if (struct != null) {
				if (temp.getStruct().equals(struct)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;
		if (struct != null) {
			result = result * 31 + struct.hashCode();
		}
		return result;
	}

	public void setStruct(StructDefinition struct) {
		this.struct = struct;
	}

	public StructDefinition getStruct() {
		if (struct == null) {
			struct = new StructDefinition();
		}
		return struct;
	}

	public void addInnerStructVariable(VariableDeclaration newVarDec) {
		// TODO Auto-generated method stub
		if (struct == null) {
			struct = new StructDefinition();
		}
		struct.addStructVariable(newVarDec);
	}

	@Override
	public Type makeClone() {
		// TODO Auto-generated method stub
		StructType newInstance = new StructType();
		newInstance.setStruct((StructDefinition) struct.makeClone());
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

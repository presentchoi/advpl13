package codemodel.structure.dcl;

import java.io.PrintStream;

import codemodel.structure.type.DeclarationType;
import codemodel.structure.type.VariableScope;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.DeclarationNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.DeclarationOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.DeclarationToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.DeclarationToTOneParamVisitor;


public class VariableDeclaration extends Declaration {
	private String name = "";
	private Type type;
	private Array array;
	private VariableScope scope;

	public String getName() {
		return name;
	}

	public VariableDeclaration setName(String name) {
		this.name = name;
		return this;
	}

	public VariableDeclaration setType(Type type) {
		this.type = type;
		return this;
	}

	public Type getType() {
		return type;
	}

	public void addType(Type type) {
		if (this.type == null) {
			this.type = type;
		} else {
			Type typeDump = this.type;
			while (typeDump.getNextType() != null) {
				typeDump = typeDump.getNextType();
			}
			typeDump.setNextType(type);
		}
	}

	@Override
	public DeclarationType getDeclarationType() {
		// TODO Auto-generated method stub
		return DeclarationType.VARIABLE;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if (hasArray()) {
			return type.toString() + " " + name + getArray().toString();
		}
		return type.toString() + " " + name;
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
	public <T> void accept(DeclarationOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	@Override
	public <T> T accept(DeclarationToTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	@Override
	public <T, T1> T accept(DeclarationToTOneParamVisitor<T, T1> visitor,
			T1 data) {
		// TODO Auto-generated method stub
		return visitor.visit(this, data);
	}

	@Override
	public void accept(DeclarationNoParamVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	@Override
	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		out.println(prefix + this.toString() + ";");
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof VariableDeclaration) {
			return isEqualName(obj) && isEqualType(obj) && isEqualArray(obj);
		}
		return false;
	}

	private boolean isEqualName(Object obj) {
		boolean isEquals;
		isEquals = name.equals(((VariableDeclaration) obj).getName());
		return isEquals;
	}

	private boolean isEqualType(Object obj) {
		boolean isEquals;
		isEquals = type == null ? (((VariableDeclaration) obj).getType()) == null
				: ((VariableDeclaration) obj).getType().equals(type);
		return isEquals;
	}

	private boolean isEqualArray(Object obj) {
		boolean isEquals;
		isEquals = array == null ? (((VariableDeclaration) obj).getArray()) == null
				: ((VariableDeclaration) obj).getArray().equals(array);
		return isEquals;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;
		result = result * 31 + name.hashCode();
		if (type == null) {
			result = result * 31 + 0;
		} else {
			result = result * 31 + type.hashCode();
		}
		if (array == null) {
			result = result * 31 + 0;
		} else {
			result = result * 31 + array.hashCode();
		}
		return result;
	}

	public void addArrayType(Array array) {
		// TODO Auto-generated method stub
		if (this.getArray() == null) {
			this.setArray(array);
		} else {
			Array typeDump = this.getArray();
			while (typeDump.getNextArray() != null) {
				typeDump = typeDump.getNextArray();
			}
			typeDump.setNextArray(array);
		}
	}

	public boolean hasArray() {
		return getArray() != null;
	}

	public void setArray(Array array) {
		this.array = array;
	}

	public Array getArray() {
		return array;
	}

	public void setScope(VariableScope scope) {
		this.scope = scope;
	}

	public VariableScope getScope() {
		return scope;
	}

	@Override
	public VariableDeclaration makeClone() {
		// TODO Auto-generated method stub
		VariableDeclaration newInstance = new VariableDeclaration();
		if (array != null) {
			newInstance.setArray(array.makeClone());
		}
		newInstance.setName(name);
		newInstance.setScope(scope);
		newInstance.setType(type.makeClone());
		return newInstance;
	}

    @Override
    public void printWithLineNum(PrintStream out, String prefix) {
        // TODO Auto-generated method stub
        out.println(getLineNum() + prefix + toString() + ";");        
    }
}

package codemodel.structure.def;

import java.io.PrintStream;
import java.util.Vector;

import codemodel.structure.dcl.Declaration;
import codemodel.structure.dcl.VariableDeclaration;
import codemodel.structure.type.DefinitionType;
import codemodel.structure.type.VariableScope;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.DefinitionNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.DefinitionOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.DefinitionToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.DefinitionToTOneParamVisitor;


public class StructDefinition implements Definition {
	private Vector<VariableDeclaration> structVariable = new Vector<VariableDeclaration>();
	private String name = "";
	private VariableScope scope;

	@Override
	public DefinitionType getDefinitionType() {
		// TODO Auto-generated method stub
		return DefinitionType.STRUCT;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setStructVariable(Vector<VariableDeclaration> structVariable) {
		this.structVariable = structVariable;
	}

	public void addStructVariable(VariableDeclaration structVariable) {
		this.structVariable.add(structVariable);
	}

	public Vector<VariableDeclaration> getStructVariable() {
		return structVariable;
	}

	@Override
	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		out.println(prefix + toString());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;
		for (Declaration d : structVariable) {
			result += 31 * d.hashCode();
		}
		result += 31 * name.hashCode();
		return result;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("struct " + name + " {\n");
		for (Declaration d : structVariable) {
			sb.append("\t" + d.toString() + ";\n");
		}
		sb.append("};");
		return sb.toString();
	}

	@Override
	public void accept(CodeModelNoParamVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	@Override
	public <T> T accept(CodeModelToTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	@Override
	public <T> T accept(DefinitionToTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	@Override
	public void accept(DefinitionNoParamVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	@Override
	public <T> void accept(DefinitionOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	@Override
	public <T, T1> T accept(DefinitionToTOneParamVisitor<T, T1> visitor, T1 data) {
		// TODO Auto-generated method stub
		return visitor.visit(this, data);
	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	@Override
	public <T, T1> T accept(CodeModelToTOneParamVisitor<T, T1> visitor, T1 data) {
		// TODO Auto-generated method stub
		return visitor.visit(this, data);
	}

	public void setScope(VariableScope scope) {
		this.scope = scope;
	}

	public VariableScope getScope() {
		return scope;
	}

	@Override
	public StructDefinition makeClone() {
		// TODO Auto-generated method stub
		StructDefinition newInstance = new StructDefinition();
		newInstance.setName(name);
		newInstance.setScope(scope);
		for (VariableDeclaration d : structVariable) {
			newInstance.addStructVariable(d.makeClone());
		}
		return newInstance;
	}
}

package codemodel.structure.def;

import java.io.PrintStream;

import codemodel.structure.dcl.Type;
import codemodel.structure.type.DefinitionType;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.DefinitionNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.DefinitionOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.DefinitionToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.DefinitionToTOneParamVisitor;


public class TypedefDefinition extends Definition {
	private Type postType;
	private Type preType;

	@Override
	public DefinitionType getDefinitionType() {
		// TODO Auto-generated method stub
		return DefinitionType.TYPEDEF;
	}

	@Override
	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		out.println(prefix + toString() + ";");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "typedef (" + getPreType().toString() + ") ("
				+ getPostType().toString() + ")";
	}

	public void setPostType(Type postType) {
		this.postType = postType;
	}

	public Type getPostType() {
		return postType;
	}

	public void setPreType(Type preType) {
		this.preType = preType;
	}

	public Type getPreType() {
		return preType;
	}

	@Override
	public void accept(CodeModelNoParamVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
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
	public <T> T accept(CodeModelToTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	@Override
	public <T, T1> T accept(CodeModelToTOneParamVisitor<T, T1> visitor, T1 data) {
		// TODO Auto-generated method stub
		return visitor.visit(this, data);
	}

	@Override
	public TypedefDefinition makeClone() {
		// TODO Auto-generated method stub
		TypedefDefinition newInstance = new TypedefDefinition();
		newInstance.setPostType(postType.makeClone());
		newInstance.setPreType(preType.makeClone());
		return newInstance;
	}

    @Override
    public void printWithLineNum(PrintStream out, String prefix) {
        // TODO Auto-generated method stub
        out.println(getLineNum() + prefix + toString() + ";");
    }
}

package codemodel.structure.def;

import java.io.PrintStream;

import codemodel.structure.dcl.FunctionDeclaration;
import codemodel.structure.stat.Block;
import codemodel.structure.type.DefinitionType;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.DefinitionNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.DefinitionOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.DefinitionToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.DefinitionToTOneParamVisitor;


public class FunctionDefinition extends Definition {
	private FunctionDeclaration functionSignature = new FunctionDeclaration();
	private Block block = new Block();
	private static final FunctionDefinition nullInstance = new FunctionDefinition();

	public static final FunctionDefinition getNullInstance() {
		return nullInstance;
	}

	public void setFunctionSignature(FunctionDeclaration functionSignature) {
		this.functionSignature = functionSignature;
	}

	public FunctionDeclaration getFunctionSignature() {
		return functionSignature;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Block getBlock() {
		return block;
	}

	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		printSignature(out, prefix);
		block.print(out, prefix);
	}

	private void printSignature(PrintStream out, String prefix) {
		out.print(prefix);
		out.println(functionSignature.toString());
	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

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
	public String toString() {
		// TODO Auto-generated method stub
		return functionSignature.toString();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof FunctionDefinition) {
			FunctionDefinition temp = (FunctionDefinition) obj;
			return temp.getFunctionSignature().equals(functionSignature);
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;
		result = result * 31 + functionSignature.hashCode();
		result = result * 31 + block.hashCode();
		return result;
	}

	@Override
	public DefinitionType getDefinitionType() {
		// TODO Auto-generated method stub
		return DefinitionType.FUNCTION;
	}

	@Override
	public FunctionDefinition makeClone() {
		// TODO Auto-generated method stub
		FunctionDefinition newInstance = new FunctionDefinition();
		newInstance.setBlock(block.makeClone());
		newInstance.setFunctionSignature(functionSignature.makeClone());
		return newInstance;
	}

	@Override
	public <T> T accept(CodeModelToTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

    @Override
    public void printWithLineNum(PrintStream out, String prefix) {
        // TODO Auto-generated method stub
        out.print(getLineNum());
        printSignature(out, prefix);
        block.printWithLineNum(out, prefix);
    }
}

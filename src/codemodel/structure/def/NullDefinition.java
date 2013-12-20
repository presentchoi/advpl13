package codemodel.structure.def;

import java.io.PrintStream;

import codemodel.structure.type.DefinitionType;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.DefinitionNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.DefinitionOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.DefinitionToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.DefinitionToTOneParamVisitor;


public class NullDefinition extends Definition {
	private static final NullDefinition instance = new NullDefinition();

	public static final NullDefinition getInstance() {
		return instance;
	}

	@Override
	public <T> T accept(CodeModelToTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T accept(DefinitionToTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void accept(CodeModelNoParamVisitor visitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void accept(DefinitionNoParamVisitor visitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void accept(DefinitionOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T, T1> T accept(CodeModelToTOneParamVisitor<T, T1> visitor, T1 data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, T1> T accept(DefinitionToTOneParamVisitor<T, T1> visitor, T1 data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefinitionType getDefinitionType() {
		// TODO Auto-generated method stub
		return DefinitionType.NULL;
	}

	@Override
	public Definition makeClone() {
		// TODO Auto-generated method stub
		return getInstance();
	}

	@Override
	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub

	}

    @Override
    public void printWithLineNum(PrintStream out, String prefix) {
        // TODO Auto-generated method stub
        
    }

}

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


public interface Definition {
	public DefinitionType getDefinitionType();

	public void print(PrintStream out, String prefix);

	public <T> T accept(CodeModelToTVisitor<T> visitor);

	public <T> T accept(DefinitionToTVisitor<T> visitor);

	public void accept(CodeModelNoParamVisitor visitor);

	public void accept(DefinitionNoParamVisitor visitor);

	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data);

	public <T> void accept(DefinitionOneParamVisitor<T> visitor, T data);

	public <T, T1> T accept(CodeModelToTOneParamVisitor<T, T1> visitor, T1 data);

	public <T, T1> T accept(DefinitionToTOneParamVisitor<T, T1> visitor, T1 data);

	public Definition makeClone();
}

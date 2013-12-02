package codemodel.structure.dcl;

import java.io.PrintStream;

import codemodel.structure.type.DeclarationType;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.DeclarationNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.DeclarationOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.DeclarationToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.DeclarationToTOneParamVisitor;


public interface Declaration {
	public DeclarationType getDeclarationType();

	public void print(PrintStream out, String prefix);

	public Declaration makeClone();

	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data);

	public <T> void accept(DeclarationOneParamVisitor<T> visitor, T data);

	public <T> T accept(CodeModelToTVisitor<T> visitor);

	public <T> T accept(DeclarationToTVisitor<T> visitor);

	public <T, T1> T accept(CodeModelToTOneParamVisitor<T, T1> visitor, T1 data);

	public <T, T1> T accept(DeclarationToTOneParamVisitor<T, T1> visitor,
			T1 data);

	public void accept(CodeModelNoParamVisitor visitor);

	public void accept(DeclarationNoParamVisitor visitor);
}

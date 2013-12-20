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

public abstract class Declaration {
    private int lineNum;

    public abstract DeclarationType getDeclarationType();

    public abstract void print(PrintStream out, String prefix);

    public abstract void printWithLineNum(PrintStream out, String prefix);

    public abstract Declaration makeClone();

    public abstract <T> void accept(CodeModelOneParamVisitor<T> visitor, T data);

    public abstract <T> void accept(DeclarationOneParamVisitor<T> visitor,
            T data);

    public abstract <T> T accept(CodeModelToTVisitor<T> visitor);

    public abstract <T> T accept(DeclarationToTVisitor<T> visitor);

    public abstract <T, T1> T accept(
            CodeModelToTOneParamVisitor<T, T1> visitor, T1 data);

    public abstract <T, T1> T accept(
            DeclarationToTOneParamVisitor<T, T1> visitor, T1 data);

    public abstract void accept(CodeModelNoParamVisitor visitor);

    public abstract void accept(DeclarationNoParamVisitor visitor);

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }
}

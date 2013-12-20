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

public abstract class Definition {
    private int lineNum;

    public abstract DefinitionType getDefinitionType();

    public abstract void print(PrintStream out, String prefix);

    public abstract void printWithLineNum(PrintStream out, String prefix);

    public abstract <T> T accept(CodeModelToTVisitor<T> visitor);

    public abstract <T> T accept(DefinitionToTVisitor<T> visitor);

    public abstract void accept(CodeModelNoParamVisitor visitor);

    public abstract void accept(DefinitionNoParamVisitor visitor);

    public abstract <T> void accept(CodeModelOneParamVisitor<T> visitor, T data);

    public abstract <T> void accept(DefinitionOneParamVisitor<T> visitor, T data);

    public abstract <T, T1> T accept(CodeModelToTOneParamVisitor<T, T1> visitor, T1 data);

    public abstract <T, T1> T accept(DefinitionToTOneParamVisitor<T, T1> visitor, T1 data);

    public abstract Definition makeClone();

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }
}

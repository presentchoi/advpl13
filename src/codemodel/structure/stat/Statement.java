package codemodel.structure.stat;

import java.io.PrintStream;

import codemodel.structure.type.StatementType;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.StatementNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.StatementOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.StatementToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.StatementToTOneParamVisitor;

/**
 * @author hjchoi (gonoki@pusan.ac.kr)
 */
public abstract class Statement {
    private int lineNum;

    public abstract StatementType getType();

    /**
     * out에 자신을 포함한 자손 Statement들을 prefix를 붙여서 출력한다 <br>
     * prefix가 필요하지 않을 경우 ""를 입력하면 된다
     * 
     * @param PrintStream
     *            out, String prefix
     */
    public abstract void print(PrintStream out, String prefix);

    public abstract void printWithLineNum(PrintStream out, String prefix);
    
    public abstract <T> void accept(CodeModelOneParamVisitor<T> visitor, T data);

    public abstract <T> T accept(CodeModelToTVisitor<T> visitor);

    public abstract <T, T1> T accept(CodeModelToTOneParamVisitor<T, T1> visitor, T1 data);

    public abstract void accept(CodeModelNoParamVisitor visitor);

    public abstract <T> T accept(StatementToTVisitor<T> visitor);

    public abstract void accept(StatementNoParamVisitor visitor);

    public abstract <T, T1> T accept(StatementToTOneParamVisitor<T, T1> visitor, T1 data);

    public abstract <T> void accept(StatementOneParamVisitor<T> visitor, T data);

    /**
     * 입력한 type과 동일한 type을 가지는 자신을 포함한 자손 Statement들의 수를 반환한다
     * 
     * @param StatementType
     *            type
     * @return int
     */
    public abstract int getStatementNum(StatementType type);

    public abstract Statement makeClone();

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }
}

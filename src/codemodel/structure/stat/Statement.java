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
public interface Statement {
	public StatementType getType();

	/**
	 * out에 자신을 포함한 자손 Statement들을 prefix를 붙여서 출력한다 <br>
	 * prefix가 필요하지 않을 경우 ""를 입력하면 된다
	 * 
	 * @param PrintStream
	 *            out, String prefix
	 */
	public void print(PrintStream out, String prefix);

	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data);

	public <T> T accept(CodeModelToTVisitor<T> visitor);

	public <T, T1> T accept(CodeModelToTOneParamVisitor<T, T1> visitor, T1 data);

	public void accept(CodeModelNoParamVisitor visitor);

	public <T> T accept(StatementToTVisitor<T> visitor);

	public void accept(StatementNoParamVisitor visitor);

	public <T, T1> T accept(StatementToTOneParamVisitor<T, T1> visitor, T1 data);

	public <T> void accept(StatementOneParamVisitor<T> visitor, T data);

	/**
	 * 입력한 type과 동일한 type을 가지는 자신을 포함한 자손 Statement들의 수를 반환한다
	 * 
	 * @param StatementType
	 *            type
	 * @return int
	 */
	public int getStatementNum(StatementType type);

	public Statement makeClone();
}

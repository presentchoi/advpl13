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
	 * out�� �ڽ��� ������ �ڼ� Statement���� prefix�� �ٿ��� ����Ѵ� <br>
	 * prefix�� �ʿ����� ���� ��� ""�� �Է��ϸ� �ȴ�
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
	 * �Է��� type�� ������ type�� ������ �ڽ��� ������ �ڼ� Statement���� ���� ��ȯ�Ѵ�
	 * 
	 * @param StatementType
	 *            type
	 * @return int
	 */
	public int getStatementNum(StatementType type);

	public Statement makeClone();
}

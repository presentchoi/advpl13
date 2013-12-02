package codemodel.structure.exp;

import java.io.PrintStream;
import java.util.List;

import codemodel.structure.dcl.VariableDeclaration;
import codemodel.structure.def.StructDefinition;
import codemodel.structure.type.ExpressionType;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.ExpressionNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.ExpressionOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.ExpressionToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.ExpressionToTOneParamVisitor;


/**
 * @author hjchoi (gonoki@pusan.ac.kr)
 * 
 */
public abstract class Expression {
	private Expression parentExpression;

	public abstract String getID();

	public abstract List<String> getIDs();

	public abstract ExpressionType getType();

	public void print(PrintStream out) {
		out.print(toString());
	}

	void setParentExpression(Expression parentExpression) {
		this.parentExpression = parentExpression;
	}

	public Expression getParentExpression() {
		return parentExpression;
	}

	public abstract <T> T accept(CodeModelToTVisitor<T> visitor);

	public abstract <T> T accept(ExpressionToTVisitor<T> visitor);

	public abstract void accept(CodeModelNoParamVisitor visitor);

	public abstract void accept(ExpressionNoParamVisitor visitor);

	public abstract <T, T1> T accept(
			CodeModelToTOneParamVisitor<T, T1> visitor, T1 data);

	public abstract <T, T1> T accept(
			ExpressionToTOneParamVisitor<T, T1> visitor, T1 data);

	public abstract <T> void accept(CodeModelOneParamVisitor<T> visitor, T data);

	public abstract <T> void accept(ExpressionOneParamVisitor<T> visitor, T data);

	/**
	 * List<String> ���·� Expression�� ��ȯ�Ѵ�
	 * 
	 * @return List<String>
	 */
	public abstract List<String> toStringList();

	/**
	 * List<ExpressionLiteral> ���·� Expression�� ��ȯ�Ѵ�
	 * 
	 * @return List<ExpressionLiteral>
	 */
	public abstract List<ExpressionLiteral> toExpressionLiteralList();

	/**
	 * Ŭ�� ��ü�� �����Ͽ� ��ȯ�Ѵ�
	 * 
	 * @return Expression
	 */
	public abstract Expression makeClone();

	/**
	 * �Է��� type�� ������ type�� ������ �ڽ��� ������ �ڼ� Expression���� ���� ��ȯ�Ѵ�
	 * 
	 * @param ExpressionType
	 *            type
	 * @return int
	 */
	public abstract int getExpressionNum(ExpressionType type);

	/**
	 * List<Expression> ���·� �Է��� type�� ������ type�� ������ Expression�� ��ȯ�Ѵ�
	 * 
	 * @return List<Expression>
	 */
	public abstract List<Expression> getExpression(ExpressionType type);

	/**
	 * Condition�� ������ Expression�� ��ȯ�Ѵ�
	 * 
	 * @return Expression
	 */
	public abstract Expression getAlternativeExpression();

	/**
	 * �ڼ� Expression �� VariableDeclaration�� ��� �ִ� List<VariableDeclaration>��
	 * ��ȯ�Ѵ�
	 * 
	 * @return List<VariableDeclaration>
	 */
	public abstract List<VariableDeclaration> getVariableDeclaration();

	/**
	 * �ڼ� Expression �� �� ������ ������ �Ǵ� VariableDeclaration�� ��� �ִ�
	 * List<VariableDeclaration>�� ��ȯ�Ѵ�
	 * 
	 * @return List<VariableDeclaration>
	 */
	public abstract List<VariableDeclaration> getDeclareRequiredVariableDeclaration();

	/**
	 * �ڼ� StructExpression�� ã�Ƴ� StructDefinition�� ��� �ִ�
	 * List<StructDefinition>�� ��ȯ�Ѵ�
	 * 
	 * @return List<StructDefinition>
	 */
	public abstract List<StructDefinition> getStructDefinition();

	/**
	 * parentExpression�� infinite loop ������ �߻��� �� �ֱ� ������ ������� �ʴ´�
	 */
	@Override
	public abstract int hashCode();

	/**
	 * parentExpression�� infinite loop ������ �߻��� �� �ֱ� ������ ������� �ʴ´�
	 */
	@Override
	public abstract boolean equals(Object obj);
}

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
	 * List<String> 형태로 Expression을 반환한다
	 * 
	 * @return List<String>
	 */
	public abstract List<String> toStringList();

	/**
	 * List<ExpressionLiteral> 형태로 Expression을 반환한다
	 * 
	 * @return List<ExpressionLiteral>
	 */
	public abstract List<ExpressionLiteral> toExpressionLiteralList();

	/**
	 * 클론 객체를 생성하여 반환한다
	 * 
	 * @return Expression
	 */
	public abstract Expression makeClone();

	/**
	 * 입력한 type과 동일한 type을 가지는 자신을 포함한 자손 Expression들의 수를 반환한다
	 * 
	 * @param ExpressionType
	 *            type
	 * @return int
	 */
	public abstract int getExpressionNum(ExpressionType type);

	/**
	 * List<Expression> 형태로 입력한 type과 동일한 type을 가지는 Expression을 반환한다
	 * 
	 * @return List<Expression>
	 */
	public abstract List<Expression> getExpression(ExpressionType type);

	/**
	 * Condition이 반전된 Expression을 반환한다
	 * 
	 * @return Expression
	 */
	public abstract Expression getAlternativeExpression();

	/**
	 * 자손 Expression 중 VariableDeclaration을 담고 있는 List<VariableDeclaration>을
	 * 반환한다
	 * 
	 * @return List<VariableDeclaration>
	 */
	public abstract List<VariableDeclaration> getVariableDeclaration();

	/**
	 * 자손 Expression 중 비교 연산의 단위가 되는 VariableDeclaration을 담고 있는
	 * List<VariableDeclaration>을 반환한다
	 * 
	 * @return List<VariableDeclaration>
	 */
	public abstract List<VariableDeclaration> getDeclareRequiredVariableDeclaration();

	/**
	 * 자손 StructExpression을 찾아내 StructDefinition을 담고 있는
	 * List<StructDefinition>을 반환한다
	 * 
	 * @return List<StructDefinition>
	 */
	public abstract List<StructDefinition> getStructDefinition();

	/**
	 * parentExpression은 infinite loop 문제가 발생할 수 있기 때문에 계산하지 않는다
	 */
	@Override
	public abstract int hashCode();

	/**
	 * parentExpression은 infinite loop 문제가 발생할 수 있기 때문에 계산하지 않는다
	 */
	@Override
	public abstract boolean equals(Object obj);
}

package codemodel.structure.exp;

import java.util.ArrayList;
import java.util.List;

import codemodel.structure.dcl.StructType;
import codemodel.structure.dcl.VariableDeclaration;
import codemodel.structure.def.StructDefinition;
import codemodel.structure.type.ExpressionLiteralType;
import codemodel.structure.type.ExpressionType;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.ExpressionNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.ExpressionOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.ExpressionToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.ExpressionToTOneParamVisitor;


public class RoundBracketExpression extends Expression {

	public static final String CLOSE_ROUND_BRACKET = ")";
	public static final String OPEN_ROUND_BRACKET = "(";
	private Expression innerExpression;
	private String id;
	private boolean needUpdateId = true;

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof RoundBracketExpression) {
			return innerExpression.equals(((RoundBracketExpression) obj)
					.getInnerExpression());
		}
		return false;
	}

	@Override
	public List<StructDefinition> getStructDefinition() {
		// TODO Auto-generated method stub
		List<VariableDeclaration> temp = getVariableDeclaration();
		List<StructDefinition> result = new ArrayList<StructDefinition>();
		for (VariableDeclaration v : temp) {
			if (v.getType() instanceof StructType) {
				result.add(((StructType) v.getType()).getStruct());
			}
		}
		return result;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;
		result = result * 31 + innerExpression.hashCode();
		return result;
	}

	@Override
	public ExpressionType getType() {
		// TODO Auto-generated method stub
		return ExpressionType.ROUND_BRACKET;
	}

	public void setInnerExpression(Expression innerExpression) {
		this.innerExpression = innerExpression;
		innerExpression.setParentExpression(this);
		needUpdateId = true;
	}

	public Expression getInnerExpression() {
		return innerExpression;
	}

	public String toString() {
		return OPEN_ROUND_BRACKET + innerExpression.toString()
				+ CLOSE_ROUND_BRACKET;
	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	@Override
	public List<String> toStringList() {
		// TODO Auto-generated method stub
		List<String> temp = new ArrayList<String>();
		temp.add(OPEN_ROUND_BRACKET);
		temp.addAll(innerExpression.toStringList());
		temp.add(CLOSE_ROUND_BRACKET);
		return temp;
	}

	@Override
	public Expression makeClone() {
		// TODO Auto-generated method stub
		RoundBracketExpression exp = new RoundBracketExpression();
		exp.setInnerExpression(innerExpression.makeClone());
		return exp;
	}

	@Override
	public int getExpressionNum(ExpressionType type) {
		// TODO Auto-generated method stub
		int result = 0;
		if (type == getType()) {
			result++;
		}
		result += innerExpression.getExpressionNum(type);
		return result;
	}

	@Override
	public List<Expression> getExpression(ExpressionType type) {
		// TODO Auto-generated method stub
		List<Expression> temp = new ArrayList<Expression>();
		if (type == getType() || type == ExpressionType.ALL) {
			temp.add(this);
		}
		temp.addAll(innerExpression.getExpression(type));
		return temp;
	}

	@Override
	public Expression getAlternativeExpression() {
		// TODO Auto-generated method stub
		RoundBracketExpression exp = new RoundBracketExpression();
		exp.setInnerExpression(innerExpression.getAlternativeExpression());
		return exp;
	}

	@Override
	public List<VariableDeclaration> getVariableDeclaration() {
		// TODO Auto-generated method stub
		List<VariableDeclaration> temp = new ArrayList<VariableDeclaration>();
		temp.addAll(innerExpression.getVariableDeclaration());
		return temp;
	}

	@Override
	public List<ExpressionLiteral> toExpressionLiteralList() {
		// TODO Auto-generated method stub
		List<ExpressionLiteral> temp = new ArrayList<ExpressionLiteral>();

		ExpressionLiteral openRoundBracket = new ExpressionLiteral();
		openRoundBracket.setType(ExpressionLiteralType.OPEN_ROUND_BRACKET);
		openRoundBracket.setLiteral(ExpressionLiteralType.OPEN_ROUND_BRACKET
				.toString());
		temp.add(openRoundBracket);

		temp.addAll(innerExpression.toExpressionLiteralList());

		ExpressionLiteral closeRoundBracket = new ExpressionLiteral();
		closeRoundBracket.setType(ExpressionLiteralType.CLOSE_ROUND_BRACKET);
		closeRoundBracket.setLiteral(ExpressionLiteralType.CLOSE_ROUND_BRACKET
				.toString());
		temp.add(closeRoundBracket);

		return temp;
	}

	@Override
	public List<VariableDeclaration> getDeclareRequiredVariableDeclaration() {
		// TODO Auto-generated method stub
		List<VariableDeclaration> temp = new ArrayList<VariableDeclaration>();
		temp.addAll(innerExpression.getDeclareRequiredVariableDeclaration());
		return temp;
	}

	@Override
	public <T> T accept(CodeModelToTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	@Override
	public void accept(CodeModelNoParamVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	@Override
	public <T, T1> T accept(CodeModelToTOneParamVisitor<T, T1> visitor, T1 data) {
		// TODO Auto-generated method stub
		return visitor.visit(this, data);
	}

	@Override
	public <T> T accept(ExpressionToTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	@Override
	public void accept(ExpressionNoParamVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	@Override
	public <T, T1> T accept(ExpressionToTOneParamVisitor<T, T1> visitor, T1 data) {
		// TODO Auto-generated method stub
		return visitor.visit(this, data);
	}

	@Override
	public <T> void accept(ExpressionOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		if (needUpdateId == true) {
			updateID();
			needUpdateId = false;
		}
		return id;
	}

	private void updateID() {
		int hashCode = hashCode();
		if (hashCode < 0) {
			id = "R_" + -hashCode;
		} else {
			id = "R" + hashCode;
		}
	}

	@Override
	public List<String> getIDs() {
		// TODO Auto-generated method stub
		List<String> newInstance = new ArrayList<String>();
		newInstance.add(getID());
		newInstance.addAll(getInnerExpression().getIDs());
		return newInstance;
	}
}

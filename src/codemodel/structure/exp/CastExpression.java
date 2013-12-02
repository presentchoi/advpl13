package codemodel.structure.exp;

import java.util.ArrayList;
import java.util.List;

import codemodel.structure.dcl.StructType;
import codemodel.structure.dcl.Type;
import codemodel.structure.dcl.VariableDeclaration;
import codemodel.structure.def.StructDefinition;
import codemodel.structure.type.ExpressionLiteralType;
import codemodel.structure.type.ExpressionType;
import codemodel.structure.type.UnaryOperatorType;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.ExpressionNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.ExpressionOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.ExpressionToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.ExpressionToTOneParamVisitor;


public class CastExpression extends Expression {
	public static final String CASTING_CLOSE_ROUND_BRACKET = ")";
	public static final String CASTING_OPEN_ROUND_BRACKET = "(";
	private Type castingType;
	private Expression castedExpression;
	private String id;
	private boolean needUpdateId = true;

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof CastExpression) {
			return castingType.equals(((CastExpression) obj).getCastingType())
					&& castedExpression.equals(((CastExpression) obj)
							.getCastedExpression());
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
		result = result * 31 + castingType.hashCode();
		result = result * 31 + castedExpression.hashCode();
		return result;
	}

	@Override
	public ExpressionType getType() {
		// TODO Auto-generated method stub
		return ExpressionType.CAST;
	}

	public void setCastedExpression(Expression castedExpression) {
		this.castedExpression = castedExpression;
		castedExpression.setParentExpression(this);
		needUpdateId = true;
	}

	public Expression getCastedExpression() {
		return castedExpression;
	}

	public String toString() {
		return CASTING_OPEN_ROUND_BRACKET + getCastingType().toString()
				+ CASTING_CLOSE_ROUND_BRACKET + " "
				+ CASTING_OPEN_ROUND_BRACKET + castedExpression.toString()
				+ CASTING_CLOSE_ROUND_BRACKET;
	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	public void setCastingType(Type castingType) {
		this.castingType = castingType;
		needUpdateId = true;
	}

	public Type getCastingType() {
		return castingType;
	}

	@Override
	public List<String> toStringList() {
		// TODO Auto-generated method stub
		List<String> temp = new ArrayList<String>();
		temp.add(CASTING_OPEN_ROUND_BRACKET);
		temp.add(getCastingType().toString());
		temp.add(CASTING_CLOSE_ROUND_BRACKET);
		temp.add(CASTING_OPEN_ROUND_BRACKET);
		temp.addAll(castedExpression.toStringList());
		temp.add(CASTING_CLOSE_ROUND_BRACKET);
		return temp;
	}

	@Override
	public Expression makeClone() {
		// TODO Auto-generated method stub
		CastExpression exp = new CastExpression();
		exp.setCastingType(castingType);
		exp.setCastedExpression(castedExpression.makeClone());
		return exp;
	}

	@Override
	public int getExpressionNum(ExpressionType type) {
		// TODO Auto-generated method stub
		int result = 0;
		if (type == getType()) {
			result++;
		}
		result += castedExpression.getExpressionNum(type);
		return result;
	}

	@Override
	public List<Expression> getExpression(ExpressionType type) {
		// TODO Auto-generated method stub
		List<Expression> temp = new ArrayList<Expression>();
		if (type == getType() || type == ExpressionType.ALL) {
			temp.add(this);
		}
		temp.addAll(castedExpression.getExpression(type));
		return temp;
	}

	@Override
	public Expression getAlternativeExpression() {
		// TODO Auto-generated method stub
		UnaryExpression notExp = new UnaryExpression();
		RoundBracketExpression bracketExp = new RoundBracketExpression();

		notExp.setOperator(UnaryOperatorType.NOT);
		notExp.setPostOperator(false);
		notExp.setOperand(bracketExp);

		bracketExp.setInnerExpression(this.makeClone());
		return notExp;
	}

	@Override
	public List<VariableDeclaration> getVariableDeclaration() {
		// TODO Auto-generated method stub
		List<VariableDeclaration> temp = new ArrayList<VariableDeclaration>();
		temp.addAll(castedExpression.getVariableDeclaration());
		return temp;
	}

	@Override
	public List<ExpressionLiteral> toExpressionLiteralList() {
		// TODO Auto-generated method stub
		List<ExpressionLiteral> temp = new ArrayList<ExpressionLiteral>();

		ExpressionLiteral castingOpenRoundBracket = new ExpressionLiteral();
		castingOpenRoundBracket
				.setType(ExpressionLiteralType.CASTING_OPEN_ROUND_BRACKET);
		castingOpenRoundBracket
				.setLiteral(ExpressionLiteralType.CASTING_OPEN_ROUND_BRACKET
						.toString());

		ExpressionLiteral castingCloseRoundBracket = new ExpressionLiteral();
		castingCloseRoundBracket
				.setType(ExpressionLiteralType.CASTING_CLOSE_ROUND_BRACKET);
		castingCloseRoundBracket
				.setLiteral(ExpressionLiteralType.CASTING_CLOSE_ROUND_BRACKET
						.toString());

		temp.add(castingOpenRoundBracket);

		ExpressionLiteral castingTypeLiteral = new ExpressionLiteral();
		castingTypeLiteral.setType(ExpressionLiteralType.TYPE);
		castingTypeLiteral.setLiteral(this.castingType.toString());
		temp.add(castingTypeLiteral);

		temp.add(castingCloseRoundBracket);

		temp.add(castingOpenRoundBracket);

		temp.addAll(castedExpression.toExpressionLiteralList());

		temp.add(castingCloseRoundBracket);

		return temp;
	}

	@Override
	public List<VariableDeclaration> getDeclareRequiredVariableDeclaration() {
		// TODO Auto-generated method stub
		List<VariableDeclaration> temp = new ArrayList<VariableDeclaration>();
		temp.addAll(castedExpression.getDeclareRequiredVariableDeclaration());
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
			id = "C_" + -hashCode;
		} else {
			id = "C" + hashCode;
		}
	}

	@Override
	public List<String> getIDs() {
		// TODO Auto-generated method stub
		List<String> newInstance = new ArrayList<String>();
		newInstance.add(getID());
		newInstance.addAll(getCastedExpression().getIDs());
		return newInstance;
	}
}

package codemodel.structure.exp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import codemodel.structure.dcl.StructType;
import codemodel.structure.dcl.VariableDeclaration;
import codemodel.structure.def.StructDefinition;
import codemodel.structure.type.ConstantType;
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


public class ConstantExpression extends Expression {
	private String literal;
	private ConstantType constantType = ConstantType.UNKNOWN;
	private String id;
	private boolean needUpdateId = true;

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof ConstantExpression) {
			return literal.equals(((ConstantExpression) obj).getLiteral())
					&& constantType.equals(((ConstantExpression) obj)
							.getConstantType());
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
		result = result * 31 + literal.hashCode();
		result = result * 31 + constantType.hashCode();
		return result;
	}

	@Override
	public ExpressionType getType() {
		// TODO Auto-generated method stub
		return ExpressionType.CONSTANT;
	}

	public void setLiteral(String literal) {
		this.literal = literal;
		needUpdateId = true;
	}

	public String getLiteral() {
		return literal;
	}

	public void setConstantType(ConstantType constantType) {
		this.constantType = constantType;
		needUpdateId = true;
	}

	public ConstantType getConstantType() {
		return constantType;
	}

	public String toString() {
		return literal;
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
		temp.add(literal);
		return temp;
	}

	@Override
	public Expression makeClone() {
		// TODO Auto-generated method stub
		ConstantExpression exp = new ConstantExpression();
		exp.setConstantType(constantType);
		exp.setLiteral(literal);
		return exp;
	}

	@Override
	public int getExpressionNum(ExpressionType type) {
		// TODO Auto-generated method stub
		int result = 0;
		if (type == getType()) {
			result++;
		}
		return result;
	}

	@Override
	public List<Expression> getExpression(ExpressionType type) {
		// TODO Auto-generated method stub
		List<Expression> temp = new ArrayList<Expression>();
		if (type == getType() || type == ExpressionType.ALL) {
			temp.add(this);
		}
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
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<ExpressionLiteral> toExpressionLiteralList() {
		// TODO Auto-generated method stub
		List<ExpressionLiteral> temp = new ArrayList<ExpressionLiteral>();

		ExpressionLiteral constant = new ExpressionLiteral();
		constant.setType(ExpressionLiteralType.CONSTANT);
		constant.setLiteral(getLiteral());

		temp.add(constant);

		return temp;
	}

	@Override
	public List<VariableDeclaration> getDeclareRequiredVariableDeclaration() {
		// TODO Auto-generated method stub
		return Collections.EMPTY_LIST;
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
			id = "Ct_" + -hashCode;
		} else {
			id = "Ct" + hashCode;
		}
	}

	@Override
	public List<String> getIDs() {
		// TODO Auto-generated method stub
		List<String> newInstance = new ArrayList<String>();
		newInstance.add(getID());
		return newInstance;
	}
}

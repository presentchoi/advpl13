package codemodel.structure.exp;

import java.util.ArrayList;
import java.util.List;

import codemodel.structure.dcl.StructType;
import codemodel.structure.dcl.VariableDeclaration;
import codemodel.structure.def.StructDefinition;
import codemodel.structure.type.BinaryOperatorType;
import codemodel.structure.type.ConstantType;
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


public class PointerExpression extends Expression {
	public static final String POINT = "*";
	private Expression pointedExpression;
	private String id;
	private boolean needUpdateId = true;

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof PointerExpression) {
			return pointedExpression.equals(((PointerExpression) obj)
					.getPointedExpression());
		}
		return false;
	}

	@Override
	public List<StructDefinition> getStructDefinition() {
		List<VariableDeclaration> temp = getVariableDeclaration();
		List<StructDefinition> result = new ArrayList<StructDefinition>();
		for (VariableDeclaration v : temp) {
			if (v.getType() instanceof StructType) {
				result.add(((StructType) v.getType()).getStruct());
			}
		}
		return result;
	}

	public Expression toPointerExpression() {
		BinaryExpression be = new BinaryExpression();
		ConstantExpression ce = new ConstantExpression();
		ce.setConstantType(ConstantType.INTEGER);
		ce.setLiteral("0");

		be.setLhsOperand(pointedExpression);
		be.setRhsOperand(ce);
		be.setOperator(BinaryOperatorType.PLUS);

		RoundBracketExpression rbe = new RoundBracketExpression();
		rbe.setInnerExpression(be);

		PointerExpression pe = new PointerExpression();
		pe.setPointedExpression(rbe);

		return pe;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;
		result = result * 31 + pointedExpression.hashCode();
		return result;
	}

	@Override
	public ExpressionType getType() {
		// TODO Auto-generated method stub
		return ExpressionType.POINTER;
	}

	public void setPointedExpression(Expression pointedExpression) {
		this.pointedExpression = pointedExpression;
		pointedExpression.setParentExpression(this);
		needUpdateId = true;
	}

	public Expression getPointedExpression() {
		return pointedExpression;
	}

	public String toString() {
		return POINT + pointedExpression.toString();
	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
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
	public List<String> toStringList() {
		// TODO Auto-generated method stub
		List<String> temp = new ArrayList<String>();
		temp.add(POINT);
		temp.addAll(pointedExpression.toStringList());
		return temp;
	}

	@Override
	public Expression makeClone() {
		// TODO Auto-generated method stub
		PointerExpression exp = new PointerExpression();
		exp.setPointedExpression(pointedExpression.makeClone());
		return exp;
	}

	@Override
	public int getExpressionNum(ExpressionType type) {
		// TODO Auto-generated method stub
		int result = 0;
		if (type == getType()) {
			result++;
		}
		result += pointedExpression.getExpressionNum(type);
		return result;
	}

	@Override
	public List<Expression> getExpression(ExpressionType type) {
		// TODO Auto-generated method stub
		List<Expression> temp = new ArrayList<Expression>();
		if (type == getType() || type == ExpressionType.ALL) {
			temp.add(this);
		}
		temp.addAll(pointedExpression.getExpression(type));
		return temp;
	}

	@Override
	public Expression getAlternativeExpression() {
		// TODO Auto-generated method stub
		PointerExpression exp = new PointerExpression();
		exp.setPointedExpression(pointedExpression.getAlternativeExpression());
		return exp;
	}

	@Override
	public List<VariableDeclaration> getVariableDeclaration() {
		// TODO Auto-generated method stub
		List<VariableDeclaration> temp = new ArrayList<VariableDeclaration>();
		temp.addAll(pointedExpression.getVariableDeclaration());
		return temp;
	}

	@Override
	public List<ExpressionLiteral> toExpressionLiteralList() {
		// TODO Auto-generated method stub
		List<ExpressionLiteral> temp = new ArrayList<ExpressionLiteral>();

		ExpressionLiteral pointerLiteral = new ExpressionLiteral();
		pointerLiteral.setType(ExpressionLiteralType.POINTER);
		pointerLiteral.setLiteral(ExpressionLiteralType.POINTER.toString());

		temp.add(pointerLiteral);
		temp.addAll(pointedExpression.toExpressionLiteralList());

		return temp;
	}

	@Override
	public List<VariableDeclaration> getDeclareRequiredVariableDeclaration() {
		// TODO Auto-generated method stub
		List<VariableDeclaration> temp = new ArrayList<VariableDeclaration>();
		temp.addAll(pointedExpression.getDeclareRequiredVariableDeclaration());
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
			id = "P_" + -hashCode;
		} else {
			id = "P" + hashCode;
		}
	}

	@Override
	public List<String> getIDs() {
		// TODO Auto-generated method stub
		List<String> newInstance = new ArrayList<String>();
		newInstance.add(getID());
		newInstance.addAll(getPointedExpression().getIDs());
		return newInstance;
	}
}

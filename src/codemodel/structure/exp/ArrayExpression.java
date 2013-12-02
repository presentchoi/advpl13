package codemodel.structure.exp;

import java.util.ArrayList;
import java.util.List;

import codemodel.structure.dcl.StructType;
import codemodel.structure.dcl.VariableDeclaration;
import codemodel.structure.def.StructDefinition;
import codemodel.structure.type.BinaryOperatorType;
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


public class ArrayExpression extends Expression {

	public static final String CLOSE_BRACKET = "]";
	public static final String OPEN_BRACKET = "[";
	private Expression variable;
	private Expression arrayLocation;
	private String id = "";
	private boolean needUpdateId = true;

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof ArrayExpression) {
			return getVariable().equals(((ArrayExpression) obj).getVariable())
					&& getArrayLocation().equals(
							((ArrayExpression) obj).getArrayLocation());
		}
		return false;
	}

	public Expression toPointerExpression() {
		BinaryExpression be = new BinaryExpression();
		be.setLhsOperand(variable);
		be.setRhsOperand(arrayLocation);
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
		result = result * 31 + variable.hashCode();
		result = result * 31 + arrayLocation.hashCode();
		return result;
	}

	@Override
	public ExpressionType getType() {
		// TODO Auto-generated method stub
		return ExpressionType.ARRAY;
	}

	public void setVariable(Expression variable) {
		this.variable = variable;
		variable.setParentExpression(this);
		needUpdateId = true;
	}

	public Expression getVariable() {
		return variable;
	}

	public void setArrayLocation(Expression arrayLocation) {
		this.arrayLocation = arrayLocation;
		arrayLocation.setParentExpression(this);
		needUpdateId = true;
	}

	public Expression getArrayLocation() {
		return arrayLocation;
	}

	public String toString() {
		return variable.toString() + OPEN_BRACKET + arrayLocation.toString()
				+ CLOSE_BRACKET;
	}

	@Override
	public List<String> toStringList() {
		// TODO Auto-generated method stub
		List<String> temp = variable.toStringList();
		temp.add(OPEN_BRACKET);
		temp.addAll(arrayLocation.toStringList());
		temp.add(CLOSE_BRACKET);
		return temp;
	}

	@Override
	public Expression makeClone() {
		// TODO Auto-generated method stub
		ArrayExpression exp = new ArrayExpression();
		exp.setArrayLocation(arrayLocation.makeClone());
		exp.setVariable(variable.makeClone());
		return exp;
	}

	@Override
	public int getExpressionNum(ExpressionType type) {
		// TODO Auto-generated method stub
		int result = 0;
		if (type == getType()) {
			result++;
		}
		result += variable.getExpressionNum(type);
		result += arrayLocation.getExpressionNum(type);
		return result;
	}

	@Override
	public List<Expression> getExpression(ExpressionType type) {
		// TODO Auto-generated method stub
		List<Expression> temp = new ArrayList<Expression>();
		if (type == getType() || type == ExpressionType.ALL) {
			temp.add(this);
		}
		temp.addAll(variable.getExpression(type));
		temp.addAll(arrayLocation.getExpression(type));
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
		temp.addAll(variable.getVariableDeclaration());
		for (VariableDeclaration e : arrayLocation.getVariableDeclaration()) {
			if (!temp.contains(e)) {
				temp.add(e);
			}
		}
		return temp;
	}

	@Override
	public List<ExpressionLiteral> toExpressionLiteralList() {
		// TODO Auto-generated method stub
		List<ExpressionLiteral> temp = variable.toExpressionLiteralList();

		ExpressionLiteral openBracket = new ExpressionLiteral();
		openBracket.setType(ExpressionLiteralType.OPEN_BRACKET);
		openBracket.setLiteral(ExpressionLiteralType.OPEN_BRACKET.toString());
		temp.add(openBracket);

		temp.addAll(arrayLocation.toExpressionLiteralList());

		ExpressionLiteral closeBracket = new ExpressionLiteral();
		closeBracket.setType(ExpressionLiteralType.CLOSE_BRACKET);
		closeBracket.setLiteral(ExpressionLiteralType.CLOSE_BRACKET.toString());
		temp.add(closeBracket);

		return temp;
	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
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
	public List<VariableDeclaration> getDeclareRequiredVariableDeclaration() {
		// TODO Auto-generated method stub
		List<VariableDeclaration> temp = new ArrayList<VariableDeclaration>();
		temp.addAll(variable.getDeclareRequiredVariableDeclaration());
		for (VariableDeclaration d : arrayLocation
				.getDeclareRequiredVariableDeclaration()) {
			if (!temp.contains(d)) {
				temp.add(d);
			}
		}
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

	@Override
	public List<String> getIDs() {
		// TODO Auto-generated method stub
		List<String> newInstance = new ArrayList<String>();
		newInstance.add(getID());
		newInstance.addAll(getVariable().getIDs());
		return newInstance;
	}

	private void updateID() {
		int hashCode = hashCode();
		if (hashCode < 0) {
			id = "A_" + -hashCode;
		} else {
			id = "A" + hashCode;
		}
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
}

package codemodel.structure.exp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import codemodel.structure.dcl.StructType;
import codemodel.structure.dcl.VariableDeclaration;
import codemodel.structure.def.FunctionDefinition;
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


public class FunctionCallExpression extends Expression {

	public static final String FUNCTION_CLOSE_ROUND_BRACKET = ")";
	public static final String FUNCTION_OPEN_ROUND_BRACKET = "(";
	private String functionName;
	private FunctionDefinition functionDefinition;
	private Expression parameterInstanceExpression = NullExpression
			.getInstance();
	private String id;
	private boolean needUpdateId = true;

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof FunctionCallExpression) {
			FunctionCallExpression temp = (FunctionCallExpression) obj;
			return functionName.equals(temp.getFunctionName())
					&& functionDefinition == null ? temp
					.getFunctionDefinition() == null : functionDefinition
					.equals(temp.getFunctionDefinition())
					&& parameterInstanceExpression.equals(temp
							.getParameterInstanceExpression());
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;
		result = result * 31 + functionName.hashCode();
		result = result * 31 + parameterInstanceExpression.hashCode();
		return result;
	}

	@Override
	public ExpressionType getType() {
		// TODO Auto-generated method stub
		return ExpressionType.FUNCTION_CALL;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
		needUpdateId = true;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setParameterInstanceExpression(
			Expression parameterInstanceExpression) {
		this.parameterInstanceExpression = parameterInstanceExpression;
		parameterInstanceExpression.setParentExpression(this);
		needUpdateId = true;
	}

	public Expression getParameterInstanceExpression() {
		return parameterInstanceExpression;
	}

	public String toString() {
		return functionName + FUNCTION_OPEN_ROUND_BRACKET
				+ parameterInstanceExpression.toString()
				+ FUNCTION_CLOSE_ROUND_BRACKET;
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
		temp.add(functionName);
		temp.add(FUNCTION_OPEN_ROUND_BRACKET);
		temp.addAll(parameterInstanceExpression.toStringList());
		temp.add(FUNCTION_CLOSE_ROUND_BRACKET);
		return temp;
	}

	@Override
	public Expression makeClone() {
		// TODO Auto-generated method stub
		FunctionCallExpression exp = new FunctionCallExpression();
		exp.setFunctionName(functionName);
		exp.setFunctionDefinition(functionDefinition);
		exp.setParameterInstanceExpression(parameterInstanceExpression
				.makeClone());
		return exp;
	}

	@Override
	public int getExpressionNum(ExpressionType type) {
		// TODO Auto-generated method stub
		int result = 0;
		if (type == getType()) {
			result++;
		}
		result += parameterInstanceExpression.getExpressionNum(type);
		return result;
	}

	@Override
	public List<Expression> getExpression(ExpressionType type) {
		// TODO Auto-generated method stub
		List<Expression> temp = new ArrayList<Expression>();
		if (type == getType() || type == ExpressionType.ALL) {
			temp.add(this);
		}
		temp.addAll(parameterInstanceExpression.getExpression(type));
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
		temp.addAll(parameterInstanceExpression.getVariableDeclaration());
		return temp;
	}

	public void setFunctionDefinition(FunctionDefinition functionDefinition) {
		this.functionDefinition = functionDefinition;
	}

	public FunctionDefinition getFunctionDefinition() {
		return functionDefinition;
	}

	@Override
	public List<ExpressionLiteral> toExpressionLiteralList() {
		// TODO Auto-generated method stub
		List<ExpressionLiteral> temp = new ArrayList<ExpressionLiteral>();

		ExpressionLiteral functionName = new ExpressionLiteral();
		functionName.setType(ExpressionLiteralType.FUNCTION_NAME);
		functionName.setLiteral(getFunctionName());
		temp.add(functionName);

		ExpressionLiteral functionOpenRoundBracket = new ExpressionLiteral();
		functionOpenRoundBracket
				.setType(ExpressionLiteralType.FUNCTION_OPEN_ROUND_BRACKET);
		functionOpenRoundBracket
				.setLiteral(ExpressionLiteralType.FUNCTION_OPEN_ROUND_BRACKET
						.toString());
		temp.add(functionOpenRoundBracket);

		temp.addAll(getParameterInstanceExpression()
				.toExpressionLiteralList());

		ExpressionLiteral functionCloseRoundBracket = new ExpressionLiteral();
		functionCloseRoundBracket
				.setType(ExpressionLiteralType.FUNCTION_CLOSE_ROUND_BRACKET);
		functionCloseRoundBracket
				.setLiteral(ExpressionLiteralType.FUNCTION_CLOSE_ROUND_BRACKET
						.toString());
		temp.add(functionCloseRoundBracket);

		return temp;
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
			id = "F_" + -hashCode;
		} else {
			id = "F" + hashCode;
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

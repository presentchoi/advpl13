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


public class BlockExpression extends Expression {
	public static final String CLOSE_BRACE = "}";
	public static final String OPEN_BRACE = "{";
	private List<Expression> blockInnerExpressions = new ArrayList<Expression>();
	private String id;
	private boolean needUpdateId = true;

	public void setBlockInnerExpressions(List<Expression> blockInnerExpressions) {
		this.blockInnerExpressions = blockInnerExpressions;
		needUpdateId = true;
	}

	public void replaceBlockInnerExpression(Expression blockInnerExpression,
			int index) {
		this.blockInnerExpressions.remove(index);
		this.blockInnerExpressions.add(index, blockInnerExpression);
		needUpdateId = true;
	}

	public void addBlockInnerExpression(Expression blockInnerExpression) {
		this.blockInnerExpressions.add(blockInnerExpression);
		needUpdateId = true;
	}

	public List<Expression> getBlockInnerExpressions() {
		return blockInnerExpressions;
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
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof BlockExpression) {
			return getBlockInnerExpressions().equals(
					((BlockExpression) obj).getBlockInnerExpressions());
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;
		result = result * 31 + blockInnerExpressions.hashCode();
		return result;
	}

	@Override
	public ExpressionType getType() {
		// TODO Auto-generated method stub
		return ExpressionType.BLOCK;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(OPEN_BRACE);
		for (int i = 0; i < blockInnerExpressions.size(); i++) {
			if (i != 0) {
				sb.append(", ");
			}
			sb.append(blockInnerExpressions.get(i).toString());
		}
		sb.append(CLOSE_BRACE);
		return sb.toString();
	}

	@Override
	public List<String> toStringList() {
		// TODO Auto-generated method stub
		List<String> temp = new ArrayList<String>();
		temp.add(OPEN_BRACE);
		for (Expression exp : blockInnerExpressions) {
			temp.addAll(exp.toStringList());
		}
		temp.add(CLOSE_BRACE);
		return temp;
	}

	@Override
	public Expression makeClone() {
		// TODO Auto-generated method stub
		BlockExpression exp = new BlockExpression();
		List<Expression> temp = new ArrayList<Expression>();
		for (Expression e : blockInnerExpressions) {
			temp.add(e.makeClone());
		}
		exp.setBlockInnerExpressions(temp);
		return exp;
	}

	@Override
	public int getExpressionNum(ExpressionType type) {
		// TODO Auto-generated method stub
		int result = 0;
		if (type == getType()) {
			result++;
		}
		for (Expression e : blockInnerExpressions) {
			result += e.getExpressionNum(type);
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
		for (Expression e : blockInnerExpressions) {
			temp.addAll(e.getExpression(type));
		}
		return temp;
	}

	@Override
	public Expression getAlternativeExpression() {
		// TODO Auto-generated method stub
		return makeClone();
	}

	@Override
	public List<VariableDeclaration> getVariableDeclaration() {
		// TODO Auto-generated method stub
		List<VariableDeclaration> temp = new ArrayList<VariableDeclaration>();
		for (Expression e : blockInnerExpressions) {
			temp.addAll(e.getVariableDeclaration());
		}
		return temp;
	}

	@Override
	public List<ExpressionLiteral> toExpressionLiteralList() {
		// TODO Auto-generated method stub
		List<ExpressionLiteral> temp = new ArrayList<ExpressionLiteral>();

		ExpressionLiteral openBrace = new ExpressionLiteral();
		openBrace.setType(ExpressionLiteralType.OPEN_BRACE);
		openBrace.setLiteral(ExpressionLiteralType.OPEN_BRACE.toString());
		temp.add(openBrace);

		for (Expression exp : blockInnerExpressions) {
			temp.addAll(exp.toExpressionLiteralList());
		}

		ExpressionLiteral closeBrace = new ExpressionLiteral();
		closeBrace.setType(ExpressionLiteralType.CLOSE_BRACE);
		closeBrace.setLiteral(ExpressionLiteralType.CLOSE_BRACE.toString());
		temp.add(closeBrace);

		return temp;
	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	@Override
	public List<VariableDeclaration> getDeclareRequiredVariableDeclaration() {
		// TODO Auto-generated method stub
		List<VariableDeclaration> temp = new ArrayList<VariableDeclaration>();
		for (Expression e : blockInnerExpressions) {
			for (VariableDeclaration d : e
					.getDeclareRequiredVariableDeclaration()) {
				if (!temp.contains(d)) {
					temp.add(d);
				}
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
			id = "B_" + -hashCode;
		} else {
			id = "B" + hashCode;
		}
	}

	@Override
	public List<String> getIDs() {
		// TODO Auto-generated method stub
		List<String> newInstance = new ArrayList<String>();
		newInstance.add(getID());
		for (Expression e : getBlockInnerExpressions()) {
			newInstance.addAll(e.getIDs());
		}
		return newInstance;
	}
}

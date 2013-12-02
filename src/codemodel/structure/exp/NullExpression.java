package codemodel.structure.exp;

import java.util.ArrayList;
import java.util.Collections;
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


public class NullExpression extends Expression {
	private static final NullExpression Null_Expression = new NullExpression();
	private String id;
	private boolean needUpdateId = true;

	public static final NullExpression getInstance() {
		return Null_Expression;
	}

	private NullExpression() {

	}

	@Override
	public List<StructDefinition> getStructDefinition() {
		// TODO Auto-generated method stub
		List<StructDefinition> result = new ArrayList<StructDefinition>();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof NullExpression) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ExpressionType getType() {
		// TODO Auto-generated method stub
		return ExpressionType.NULL;
	}

	public String toString() {
		return "";
	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	@Override
	public List<String> toStringList() {
		// TODO Auto-generated method stub
		return Collections.EMPTY_LIST;
	}

	@Override
	public Expression makeClone() {
		// TODO Auto-generated method stub
		NullExpression exp = new NullExpression();
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
		return this.makeClone();
	}

	@Override
	public List<VariableDeclaration> getVariableDeclaration() {
		// TODO Auto-generated method stub
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<ExpressionLiteral> toExpressionLiteralList() {
		// TODO Auto-generated method stub
		return Collections.EMPTY_LIST;
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
			id = "N_" + -hashCode;
		} else {
			id = "N" + hashCode;
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

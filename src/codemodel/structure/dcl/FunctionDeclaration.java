package codemodel.structure.dcl;

import java.io.PrintStream;
import java.util.Vector;

import codemodel.structure.type.DeclarationType;
import codemodel.structure.type.VariableScope;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.DeclarationNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.DeclarationOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.DeclarationToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.DeclarationToTOneParamVisitor;


public class FunctionDeclaration implements Declaration {
	private Type returnType;
	private String functionName;
	private Vector<VariableDeclaration> parameters = new Vector<VariableDeclaration>();

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setParameters(Vector<VariableDeclaration> parameters) {
//		System.err.println(parameters);
		for (VariableDeclaration d : parameters) {
			d.setScope(VariableScope.LOCAL);
		}
		this.parameters = parameters;
	}

	/**
	 * 만약 같은 이름의 변수가 이미 존재할 경우
	 * 바꿔 끼운다.
	 * @param pnu.oos.conga.codemodel.structure.dcl.VariableDeclaration */
	public void addParameter(VariableDeclaration parameter) {
		boolean notExist = true; 
		for (VariableDeclaration p : parameters) {
			if (p.getName().equals(parameter.getName())) {
				notExist = false;
				break;
			}
		}
		if (notExist) {
			parameter.setScope(VariableScope.LOCAL);
			parameters.add(parameter);
		}
	}
	
	/**
	 * 만약 파라메터 추가를 원할 경우 이 메소드가 아닌
	 * addParameter를 사용한다.
	 * @return Vector<VariableDeclaration>
	 * */
	public Vector<VariableDeclaration> getParameters() {
		return parameters;
	}

	@Override
	public DeclarationType getDeclarationType() {
		// TODO Auto-generated method stub
		return DeclarationType.FUNCTION;
	}

	public void setReturnType(Type returnType) {
		this.returnType = returnType;
	}

	public Type getReturnType() {
		return returnType;
	}

	@Override
	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		out.println(prefix + toString() + ";");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = returnType.toString() + " " + functionName + "(";
		for (int i = 0; i < parameters.size(); i++) {
			if (i != 0) {
				result += ", " + parameters.get(i).toString();
			} else {
				result += parameters.get(i).toString();
			}
		}
		result += ")";
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof FunctionDeclaration) {
			FunctionDeclaration fd = (FunctionDeclaration) obj;
			if (!returnType.equals(fd.getReturnType())) {
				return false;
			} else if (!functionName.equals(fd.getFunctionName())) {
				return false;
			} else if (parameters.size() == fd.getParameters().size()) {
				for (int i = 0; i < parameters.size(); i++) {
					boolean paramCheck = parameters.get(i).equals(
							fd.getParameters().get(i));
					if (!paramCheck) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public FunctionDeclaration makeClone() {
		// TODO Auto-generated method stub
		FunctionDeclaration newInstance = new FunctionDeclaration();
		newInstance.setFunctionName(functionName);
		newInstance.setReturnType(returnType.makeClone());
		for (VariableDeclaration v : getParameters()) {
			newInstance.addParameter(v.makeClone());
		}
		return newInstance;
	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
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
	public <T> void accept(DeclarationOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}

	@Override
	public <T> T accept(DeclarationToTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	@Override
	public <T, T1> T accept(DeclarationToTOneParamVisitor<T, T1> visitor,
			T1 data) {
		// TODO Auto-generated method stub
		return visitor.visit(this, data);
	}

	@Override
	public void accept(DeclarationNoParamVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}
}

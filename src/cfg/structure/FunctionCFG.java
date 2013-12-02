package cfg.structure;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import codemodel.structure.dcl.Declaration;
import codemodel.structure.dcl.FunctionDeclaration;
import codemodel.structure.dcl.VariableDeclaration;
import codemodel.structure.stat.Statement;


public class FunctionCFG {
	private Node firstNode;
	private FunctionDeclaration functionSignature;
	private List<Declaration> globalDeclaration;
	private List<VariableDeclaration> globalVariable = new ArrayList<VariableDeclaration>();
	private List<Statement> globalInitStatement;

	public Node getFirstNode() {
		return firstNode;
	}

	public void setFirstNode(Node firstNode) {
		this.firstNode = firstNode;
	}

	public FunctionDeclaration getFunctionSignature() {
		return functionSignature;
	}

	public void setFunctionSignature(FunctionDeclaration functionSignature) {
		this.functionSignature = functionSignature;
	}

	public void setGlobalDeclarations(List<Declaration> globalDeclaration) {
		this.globalDeclaration = globalDeclaration;
		for (Declaration d : globalDeclaration) {
			if (d instanceof VariableDeclaration) {
				globalVariable.add((VariableDeclaration) d);
			}
		}
	}

	public List<Declaration> getGlobalDeclaration() {
		return globalDeclaration;
	}

	public List<VariableDeclaration> getGlobalVariable() {
		return globalVariable;
	}

	public void setGlobalInitStatements(List<Statement> globalInitStatement) {
		this.globalInitStatement = globalInitStatement;
	}

	public List<Statement> getGlobalInitStatement() {
		return globalInitStatement;
	}

	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		functionSignature.print(out, prefix);
		for (Declaration d : globalDeclaration) {
			d.print(out, prefix);
		}

		for (Statement s : globalInitStatement) {
			s.print(out, prefix);
		}
		firstNode.print(out, prefix);
	}

	public void print(PrintStream out) {
		// TODO Auto-generated method stub
		print(out, "");
	}
}

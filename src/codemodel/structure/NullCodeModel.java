package codemodel.structure;

import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import codemodel.structure.dcl.Declaration;
import codemodel.structure.def.Definition;
import codemodel.structure.def.FunctionDefinition;
import codemodel.structure.stat.Statement;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;


public class NullCodeModel implements CodeModel {
	private static final NullCodeModel instance = new NullCodeModel();

	public static final NullCodeModel getInstance() {
		return instance;
	}

	@Override
	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> T accept(CodeModelToTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, T1> T accept(CodeModelToTOneParamVisitor<T, T1> visitor, T1 data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void accept(CodeModelNoParamVisitor visitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAll(CSource code) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addDeclaration(Declaration declaration) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addDefinition(Definition definition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addStatement(Statement statement) {
		// TODO Auto-generated method stub

	}

	@Override
	public FunctionDefinition findFunction(String functionName) {
		// TODO Auto-generated method stub
		return FunctionDefinition.getNullInstance();
	}

	@Override
	public List<Declaration> getDeclarations() {
		// TODO Auto-generated method stub
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<Definition> getDefinitions() {
		// TODO Auto-generated method stub
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<FunctionDefinition> getFunctionDefinitions() {
		// TODO Auto-generated method stub
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<Statement> getInitStatements() {
		// TODO Auto-generated method stub
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<String> getPreprocess() {
		// TODO Auto-generated method stub
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<Statement> getStatements() {
		// TODO Auto-generated method stub
		return Collections.EMPTY_LIST;
	}

	@Override
	public CodeModel makeClone() {
		// TODO Auto-generated method stub
		return getInstance();
	}

	@Override
	public void print(PrintStream out) {
		// TODO Auto-generated method stub

	}

	@Override
	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDeclarations(List<Declaration> declarations) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDefinitions(List<Definition> definitions) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setInitStatements(List<Statement> initStatements) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPreprocess(List<String> preprocess) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStatements(List<Statement> statement) {
		// TODO Auto-generated method stub

	}

}

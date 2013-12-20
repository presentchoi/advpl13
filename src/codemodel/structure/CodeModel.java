package codemodel.structure;

import java.io.PrintStream;
import java.util.List;

import codemodel.structure.dcl.Declaration;
import codemodel.structure.def.Definition;
import codemodel.structure.def.FunctionDefinition;
import codemodel.structure.stat.InitDeclaratable;
import codemodel.structure.stat.Statement;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;


public interface CodeModel extends InitDeclaratable {
	public void setPreprocess(List<String> preprocess);

	public List<String> getPreprocess();

	public void setDeclarations(List<Declaration> declarations);

	public void addDeclaration(Declaration declaration);

	public List<Declaration> getDeclarations();

	public void addDefinition(Definition definition);

	public List<Definition> getDefinitions();

	public void addStatement(Statement statement);

	public List<Statement> getStatements();

	public void setStatements(List<Statement> statement);

	public void print(PrintStream out);

	public void print(PrintStream out, String prefix);

    public void printWithLineNum(PrintStream out, String prefix);

	public void addAll(CSource code);

	public void setInitStatements(List<Statement> initStatements);

	public List<Statement> getInitStatements();

	public void setDefinitions(List<Definition> definitions);

	public CodeModel makeClone();

	public FunctionDefinition findFunction(String functionName);

	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data);

	public <T> T accept(CodeModelToTVisitor<T> visitor);

	public <T, T1> T accept(CodeModelToTOneParamVisitor<T, T1> visitor, T1 data);

	public void accept(CodeModelNoParamVisitor visitor);

	public List<FunctionDefinition> getFunctionDefinitions();
}

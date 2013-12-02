package codemodel.structure.stat;

import java.util.List;

import codemodel.structure.dcl.Declaration;
import codemodel.structure.def.Definition;


public interface InitDeclaratable {
	public void setStatements(List<Statement> statements);

	public void addStatement(Statement statement);

	public List<Statement> getStatements();

	public void setDeclarations(List<Declaration> localDeclarations);

	public void addDeclaration(Declaration localDeclaration);

	public List<Declaration> getDeclarations();

	public void setDefinitions(List<Definition> localDefinitions);
	
	public void addDefinition(Definition localDefinition);
	
	public List<Definition> getDefinitions();
}

package codemodel.structure.stat;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import codemodel.structure.dcl.Declaration;
import codemodel.structure.dcl.VariableDeclaration;
import codemodel.structure.def.Definition;
import codemodel.structure.def.EnumDefinition;
import codemodel.structure.def.StructDefinition;
import codemodel.structure.type.StatementType;
import codemodel.structure.type.VariableScope;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.noparam.StatementNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.oneparam.StatementOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.tot.StatementToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;
import codemodel.transfer.totoneparam.StatementToTOneParamVisitor;


public class Block implements NonconditionStatement, InitDeclaratable {
	private List<Statement> blockStatements = new ArrayList<Statement>();
	private List<Declaration> localDeclarations = new ArrayList<Declaration>();
	private List<Definition> localDefinitions = new ArrayList<Definition>();

	@Override
	public StatementType getType() {
		// TODO Auto-generated method stub
		return StatementType.BLOCK;
	}

	@Override
	public void addDeclaration(Declaration declaration) {
		// TODO Auto-generated method stub
		if (declaration instanceof VariableDeclaration) {
			((VariableDeclaration) declaration).setScope(VariableScope.LOCAL);
		}
		this.localDeclarations.add(declaration);
	}

	@Override
	public void addStatement(Statement statement) {
		// TODO Auto-generated method stub
		this.blockStatements.add(statement);
	}

	@Override
	public List<Declaration> getDeclarations() {
		// TODO Auto-generated method stub
		return localDeclarations;
	}

	@Override
	public List<Statement> getStatements() {
		// TODO Auto-generated method stub
		return blockStatements;
	}

	@Override
	public void setDeclarations(List<Declaration> declarations) {
		// TODO Auto-generated method stub
		for (Declaration d : declarations) {
			if (d instanceof VariableDeclaration) {
				((VariableDeclaration) d).setScope(VariableScope.LOCAL);
			}
		}
		this.localDeclarations = declarations;
	}

	@Override
	public void setStatements(List<Statement> statement) {
		// TODO Auto-generated method stub
		this.blockStatements = statement;
	}

	public void print(PrintStream out, String prefix) {
		// TODO Auto-generated method stub
		out.println(prefix + "{");
		for (Declaration var : localDeclarations) {
			var.print(out, "\t" + prefix);
		}
		for (Statement stat : blockStatements) {
			stat.print(out, "\t" + prefix);
		}
		out.println(prefix + "}");
	}

	@Override
	public void accept(CodeModelNoParamVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	@Override
	public int getStatementNum(StatementType type) {
		// TODO Auto-generated method stub
		int result = 0;
		if (type == getType()) {
			result++;
		}
		for (Statement s : blockStatements) {
			result += s.getStatementNum(type);
		}
		return result;
	}

	@Override
	public void addDefinition(Definition localDefinition) {
		// TODO Auto-generated method stub
		if (localDefinition instanceof EnumDefinition) {
			((EnumDefinition) localDefinition).setScope(VariableScope.LOCAL);
		} else if (localDefinition instanceof StructDefinition) {
			((StructDefinition) localDefinition).setScope(VariableScope.LOCAL);
		}
		this.localDefinitions.add(localDefinition);
	}

	@Override
	public List<Definition> getDefinitions() {
		// TODO Auto-generated method stub
		return localDefinitions;
	}

	@Override
	public void setDefinitions(List<Definition> localDefinitions) {
		// TODO Auto-generated method stub
		for (Definition d : localDefinitions) {
			if (d instanceof EnumDefinition) {
				((EnumDefinition) d).setScope(VariableScope.LOCAL);
			} else if (d instanceof StructDefinition) {
				((StructDefinition) d).setScope(VariableScope.LOCAL);
			}
		}
		this.localDefinitions = localDefinitions;
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
	public <T, T1> T accept(CodeModelToTOneParamVisitor<T, T1> visitor, T1 data) {
		// TODO Auto-generated method stub
		return visitor.visit(this, data);
	}

	@Override
	public Block makeClone() {
		// TODO Auto-generated method stub
		Block newInstance = new Block();

		for (Declaration d : this.getDeclarations()) {
			newInstance.addDeclaration(d.makeClone());
		}
		for (Definition d : this.getDefinitions()) {
			newInstance.addDefinition(d.makeClone());
		}
		for (Statement s : this.getStatements()) {
			newInstance.addStatement(s.makeClone());
		}
		return newInstance;
	}

	public void addAll(Block blockStatement) {
		// TODO Auto-generated method stub
		for (Declaration d : blockStatement.getDeclarations()) {
			addDeclaration(d);
		}
		for (Definition d : blockStatement.getDefinitions()) {
			addDefinition(d);
		}
		for (Statement s : blockStatement.getStatements()) {
			addStatement(s);
		}
	}

	@Override
	public <T> T accept(StatementToTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	@Override
	public void accept(StatementNoParamVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	@Override
	public <T, T1> T accept(StatementToTOneParamVisitor<T, T1> visitor, T1 data) {
		// TODO Auto-generated method stub
		return visitor.visit(this, data);
	}

	@Override
	public <T> void accept(StatementOneParamVisitor<T> visitor, T data) {
		// TODO Auto-generated method stub
		visitor.visit(this, data);
	}
}

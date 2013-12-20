package codemodel.structure;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import codemodel.structure.dcl.Declaration;
import codemodel.structure.dcl.VariableDeclaration;
import codemodel.structure.def.Definition;
import codemodel.structure.def.EnumDefinition;
import codemodel.structure.def.FunctionDefinition;
import codemodel.structure.def.StructDefinition;
import codemodel.structure.stat.Statement;
import codemodel.structure.type.VariableScope;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;
import codemodel.transfer.tot.CodeModelToTVisitor;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;


public class CSource implements CodeModel {
	private List<String> preprocess = new ArrayList<String>();
	private List<Declaration> declarations = new ArrayList<Declaration>();
	private List<Statement> initStatements = new ArrayList<Statement>();
	private List<Definition> definitions = new ArrayList<Definition>();
	private List<FunctionDefinition> functionDefinitions = new ArrayList<FunctionDefinition>();

	public void setPreprocess(List<String> preprocess) {
		this.preprocess = preprocess;
	}

	public List<String> getPreprocess() {
		return preprocess;
	}

	public void setDeclarations(List<Declaration> declarations) {
		for (Declaration d : declarations) {
			if (d instanceof VariableDeclaration) {
				((VariableDeclaration) d).setScope(VariableScope.GLOBAL);
			}
		}
		this.declarations = declarations;
	}

	public void addDeclaration(Declaration declaration) {
		if (declaration instanceof VariableDeclaration) {
			((VariableDeclaration) declaration).setScope(VariableScope.GLOBAL);
		}
		this.declarations.add(declaration);
	}

	public List<Declaration> getDeclarations() {
		return declarations;
	}

	public void addDefinition(Definition definition) {
		if (definition instanceof EnumDefinition) {
			((EnumDefinition) definition).setScope(VariableScope.GLOBAL);
		} else if (definition instanceof StructDefinition) {
			((StructDefinition) definition).setScope(VariableScope.GLOBAL);
		} else if (definition instanceof FunctionDefinition) {
			functionDefinitions.add((FunctionDefinition) definition);
		}
		this.definitions.add(definition);
	}

	public List<Definition> getDefinitions() {
		return definitions;
	}

	@Override
	public void addStatement(Statement statement) {
		// TODO Auto-generated method stub
		this.getInitStatements().add(statement);
	}

	@Override
	public List<Statement> getStatements() {
		// TODO Auto-generated method stub
		return getInitStatements();
	}

	@Override
	public void setStatements(List<Statement> statement) {
		// TODO Auto-generated method stub
		this.setInitStatements(statement);
	}

	public void print(PrintStream out) {
		for (String p : preprocess) {
			out.println(p);
		}
		for (Declaration nSt : declarations) {
			nSt.print(out, "");
		}
		for (Statement initSt : initStatements) {
			initSt.print(out, "");
		}
		for (Definition d : definitions) {
			d.print(out, "");
		}
	}

	public void print(PrintStream out, String prefix) {
		for (String p : preprocess) {
			out.println(p);
		}
		for (Declaration nSt : declarations) {
			nSt.print(out, prefix);
		}
		for (Statement initSt : initStatements) {
			initSt.print(out, prefix);
		}
		for (Definition d : definitions) {
			d.print(out, prefix);
		}
	}

	public void addAll(CSource code) {
		List<String> p = code.getPreprocess();
		for (String str : p) {
			if (!preprocess.contains(str)) {
				preprocess.add(str);
			}
		}
		List<Declaration> declarations = code.getDeclarations();
		for (Declaration declaration : declarations) {
			if (!declarations.contains(declaration)) {
				declarations.add(declaration);
			}
		}
		List<Statement> statements = code.getInitStatements();
		for (Statement statement : statements) {
			if (!initStatements.contains(statement)) {
				initStatements.add(statement);
			}
		}
		List<Definition> definitions = code.getDefinitions();
		for (Definition definition : definitions) {
			if (!definitions.contains(definition)) {
				definitions.add(0, definition);
			}
			if (definition instanceof FunctionDefinition) {
				FunctionDefinition func = (FunctionDefinition) definition;
				if (!functionDefinitions.contains(func)) {
					functionDefinitions.add(0, func);
				}
			}
		}
	}

	public void setInitStatements(List<Statement> initStatements) {
		this.initStatements = initStatements;
	}

	public List<Statement> getInitStatements() {
		return initStatements;
	}

	@Override
	public void setDefinitions(List<Definition> definitions) {
		// TODO Auto-generated method stub
		for (Definition d : definitions) {
			if (d instanceof EnumDefinition) {
				((EnumDefinition) d).setScope(VariableScope.GLOBAL);
			} else if (d instanceof StructDefinition) {
				((StructDefinition) d).setScope(VariableScope.GLOBAL);
			} else if (d instanceof FunctionDefinition) {
				functionDefinitions.add((FunctionDefinition) d);
			}
		}
		this.definitions = definitions;
	}

	public CSource makeClone() {
		// TODO Auto-generated method stub
		CSource newInstance = new CSource();

		newInstance.setPreprocess(new ArrayList<String>(this.getPreprocess()));
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

	public FunctionDefinition findFunction(String functionName) {
		// TODO Auto-generated method stub
		for (FunctionDefinition f : functionDefinitions) {
			if (functionName.equals(f.getFunctionSignature().getFunctionName())) {
				return f;
			}
		}
		new IllegalArgumentException("cannot find function : " + functionName);
		return null;
	}

	public <T> void accept(CodeModelOneParamVisitor<T> visitor, T data) {
		visitor.visit(this, data);
	}

	public <T> T accept(CodeModelToTVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public <T, T1> T accept(CodeModelToTOneParamVisitor<T, T1> visitor, T1 data) {
		return visitor.visit(this, data);
	}

	public void accept(CodeModelNoParamVisitor visitor) {
		visitor.visit(this);
	}

	public List<FunctionDefinition> getFunctionDefinitions() {
		return functionDefinitions;
	}

    @Override
    public void printWithLineNum(PrintStream out, String prefix) {
        for (String p : preprocess) {
            out.println(p);
        }
        for (Declaration nSt : declarations) {
            nSt.printWithLineNum(out, prefix);
        }
        for (Statement initSt : initStatements) {
            initSt.printWithLineNum(out, prefix);
        }
        for (Definition d : definitions) {
            d.printWithLineNum(out, prefix);
        }
    }
}

package cfg.structure.transfer;

import codemodel.structure.CSource;
import codemodel.structure.def.FunctionDefinition;
import codemodel.transfer.CodeModelGenerator;
import cfg.structure.EndNode;
import cfg.structure.FunctionCFG;
import cfg.structure.Node;
import cfg.structure.StartNode;

public class CFGGenerator {
	public static FunctionCFG getCFGFromFile(String codeFileName,
			String functionName) {
		CSource codemodel = CodeModelGenerator
				.getCodeModelFromFile(codeFileName);

		FunctionCFG cfg = makeCFG(functionName, codemodel);

		return cfg;
	}

	public static FunctionCFG getCFGFromCodeModel(CSource codemodel,
			String functionName) {
		FunctionCFG cfg = makeCFG(functionName, codemodel);

		return cfg;
	}
	
	public static FunctionCFG getCFGFromString(String str, String functionName) {
		CSource codemodel = CodeModelGenerator.getCodeModelFromString(str);

		FunctionCFG cfg = makeCFG(functionName, codemodel);

		return cfg;
	}

	private static FunctionCFG makeCFG(String functionName, CSource codemodel) {
		FunctionCFG cfg = new FunctionCFG();

		FunctionDefinition f = codemodel.findFunction(functionName);

		StartNode startNode = new StartNode();

		CFGGenerateVisitor cfgMakeVisitor = new CFGGenerateVisitor();
		Node firstNode = cfgMakeVisitor.visit(f.getBlock());
		firstNode.accept(new CFGNodeSettingVisitor(), new EndNode(cfgMakeVisitor
				.getEndNodeNum()));

		startNode.setFirstNode(firstNode);

		cfg.setFunctionSignature(f.getFunctionSignature());
		cfg.setFirstNode(startNode);
		cfg.setGlobalDeclarations(codemodel.getDeclarations());
		cfg.setGlobalInitStatements(codemodel.getStatements());
		return cfg;
	}
}

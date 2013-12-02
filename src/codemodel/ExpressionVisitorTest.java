package codemodel;

import codemodel.transfer.CodeModelGenerator;
import cfg.structure.FunctionCFG;
import cfg.structure.transfer.CFGGenerator;

public class ExpressionVisitorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "testCode/triangle4.c";
		String functionName = "triangle4";

		FunctionCFG cfg = CFGGenerator.getCFGFromFile(fileName, functionName);
		cfg.print(System.out, "");
		CodeModelGenerator.getCodeModelFromFile(fileName);
	}
}

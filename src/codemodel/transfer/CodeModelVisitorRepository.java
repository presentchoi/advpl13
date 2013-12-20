package codemodel.transfer;

import java.util.List;
import java.util.Stack;

import codemodel.structure.dcl.Declaration;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;
import codemodel.transfer.oneparam.CodeModelOneParamVisitor;


public class CodeModelVisitorRepository {
	private static final CodeModelNoParamVisitor conditionStatementTransferVisitor = new ConditionStatementTransferVisitor();
	private static final CodeModelOneParamVisitor<Stack<List<Declaration>>> secondPathSymbolicAnalysisVisitor = new SecondPathAnalysisVisitor();
    private static final CodeModelNoParamVisitor lineNumberAnalysisVisitor = new LineNumberAnalysisVisitor();

	public static CodeModelNoParamVisitor getConditionStatementTransferVisitor() {
		return conditionStatementTransferVisitor;
	}

	public static CodeModelOneParamVisitor<Stack<List<Declaration>>> getSecondPathSymbolicAnalysisVisitor() {
		return secondPathSymbolicAnalysisVisitor;
	}

	public static CodeModelNoParamVisitor getLineNumberAnalysisVisitor() {
	    return lineNumberAnalysisVisitor;	    
	}
}

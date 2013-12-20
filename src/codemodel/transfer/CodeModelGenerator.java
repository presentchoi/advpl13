package codemodel.transfer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;

import codemodel.parser.CParser;
import codemodel.parser.node.ASTTranslationUnit;
import codemodel.parser.node.Node;
import codemodel.parser.transfer.ParserTransferVisitorRepository;
import codemodel.structure.CSource;
import codemodel.structure.dcl.Declaration;

public class CodeModelGenerator {
    public static CSource getCodeModelFromFile(String fileName) {
        CSource code = new CSource();

        CParser cp = CParser.runParserFromFile(fileName);
        Node rootNode = cp.getJjtree().rootNode();

        setPreprocessCCode(code, fileName);
        setCSource(code, rootNode);

        runAdditionalTransfer(code);
        return code;
    }

    private static void runAdditionalTransfer(CSource code) {
        CodeModelVisitorRepository.getConditionStatementTransferVisitor()
                .visit(code);
        CodeModelVisitorRepository.getSecondPathSymbolicAnalysisVisitor()
                .visit(code, new Stack<List<Declaration>>());
        CodeModelVisitorRepository.getLineNumberAnalysisVisitor().visit(
                code);
    }

    public static CSource getCodeModelFromString(String str) {
        CSource code = new CSource();

        CParser cp = CParser.runParserFromString(str);
        Node rootNode = cp.getJjtree().rootNode();
        rootNode.dump("");
        setCSource(code, rootNode);

        runAdditionalTransfer(code);
        return code;
    }

    private static void setPreprocessCCode(CSource code, String fileName) {
        // TODO Auto-generated method stub
        FileInputStream file = null;

        try {
            file = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        InputStreamReader is = new InputStreamReader(file);
        BufferedReader br = new BufferedReader(is);
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                // System.err.println(line);
                if (line.length() > 0) {
                    if (line.charAt(0) == '#') {
                        code.getPreprocess().add(line);
                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void setCSource(CSource code, Node node) {
        ParserTransferVisitorRepository.getCSourceVisitor().visit(
                (ASTTranslationUnit) node, code);
    }
}

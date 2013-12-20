import java.util.List;

import codemodel.structure.CSource;
import codemodel.structure.dcl.Array;
import codemodel.structure.dcl.FunctionDeclaration;
import codemodel.structure.dcl.StructType;
import codemodel.structure.dcl.TypeID;
import codemodel.structure.dcl.VariableDeclaration;
import codemodel.structure.def.EnumDefinition;
import codemodel.structure.def.FunctionDefinition;
import codemodel.structure.def.StructDefinition;
import codemodel.structure.def.TypedefDefinition;
import codemodel.structure.exp.ArrayExpression;
import codemodel.structure.exp.BinaryExpression;
import codemodel.structure.exp.BlockExpression;
import codemodel.structure.exp.CastExpression;
import codemodel.structure.exp.ConditionExpression;
import codemodel.structure.exp.ConstantExpression;
import codemodel.structure.exp.FunctionCallExpression;
import codemodel.structure.exp.NullExpression;
import codemodel.structure.exp.PointerExpression;
import codemodel.structure.exp.RoundBracketExpression;
import codemodel.structure.exp.StructExpression;
import codemodel.structure.exp.UnaryExpression;
import codemodel.structure.exp.VariableExpression;
import codemodel.structure.stat.Block;
import codemodel.structure.stat.DoWhileStatement;
import codemodel.structure.stat.ExpressionStatement;
import codemodel.structure.stat.ForStatement;
import codemodel.structure.stat.IfStatement;
import codemodel.structure.stat.InstrumentBlock;
import codemodel.structure.stat.InstrumentStatement;
import codemodel.structure.stat.JumpStatement;
import codemodel.structure.stat.LabeledStatement;
import codemodel.structure.stat.WhileStatement;
import codemodel.transfer.totoneparam.CodeModelToTOneParamVisitor;

public class ToTOneParamVisitorExample implements
		CodeModelToTOneParamVisitor<String, List<String>> {

	@Override
	public String visit(FunctionDeclaration functionDeclaration,
			List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(VariableDeclaration variableDeclaration,
			List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(EnumDefinition enumDefinition, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(FunctionDefinition functionDefinition, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(StructDefinition structDefinition, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(TypedefDefinition typedefDefinition, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ArrayExpression arrayExpression, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(BinaryExpression binaryExpression, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(BlockExpression blockExpression, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(CastExpression castExpression, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ConditionExpression conditionExpression,
			List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ConstantExpression constantExpression, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(FunctionCallExpression functionCallExpression,
			List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(NullExpression nullExpression, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(PointerExpression pointerExpression, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(RoundBracketExpression roundBracketExpression,
			List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(StructExpression structExpression, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(UnaryExpression unaryExpression, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(VariableExpression variableExpression, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(Block block, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ExpressionStatement expressionStatement,
			List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ForStatement forStatement, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(IfStatement ifStatement, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(JumpStatement jumpStatement, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(WhileStatement whileStatement, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(LabeledStatement labeledStatement, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(DoWhileStatement doWhileStatement, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(InstrumentStatement instrumentStatement,
			List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(InstrumentBlock instrumentBlock, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(TypeID typeID, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(Array array, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(StructType structType, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(CSource courceCode, List<String> data) {
		// TODO Auto-generated method stub
		return null;
	}

}

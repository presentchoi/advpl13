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
import codemodel.structure.stat.Statement;
import codemodel.structure.stat.WhileStatement;
import codemodel.transfer.noparam.CodeModelNoParamVisitor;


public class NoParamVisitorExample implements CodeModelNoParamVisitor {

	@Override
	public void visit(FunctionDeclaration functionDeclaration) {
		// TODO Auto-generated method stub
	}

	@Override
	public void visit(VariableDeclaration variableDeclaration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(EnumDefinition enumDefinition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(StructDefinition structDefinition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(TypedefDefinition typedefDefinition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FunctionDefinition functionDefinition) {
		// TODO Auto-generated method stub
		// ±¸Çö
		functionDefinition.getBlock().accept(this);
		
	}

	@Override
	public void visit(ArrayExpression arrayExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BinaryExpression binaryExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BlockExpression blockExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(CastExpression castExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ConditionExpression conditionExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ConstantExpression constantExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FunctionCallExpression functionCallExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(NullExpression nullExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(PointerExpression pointerExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(RoundBracketExpression roundBracketExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(StructExpression structExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(UnaryExpression unaryExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(VariableExpression variableExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Block block) {
		// TODO Auto-generated method stub
		for (Statement s : block.getStatements()) {
			s.accept(this);
		}
	}

	@Override
	public void visit(ExpressionStatement expressionStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ForStatement forStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IfStatement ifStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(JumpStatement jumpStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(WhileStatement whileStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(LabeledStatement labeledStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DoWhileStatement doWhileStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(InstrumentStatement instrumentStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(InstrumentBlock instrumentBlock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(TypeID typeID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Array array) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(StructType structType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(CSource sourceCode) {
		// TODO Auto-generated method stub
		
	}

}

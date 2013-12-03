import org.junit.Test;

import codemodel.structure.CSource;
import codemodel.structure.CodeModel;
import codemodel.transfer.CodeModelGenerator;

public class TestClass {
	@Test
	public void generateCodeModel() {
		CodeModel code = CodeModelGenerator.getCodeModelFromFile("triangle3_2.c");
		code.print(System.out);
	}
}

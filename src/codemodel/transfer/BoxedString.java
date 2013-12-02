package codemodel.transfer;

public class BoxedString {
	private StringBuilder stringBuilder = new StringBuilder();

	public BoxedString(String str) {
		// TODO Auto-generated constructor stub
		stringBuilder.append(str);
	}

	public BoxedString() {
		// TODO Auto-generated constructor stub
	}

	public void setString(String str) {
		stringBuilder.delete(0, stringBuilder.length() - 1);
		stringBuilder.append(str);
	}

	public String toString() {
		return stringBuilder.toString();
	}

	public void addString(String str) {
		// TODO Auto-generated method stub
		stringBuilder.append(str);
	}
}

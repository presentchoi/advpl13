package codemodel.structure.type;

public enum StructOperatorType {
	DOT, POINT_DOT;
	
	public String toString() {
		switch (this) {
		case DOT:
			return ".";
		case POINT_DOT:
			return "->";
		}
		return null;
	}
}

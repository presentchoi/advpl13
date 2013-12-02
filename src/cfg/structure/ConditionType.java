package cfg.structure;

public enum ConditionType {
	IF, WHILE;

	public String toString() {
		switch (this) {
		case IF:
			return "IF";
		case WHILE:
			return "WHILE";
		}
		return null;
	}
}

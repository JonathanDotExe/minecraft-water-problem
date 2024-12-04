package at.jku.complexity.watersatsolver.cnf;

public class Literal {

	private final int variable;
	private final boolean negate;
	
	public Literal(int variable, boolean negate) {
		super();
		this.variable = variable;
		this.negate = negate;
	}

	public int getVariable() {
		return variable;
	}

	public boolean isNegate() {
		return negate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (negate ? 1231 : 1237);
		result = prime * result + variable;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Literal other = (Literal) obj;
		if (negate != other.negate)
			return false;
		if (variable != other.variable)
			return false;
		return true;
	}
	
}

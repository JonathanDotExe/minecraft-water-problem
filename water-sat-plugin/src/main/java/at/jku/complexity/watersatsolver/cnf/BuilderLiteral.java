package at.jku.complexity.watersatsolver.cnf;

public class BuilderLiteral {

	private final String variable;
	private final boolean negate;
	
	public BuilderLiteral(String variable, boolean negate) {
		super();
		this.variable = variable;
		this.negate = negate;
	}

	public String getVariable() {
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
		result = prime * result + ((variable == null) ? 0 : variable.hashCode());
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
		BuilderLiteral other = (BuilderLiteral) obj;
		if (negate != other.negate)
			return false;
		if (variable == null) {
			if (other.variable != null)
				return false;
		} else if (!variable.equals(other.variable))
			return false;
		return true;
	}
	
}

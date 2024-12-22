package at.jku.complexity.watersatsolver.cnf;

import java.util.Arrays;

public class Clause {
	
	private final Literal[] literals;

	public Clause(Literal[] literals) {
		super();
		this.literals = literals.clone();
	}

	public Literal[] getLiterals() {
		return literals.clone();
	}
	
	public boolean hasLiteral(Literal lit) {
		return Arrays.stream(literals).anyMatch(l -> lit.equals(l));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((literals == null) ? 0 : literals.hashCode());
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
		Clause other = (Clause) obj;
		if (literals == null) {
			if (other.literals != null)
				return false;
		} else if (!literals.equals(other.literals))
			return false;
		return true;
	}
	
}

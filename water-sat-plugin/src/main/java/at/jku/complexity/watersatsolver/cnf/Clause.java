package at.jku.complexity.watersatsolver.cnf;

import java.util.Collections;
import java.util.Set;

public class Clause {
	
	private final Set<Literal> literals;

	public Clause(Set<Literal> literals) {
		super();
		this.literals = Collections.unmodifiableSet(literals);
	}

	public Set<Literal> getLiterals() {
		return literals;
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

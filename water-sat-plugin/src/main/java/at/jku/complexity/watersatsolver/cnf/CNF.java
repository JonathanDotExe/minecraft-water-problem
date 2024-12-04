package at.jku.complexity.watersatsolver.cnf;

import java.util.Collections;
import java.util.Set;

public class CNF {
	
	private final String[] variables;
	private final Set<Clause> clauses;
	
	public CNF(String[] variables, Set<Clause> clauses) {
		super();
		this.variables = variables;
		this.clauses = Collections.unmodifiableSet(clauses);
	}

	public String[] getVariables() {
		return variables.clone();
	}

	public Set<Clause> getClauses() {
		return clauses;
	}

	public String getVariableName(int variable) {
		return variables[variable];
	}
	
}

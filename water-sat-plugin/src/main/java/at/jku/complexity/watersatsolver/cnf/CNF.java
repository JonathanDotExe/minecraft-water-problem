package at.jku.complexity.watersatsolver.cnf;

public class CNF {
	
	private final String[] variables;
	private final Clause[] clauses;
	
	public CNF(String[] variables, Clause[] clauses) {
		super();
		this.variables = variables;
		this.clauses = clauses.clone();
	}

	public String[] getVariables() {
		return variables.clone();
	}

	public Clause[] getClauses() {
		return clauses.clone();
	}

	public String getVariableName(int variable) {
		return variables[variable];
	}
	
}

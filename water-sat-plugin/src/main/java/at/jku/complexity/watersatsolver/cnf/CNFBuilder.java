package at.jku.complexity.watersatsolver.cnf;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CNFBuilder {

	private int nextVariable = 0;
	private Map<String, Integer> variables = new HashMap<>();
	private Set<Clause> clauses = new HashSet<>();
	
	public void addClause(BuilderLiteral... clause) {
		addClause(new HashSet<>(Arrays.asList(clause)));
	}
	
	public void addClause(Set<BuilderLiteral> clause) {
		Set<Literal> literals = new HashSet<>();
		for (BuilderLiteral l : clause) {
			//Create new var id
			int id = 0;
			if (!variables.containsKey(l.getVariable())) {
				variables.put(l.getVariable(), nextVariable);
				id = nextVariable;
				nextVariable++;
			}
			else {
				id = variables.get(l.getVariable());
			}
			
			literals.add(new Literal(id, l.isNegate()));
		}
		
		clauses.add(new Clause(literals));
	}
	
	public CNF build() {
		String[] vars = new String[nextVariable];
		for (Map.Entry<String, Integer> e : variables.entrySet()) {
			vars[e.getValue()] = e.getKey();
		}
		return new CNF(vars, clauses);
	}
	
}

package at.jku.complexity.watersatsolver.cnf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CNFBuilder {

	private int nextVariable = 0;
	private Map<String, Integer> variables = new HashMap<>();
	private List<Clause> clauses = new ArrayList<Clause>();

	public void addClause(BuilderLiteral... clause) {
		Literal[] literals = new Literal[clause.length];
		for (int i = 0; i < clause.length; i++) {
			BuilderLiteral l = clause[i];
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
			
			literals[i] = new Literal(id, l.isNegate());
		}
		
		clauses.add(new Clause(literals));
	}
	
	public CNF build() {
		String[] vars = new String[nextVariable];
		for (Map.Entry<String, Integer> e : variables.entrySet()) {
			vars[e.getValue()] = e.getKey();
		}
		return new CNF(vars, clauses.toArray(new Clause[clauses.size()]));
	}
	
}

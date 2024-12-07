package at.jku.complexity.watersatsolver.cnf;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CNFParser {
	
	private static final String OR = "|";
	private static final String AND = "&";
	private static final String NOT = "!";
	private static final String BRACKET_OPEN = "(";
	private static final String BRACKET_CLOSE = ")";

	
	private static BuilderLiteral parseLiteral(String token) {
		System.out.println("Checking literal " + token);
		boolean negate = false;
		token = token.replace(BRACKET_OPEN, "").replace(BRACKET_CLOSE, "").trim();
		if (token.startsWith(NOT)) {
			token = token.substring(1);
			negate = true;
		}
		if (token.isEmpty() || !token.chars().allMatch(Character::isAlphabetic)) {
			System.err.println(token + " is not a valid literal!");
			return null;
		}
		return new BuilderLiteral(token, negate);
	}
	
	
	private static Set<BuilderLiteral> parseClause(String token) {
		System.out.println("Checking clause " + token);
		token = token.trim();
		Set<BuilderLiteral> clause = new HashSet<>();
		//Single literal
		BuilderLiteral l = parseLiteral(token);
		if (l != null) {
			clause.add(l);
			return clause;
		}
		//Brackets
		if (!token.startsWith(BRACKET_OPEN) || !token.endsWith(BRACKET_CLOSE)) {
			System.out.println("No brackets for clause " + token);
			return null;
		}
		
		while(token.startsWith(BRACKET_OPEN) && token.endsWith(BRACKET_CLOSE)) {
			token = token.substring(1, token.length() - 1);
		}
		System.out.println(token);
		String[] literals = token.split("\\" + OR);
		System.out.println(Arrays.toString(literals));
		System.out.println(OR);
		for (String t : literals) {
			l = parseLiteral(t);
			if (l == null) {
				return null;
			}
			clause.add(l);
		}
		return clause;
	}
	
	public static CNF parse(String token) {
		CNFBuilder builder = new CNFBuilder();
		token = token.trim();		
		
		String[] clauseStrings = token.split(AND);
		
		for (String t : clauseStrings) {
			Set<BuilderLiteral> clause = parseClause(t);
			if (clause == null) {
				return null;
			}
			builder.addClause(clause);
		}
		
		
		return builder.build();
	}

}

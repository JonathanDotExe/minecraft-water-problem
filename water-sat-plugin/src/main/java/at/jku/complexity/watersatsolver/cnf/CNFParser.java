package at.jku.complexity.watersatsolver.cnf;

import java.util.ArrayList;
import java.util.List;

public class CNFParser {
	
	private static final String OR = "|";
	private static final String AND = "&";
	private static final String NOT = "!";
	private static final String BRACKET_OPEN = "(";
	private static final String BRACKET_CLOSE = ")";

	
	private static BuilderLiteral parseLiteral(String token) {
		boolean negate = false;
		token = token.replace(BRACKET_OPEN, "").replace(BRACKET_CLOSE, "").trim();
		if (token.startsWith(NOT)) {
			token = token.substring(1);
			negate = true;
		}
		if (token.isEmpty() || !token.chars().allMatch(Character::isAlphabetic)) {
			return null;
		}
		return new BuilderLiteral(token, negate);
	}
	
	
	private static BuilderLiteral[] parseClause(String token) {
		token = token.trim();
		List<BuilderLiteral> clause = new ArrayList<>();
		//Single literal
		BuilderLiteral l = parseLiteral(token);
		if (l != null) {
			clause.add(l);
			return clause.toArray(new BuilderLiteral[clause.size()]);
		}
		//Brackets
		if (!token.startsWith(BRACKET_OPEN) || !token.endsWith(BRACKET_CLOSE)) {
			return null;
		}
		
		while(token.startsWith(BRACKET_OPEN) && token.endsWith(BRACKET_CLOSE)) {
			token = token.substring(1, token.length() - 1);
		}
		String[] literals = token.split("\\" + OR);
		for (String t : literals) {
			l = parseLiteral(t);
			if (l == null) {
				return null;
			}
			clause.add(l);
		}
		return clause.toArray(new BuilderLiteral[clause.size()]);
	}
	
	public static CNF parse(String token) {
		CNFBuilder builder = new CNFBuilder();
		token = token.trim();		
		
		String[] clauseStrings = token.split(AND);
		
		for (String t : clauseStrings) {
			BuilderLiteral[] clause = parseClause(t);
			if (clause == null) {
				return null;
			}
			builder.addClause(clause);
		}
		
		
		return builder.build();
	}

}

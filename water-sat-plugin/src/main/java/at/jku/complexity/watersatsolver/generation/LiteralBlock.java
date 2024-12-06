package at.jku.complexity.watersatsolver.generation;

import org.bukkit.Location;

import at.jku.complexity.watersatsolver.cnf.Literal;

public class LiteralBlock {

	private final Location location;
	private final Literal literal;
	
	public LiteralBlock(Location location, Literal literal) {
		super();
		this.location = location;
		this.literal = literal;
	}
	
	public Location getLocation() {
		return location;
	}
	public Literal getLiteral() {
		return literal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((literal == null) ? 0 : literal.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
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
		LiteralBlock other = (LiteralBlock) obj;
		if (literal == null) {
			if (other.literal != null)
				return false;
		} else if (!literal.equals(other.literal))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}
	
}

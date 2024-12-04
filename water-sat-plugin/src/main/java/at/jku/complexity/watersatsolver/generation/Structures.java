package at.jku.complexity.watersatsolver.generation;

import java.io.IOException;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.structure.Structure;

public final class Structures {
	
	public static final String SPLITTER = "splitter";
	public static final String CLAUSE = "clause";
	public static final String VARIABLE = "variable";
	private static Structure splitter;
	private static Structure clause;
	private static Structure variable;
	
	private Structures() {
		
	}
	
	public static void registerStructures() throws IOException {
		splitter = Bukkit.getStructureManager().loadStructure(Structures.class.getResourceAsStream("/" + SPLITTER + ".nbt"));
		clause = Bukkit.getStructureManager().loadStructure(Structures.class.getResourceAsStream("/" + CLAUSE + ".nbt"));
		variable = Bukkit.getStructureManager().loadStructure(Structures.class.getResourceAsStream("/" + VARIABLE + ".nbt"));
	}
	
	public static void placeSplitter(Location loc) {
		splitter.place(loc, false, StructureRotation.NONE, Mirror.NONE, 0, 1, new Random());
	}
	
	public static void placeClause(Location loc) {
		clause.place(loc, false, StructureRotation.NONE, Mirror.NONE, 0, 1, new Random());
	}
	
	public static void placeVariable(Location loc) {
		variable.place(loc, false, StructureRotation.NONE, Mirror.NONE, 0, 1, new Random());
	}

	public static Structure getSplitter() {
		return splitter;
	}

	public static Structure getClause() {
		return clause;
	}

	public static Structure getVariable() {
		return variable;
	}

}

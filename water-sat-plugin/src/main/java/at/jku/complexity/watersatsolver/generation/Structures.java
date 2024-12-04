package at.jku.complexity.watersatsolver.generation;

import java.io.IOException;

import org.bukkit.Bukkit;

public final class Structures {
	
	public static final String SPLITTER = "splitter";
	public static final String CLAUSE = "clause";
	public static final String VARIABLE = "variable";
	
	private Structures() {
		
	}
	
	public static void registerStructures() throws IOException {
		Bukkit.getStructureManager().loadStructure(Structures.class.getResourceAsStream(SPLITTER + ".nbt"));
		Bukkit.getStructureManager().loadStructure(Structures.class.getResourceAsStream(CLAUSE + ".nbt"));
		Bukkit.getStructureManager().loadStructure(Structures.class.getResourceAsStream(VARIABLE + ".nbt"));
	}

}

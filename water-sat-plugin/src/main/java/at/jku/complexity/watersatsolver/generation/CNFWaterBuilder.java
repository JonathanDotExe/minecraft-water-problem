package at.jku.complexity.watersatsolver.generation;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.util.BlockVector;

import at.jku.complexity.watersatsolver.cnf.CNF;
import at.jku.complexity.watersatsolver.cnf.Clause;
import at.jku.complexity.watersatsolver.cnf.Literal;

public class CNFWaterBuilder {
	
	
	public static CNFWaterMachine build(Location place, CNF cnf) {
		int width = (Structures.getVariable().getSize().getBlockX() - 1) * cnf.getVariables().length + 1;
		int length = Structures.getVariable().getSize().getBlockZ() + Structures.getSplitter().getSize().getBlockZ() * cnf.getClauses().length;
		int height = Structures.getVariable().getSize().getBlockY() + cnf.getClauses().length + cnf.getVariables().length + 1;

		Location start = place.clone();
		place = place.clone();
		place.add(0, height - Structures.getVariable().getSize().getBlockY(), 0);
		
		int startX = place.getBlockX();
		Location[] variableLocs = new Location[cnf.getVariables().length];
		Set<Location> waterLocs = new HashSet<>();
		Set<Location> sandLocs = new HashSet<>();
		//Place variables
		for (int i = 0; i < cnf.getVariables().length; i++) {
			Structures.placeVariable(place);
			//Assignment water
			variableLocs[i] = place.clone().add(1, 3, 2);
			//Negation water
			waterLocs.add(place.clone().add(4, 1, 1));
			//Sand
			sandLocs.add(place.clone().add(4, 3, 2));
			//Sign
			Location signPlace = place.clone().add(1, Structures.getVariable().getSize().getY(), 1);
			signPlace.getBlock().setType(Material.OAK_SIGN);
			Sign sign = (Sign) signPlace.getBlock().getState();
			sign.getSide(Side.FRONT).setLine(1, cnf.getVariables()[i]);
			sign.update(false);
			//Add loc
			place.add(Structures.getVariable().getSize().getX() - 1, 0, 0);
		}

		place.add(0, -1, Structures.getVariable().getSize().getZ());
		//Clauses
		for (Clause clause : cnf.getClauses()) {
			place.setX(startX);

			//Splitters
			for (int i = 0; i < cnf.getVariables().length; i++) {
				Structures.placeSplitter(place);
				final Literal l = new Literal(i, false);
				final Literal nl = new Literal(i, true);
				//Positive
				if (clause.hasLiteral(l)) {
					place.clone().add(2, 2, 0).getBlock().setType(Material.AIR);
				}
				//Negative
				if (clause.hasLiteral(nl)) {
					place.clone().add(3, 2, 0).getBlock().setType(Material.AIR);
				}
				
				place.add(Structures.getSplitter().getSize().getX() - 1, 0, 0);
			}
			int startY = place.getBlockY();
			
			place.setX(startX);
			place.add(0, -2, -1);
			
			//Clauses
			for (int i = 0; i < cnf.getVariables().length; i++) {
				Structures.placeClause(place);
				place.add(Structures.getClause().getSize().getX() - 1, -1, 0);
			}
			
			place.setY(startY);
			place.add(0, -1, Structures.getSplitter().getSize().getZ() + 1);
		}
		
		return new CNFWaterMachine(start, new BlockVector(width, height, length), variableLocs, waterLocs, sandLocs, 5 * 7 * height);
	}

}

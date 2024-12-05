package at.jku.complexity.watersatsolver.generation;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;

import at.jku.complexity.watersatsolver.cnf.CNF;
import at.jku.complexity.watersatsolver.cnf.Clause;

public class CNFWaterBuilder {
	
	
	public static void build(Location place, CNF cnf, boolean... assignment) {
		//TODO add height first
		place = place.clone();
		int startX = place.getBlockX();
		//Place variables
		for (String var : cnf.getVariables()) {
			Structures.placeVariable(place);
			//Sign
			Location signPlace = place.clone().add(1, Structures.getVariable().getSize().getY(), 1);
			signPlace.getBlock().setType(Material.OAK_SIGN);
			Sign sign = (Sign) signPlace.getBlock().getState();
			sign.getSide(Side.FRONT).setLine(1, var);
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
				place.add(Structures.getSplitter().getSize().getX() - 1, 0, 0);
				//TODO erase blocks for literals
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
	}

}

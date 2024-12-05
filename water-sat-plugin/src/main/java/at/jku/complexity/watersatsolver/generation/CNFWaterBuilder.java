package at.jku.complexity.watersatsolver.generation;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;

import at.jku.complexity.watersatsolver.cnf.CNF;
import at.jku.complexity.watersatsolver.cnf.Clause;
import at.jku.complexity.watersatsolver.cnf.Literal;

public class CNFWaterBuilder {
	
	
	public static void build(Location place, CNF cnf, boolean... assignment) {
		//TODO add height first
		place = place.clone();
		int startX = place.getBlockX();
		List<Location> waterLocs = new ArrayList<>();
		//Place variables
		for (int i = 0; i < cnf.getVariables().length; i++) {
			Structures.placeVariable(place);
			//Assignment water
			if (assignment[i]) {
				waterLocs.add(place.clone().add(1, 3, 2));
			}
			//Negation water
			waterLocs.add(place.clone().add(4, 1, 1));
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
				//Positive
				if (clause.getLiterals().contains(new Literal(i, false))) {
					place.clone().add(2, 2, 0).getBlock().setType(Material.AIR);
				}
				//Negative
				if (clause.getLiterals().contains(new Literal(i, true))) {
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
		
		//Place water
		for (Location p : waterLocs) {
			p.getBlock().setType(Material.WATER);
		}
		
	}

}

package at.jku.complexity.watersatsolver.generation;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;

import at.jku.complexity.watersatsolver.cnf.CNF;

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
		//Splitters
		place.setX(startX);
		place.add(0, 0, Structures.getVariable().getSize().getZ());
		
		for (int i = 0; i < cnf.getVariables().length; i++) {
			Structures.placeSplitter(place);
			place.add(Structures.getSplitter().getSize().getX() - 1, 0, 0);
			//TODO erase blocks
		}
	}

}

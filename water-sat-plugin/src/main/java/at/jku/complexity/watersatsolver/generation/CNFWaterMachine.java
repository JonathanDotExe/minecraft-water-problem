package at.jku.complexity.watersatsolver.generation;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.BlockVector;

public class CNFWaterMachine {
	
	private final Location location;
	private final BlockVector size;
	
	private final Location[] variableWaterLocations;
	private final Set<Location> fixedWaterLocations;
	private final Set<Location> sandLocations;
	
	private final long ticksTillFinished;
	
	
	
	public CNFWaterMachine(Location location, BlockVector size, Location[] variableWaterLocations,
			Set<Location> fixedWaterLocations, Set<Location> sandLocations,
			long ticksTillFinished) {
		super();
		this.location = location;
		this.size = size;
		this.variableWaterLocations = variableWaterLocations;
		this.fixedWaterLocations = fixedWaterLocations;
		this.sandLocations = sandLocations;
		this.ticksTillFinished = ticksTillFinished;
	}

	public void assign(boolean... assignment) {
		//Clear water
		for (int x = 0; x < size.getBlockX(); x++) {
			for (int y = 0; y < size.getBlockY(); y++) {
				for (int z = 0; z < size.getBlockZ(); z++) {
					Block block = location.getBlock().getRelative(x, y, z);
					if (block.getType() == Material.WATER) {
						block.setType(Material.AIR);
					}
				}
			}
		}
		//Sand
		for (Location location : sandLocations) {
			location.clone().add(0, -2, 0).getBlock().setType(Material.AIR, false);
			location.clone().add(0, -1, 0).getBlock().setType(Material.TRIPWIRE, false);
			location.getBlock().setType(Material.SAND, false);
		}
		//Variables
		for (int i = 0; i < variableWaterLocations.length; i++) {
			variableWaterLocations[i].getBlock().setType(assignment[i] ? Material.WATER : Material.AIR);
		}
		//Fixed water
		for (Location location : fixedWaterLocations) {
			location.getBlock().setType(Material.WATER);
		}
	}
	
	public boolean checkSAT() {
		boolean sat = true;
		for (int x = 0; x < size.getBlockX() && sat; x++) {
			for (int y = 0; y < size.getBlockY() && sat; y++) {
				for (int z = 0; z < size.getBlockZ() && sat; z++) {
					Block block = location.getBlock().getRelative(x, y, z);
					if (block.getType() == Material.GOLD_BLOCK) {
						sat = block.getRelative(0, 1, 0).getType() == Material.WATER;
					}
				}
			}
		}
		return sat;
	}

	public Location getLocation() {
		return location.clone();
	}

	public BlockVector getSize() {
		return size.clone();
	}

	public long getTicksTillFinished() {
		return ticksTillFinished;
	}

}

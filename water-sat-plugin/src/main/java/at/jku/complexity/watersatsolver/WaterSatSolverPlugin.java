package at.jku.complexity.watersatsolver;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;


public class WaterSatSolverPlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		super.onEnable();
		//Load structures
		try {
			Structures.registerStructures();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}

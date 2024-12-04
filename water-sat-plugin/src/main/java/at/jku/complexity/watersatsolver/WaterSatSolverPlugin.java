package at.jku.complexity.watersatsolver;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import at.jku.complexity.watersatsolver.commands.GenerateCNFCommand;
import at.jku.complexity.watersatsolver.generation.Structures;


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
		
		//Commands
		getCommand(GenerateCNFCommand.COMMAND_NAME).setExecutor(new GenerateCNFCommand());
	}
	
}

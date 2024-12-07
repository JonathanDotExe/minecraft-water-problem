package at.jku.complexity.watersatsolver.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import at.jku.complexity.watersatsolver.cnf.CNF;
import at.jku.complexity.watersatsolver.cnf.CNFParser;
import at.jku.complexity.watersatsolver.generation.CNFWaterBuilder;
import at.jku.complexity.watersatsolver.generation.CNFWaterMachine;

public class GenerateCNFCommand implements CommandExecutor {

	public static final String COMMAND_NAME = "generatecnf";
	private Plugin plugin;

	
	public GenerateCNFCommand(Plugin plugin) {
		super();
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase(COMMAND_NAME)) {
			if (sender.isOp() && sender instanceof Player) {
				if (args.length >= 1) {
					String input = "";
					for (int i = 0; i < args.length; i++) {
						input += args[i] + " ";
					}
					//TODO parse cnf
					/*CNFBuilder builder = new CNFBuilder();
					builder.addClause(new BuilderLiteral("x", false), new BuilderLiteral("y", false), new BuilderLiteral("z", false));
					builder.addClause(new BuilderLiteral("x", true), new BuilderLiteral("y", false), new BuilderLiteral("z", false));
					builder.addClause(new BuilderLiteral("x", false), new BuilderLiteral("y", false), new BuilderLiteral("z", true));*/
					
					CNF cnf = CNFParser.parse(input);
					
					if (cnf == null) {
						sender.sendMessage("The input " + input + " is not a valid cnf!");
						return true;
					}
					
					CNFWaterMachine machine =  CNFWaterBuilder.build(((Player) sender).getLocation(), cnf);
					
					//Assignments
					RunningCNF run = new RunningCNF(cnf, machine);
					run.start(plugin, sender);
							
					return true;
				}
				else {
					return false;
				}
			}
			else {
				sender.sendMessage("This command can only be executed by a player with operator permissions!");
			}
		}
		return false;
	}
	
}

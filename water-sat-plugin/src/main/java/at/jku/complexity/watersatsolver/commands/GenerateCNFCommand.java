package at.jku.complexity.watersatsolver.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.jku.complexity.watersatsolver.cnf.BuilderLiteral;
import at.jku.complexity.watersatsolver.cnf.CNF;
import at.jku.complexity.watersatsolver.cnf.CNFBuilder;
import at.jku.complexity.watersatsolver.generation.CNFWaterBuilder;

public class GenerateCNFCommand implements CommandExecutor {

	public static final String COMMAND_NAME = "generatecnf";
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase(COMMAND_NAME)) {
			if (sender.isOp() && sender instanceof Player) {
				if (args.length >= 1) {
					//String input = args[0];
					//TODO parse cnf
					CNFBuilder builder = new CNFBuilder();
					builder.addClause(new BuilderLiteral("x", false), new BuilderLiteral("y", false), new BuilderLiteral("z", false));
					builder.addClause(new BuilderLiteral("x", true), new BuilderLiteral("y", false), new BuilderLiteral("z", false));
					builder.addClause(new BuilderLiteral("x", false), new BuilderLiteral("y", false), new BuilderLiteral("z", true));
					CNF cnf = builder.build();
					
					CNFWaterBuilder.build(((Player) sender).getLocation(), cnf, true, true, true);	
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

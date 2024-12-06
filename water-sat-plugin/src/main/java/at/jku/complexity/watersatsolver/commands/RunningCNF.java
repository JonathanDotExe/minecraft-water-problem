package at.jku.complexity.watersatsolver.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import at.jku.complexity.watersatsolver.cnf.CNF;
import at.jku.complexity.watersatsolver.generation.CNFWaterMachine;

public class RunningCNF {
	
	private final long numAssignments;
	private final CNFWaterMachine machine;
	private long nextAssignment = 0;
	private boolean[] assignment;
	private boolean sat = false;
	
	
	
	public RunningCNF(CNF cnf, CNFWaterMachine machine) {
		super();
		this.numAssignments = (int) Math.pow(2, cnf.getVariables().length);
		assignment = new boolean[cnf.getVariables().length];
		this.machine = machine;
	}
	
	public void start(Plugin plugin, CommandSender sender) {
		bitsToBooleanArray(nextAssignment, assignment);
		nextAssignment++;
		
		sender.sendMessage("Checking assignment " + Arrays.toString(assignment) + " ...");
		machine.assign(assignment);
	
		//Display result
		Bukkit.getScheduler().runTaskLater(plugin, () -> performStep(plugin, sender), machine.getTicksTillFinished());
		
	}
	
	private void performStep(Plugin plugin, CommandSender sender) {
		sat = machine.checkSAT();
		sender.sendMessage(sat ? "The assignment is satisfying!" : "The assignment is not satisfying");
		
		if (!isFinished()) {
			bitsToBooleanArray(nextAssignment, assignment);
			nextAssignment++;
			
			sender.sendMessage("Checking assignment " + Arrays.toString(assignment) + " ...");
			machine.assign(assignment);
			
			Bukkit.getScheduler().runTaskLater(plugin, () -> performStep(plugin, sender), machine.getTicksTillFinished());
		}
	}

	public boolean isFinished() {
		return sat || nextAssignment >= numAssignments;
	}
	
	private static void bitsToBooleanArray(long n, boolean[] arr) {
		for (int i = 0; i < arr.length; i++) {
			long bit = n & 1;
			arr[i] = bit != 0;
			n = n >> 1;
		}
	}

}

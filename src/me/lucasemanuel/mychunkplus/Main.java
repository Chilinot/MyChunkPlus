/**
 *  Name: Main.java
 *  Date: 22:50:54 - 16 aug 2012
 * 
 *  Author: LucasEmanuel @ bukkit forums
 *  
 *  
 *  Description:
 *  
 *  
 *  
 * 
 * 
 */

package me.lucasemanuel.mychunkplus;

import me.lucasemanuel.mychunkplus.utils.Config;
import me.lucasemanuel.mychunkplus.utils.ConsoleLogger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private ConsoleLogger logger;
	private ChunkCleaner chunkcleaner;
	
	public void onEnable() {
		Config.load(this);
		this.logger = new ConsoleLogger(this, "Main");
		
		logger.debug("Initiating startup.");
		
		this.getServer().getPluginManager().registerEvents(new PlusListener(this), this);
		
		Commands executor = new Commands(this);
		this.getCommand("mcpcleanworld").setExecutor(executor);
		
		chunkcleaner = new ChunkCleaner(this);
		
		logger.debug("Startup finished!");
	}
	
	public ChunkCleaner getChunkCleaner() {
		return chunkcleaner;
	}
}

class Commands implements CommandExecutor {
	
	private ConsoleLogger logger;
	private Main plugin;
	
	public Commands(Main instance) {
		plugin = instance;
		logger = new ConsoleLogger(instance, "CommandExecutor");
		
		logger.debug("Initiated");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("mcpcleanworld")) {
			
			if(args.length != 1) return false;
			
			this.plugin.getChunkCleaner().removeChunks(args[0]);
			
			return true;
		}
		
		return false;
	}
}
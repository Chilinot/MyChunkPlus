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

import me.ellbristow.mychunk.MyChunk;
import me.ellbristow.mychunk.MyChunkChunk;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private ConsoleLogger logger;
	
	public void onEnable() {
		Config.load(this);
		this.logger = new ConsoleLogger(this, "Main");
		
		logger.debug("Initiating startup.");
		
		this.getServer().getPluginManager().registerEvents(new PlusListener(this), this);
		
		logger.debug("Startup finished!");
	}

}

class PlusListener implements Listener {
	
	private ConsoleLogger logger;
	
	public PlusListener(Main instance) {
		this.logger = new ConsoleLogger(instance, "Listener");
		
		logger.debug("Initiated");
	}
	
	@EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
	public void onBlockPlace(BlockPlaceEvent event) {
		MyChunkChunk chunk = new MyChunkChunk(event.getBlock(), (MyChunk) Bukkit.getServer().getPluginManager().getPlugin("MyChunk"));
		
		if(!chunk.isClaimed()) {
			event.getPlayer().sendMessage(ChatColor.RED + "Du har inte tillåtelse att bygga på mark du inte äger!");
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
	public void onBlockBreak(BlockBreakEvent event) {
		MyChunkChunk chunk = new MyChunkChunk(event.getBlock(), (MyChunk) Bukkit.getServer().getPluginManager().getPlugin("MyChunk"));
		
		if(!chunk.isClaimed()) {
			event.getPlayer().sendMessage(ChatColor.RED + "Du har inte tillåtelse att bygga på mark du inte äger!");
			event.setCancelled(true);
		}
	}
}

/**
 *  Name: PlusListener.java
 *  Date: 16:48:06 - 26 aug 2012
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

import java.util.HashSet;

import me.ellbristow.mychunk.MyChunkChunk;
import me.lucasemanuel.mychunkplus.utils.ConsoleLogger;
import me.lucasemanuel.mychunkplus.utils.MyChunkHook;
import me.lucasemanuel.mychunkplus.utils.WorldGuardHook;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;

class PlusListener implements Listener {
	
	private ConsoleLogger logger;
	
	private HashSet<Location> monitoredsigns = new HashSet<Location>();
	
	public PlusListener(Main instance) {
		this.logger = new ConsoleLogger(instance, "Listener");
		
		logger.debug("Initiated");
	}
	
	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void onBlockPlace(BlockPlaceEvent event) {
		
		MyChunkChunk chunk = MyChunkHook.getChunk(event.getBlock());
		
		if(!chunk.isClaimed() && !event.getPlayer().hasPermission("mychunkplus.override") && !WorldGuardHook.isRegion(event.getBlock().getLocation())) {
			
			if(event.getBlock().getType().equals(Material.WALL_SIGN) || event.getBlock().getType().equals(Material.SIGN_POST)) {
				this.monitoredsigns.add(event.getBlock().getLocation());
			}
			else {
				event.getPlayer().sendMessage(ChatColor.RED + "Du har inte till�telse att bygga p� mark du inte �ger!");
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void onBlockBreak(BlockBreakEvent event) {
		
		MyChunkChunk chunk = MyChunkHook.getChunk(event.getBlock());
		
		if(!chunk.isClaimed() && !event.getPlayer().hasPermission("mychunkplus.override") && !WorldGuardHook.isRegion(event.getBlock().getLocation())) {
			event.getPlayer().sendMessage(ChatColor.RED + "Du har inte till�telse att bygga p� mark du inte �ger!");
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void onSignChange(SignChangeEvent event) {
		
		if(monitoredsigns.contains(event.getBlock().getLocation())) {
			if(!event.getPlayer().hasPermission("mychunkplus.override")) {
				String firstline = event.getLine(0).toLowerCase();
				
				if(!firstline.equals("[claim]")) {
					event.getPlayer().sendMessage(ChatColor.RED + "Du har inte till�telse att bygga p� mark du inte �ger!");
					event.setCancelled(true);
					event.getBlock().breakNaturally();
					this.monitoredsigns.remove(event.getBlock().getLocation());
				}
			}
			else {
				monitoredsigns.remove(event.getBlock().getLocation());
			}
		}
	}
}
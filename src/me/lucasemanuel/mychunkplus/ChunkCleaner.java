/**
 *  Name: ChunkCleaner.java
 *  Date: 16:34:50 - 26 aug 2012
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

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.ellbristow.mychunk.MyChunkChunk;
import me.lucasemanuel.mychunkplus.utils.ConsoleLogger;
import me.lucasemanuel.mychunkplus.utils.MyChunkHook;

public class ChunkCleaner {
	
	private ConsoleLogger logger;
	
	public ChunkCleaner(Main instance) {
		logger = new ConsoleLogger(instance, "ChunkCleaner");
		
		logger.debug("Initiated");
	}
	
	public void removeChunks(String worldname) {
		
		World world = Bukkit.getWorld(worldname);
		if(world == null) { 
			System.out.println("World doesnt exist!");
			return; 
		}
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(new File("plugins/MyChunk/chunks.yml"));
		if(config == null) {
			System.out.println("Could not load chunks.yml!");
			return;
		}
		
		for(String string : config.getKeys(false)) {
			if(string.startsWith(world.getName())) {
				
				String[] parts = string.split("_");
				
				int x = Integer.parseInt(parts[parts.length - 2]);
				int z = Integer.parseInt(parts[parts.length - 1]);
				
				System.out.println("X: " + x);
				System.out.println("Z: " + z);
				
				MyChunkChunk chunk = MyChunkHook.getChunk(world.getChunkAt(x, z).getBlock(0, 0, 0));
				
				if(chunk.isClaimed()) chunk.unclaim();
			}
		}
	}
}

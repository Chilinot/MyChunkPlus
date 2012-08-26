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

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;

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
		
		for(Chunk chunk : world.getLoadedChunks()) {
			
			MyChunkChunk mychunk = MyChunkHook.getChunk(chunk.getBlock(0, 0, 0));
			
			if(mychunk.isClaimed()) {
				mychunk.unclaim();
			}
		}
	}
}

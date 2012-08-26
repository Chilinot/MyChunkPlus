/**
 *  Name: MyChunkHook.java
 *  Date: 16:52:40 - 26 aug 2012
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

package me.lucasemanuel.mychunkplus.utils;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;

import me.ellbristow.mychunk.MyChunk;
import me.ellbristow.mychunk.MyChunkChunk;

public class MyChunkHook {
	
	public static MyChunkChunk getChunk(Block block) {
		return new MyChunkChunk(block, MyChunkHook.getMyChunk());
	}
	
	public static MyChunk getMyChunk() {
		return (MyChunk) Bukkit.getServer().getPluginManager().getPlugin("MyChunk");
	}
}

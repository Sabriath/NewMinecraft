package net.minecraft.api;

import net.minecraft.src.*;

public interface IMod
{
	//After all mods have been added and instanced, this is run once
	public abstract void onLoad();
	
	//When an old world is loaded, this is called for each IMod once
	public abstract void onLoadBlockIDs(World world, ITag configs);
	
	/*This function is called in one of two different times:
	 * 1: After the prvious 'onLoadBlockIDs' has been done for all IMods
	 * 2: When a new world is loaded
	 * 
	 * It is only called once for each IMod*/
	public abstract void onNewBlockIDs(World world, ITag configs);
	
	//This function is called for each IMod when the world is being saved
	public abstract void onSave(World world, ITag configs);
}
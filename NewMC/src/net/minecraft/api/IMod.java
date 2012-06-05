package net.minecraft.api;

import net.minecraft.src.*;

public interface IMod
{
	//After all mods have been added and instanced, this is run once when world loads
	public abstract void onLoad(IWorld world);
	
	//After all mods have run 'onLoad', any registered for LoadLast will run this event
	public abstract void onLoadLast(IWorld world, boolean triggered);
}
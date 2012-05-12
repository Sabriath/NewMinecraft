package net.minecraft.api;

import net.minecraft.src.*;

public interface IMod
{
	//After all mods have been added and instanced, this is run once
	public abstract void onLoad(IWorld world);	
}
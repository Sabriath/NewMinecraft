package net.minecraft.api;

public interface IWorld
{
	public abstract int registerBlock(IBlock block, String name);
	public abstract int registerItem(IItem item, String name);
	
	public abstract int getBlockID(String name);
	public abstract int getItemID(String name);
	public abstract String getBlockName(int id);
	public abstract String getItemName(int id);
	public abstract IBlock getBlock(int id);
	public abstract IItem getItem(int id);
}

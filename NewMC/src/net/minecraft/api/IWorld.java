package net.minecraft.api;

public interface IWorld
{
	public abstract int registerBlock(IBlock block, String name);
	public abstract int registerItem(IItem item, String name);
	//public abstract int registerUVRender(imgfile, filex, filey, width, height, needsort)
	
	public abstract int getBlockID(String name);
	public abstract int getItemID(String name);
	public abstract String getBlockName(int id);
	public abstract String getItemName(int id);
	public abstract IBlock getBlock(int id);
	public abstract IItem getItem(int id);
	
	public abstract boolean isChunkLoaded(int x, int y, int z);
	
	public abstract int getBlockIDAt(int x, int y, int z);
	public abstract IBlock getBlockAt(int x, int y, int z);
	public abstract BetterQueue<Integer> getRayTrace(double x1, double y1, double z1, double x2, double y2, double z2);
}
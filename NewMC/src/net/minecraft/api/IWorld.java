package net.minecraft.api;

public interface IWorld
{
	public abstract int registerBlock(IBlock block, String name);
	public abstract int getBlockID(String name);
	public abstract String getBlockName(int id);
	public abstract IBlock getBlock(int id);
	public abstract int getBlockIDAt(int x, int y, int z);
	public abstract int getBlockIDAt(Orientation o);
	public abstract IBlock getBlockAt(int x, int y, int z);
	public abstract boolean isBlockBlocking(int x, int y, int z, int face);
	public abstract boolean setBlockIDAt(int id, int x, int y, int z, double fx, double fy, double fz);
	public abstract boolean updateRandomTick(int x, int y, int z, int minticks, int maxticks);

	public abstract int registerItem(IItem item, String name);
	public abstract int getItemID(String name);
	public abstract String getItemName(int id);
	public abstract IItem getItem(int id);
	public abstract IItem newItem(int id);
	
	public abstract boolean isChunkLoaded(int x, int y, int z);
	public abstract BetterQueue<Integer> getRayTrace(double x1, double y1, double z1, double x2, double y2, double z2);
	
	public abstract ITexture registerTexture(String imgfile, int filex, int filey, int width, int height, int colorfilter, boolean transparent);
	public abstract void addTriangle(Texel t1, Texel t2, Texel t3);
	public abstract int getLight(double x, double y, double z);
	
	public abstract int rndInt();
	public abstract double rnd();
}
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
	
	public abstract boolean ChunkExists(int x, int y, int z);
	
	public abstract int getBlockIDAt(int x, int y, int z);
	public abstract IBlock getBlockAt(int x, int y, int z);
	public abstract int getLightAt(int x, int y, int z);
	
	public abstract int DrawTri(	float x1, float y1, float z1,
									float x2, float y2, float z2,
									float x3, float y3, float z3,
									int uvID, int uvP1, int uvP2, int uvP3,
									int rgb);
	public abstract void DrawSqr(	float x1, float y1, float z1,
									float x2, float y2, float z2,
									float x3, float y3, float z3,
									float x4, float y4, float z4,
									int uvID, int rgb);
}
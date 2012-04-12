package net.minecraft.src;

public interface IBlock
{
	public abstract int getBlockID();
	public abstract String getBlockName();
	
	public abstract boolean isSolid(World world, int x, int y, int z);
	
	public abstract float breakSpeed(IItem holding, World world, int x, int y, int z, int orientation);
	public abstract boolean isValidBreak(IItem holding, World world, int x, int y, int z, int orientation);
	public abstract Object onBreakDrop(World world, int x, int y, int z, int orientation);
	
	public abstract void updateBlockTick(World world, int x, int y, int z);
	public abstract void onBlockAdd(World world, int x, int y, int z, int fx, int fy, int fz);
	public abstract void onBlockRemove(World world, int x, int y, int z, int fx, int fy, int fz);
}

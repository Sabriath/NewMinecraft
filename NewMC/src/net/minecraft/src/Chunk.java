package net.minecraft.src;

public class Chunk
{
	private boolean Persist;
	private int cx;
	private int cy;
	private int cz;
	private short blocks[];

	public Chunk(int x, int y, int z)
	{
		cx = x & -16;
		cy = y & -16;
		cz = z & -16;
		blocks = new short[4096];
		Persist = false;
	}

	public void setPersist(boolean p)
	{
		Persist = p;
	}

	public boolean isXYZstart(int x, int y, int z)
	{
		if(cx != x)
			return false;
		if(cy != y)
			return false;
		if(cz != z)
			return false;
		return true;
	}
	
	public boolean canDrop(int x, int y, int z, float sqdistance)
	{
		if(Persist)
			return false;
		float fx = ((float)(x - cx)) + 7.5f;
		float fy = ((float)(y - cy)) + 7.5f;
		float fz = ((float)(z - cz)) + 7.5f;
		if(sqdistance < (fx * fx + fy * fy + fz * fz))
			return true;
		return false;
	}

	public int getBlock(int x, int y, int z)
	{
		return 65535 & (int)(blocks[(z & 15) << 8 | (y & 15) << 4 | (x & 15)]);
	}

	public void setBlock(int x, int y, int z, int block)
	{
		int f = (z & 15) << 8 | (y & 15) << 4 | (x & 15);
		blocks[f] = (short)block;
	}
}
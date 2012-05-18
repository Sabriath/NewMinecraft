package net.minecraft.src;

public class Chunk
{
	private static int lightLevs[] = {	  0,  17,  34,  51,
										 68,  85, 102, 119,
										136, 153, 170, 187,
										204, 221, 238, 255};
	private boolean Persist;
	private int cx;
	private int cy;
	private int cz;
	private short blocks[];
	private byte light[];
	
	public Chunk(int x, int y, int z)
	{
		cx = x & -16;
		cy = y & -16;
		cz = z & -16;
		blocks = new short[4096];
		light = new byte[4096];
		Persist = false;
	}
	
	public void setPersist(boolean p)
	{
		Persist = p;
	}
	
	public boolean isXYZ(int x, int y, int z)
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

	public short getBlock(int x, int y, int z)
	{
		return (short)(blocks[(z & 15) << 8 | (y & 15) << 4 | (x & 15)] & 4095);
	}

	public void setBlock(int x, int y, int z, short block)
	{
		int f = (z & 15) << 8 | (y & 15) << 4 | (x & 15);
		blocks[f] = (short)((blocks[f] & 61440) | block);
	}
	
	public int getLight(int x, int y, int z)
	{
		int b = (z & 15) << 8 | (y & 15) << 4 | (x & 15);
		int r = lightLevs[(blocks[b] & 61440) >> 12] << 16;
		int g = lightLevs[light[b] >> 4] << 8;
		return lightLevs[light[b] & 15] | r | g;
	}

	public void setLight(int x, int y, int z, short lt)
	{
		int f = (z & 15) << 8 | (y & 15) << 4 | (x & 15);
		blocks[f] = (short)((blocks[f] & 4095) | ((lt & 3840) << 4));
		light[f] = (byte)(lt & 255);
	}
}
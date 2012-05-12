package net.minecraft.src;

public class Chunk
{
	private short blocks[];
	private byte light[];
	
	public Chunk()
	{
		blocks = new short[4096];
		light = new byte[4096];
	}
	
	public short getBlock(int x, int y, int z)
	{
		return (short)(blocks[z << 8 | y << 4 | x] & 4095);
	}

	public void setBlock(int x, int y, int z, short block)
	{
		int f = z << 8 | y << 4 | x;
		blocks[f] = (short)((blocks[f] & 61440) | block);
	}
	
	public short getLight(int x, int y, int z)
	{
		int f = z << 8 | y << 4 | x;
		return (short)(((blocks[f] & 61440) >> 4) | light[f]);
	}

	public void setLight(int x, int y, int z, short lt)
	{
		int f = z << 8 | y << 4 | x;
		blocks[f] = (short)((blocks[f] & 4095) | ((lt & 3840) << 4));
		light[f] = (byte)(lt & 255);
	}
}
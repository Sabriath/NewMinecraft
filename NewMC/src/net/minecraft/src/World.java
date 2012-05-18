package net.minecraft.src;

import net.minecraft.api.*;
import java.util.*;

public class World
	implements IWorld
{
	private IBlock blocksList[] = new IBlock[4096];
	private String blocksName[] = new String[4096];
	private IItem itemsList[] = new IItem[65536];
	private String itemsName[] = new String[65536];
	private List<Chunk> chunksDropping = new ArrayList();
	private List<Chunk> chunkList = new ArrayList();
	
	@Override
	public int registerBlock(IBlock block, String name)
	{
		int j = -1;
		
		for(int i = 0; i < 4096; i++)
		{
			if(blocksName[i] == null)
			{
				if(j == -1)
					j = i;
			}
			else if(blocksName[i] == name)
			{
				if(blocksList[i] != null)
					throw new RuntimeException("'" + name + "' already exists as a block type");
				blocksList[i] = block;
				return i;
			}
		}
		if(j == -1)
			throw new RuntimeException("Maximum block IDs reached");
		blocksName[j] = name;
		blocksList[j] = block;
		return j;
	}
	
	@Override
	public int registerItem(IItem item, String name)
	{
		int j = -1;
		
		for(int i = 0; i < 65536; i++)
		{
			if(itemsName[i] == null)
			{
				if(j == -1)
					j = i;
			}
			else if(itemsName[i] == name)
			{
				if(itemsList[i] != null)
					throw new RuntimeException("'" + name + "' already exists as an item type");
				itemsList[i] = item;
				return i;
			}
		}
		if(j == -1)
			throw new RuntimeException("Maximum item IDs reached");
		itemsName[j] = name;
		itemsList[j] = item;
		return j;
	}
	
	@Override
	public int getBlockID(String name)
	{
		for(int i = 0; i < 4096; i++)
			if(blocksName[i] == name)
				return i;
		return -1;
	}
	
	@Override
	public int getItemID(String name)
	{
		for(int i = 0; i < 65536; i++)
			if(itemsName[i] == name)
				return i;
		return -1;
	}
	
	@Override
	public String getBlockName(int id)
	{
		if((id >= 0) && (id < 4096))
			return blocksName[id];
		return null;
	}
	
	@Override
	public String getItemName(int id)
	{
		if((id >= 0) && (id < 65536))
			return itemsName[id];
		return null;
	}
	
	@Override
	public IBlock getBlock(int id)
	{
		if((id >= 0) && (id < 4096))
			return blocksList[id];
		return null;
	}
	
	@Override
	public IItem getItem(int id)
	{
		if((id >= 0) && (id < 65536))
			return itemsList[id];
		return null;
	}

	@Override
	public boolean ChunkExists(int x, int y, int z)
	{
		int tx = x & -16;
		int ty = y & -16;
		int tz = z & -16;
		
		for(Chunk i : chunkList)
		{
			if(i.isXYZ(tx, ty, tz))
				return true;
		}
		return false;
	}

	@Override
	public int getBlockIDAt(int x, int y, int z)
	{
		int tx = x & -16;
		int ty = y & -16;
		int tz = z & -16;
		
		for(Chunk i : chunkList)
		{
			if(i.isXYZ(tx, ty, tz))
				return (int)(i.getBlock(tx, ty, tz));
		}
		return -1;
	}

	@Override
	public IBlock getBlockAt(int x, int y, int z)
	{
		int tx = x & -16;
		int ty = y & -16;
		int tz = z & -16;
		
		for(Chunk i : chunkList)
		{
			if(i.isXYZ(tx, ty, tz))
				return blocksList[i.getBlock(tx, ty, tz)];
		}
		return null;
	}

	@Override
	public int getLightAt(int x, int y, int z)
	{
		int tx = x & -16;
		int ty = y & -16;
		int tz = z & -16;
		
		for(Chunk i : chunkList)
		{
			if(i.isXYZ(tx, ty, tz))
				return i.getLight(tx, ty, tz);
		}
		return 0;
	}
	
	@Override
	public int DrawTri(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, int uvID, int uvP1, int uvP2, int uvP3, int rgb)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void DrawSqr(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4, int uvID, int rgb)
	{
		DrawTri(x1, y1, z1, x2, y2, z2, x4, y4, z4, uvID, 0, 1, 3, rgb);
		DrawTri(x4, y4, z4, x2, y2, z2, x3, y3, z3, uvID, 3, 1, 2, rgb);
	}
	
}

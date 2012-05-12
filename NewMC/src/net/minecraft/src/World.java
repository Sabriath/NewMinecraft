package net.minecraft.src;

import net.minecraft.api.*;

public class World
	implements IWorld
{
	private IBlock blocksList[] = new IBlock[4096];
	private String blocksName[] = new String[4096];
	private IItem itemsList[] = new IItem[65536];
	private String itemsName[] = new String[65536];
	
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
	
	public int getBlockID(String name)
	{
		for(int i = 0; i < 4096; i++)
			if(blocksName[i] == name)
				return i;
		return -1;
	}
	
	public int getItemID(String name)
	{
		for(int i = 0; i < 65536; i++)
			if(itemsName[i] == name)
				return i;
		return -1;
	}
	
	public String getBlockName(int id)
	{
		if((id >= 0) && (id < 4096))
			return blocksName[id];
		return null;
	}
	
	public String getItemName(int id)
	{
		if((id >= 0) && (id < 65536))
			return itemsName[id];
		return null;
	}
	
	public IBlock getBlock(int id)
	{
		if((id >= 0) && (id < 4096))
			return blocksList[id];
		return null;
	}
	
	public IItem getItem(int id)
	{
		if((id >= 0) && (id < 65536))
			return itemsList[id];
		return null;
	}

	public ItemStack newItemStack(int id, int dmg, int count)
	{
		return new ItemStack(id, dmg, count);
	}
}

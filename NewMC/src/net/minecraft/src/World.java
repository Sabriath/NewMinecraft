package net.minecraft.src;

import net.minecraft.api.*;

public class World
{
	public IBlock blocksList[] = new IBlock[65536];
	public int lastBlock = 0;
	public IItem itemsList[] = new IItem[65536];
	public int lastItem = 0;
	
	public int registerNewBlock(IBlock block)
	{
		int i;
		for(i = lastBlock; i < 65536; i++)
		{
			if(blocksList[i] == null)
			{
				blocksList[i] = block;
				lastBlock = i + 1;
				return i;
			}
		}
		// TODO: actually throw something meaningful
		throw new IllegalArgumentException("registerNewBlock maxed");
	}
	
	public int registerNewBlockWithMeta(IBlock block, int cidr)
	{
		int i, j;
		for(i = (lastBlock + cidr - 1) & (65536 - cidr); i < 65536; i += cidr)
		{
			if(blocksList[i] == null)
			{
				for(j = 0; j < cidr; j++)
					blocksList[i + j] = block;
				return i;
			}
		}
		//TODO: actually throw something meaningful
		throw new IllegalArgumentException("registerNewBlockWithMeta maxed");
	}
	
	public void registerBlock(IBlock block, int id)
	{
		if(blocksList[id] == null)
			blocksList[id] = block;
		else
			throw new IllegalArgumentException((new StringBuilder()).append("Slot ").append(id).append(" is already occupied by ").append(blocksList[id]).append(" when adding Block ").append(block).toString());
	}
	
	public void registerBlockWithMeta(IBlock block, int id, int cidr)
	{
		int i;
		for(i = 0; i < cidr; i++)
		{
			if(blocksList[id + i] != null)
				throw new IllegalArgumentException((new StringBuilder()).append("Slot ").append(id).append(" is already occupied by ").append(blocksList[id]).append(" when adding MetaBlock ").append(block).toString());
			blocksList[id + i] = block;
		}
	}
	
	public int registerNewItem(IItem item)
	{
		int i;
		for(i = lastItem; i < 32000; i++)
		{
			if(itemsList[i] == null)
			{
				itemsList[i] = item;
				lastItem = i + 1;
				return i;
			}
		}
		// TODO: actually throw something meaningful
		throw new IllegalArgumentException("registerNewItem maxed");
	}
	
	public void registerItem(IItem item, int id)
	{

		if(itemsList[id] == null)
			itemsList[id] = item;
		else
			throw new IllegalArgumentException((new StringBuilder()).append("Slot ").append(id).append(" is already occupied by ").append(itemsList[id]).append(" when adding Item ").append(item).toString());
	}
	
	public ItemStack newItemStack(int id, int dmg, int count)
	{
		return new ItemStack(id, dmg, count);
	}
}

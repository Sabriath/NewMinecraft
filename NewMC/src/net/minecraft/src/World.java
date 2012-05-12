package net.minecraft.src;

import net.minecraft.api.*;

public class World
{
	private IBlock blocksList[] = new IBlock[32768];
	private int lastBlock = 0;
	private IItem itemsList[] = new IItem[32768];
	private int lastItem = 0;
	
	public int registerNewBlock(IBlock block)
	{
		int i;
		for(i = lastBlock; i < 32768; i++)
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
		
	public void registerBlock(IBlock block, int id)
	{
		if(blocksList[id] == null)
			blocksList[id] = block;
		else
			throw new IllegalArgumentException((new StringBuilder()).append("Slot ").append(id).append(" is already occupied by ").append(blocksList[id]).append(" when adding Block ").append(block).toString());
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
				return i | 32768;
			}
		}
		// TODO: actually throw something meaningful
		throw new IllegalArgumentException("registerNewItem maxed");
	}
	
	public void registerItem(IItem item, int id)
	{
		id &= 32767;
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

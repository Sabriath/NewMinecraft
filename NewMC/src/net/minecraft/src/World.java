package net.minecraft.src;

public class World
{
	public IBlock blocksList[] = new IBlock[4096];
	public int lastBlock = 0;
	public IItem itemsList[] = new IItem[32000];
	public int lastItem = 0;
	
	public int registerNewBlock(IBlock block)
	{
		int i;
		for(i = lastBlock; i < 4096; i++)
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
	
	public int registerNewBlockWithMeta(IBlock block)
	{
		int i, j;
		for(i = (lastBlock + 15) & 4080; i < 4096; i += 16)
		{
			if(blocksList[i] == null)
			{
				for(j = 0; j < 16; j++)
					blocksList[i + j] = block;
				return i;
			}
		}
		//TODO: actually throw something meaningful
		throw new IllegalArgumentException("registerNewBlockWithMeta maxed");
	}
	
	public int registerBlock(IBlock block, int id)
	{
		if(blocksList[id] == null)
		{
			blocksList[id] = block;
			return id;
		}
		throw new IllegalArgumentException((new StringBuilder()).append("Slot ").append(id).append(" is already occupied by ").append(blocksList[id]).append(" when adding Block ").append(block).toString());
	}
	
	public int registerBlockWithMeta(IBlock block, int id)
	{
		int i;
		for(i = 0; i < 16; i++)
		{
			if(blocksList[id + i] != null)
			{
				throw new IllegalArgumentException((new StringBuilder()).append("Slot ").append(id).append(" is already occupied by ").append(blocksList[id]).append(" when adding MetaBlock ").append(block).toString());
			}
			blocksList[id + i] = block;
		}
		return id;
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
	
	public int registerItem(IItem item, int id)
	{

		if(itemsList[id] == null)
		{
			itemsList[id] = item;
			return id;
		}
		throw new IllegalArgumentException((new StringBuilder()).append("Slot ").append(id).append(" is already occupied by ").append(itemsList[id]).append(" when adding Item ").append(item).toString());
	}
	
	public ItemStack newItemStack(int id, int dmg, int count)
	{
		return new ItemStack(id, dmg, count);
	}
}

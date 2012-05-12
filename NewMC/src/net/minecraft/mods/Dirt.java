package net.minecraft.mods;

import net.minecraft.api.*;
import net.minecraft.src.*;

public class Dirt
	implements IMod, IBlock, IItem
{
	
	//IMod
		int blockID = -1;
		int itemID = -1;
		
		@Override
		public void onLoad()
		{
		}
	
		@Override
		public void onNewBlockIDs(World world, ITag configs)
		{
			if(blockID == -1)
			{
				blockID = world.registerNewBlock(this);
				configs.FindOrAdd("Dirt.Block").SetInt(blockID);
			}
			if(itemID == -1)
			{
				itemID = world.registerNewItem(this);
				configs.FindOrAdd("Dirt.Int").SetInt(itemID);
			}
		}

		@Override
		public void onLoadBlockIDs(World world, ITag configs)
		{
			ITag t = configs.Find("Dirt");
			if(t == null) return;
			blockID = t.FindOrAdd("Block").GetInt();
			itemID = t.FindOrAdd("Item").GetInt();
		}

		@Override
		public void onSave(World world, ITag configs)
		{
		}

		
	//IBlock
		
		@Override
		public int getBlockID()
		{
			return blockID;
		}
		
		@Override
		public String getBlockName()
		{
			return "Dirt";
		}
		
		@Override
		public boolean isSolid(World world, int x, int y, int z)
		{
			return true;
		}
		
		@Override
		public float breakSpeed(IItem holding, World world, int x, int y, int z, int orientation)
		{
			if(holding instanceof IShovel)
				return ((IShovel)holding).shovelSpeed();
			return 0.6F;
		}
		
		@Override
		public boolean isValidBreak(IItem holding, World world, int x, int y, int z, int orientation)
		{
			return true;
		}
		
		@Override
		public Object onBreakDrop(World world, int x, int y, int z, int orientation)
		{
			return world.newItemStack(itemID, 0, 1);
		}
		
		@Override
		public int updateBlockTick(World world, int x, int y, int z)
		{
			return 0;
		}
		
		@Override
		public void onBlockAdd(World world, int x, int y, int z, int fx, int fy, int fz)
		{
		}
		
		@Override
		public void onBlockRemove(World world, int x, int y, int z, int fx, int fy, int fz)
		{
		}
		
	//IItem
		
		@Override
		public int getItemID()
		{
			return itemID;
		}
		
		@Override
		public String getItemName()
		{
			return "Dirt";
		}
}
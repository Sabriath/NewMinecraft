package net.minecraft.mods;

import net.minecraft.src.*;

public class Dirt
	implements IMod, IBlock, IItem
{
	
	//IMod
		public Dirt block;
		
		public String getName()	{		return "Dirt";	}
		public int getVersion()	{		return 0;	}
	
		public String getDependencyName(int index)		{		return "";	}
		public boolean isDependencyValid(int version)	{		return true;	}
	
		public void onLoad()
		{
		}
	
		public void onMapNew(World world)
		{
			block = new Dirt();
			block.blockID = world.registerNewBlock(block);
			block.itemID = world.registerNewItem(block);
		}
	
		public void onMapLoad(World world, IFile file)
		{
			int i = file.readCFGInt("Block.Dirt", -1);
			block = new Dirt();
			if(i == -1)
				block.blockID = world.registerNewBlock(block);
			else
				block.blockID = world.registerBlock(block, i);
			i = file.readCFGInt("Item.Dirt", -1);
			if(i == -1)
				block.itemID = world.registerNewBlock(block);
			else
				block.itemID = world.registerItem(block, i);
		}
	
		public void onMapSave(World world, IFile file) 
		{
			file.writeCFGInt("Block.Dirt", block.blockID);
			file.writeCFGInt("Item.Dirt", block.itemID);
		}
		
		
	//IBlock
		public int blockID;
		
		public int getBlockID()
		{
			return blockID;
		}
		
		public String getBlockName()
		{
			return "Dirt";
		}
		
		public boolean isSolid(World world, int x, int y, int z)
		{
			return true;
		}
		
		public float breakSpeed(IItem holding, World world, int x, int y, int z, int orientation)
		{
			if(holding instanceof IShovel)
				return ((IShovel)holding).shovelSpeed();
			return 0.6F;
		}
		
		public boolean isValidBreak(IItem holding, World world, int x, int y, int z, int orientation)
		{
			if(holding instanceof IShovel)
				return true;
			return false;
		}
		
		public Object onBreakDrop(World world, int x, int y, int z, int orientation)
		{
			return world.newItemStack(itemID, 0, 1);
		}
		
		public void updateBlockTick(World world, int x, int y, int z)
		{
		}
		
		public void onBlockAdd(World world, int x, int y, int z, int fx, int fy, int fz)
		{
		}
		
		public void onBlockRemove(World world, int x, int y, int z, int fx, int fy, int fz)
		{
		}
		
		
	//IItem
		public int itemID;
		
		public int getItemID()
		{
			return itemID;
		}
		
		public String getItemName()
		{
			return "Dirt";
		}
}
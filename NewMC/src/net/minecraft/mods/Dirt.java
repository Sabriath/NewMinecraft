package net.minecraft.mods;

import net.minecraft.api.*;
import net.minecraft.src.*;

public class Dirt
	implements IMod, IBlock, IItem
{
	
	//IMod
		public Dirt block = null;
		
		@Override
		public void onLoad()
		{
		}
	
		@Override
		public void onMapNew(World world)
		{
			block = new Dirt();
			block.blockID = world.registerNewBlock(block);
			block.itemID = world.registerNewItem(block);
		}
	
		@Override
		public void onMapLoad(World world, IFile file)
		{
			int i = file.readCFGInt("Block.Dirt", -1);
			int j = file.readCFGInt("Item.Dirt", -1);
			block = new Dirt();
			block.blockID = i;
			block.itemID = j;
			if(i != -1)
				world.registerBlock(block, i);
			if(j != -1)
				world.registerItem(block, j);
		}
		
		@Override
		public void onMapLoadFinal(World world, IFile file)
		{
			if(block.blockID == -1)
				block.blockID = world.registerNewBlock(block);
			if(block.itemID == -1)
				block.itemID = world.registerNewItem(block);
		}
	
		@Override
		public void onMapSave(World world, IFile file) 
		{
			file.writeCFGInt("Block.Dirt", block.blockID);
			file.writeCFGInt("Item.Dirt", block.itemID);
		}
		
		
	//IBlock
		public int blockID;
		
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
		public void updateBlockTick(World world, int x, int y, int z)
		{
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
		public int itemID;
		
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
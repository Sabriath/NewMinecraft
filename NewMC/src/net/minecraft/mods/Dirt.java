package net.minecraft.mods;

import net.minecraft.api.*;
import net.minecraft.src.*;

public class Dirt
	implements IMod, IBlock, IPlaceableItem
{
	
	//IMod
		int blockID = -1;
		int itemID = -1;
		
		@Override
		public void onLoad(IWorld world)
		{
			blockID = world.registerBlock(this, "Dirt");
			itemID = world.registerItem(this, "Dirt");
		}
	
	//IBlock
		
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
		
	//IPlaceableItem
		public int PlacedBlockID(IWorld world)
		{
			return blockID;
		}
}
package net.minecraft.mods;

import net.minecraft.api.*;
import net.minecraft.src.*;

public class Dirt
	implements IMod, IBlock, IPlaceableItem
{
	
	//IMod
		int blockID = -1;
		int itemID = -1;
		int uvID = -1; 
		
		@Override
		public void onLoad(IWorld world)
		{
			blockID = world.registerBlock(this, "Dirt");
			itemID = world.registerItem(this, "Dirt");
			//uvID = world.registerUVRender("vanilla.img", 0, 0, 16, 16, false);
		}
	
	//IBlock
		
		@Override
		public boolean isSolid(IWorld world, int x, int y, int z)
		{
			return true;
		}
		
		@Override
		public float breakSpeed(IItem holding, World world, int x, int y, int z, float fx, float fy, float fz)
		{
			if(holding instanceof IShovel)
				return ((IShovel)holding).shovelSpeed();
			return 0.6F;
		}
		
		@Override
		public boolean isValidBreak(IItem holding, World world, int x, int y, int z, float fx, float fy, float fz)
		{
			return true;
		}
		
		@Override
		public void onBreakDrop(IWorld world, int x, int y, int z, float fx, float fy, float fz)
		{
			//world.newEntity(this, x + 0.5f, y + 0.5f, z + 0.5f, world.getRND() - 0.5f, world.getRND() - 0.5f, 0.2f);
		}
		
		@Override
		public int updateBlockTick(IWorld world, int x, int y, int z)
		{
			return 0;
		}
		
		@Override
		public void onBlockAdd(IWorld world, int x, int y, int z, float fx, float fy, float fz)
		{
		}
		
		@Override
		public void onBlockRemove(IWorld world, int x, int y, int z, float fx, float fy, float fz)
		{
		}

		@Override
		public void onRender(IWorld world, int x, int y, int z)
		{
			IBlock b;
			float xn = x;
			float yn = y;
			float zn = z;
			float xi = xn + 1.0f;
			float yi = yn + 1.0f;
			float zi = zn - 1.0f;
			
			if(((b = world.getBlockAt(x - 1, y, z)) == null) || (!b.isSolid(world, x - 1, y, z)))
				world.DrawSqr(xn, yi, zn, xn, yn, zn, xn, yn, zi, xn, yi, zi, uvID, world.getLightAt(x - 1, y, z));			
			if(((b = world.getBlockAt(x + 1, y, z)) == null) || (!b.isSolid(world, x + 1, y, z)))
				world.DrawSqr(xi, yn, zn, xi, yi, zn, xi, yi, zi, xi, yn, zi, uvID, world.getLightAt(x + 1, y, z));
			if(((b = world.getBlockAt(x, y - 1, z)) == null) || (!b.isSolid(world, x, y - 1, z)))
				world.DrawSqr(xn, yn, zn, xi, yn, zn, xi, yn, zi, xn, yn, zi, uvID, world.getLightAt(x, y - 1, z));
			if(((b = world.getBlockAt(x, y + 1, z)) == null) || (!b.isSolid(world, x, y + 1, z)))
				world.DrawSqr(xi, yi, zn, xn, yi, zn, xn, yi, zi, xi, yi, zi, uvID, world.getLightAt(x, y + 1, z));
			if(((b = world.getBlockAt(x, y, z - 1)) == null) || (!b.isSolid(world, x, y, z - 1)))
				world.DrawSqr(xi, yn, zi, xi, yi, zi, xn, yi, zi, xn, yn, zi, uvID, world.getLightAt(x, y, z - 1));
			if(((b = world.getBlockAt(x, y, z + 1)) == null) || (!b.isSolid(world, x, y, z + 1)))
				world.DrawSqr(xn, yn, zn, xn, yi, zn, xi, yi, zn, xi, yn, zn, uvID, world.getLightAt(x, y, z + 1));
		}
		
	//IPlaceableItem
		public int PlacedBlockID(IWorld world)
		{
			return blockID;
		}

}
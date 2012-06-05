package net.minecraft.mods;

import net.minecraft.api.*;

public class Dirt
	implements IMod, IBlock, IPlaceableItem
{
	//IMod
		int blockID = -1;
		int itemID = -1;
		ITexture blockTex;
		
		@Override
		public void onLoad(IWorld world)
		{
			blockID = world.registerBlock(this, "Dirt");
			itemID = world.registerItem(this, "Dirt");
			blockTex = world.registerTexture("vanilla.img", 0, 0, 16, 16, 0, false);
		}
		
		@Override
		public void onLoadLast(IWorld world, boolean triggered)
		{
		}

	//IBlock
		
		@Override
		public boolean isSolid(IWorld world, int x, int y, int z)
		{
			return true;
		}
		
		@Override
		public float breakSpeed(IItem holding, IWorld world, int x, int y, int z, double fx, double fy, double fz)
		{
			if(holding instanceof IShovel)
				return ((IShovel)holding).shovelSpeed();
			return 0.5F;
		}
		
		@Override
		public boolean isValidBreak(IItem holding, IWorld world, int x, int y, int z, double fx, double fy, double fz)
		{
			return true;
		}
		
		@Override
		public IItem[] onBreakDrop(IWorld world, int x, int y, int z, double fx, double fy, double fz)
		{
			IItem t[] = new IItem[1];
			t[0] = new Dirt();
			return t;
		}
		
		@Override
		public void updateBlockTick(IWorld world, int x, int y, int z)
		{
		}
		
		@Override
		public void updateBlockRandomTick(IWorld world, int x, int y, int z)
		{
		}
		
		@Override
		public void onBlockAdd(IWorld world, int x, int y, int z, double fx, double fy, double fz)
		{
		}
		
		@Override
		public void onBlockRemove(IWorld world, int x, int y, int z, double fx, double fy, double fz)
		{
		}

		@Override
		public boolean isBlocking(IWorld world, int x, int y, int z, int face)
		{
			return true;
		}
		
		@Override
		public void onRender(IWorld world, int x, int y, int z)
		{
			Texel p0 = new Texel(blockTex, x, y, z, 0.0, 0.0);
			Texel p1 = new Texel(blockTex, x, y, z+1, 0.0, 1.0);
			Texel p2 = new Texel(blockTex, x, y+1, z, 1.0, 0.0);
			Texel p3 = new Texel(blockTex, x, y+1, z+1);
			Texel p4 = new Texel(blockTex, x+1, y, z, 0.0, 1.0);
			Texel p5 = new Texel(blockTex, x+1, y, z+1, 0.0, 0.0);
			Texel p6 = new Texel(blockTex, x+1, y+1, z, 1.0, 1.0);
			Texel p7 = new Texel(blockTex, x+1, y+1, z+1, 1.0, 0.0);
			 
			if(!world.isBlockBlocking(x, y, z-1, 0))
			{
				world.addTriangle(p0, p2, p4);
				world.addTriangle(p4, p2, p6);
			}
			if(!world.isBlockBlocking(x,  y, z+1, 1))
			{
				world.addTriangle(p5, p7, p1);
				world.addTriangle(p1, p7, p3.UV(1.0, 1.0));
			}
			if(!world.isBlockBlocking(x, y-1, z, 3))
			{
				world.addTriangle(p5, p1.UV(1.0, 0.0), p4);
				world.addTriangle(p4, p1, p0.UV(1.0, 1.0));
			}
			if(!world.isBlockBlocking(x,  y+1, z, 2))
			{
				world.addTriangle(p3.UV(0.0, 0.0), p7, p2.UV(0.0, 1.0));
				world.addTriangle(p2, p7, p6);
			}
			if(!world.isBlockBlocking(x-1, y, z, 5))
			{
				world.addTriangle(p1.UV(0.0, 0.0), p3.UV(1.0, 0.0), p0.UV(0.0, 1.0));
				world.addTriangle(p0, p3, p2.UV(1.0, 1.0));
			}
			if(!world.isBlockBlocking(x+1,  y, z, 4))
			{
				world.addTriangle(p7.UV(0.0, 0.0), p5.UV(1.0, 0.0), p6.UV(0.0, 1.0));
				world.addTriangle(p6, p5, p4.UV(1.0, 1.0));
			}
		}
		
	//IItem
		@Override
		public IItem spawn()
		{
			return new Dirt();
		}
		
	//IPlaceableItem
		@Override
		public int PlacedBlockID(IWorld world)
		{
			return blockID;
		}
}
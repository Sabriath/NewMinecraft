package net.minecraft.mods;

import net.minecraft.api.*;
import java.util.*;

public class Grass
	implements IMod, IBlock, IPlaceableItem
{
	//IMod
		int blockID = -1;
		int dirtID = -1;
		int itemID = -1;
		ITexture blockTexTop;
		ITexture blockTexSides;
		ITexture blockTexBottom;
		
		@Override
		public void onLoad(IWorld world)
		{
			blockID = world.registerBlock(this, "Grass");
			itemID = world.registerItem(this, "Grass");
			blockTexTop = world.registerTexture("vanilla.img", 0, 0, 16, 16, 0, false);
			blockTexSides = world.registerTexture("vanilla.img", 0, 0, 16, 16, 0, false);
			blockTexBottom = world.registerTexture("vanilla.img", 0, 0, 16, 16, 0, false);
		}
		
		@Override
		public void onLoadLast(IWorld world)
		{
			dirtID = world.getBlockID("Dirt");
			if(dirtID == -1)
				dirtID = blockID;
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
			return 0.6F;
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
			t[0] = world.newItem(dirtID);
			return t;
		}
	
		@Override
		public void updateBlockTick(IWorld world, int x, int y, int z)
		{
			if(dirtID == -1)
				return;
			
			//get light from directly above this block and die if not enough
			int light = world.getLight(0.5 + (double)x, 0.5 + (double)y, 1.05 + (double)z);
			if((light & 65280) < 8192)
			{
				world.setBlockIDAt(dirtID, x, y, z, 0.5 + (double)x, 0.5 + (double)y, 1.05 + (double)z);
				return;
			}
			
			boolean needsUpdate = false;
			Orientation o = new Orientation(x, y, z-1);
			List<Orientation> t = o.Iters(Orientation.MaskNorth | Orientation.MaskWest | Orientation.MaskEast | Orientation.MaskSouth);
			int r = (int)(4 * world.rnd());
			for(int i = 0; i < 4; i++)
			{
				o = t.get(r);
				r = (r + 1) % 4;
				if(world.getBlockIDAt(o) == dirtID)
				{
					if((world.getLight(0.5 + (double)o.x, 0.5 + (double)o.y, 1.05 + (double)o.z) & 65280) > 24576)
					{
						world.setBlockIDAt(blockID, o.x, o.y, o.z, 0.5 + (double)o.x, 0.5 + (double)o.y, 1.05 + (double)o.z);
						break;
					}
					needsUpdate = true;
				}
			}
			r = (int)(4 * world.rnd());
			for(int i = 0; i < 4; i++)
			{
				o = t.get(r);
				o.z++;
				r = (r + 1) % 4;
				if(world.getBlockIDAt(o) == dirtID)
				{
					if((world.getLight(0.5 + (double)o.x, 0.5 + (double)o.y, 1.05 + (double)o.z) & 65280) > 24576)
					{
						world.setBlockIDAt(blockID, o.x, o.y, o.z, 0.5 + (double)o.x, 0.5 + (double)o.y, 1.05 + (double)o.z);
						break;
					}
					needsUpdate = true;
				}
			}
			for(int i = 0; i < 4; i++)
			{
				o = t.get(r);
				o.z+=2;
				r = (r + 1) % 4;
				if(world.getBlockIDAt(o) == dirtID)
				{
					if((world.getLight(0.5 + (double)o.x, 0.5 + (double)o.y, 1.05 + (double)o.z) & 65280) > 24576)
					{
						world.setBlockIDAt(blockID, o.x, o.y, o.z, 0.5 + (double)o.x, 0.5 + (double)o.y, 1.05 + (double)o.z);
						break;
					}
					needsUpdate = true;
				}
			}
			if(needsUpdate)
				world.updateRandomTick(x, y, z, 120, 2400);
		}
	
		@Override
		public void onBlockAdd(IWorld world, int x, int y, int z, double fx, double fy, double fz)
		{
			world.updateRandomTick(x, y, z, 120, 2400);
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
			Texel p0 = new Texel(blockTexBottom, x, y, z, 0.0, 0.0);
			Texel p1 = new Texel(blockTexTop, x, y, z+1, 0.0, 1.0);
			Texel p2 = new Texel(blockTexBottom, x, y+1, z, 1.0, 0.0);
			Texel p3 = new Texel(blockTexTop, x, y+1, z+1);
			Texel p4 = new Texel(blockTexBottom, x+1, y, z, 0.0, 1.0);
			Texel p5 = new Texel(blockTexTop, x+1, y, z+1, 0.0, 0.0);
			Texel p6 = new Texel(blockTexBottom, x+1, y+1, z, 1.0, 1.0);
			Texel p7 = new Texel(blockTexTop, x+1, y+1, z+1, 1.0, 0.0);
			 
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
			p0.tex = blockTexSides;
			p1.tex = blockTexSides;
			p2.tex = blockTexSides;
			p3.tex = blockTexSides;
			p4.tex = blockTexSides;
			p5.tex = blockTexSides;
			p6.tex = blockTexSides;
			p7.tex = blockTexSides;
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
			return new Grass();
		}
		
	//IPlaceableItem
		@Override
		public int PlacedBlockID(IWorld world)
		{
			return blockID;
		}
	
}

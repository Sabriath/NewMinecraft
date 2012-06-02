package net.minecraft.api;

import java.util.*;

public class Orientation
{
	public static int Up = 0;
	public static int Down = 1;
	public static int North = 2;
	public static int South = 3;
	public static int West = 4;
	public static int East = 5;
	
	public static int MaskUp = 1;
	public static int MaskDown = 2;
	public static int MaskNorth = 4;
	public static int MaskSouth = 8;
	public static int MaskWest = 16;
	public static int MaskEast = 32;
	
	public int x;
	public int y;
	public int z;
	
	public Orientation(int tx, int ty, int tz)
	{
		x = tx;
		y = ty;
		z = tz;
	}
	
	public Orientation Copy()
	{
		return new Orientation(x, y, z);
	}
	
	public Orientation Iter(int face)
	{
		switch(face)
		{
			case 0: z++; break;
			case 1: z--; break;
			case 2: y--; break;
			case 3: y++; break;
			case 4: x--; break;
			case 5: x++; break;
		}
		return this;
	}
	
	public List<Orientation> Iters(int bitmask)
	{
		List<Orientation> t = new ArrayList();
		
		if((bitmask & MaskUp) != 0)
			t.add(new Orientation(x,y,z+1));
		if((bitmask & MaskDown) != 0)
			t.add(new Orientation(x,y,z-1));
		if((bitmask & MaskNorth) != 0)
			t.add(new Orientation(x,y-1,z));
		if((bitmask & MaskSouth) != 0)
			t.add(new Orientation(x,y+1,z));
		if((bitmask & MaskWest) != 0)
			t.add(new Orientation(x-1,y,z));
		if((bitmask & MaskEast) != 0)
			t.add(new Orientation(x+1,y,z));
		return t;
	}
}

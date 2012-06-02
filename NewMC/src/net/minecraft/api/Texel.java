package net.minecraft.api;

public class Texel
{
	public ITexture tex;
	public double x, y, z, u, v;
	
	public Texel(ITexture t, double tx, double ty, double tz)
	{
		tex = t;
		x = tx;
		y = ty;
		z = tz;
	}
	
	public Texel(ITexture t, double tx, double ty, double tz, double tu, double tv)
	{
		tex = t;
		x = tx;
		y = ty;
		z = tz;
		u = tu;
		v = tv;
	}
	
	public Texel UV(double tu, double tv)
	{
		u = tu;
		v = tv;
		return this;
	}
}

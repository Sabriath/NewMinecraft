package net.minecraft.api;

public interface ITag
{
	public abstract ITag Find(String key);
	public abstract ITag Add(String key);
	public abstract ITag Set(String val);
	public abstract String Get();
	
	public abstract void SetM(ITag m);
	public abstract void SetF(ITag f);
	public abstract void SetL(ITag l);
	public abstract void SetP(ITag p);
	public abstract void SetN(ITag n);
	public abstract void SetK(String k);
	
	public abstract ITag GetM();
	public abstract ITag GetF();
	public abstract ITag GetL();
	public abstract ITag GetP();
	public abstract ITag GetN();
	public abstract String GetK();
}

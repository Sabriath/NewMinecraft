package net.minecraft.api;

public interface ITag
{
	//These are the exposed and used mainly for modding
	public abstract ITag Find(String key);
	public abstract ITag FindOrAdd(String key);
	public abstract ITag SetStr(String val);
	public abstract ITag SetInt(int val);
	public abstract String GetStr();
	public abstract int GetInt();
	
	//These are required for cross-talk with setting links
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

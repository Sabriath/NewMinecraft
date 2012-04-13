package net.minecraft.api;

public interface IFile
{
	//ctor $IFile(dirname)
	
	public abstract String readCFGString(String key, String deflt);
	public abstract int    readCFGInt(String key, int deflt);
	public abstract void   writeCFGString(String key, String dat);
	public abstract void   writeCFGInt(String key, int dat);
}
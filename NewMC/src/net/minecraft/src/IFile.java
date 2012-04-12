package net.minecraft.src;

public interface IFile
{
	public abstract String readCFGString(String key, String deflt);
	public abstract int    readCFGInt(String key, int deflt);
	public abstract void   writeCFGString(String key, String dat);
	public abstract void   writeCFGInt(String key, int dat);
	
}

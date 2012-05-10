package net.minecraft.api;

public interface IFile
{
	public abstract boolean readCFGs(String fname, ITag host);
	public abstract boolean writeCFGs(String fname, ITag host);
}
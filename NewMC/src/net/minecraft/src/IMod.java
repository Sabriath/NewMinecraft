package net.minecraft.src;


public interface IMod
{
	public abstract String getName();
	public abstract int getVersion();
	
	public abstract String getDependencyName(int index);
	public abstract boolean isDependencyValid(int version);
	
	public abstract void onLoad();
	public abstract void onMapNew(World world);
	public abstract void onMapLoad(World world, IFile file);
	public abstract void onMapSave(World world, IFile file);
	
}

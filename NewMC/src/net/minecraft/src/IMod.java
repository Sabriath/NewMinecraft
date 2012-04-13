package net.minecraft.src;

public interface IMod
{
	public abstract void onLoad();
	public abstract void onMapNew(World world);
	public abstract void onMapLoad(World world, IFile file);
	public abstract void onMapLoadFinal(World world, IFile file);
	public abstract void onMapSave(World world, IFile file);
}
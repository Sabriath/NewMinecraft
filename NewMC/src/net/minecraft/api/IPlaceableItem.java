package net.minecraft.api;

public interface IPlaceableItem
	extends IItem
{
	public abstract int PlacedBlockID(IWorld world);
}

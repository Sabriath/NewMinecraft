package net.minecraft.src;

public class ItemStack
{
	public int itemID;
	public int itemDmg;
	public int itemCount;
	
	ItemStack(int id, int dmg, int count)
	{
		itemID = id;
		itemDmg = dmg;
		itemCount = count;
	}
}

package net.minecraft.api;

public interface IItem 
{
	//returns the ID of item in this instance, should match registration
	public abstract int getItemID();
	
	//returns the common name
	public abstract String getItemName();
}

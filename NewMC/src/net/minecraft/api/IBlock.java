package net.minecraft.api;

import net.minecraft.src.*;

public interface IBlock
{
	//returns the ID of block in this instance, should match registration
	public abstract int getBlockID();
	
	//returns the common name
	public abstract String getBlockName();
	
	//Can this block be stood on and block movement?
	public abstract boolean isSolid(World world, int x, int y, int z);
	
	//A value usually between 0.0F and 1.0F that determines speed the block will break at
	public abstract float breakSpeed(IItem holding, World world, int x, int y, int z, int orientation);
	
	/*This is run when the block is broken to check whether it drops an item,
	 * returning 'true' will cause 'onBreakDrop' to be run
	 */
	public abstract boolean isValidBreak(IItem holding, World world, int x, int y, int z, int orientation);
	
	//Only run if the previous function returns 'true'
	public abstract Object onBreakDrop(World world, int x, int y, int z, int orientation);
	
	/*When this block requires an update, this function is called
	 * The returned value determines how long (if any) another update occurs on the same block in ticks
	 * If '0' returns, the block is not re-added for update
	 * */
	public abstract int updateBlockTick(World world, int x, int y, int z);
	
	/*Called directly after the block has been added to the world but before the world
	 *  is updated for view/network.  'fx/fy/fz' is the position character was in when block was placed. 
	 */
	public abstract void onBlockAdd(World world, int x, int y, int z, int fx, int fy, int fz);
	
	/*Called directly after the block has been removed from the world but before the world
	 * is updated for view/network.  'fx/fy/fz' is the position the character was in when block was removed.
	 */
	public abstract void onBlockRemove(World world, int x, int y, int z, int fx, int fy, int fz);
}

package net.minecraft.api;

import net.minecraft.src.*;

public interface IBlock
{
	//Can this block be stood on and block movement?
	public abstract boolean isSolid(IWorld world, int x, int y, int z);
	
	//A value usually between 0.0F and 1.0F that determines speed the block will break at
	public abstract float breakSpeed(IItem holding, World world, int x, int y, int z, float fx, float fy, float fz);
	
	/*This is run when the block is broken to check whether it drops an item,
	 * returning 'true' will cause 'onBreakDrop' to be run
	 */
	public abstract boolean isValidBreak(IItem holding, World world, int x, int y, int z, float fx, float fy, float fz);
	
	//Only run if the previous function returns 'true'
	public abstract void onBreakDrop(IWorld world, int x, int y, int z, float fx, float fy, float fz);
	
	/*When this block requires an update, this function is called
	 * The returned value determines how long (if any) another update occurs on the same block in ticks
	 * If '0' returns, the block is not re-added for update
	 * */
	public abstract int updateBlockTick(IWorld world, int x, int y, int z);
	
	/*Called directly after the block has been added to the world but before the world
	 *  is updated for view/network.  'fx/fy/fz' is the position character was in when block was placed. 
	 */
	public abstract void onBlockAdd(IWorld world, int x, int y, int z, float fx, float fy, float fz);
	
	/*Called directly after the block has been removed from the world but before the world
	 * is updated for view/network.  'fx/fy/fz' is the position the character was in when block was removed.
	 */
	public abstract void onBlockRemove(IWorld world, int x, int y, int z, float fx, float fy, float fz);
	
	/*Called when the block needs to be rendered using world.Draw* functionality
	 */
	public abstract void onRender(IWorld world, int x, int y, int z);
}

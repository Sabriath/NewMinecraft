package net.minecraft.api;

import net.minecraft.src.*;

public interface IBlock
{
	//Can this block be stood on and block movement?
	public abstract boolean isSolid(IWorld world, int x, int y, int z);
	
	//A value usually between 0.0F and 1.0F that determines speed the block will break at
	public abstract float breakSpeed(IItem holding, IWorld world, int x, int y, int z, double fx, double fy, double fz);
	
	/*This is run when the block is broken to check whether it drops an item,
	 * returning 'true' will cause 'onBreakDrop' to be run	 */
	public abstract boolean isValidBreak(IItem holding, IWorld world, int x, int y, int z, double fx, double fy, double fz);
	
	//Only run if the previous function returns 'true'
	public abstract IItem[] onBreakDrop(IWorld world, int x, int y, int z, double fx, double fy, double fz);
	
	//When this block was given an update trigger 
	public abstract void updateBlockTick(IWorld world, int x, int y, int z);
	
	//When this block has been randomly selected and is registered for random ticks
	public abstract void updateBlockRandomTick(IWorld world, int x, int y, int z);	
	
	/*Called directly after the block has been added to the world but before the world
	 *  is updated for view/network.  'fx/fy/fz' is the position character was in when block was placed. */
	public abstract void onBlockAdd(IWorld world, int x, int y, int z, double fx, double fy, double fz);
	
	/*Called directly after the block has been removed from the world but before the world
	 * is updated for view/network.  'fx/fy/fz' is the position the character was in when block was removed.	 */
	public abstract void onBlockRemove(IWorld world, int x, int y, int z, double fx, double fy, double fz);
	
	/*Return whether or not this blocks the view of the block in the next section
	 * according to orientation 'face'	 */
	public abstract boolean isBlocking(IWorld world, int x, int y, int z, int face);
	
	//Called when the block needs to be rendered using world.Draw* functionality
	public abstract void onRender(IWorld world, int x, int y, int z);
}

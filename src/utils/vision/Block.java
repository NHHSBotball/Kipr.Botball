package utils.vision;

import utils.pathfinding.Location;

/* ------- Block Data ------- */
/**
 * Represents the various block types.
 */
public enum Block {
	RED (0),
	YELLOW (2),
	BLUE (3);
	
	private int channel;
	
	Block (int channel) {
		this.channel = channel;
	}
	
	/**
	 * Gets the channel number for the CBC camera's color tracking.
	 * 
	 * @return channel number
	 */
	public int getChannel() {
		return channel;
	}
	
	private static Block[] blockOrder = new Block[3];
	
	/**
	 * Sets the color of a specified block.
	 * 
	 * Block 0 is the block near the fence, 1 is the corner block, 2 is the block near the board edge.
	 * 
	 * @param index block index
	 * @param b block color to use
	 */
	public static void setBlock(int index, Block b) {
		if (index < 0 || index > 2)
			throw new IllegalArgumentException("Block index cannot be greater than 2.");
		if (blockOrder[index] != null)
			throw new RuntimeException("The first block was already set.");
		blockOrder[index] = b;
	}
	
	/**
	 * Sets the color of a block, specified by its location.
	 * 
	 * Block 0 is the block near the fence, 1 is the corner block, 2 is the block near the board edge.
	 * 
	 * @param location block location
	 * @param b block color to use
	 */
	public static void setBlock(Location location, Block b) {
		int index;
		for(index = 0; index < Location.getBlockLocations().length; index++){
			if(location == Location.getBlockLocations()[index]){
				Block.setBlock(index, b);
				break;
			}
		}
	}
	/**
	 * Gets the color of a specified block.
	 * 
	 * Block 0 is the block near the fence, 1 is the corner block, 2 is the block near the board edge.
	 * 
	 * @param index block index
	 * @return block color
	 */
	public static Block getBlock(int index) {
		if (index < 0 || index > 2)
			throw new IllegalArgumentException("Block index cannot be greater than 2.");
		return blockOrder[index];
	}
	
	/**
	 * Gets the blockOrder array.
	 * 
	 * Block 0 is the block near the fence, 1 is the corner block, 2 is the block near the board edge.
	 * 
	 * @param index block index
	 * @return block color
	 */
	public static Block[] getBlocks() {
		return blockOrder;
	}
	
	/**
	 * Sets the color of a block, specified by its location.
	 * 
	 * Block 0 is the block near the fence, 1 is the corner block, 2 is the block near the board edge.
	 * 
	 * @param location block location
	 * @param b block color to use
	 */
	public static Block getBlock(Location location) {
		Block block = null;
		int index;
		for(index = 0; index < Location.getBlockLocations().length; index++){
			if(location == Location.getBlockLocations()[index]){
				block = Block.getBlock(index);
				break;
			}
		}
		if(block == null) throw new IllegalArgumentException("Location not found or Block not found at location.");
		return block;
	}
	
	/**
	 * Gets the color of the block currently in the frame of the camera.
	 * 
	 * @return block color
	 */
	public static Block seeBlock(){
		Block block;
		
		Blob red = new Blob(Block.RED.getChannel(), 0);
		Blob yellow = new Blob(Block.YELLOW.getChannel(), 0);
		Blob blue = new Blob(Block.BLUE.getChannel(), 0);
		                                                                    block = Block.RED;
		if(yellow.score() > red.score() && yellow.score() > blue  .score()) block = Block.YELLOW;
		if(blue  .score() > red.score() && blue  .score() > yellow.score()) block = Block.BLUE;
		
		return block;
	}
}

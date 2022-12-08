package com.mygdx.maze.maze;

/**
 * The TileType enum represents the different types of tiles
 * which mazes can be made out of.
 */
public enum TileType
{
	SQUARE;
	
	/**
	 * Gets the number of tile sides corresponding to each enum
	 * @return Number of tile sides.
	 */
	public int getTypeSideNumber()
	{
		switch(this)
		{
			case SQUARE: return 4;
			default: return 0;
		}
	}
}

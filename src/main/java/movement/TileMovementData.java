package movement;

import graphics.Tile;

/**
 * This class is a model for movement data concerning a single {@link Tile}.
 * 
 * @author mareike
 *
 */
public class TileMovementData
{
	private final int countMovementPossibilities;
	private final Tile tile;

	/**
	 * Creates a new {@link TileMovementData} instance.
	 * 
	 * @param countMovementPossibilities
	 *            the number of movement possibilities from this tile
	 * @param tile
	 *            the tile in question
	 */
	public TileMovementData(int countMovementPossibilities, Tile tile)
	{
		this.countMovementPossibilities = countMovementPossibilities;
		this.tile = tile;
	}

	/**
	 * Returns the number of movement possibilities of the tile.
	 * 
	 * @return the number of movement possibilities of the tile.
	 */
	public int getCountMovementPossibilities()
	{
		return countMovementPossibilities;
	}

	/**
	 * Returns the {@link Tile}.
	 * 
	 * @return the tile.
	 */
	public Tile getTile()
	{
		return tile;
	}

}

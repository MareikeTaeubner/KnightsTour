package movement;

import java.util.List;

import graphics.Tile;

/**
 * This class is a model that represents an entry in the movement history of a
 * chess piece.
 * 
 * @author mareike
 *
 */
public class HistoryEntry
{
	private final Tile tile;
	private final List<Tile> possibleDestinationTiles;

	/**
	 * Creates a new {@link HistoryEntry} instance.
	 * 
	 * @param tile
	 *            the tile in question
	 * @param possibleDestinationTiles
	 *            the possible destination tiles reached from this tile
	 */
	public HistoryEntry(Tile tile, List<Tile> possibleDestinationTiles)
	{
		this.tile = tile;
		this.possibleDestinationTiles = possibleDestinationTiles;
	}

	/**
	 * Returns the tile.
	 * 
	 * @return the tile.
	 */
	public Tile getTile()
	{
		return tile;
	}

	/**
	 * Returns the list of possible destination tiles reached from this tile.
	 * 
	 * @return the list of possible destination tiles reached from this tile.
	 */
	public List<Tile> getPossibleDestinationTiles()
	{
		return possibleDestinationTiles;
	}

	/**
	 * Removes the given tile from the list of possible destination tiles.
	 * 
	 * @param tile
	 *            the tile that shall be removed
	 */
	public void removeTileFromPossibleDestinationTiles(Tile tile)
	{
		possibleDestinationTiles.remove(tile);
	}

}

package movement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import graphics.Chessboard;
import graphics.Piece;
import graphics.Tile;

/**
 * This class provides functionality to calculate the movement of a chess
 * {@link Piece}.
 * 
 * @author mareike
 *
 */
public class MovementCalculator
{
	/**
	 * Returns the <b>optimal</b> destination tile for the tile with the given
	 * coordinates. An optimal destination tile is a tile with the least number
	 * of destination tiles from itself. </br>
	 * If more than one tile with the same number of destination tiles is found-
	 * the first found tile is returned</br>
	 * Returns <code>null</code> if <b>no</b> destination tiles was found for
	 * the given coordinates.
	 * <p>
	 * See <b> Warnsdorff's rule </b>
	 * 
	 * @param possibleDestinationTiles
	 *            the destination tiles that can be reached from the current
	 *            tile with the given movement vectors
	 * @param board
	 *            the chess board
	 * @param movementVectors
	 *            the movement vectors of the chess piece
	 * @return the <b>optimal</b> destination tile for the tile with the given
	 *         coordinates or <code>null</code> if <b>no</b> destination tile
	 *         was found.
	 */
	public Tile calculateOptimalDestinationTile(
			List<Tile> possibleDestinationTiles, Chessboard board,
			Collection<MovementVector> movementVectors)
	{
		List<TileMovementData> possibleTiles = new ArrayList<>();

		for (Tile tile : possibleDestinationTiles)
		{
			int x = tile.getX();
			int y = tile.getY();
			List<Tile> destinationTiles = fetchPossibleDestinationTiles(x, y,
					board, movementVectors);
			int count = destinationTiles.size();
			possibleTiles.add(new TileMovementData(count, tile));

		}

		Tile bestDestinationTile = sortOutBestDestinationTile(possibleTiles);

		return bestDestinationTile;

	}

	/**
	 * Returns the possible tiles that can be reached from the tile with the
	 * given x and y coordinates.
	 * 
	 * @param x
	 *            the x coordinate of the tile in question
	 * @param y
	 *            the y coordinate of the tile in question
	 * @param board
	 *            the {@link Chessboard}
	 * @param movementVectors
	 *            the movement vectors of the chess piece
	 * @return the possible tiles that can be reached from the tile with the
	 *         given x and y coordinates. Returns an empty {@link List} if none
	 *         were found.
	 */
	public List<Tile> fetchPossibleDestinationTiles(int x, int y,
			Chessboard board, Collection<MovementVector> movementVectors)
	{
		List<Tile> result = new ArrayList<>();

		for (MovementVector movementVector : movementVectors)
		{
			int dX = movementVector.getDX();
			int dY = movementVector.getDY();
			int destX = x + dX;
			int destY = y + dY;

			Tile tile = board.fetchTile(destX, destY);

			if (tile == null)
			{
				continue;
			} else if (tile.isVisited())
			{
				continue;
			} else
			{
				result.add(tile);
			}
		}

		return result;
	}

	private Tile sortOutBestDestinationTile(
			List<TileMovementData> possibleTiles)
	{
		// count that will be never reached
		int smallestDestinationCount = 99;
		TileMovementData data = null;

		for (TileMovementData tileMovementData : possibleTiles)
		{
			int count = tileMovementData.getCountMovementPossibilities();
			if (count < smallestDestinationCount)
			{
				smallestDestinationCount = count;
				data = tileMovementData;
			}
		}

		Tile result = data.getTile();

		return result;
	}

}

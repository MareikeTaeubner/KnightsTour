package movement;

import java.util.Collection;
import java.util.Deque;
import java.util.List;

import graphics.Chessboard;
import graphics.Piece;
import graphics.Tile;

public class PieceMover
{
	private final MovementCalculator movementCalculator;
	private final Deque<HistoryEntry> movementHistory;

	/**
	 * Creates a new {@link PieceMover} instance.
	 * 
	 * @param movementCalculator
	 *            {@link MovementCalculator} - calculates the piece movement on
	 *            the board
	 * @param movementHistory
	 *            shall contain the movement history entries - <b>must</b>
	 *            contain the {@link HistoryEntry} with the starting tile
	 * 
	 */
	public PieceMover(MovementCalculator movementCalculator,
			Deque<HistoryEntry> movementHistory)
	{
		this.movementCalculator = movementCalculator;
		this.movementHistory = movementHistory;
	}

	public void movePieceToNextTile(Chessboard board, Piece piece)
	{
		HistoryEntry lastEntry = movementHistory.peekFirst();
		List<Tile> destinationTiles = lastEntry.getPossibleDestinationTiles();
		if (destinationTiles.isEmpty())
		{
			backtrack(lastEntry, piece);
		} else
		{
			Collection<MovementVector> movementVectors = piece
					.getMovementVectors();
			Tile optimalDestinationTile = movementCalculator
					.calculateOptimalDestinationTile(destinationTiles, board,
							movementVectors);

			Tile from = lastEntry.getTile();
			movePiece(from, optimalDestinationTile, piece);
			addHistoryEntry(optimalDestinationTile, board, movementVectors);
		}
	}

	private void addHistoryEntry(Tile newTile, Chessboard board,
			Collection<MovementVector> movementVectors)
	{
		int x = newTile.getX();
		int y = newTile.getY();

		List<Tile> possibleDestinationTiles = movementCalculator
				.fetchPossibleDestinationTiles(x, y, board, movementVectors);
		HistoryEntry newEntry = new HistoryEntry(newTile,
				possibleDestinationTiles);

		movementHistory.push(newEntry);
	}

	private void movePiece(Tile from, Tile to, Piece piece)
	{
		from.setPiece(null);
		from.setVisited(true);

		to.setPiece(piece);
	}

	private void backtrack(HistoryEntry lastEntry, Piece piece)
	{
		movementHistory.removeFirst();
		HistoryEntry entry = movementHistory.peekFirst();
		Tile tile = lastEntry.getTile();
		entry.getPossibleDestinationTiles().remove(tile);
		entry.getTile().setPiece(piece);
		entry.getTile().setVisited(false);
		tile.setVisited(false);
		tile.setPiece(null);
	}
}

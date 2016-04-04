package graphics;

import java.awt.Graphics;
import java.util.Collection;

import movement.MovementVector;

/**
 * This interface represents a chess piece.
 * 
 * @author mareike
 *
 */
public interface Piece
{
	/**
	 * Draws this chess piece on the given position.
	 * 
	 * @param g
	 *            the graphics context
	 * @param x
	 *            the X coordinate
	 * @param y
	 *            the Y coordinate
	 * @param tileSize
	 *            the size of the tile the piece is drawn on
	 */
	void draw(Graphics g, int x, int y, int tileSize);

	/**
	 * Returns the {@link MovementVector}s of this piece.
	 * 
	 * @return the {@link MovementVector}s of this piece.
	 */
	Collection<MovementVector> getMovementVectors();
}

package graphics;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class represents a tile of a chess board.
 * 
 * @author mareike
 *
 */
public class Tile
{
	private TileColor tileColor;
	private Piece piece;
	private boolean visited;
	private final int x;
	private final int y;

	/**
	 * Creates a new {@link Tile} instance.
	 * 
	 * @param tileColor
	 *            the color of the tile
	 * @param x
	 *            the x coordinate of this tile
	 * @param y
	 *            the y coordinate of this tile
	 */
	public Tile(TileColor tileColor, int x, int y)
	{
		this.tileColor = tileColor;
		this.x = x;
		this.y = y;

	}

	/**
	 * Draws this tile on its position using its default color.
	 * 
	 * @param g
	 *            the graphic context
	 */
	public void paint(Graphics g, int size, int x, int y)
	{
		Color color = tileColor.getColor();
		if (visited)
		{
			color = tileColor.getModifyColor();
		}

		g.setColor(color);
		g.fillRect(x, y, size, size);

		if (piece != null)
		{
			piece.draw(g, x, y, size);
		}
	}

	/**
	 * Returns the piece on this tile.
	 * 
	 * @return the piece on this tile.
	 */
	public Piece getPiece()
	{
		return piece;
	}

	/**
	 * Sets the chess piece on this tile
	 * 
	 * @param piece
	 *            the chess piece on this tile.
	 */
	public void setPiece(Piece piece)
	{
		this.piece = piece;
	}

	/**
	 * Returns <code>true</code> if this tile has been visited. Returns
	 * <code>false</code> otherwise.
	 * 
	 * @return <code>true</code> if this tile has been visited. Returns
	 *         <code>false</code> otherwise.
	 */
	public boolean isVisited()
	{
		return visited;
	}

	/**
	 * Sets the visited flag.
	 * 
	 * @param visited
	 *            the visited flag. Is <code>true</code> if this tile has been
	 *            visited.
	 */
	public void setVisited(boolean visited)
	{
		this.visited = visited;
	}

	/**
	 * Returns the x coordinate of this tile.
	 * 
	 * @return the x coordinate of this tile.
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * Returns the y coordinate of this tile.
	 * 
	 * @return the y coordinate of this tile.
	 */
	public int getY()
	{
		return y;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}

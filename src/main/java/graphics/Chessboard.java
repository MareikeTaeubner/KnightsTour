package graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

/**
 * This class represents a chess board consisting of {@link Tile}s.
 * 
 * @author mareike
 *
 */
public class Chessboard extends Component
{
	private static final long serialVersionUID = 1L;

	private final TileColor lightColor;
	private final TileColor darkColor;
	private final Tile[][] board;
	private final int boardSize;

	/**
	 * Creates a new {@link Chessboard} instance.
	 * 
	 * @param darkColor
	 *            the dark {@link Tile} color
	 * @param lightColor
	 *            the light {@link Tile} color
	 * @param boardSize
	 *            the number of tiles the board shall have in each direction
	 */
	public Chessboard(TileColor darkColor, TileColor lightColor, int boardSize)
	{
		this.boardSize = boardSize;
		this.lightColor = lightColor;
		this.darkColor = darkColor;

		this.board = new Tile[boardSize][boardSize];

		boolean beginWithDark = true;

		for (int y = 0; y < boardSize; y++)
		{
			createLine(beginWithDark, y);

			if (beginWithDark)
			{
				beginWithDark = false;
			} else
			{
				beginWithDark = true;
			}
		}
	}

	@Override
	public void paint(Graphics g)
	{
		paintBoard(g);
	}

	@Override
	public void update(Graphics g)
	{
		paintBoard(g);
	}

	/**
	 * Returns the chess board.
	 * 
	 * @return the chess board.
	 */
	public Tile[][] getBoard()
	{
		return board;
	}

	/**
	 * Returns the {@link Tile} with the given x and y coordinates or
	 * <code>null</code> if there is no {@link Tile} with those coordinates.
	 * 
	 * @param x
	 *            the x coordinate of the {@link Tile}
	 * @param y
	 *            the y coordinate of the {@link Tile}
	 * @return he {@link Tile} with the given x and y coordinates or
	 *         <code>null</code> if there is no {@link Tile} with those
	 *         coordinates.
	 */
	public Tile fetchTile(int x, int y)
	{

		if (x < 0 || x >= boardSize)
		{
			return null;
		}

		if (y < 0 || y >= boardSize)
		{
			return null;
		}

		Tile tile = board[y][x];

		return tile;
	}

	/**
	 * Returns the number of tiles the chess board contains.
	 * 
	 * @return the number of tiles the chess board contains.
	 */
	public int grabTileNumber()
	{
		int tileNumber = boardSize * boardSize;
		return tileNumber;
	}

	private void createLine(boolean beginWithDark, int y)
	{
		boolean isDark = beginWithDark;
		for (int x = 0; x < boardSize; x++)
		{
			Color color;
			Color visitedColor;
			if (isDark)
			{
				color = darkColor.getColor();
				visitedColor = darkColor.getModifyColor();
				isDark = false;
			} else
			{
				color = lightColor.getColor();
				visitedColor = lightColor.getModifyColor();
				isDark = true;
			}

			TileColor tileColor = new TileColor(color, visitedColor);
			board[y][x] = new Tile(tileColor, x, y);
		}
	}

	private void paintBoard(Graphics g)
	{
		int width = super.getWidth();
		int height = super.getHeight();

		int size = Math.min(width, height) / boardSize;

		for (int y = 0; y < boardSize; y++)
		{
			for (int x = 0; x < boardSize; x++)
			{
				board[y][x].paint(g, size, x * size, y * size);
			}
		}
	}

}

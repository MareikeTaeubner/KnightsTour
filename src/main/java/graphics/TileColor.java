package graphics;

import java.awt.Color;

/**
 * This class represents the possible {@link Color}s of a {@link Tile}.
 * 
 * @author mareike
 *
 */
public class TileColor
{
	private final Color color;
	private final Color modifyColor;

	/**
	 * Creates a new {@link TileColor} instance.
	 * 
	 * @param color
	 *            the color of the {@link Tile}
	 * @param modifyColor
	 *            the color of the {@link Tile} after modification
	 */
	public TileColor(Color color, Color modifyColor)
	{
		super();
		this.color = color;
		this.modifyColor = modifyColor;
	}

	/**
	 * Returns the color of the tile.
	 * 
	 * @return the color.
	 */
	public Color getColor()
	{
		return color;
	}

	/**
	 * Returns the modification color of the tile.
	 * 
	 * @return the modification color.
	 */
	public Color getModifyColor()
	{
		return modifyColor;
	}

}

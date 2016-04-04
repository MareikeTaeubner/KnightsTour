package movement;

/**
 * This class is a model that describes one movement vector of a chess piece.
 * 
 * @author mareike
 *
 */
public class MovementVector
{
	private final int dY;
	private final int dX;

	/**
	 * Creates a new {@link MovementVector} instance.
	 * 
	 * @param dY
	 *            delta Y, the difference to the y coordinate
	 * @param dX
	 *            delta X, the difference to the x coordinate
	 */
	public MovementVector(int dY, int dX)
	{
		this.dX = dX;
		this.dY = dY;
	}

	/**
	 * Returns the difference to the y coordinate - delta Y.
	 * 
	 * @return delta y.
	 */
	public int getDY()
	{
		return dY;
	}

	/**
	 * Returns the difference to the x coordinate - delta X.
	 * 
	 * @return delta X.
	 */
	public int getDX()
	{
		return dX;
	}

}

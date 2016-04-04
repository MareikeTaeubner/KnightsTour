package serialisation;

/**
 * This class is a XML model that represents a single step in the movement
 * history.
 * 
 * @author mareike
 *
 */
public class Step
{
	private int stepNumber;
	private int x;
	private int y;

	/**
	 * Returns the X coordinate of the actual tile.
	 * 
	 * @return the X coordinate of the actual tile.
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * Sets the X coordinate of the actual tile.
	 * 
	 * @param x
	 *            the X coordinate of the actual tile.
	 */
	public void setX(int x)
	{
		this.x = x;
	}

	/**
	 * Returns the Y coordinate of the actual tile.
	 * 
	 * @return the Y coordinate of the actual tile.
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * Sets the Y coordinate of the actual tile.
	 * 
	 * @param y
	 *            the Y coordinate of the actual tile.
	 */
	public void setY(int y)
	{
		this.y = y;
	}

	/**
	 * Returns the number of this step.
	 * 
	 * @return the number of this step.
	 */
	public int getStepNumber()
	{
		return stepNumber;
	}

	/**
	 * Sets the number of this step.
	 * 
	 * @param stepNumber
	 *            the number of this step.
	 */
	public void setStepNumber(int stepNumber)
	{
		this.stepNumber = stepNumber;
	}

}

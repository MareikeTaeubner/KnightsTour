package serialisation;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is a XML model class that represents the movement history of the
 * piece.
 * 
 * @author mareike
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MovementHistory
{
	@XmlElementWrapper(name = "steps")
	@XmlElement(name = "step")
	private List<Step> steps;

	/**
	 * Returns the movement steps.
	 * 
	 * @return Returns the movement steps.
	 */
	public List<Step> getSteps()
	{
		return steps;
	}

	/**
	 * Sets the {@link List} of movement steps.
	 * 
	 * @param steps
	 *            the {@link List} of movement steps.
	 */
	public void setSteps(List<Step> steps)
	{
		this.steps = steps;
	}

}

package serialisation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import graphics.Tile;
import movement.HistoryEntry;

/**
 * This class provides functionality to write an XML into a file.
 * 
 * @author mareike
 *
 */
public class XmlWriter
{
	/**
	 * Writes the given movement history in correct order as XML into the given
	 * {@link File}
	 * 
	 * @param history
	 *            the movement history
	 * @param file
	 *            the file the XML shall be written to
	 */
	public void writeHistory(Deque<HistoryEntry> history, File file)
	{
		List<Step> steps = createStepList(history);

		MovementHistory movementHistory = new MovementHistory();
		movementHistory.setSteps(steps);

		writeXml(file, movementHistory);
	}

	private List<Step> createStepList(Deque<HistoryEntry> history)
	{
		List<HistoryEntry> historyList = new ArrayList<>(history);
		Collections.reverse(historyList);

		List<Step> steps = new ArrayList<>();
		int stepNumber = 1;

		for (HistoryEntry historyEntry : historyList)
		{
			Tile tile = historyEntry.getTile();
			int x = tile.getX();
			int y = tile.getY();

			Step step = new Step();
			step.setStepNumber(stepNumber);
			step.setX(x);
			step.setY(y);

			steps.add(step);
			stepNumber++;
		}

		return steps;
	}

	private void writeXml(File file, MovementHistory history)
	{
		try
		{
			JAXBContext context = JAXBContext
					.newInstance(MovementHistory.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.marshal(history, file);

		} catch (JAXBException e)
		{
			throw new RuntimeException(e);
		}
	}
}

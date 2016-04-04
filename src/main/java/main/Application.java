package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import graphics.Chessboard;
import graphics.ImageKnight;
import graphics.Knight;
import graphics.Tile;
import graphics.TileColor;
import movement.HistoryEntry;
import movement.MovementCalculator;
import movement.PieceMover;
import serialisation.XmlWriter;

public class Application
{
	public static void main(String[] args) throws InterruptedException
	{
		int boardSize = 8;

		TileColor darkColor = new TileColor(Color.BLACK, Color.ORANGE);
		TileColor lightColor = new TileColor(Color.WHITE, Color.YELLOW);
		Chessboard chessBoard = new Chessboard(darkColor, lightColor,
				boardSize);

		// creates panel with the chessboard in the center
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(chessBoard, BorderLayout.CENTER);

		// creates a control panel whose control elements sit in a strait line
		JPanel controls = new JPanel();
		controls.setLayout(new BoxLayout(controls, BoxLayout.LINE_AXIS));

		// creates the start X input element and adds it to the control panel
		controls.add(new JLabel("Start X"));
		controls.add(Box.createHorizontalStrut(10));
		final JSpinner startxInput = new JSpinner(
				new SpinnerNumberModel(0, 0, boardSize - 1, 1));
		controls.add(startxInput);

		controls.add(Box.createHorizontalStrut(50));
		// creates the start Y input element and adds it to the control panel
		controls.add(new JLabel("Start Y"));
		controls.add(Box.createHorizontalStrut(10));
		final JSpinner startyInput = new JSpinner(
				new SpinnerNumberModel(0, 0, boardSize - 1, 1));
		controls.add(startyInput);

		// creates the start button - does nothing yet
		controls.add(Box.createHorizontalStrut(200));
		JButton start = new JButton("start");
		controls.add(start);
		// adds a empty border to the control panel
		controls.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		// adds the control panel to the main panel in the south position
		panel.add(controls, BorderLayout.SOUTH);

		// adds an empty border to the main panel
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JFrame frame = new JFrame();
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);

		// the last to do after chess board creation
		frame.setVisible(true);

		Knight knight = new ImageKnight();
		start.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				performInNonUiThread(new Runnable()
				{
					@Override
					public void run()
					{
						int startX = (Integer) startxInput.getValue();
						int startY = (Integer) startyInput.getValue();

						executePath(chessBoard, knight, startX, startY);
					}
				});
			}
		});
	}

	private static void executePath(Chessboard chessboard, Knight piece,
			int startX, int startY)
	{
		// erases the state of the last run
		Tile[][] board = chessboard.getBoard();
		for (int y = 0; y < board.length; y++)
		{
			for (int x = 0; x < board[y].length; x++)
			{
				board[y][x].setPiece(null);
				board[y][x].setVisited(false);
			}
		}

		MovementCalculator calculator = new MovementCalculator();
		Deque<HistoryEntry> movementHistory = new LinkedList<>();

		performPathSearch(chessboard, piece, board, startX, startY, calculator,
				movementHistory);

		XmlWriter writer = new XmlWriter();
		File file = new File("/tmp/movementHistory.xml");
		writer.writeHistory(movementHistory, file);
	}

	private static void performPathSearch(Chessboard chessBoard, Knight knight,
			Tile[][] board, int startX, int startY,
			MovementCalculator calculator, Deque<HistoryEntry> movementHistory)
	{
		Tile from = board[startY][startX];
		from.setVisited(true);

		List<Tile> possibleDestinationTiles = calculator
				.fetchPossibleDestinationTiles(startX, startY, chessBoard,
						knight.getMovementVectors());
		HistoryEntry firstEntry = new HistoryEntry(from,
				possibleDestinationTiles);
		movementHistory.add(firstEntry);
		PieceMover mover = new PieceMover(calculator, movementHistory);

		while (movementHistory.size() < chessBoard.grabTileNumber())
		{
			mover.movePieceToNextTile(chessBoard, knight);
			chessBoard.repaint();
			sleep(500L);
		}

		System.out.printf("finished [%d|%d]\n", startX, startY);
	}

	private static void performInNonUiThread(Runnable runnable)
	{
		Executors.newSingleThreadExecutor().execute(runnable);
	}

	private static void sleep(long milliseconds)
	{
		try
		{
			Thread.sleep(milliseconds);
		} catch (InterruptedException e)
		{
			Thread.currentThread().interrupt();
		}
	}
}

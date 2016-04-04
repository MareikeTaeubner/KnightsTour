package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import movement.MovementVector;

public class Knight implements Piece
{
	private final Color color;
	private final List<MovementVector> movementVectors;

	public Knight(Color color)
	{
		this.color = color;
		this.movementVectors = new ArrayList<>();
		movementVectors.add(new MovementVector(-2, 1));
		movementVectors.add(new MovementVector(-1, 2));
		movementVectors.add(new MovementVector(1, 2));
		movementVectors.add(new MovementVector(2, 1));
		movementVectors.add(new MovementVector(2, -1));
		movementVectors.add(new MovementVector(1, -2));
		movementVectors.add(new MovementVector(-1, -2));
		movementVectors.add(new MovementVector(-2, -1));
	}

	@Override
	public void draw(Graphics g, int x, int y, int tileSize)
	{
		g.setColor(color);

		drawBody(g, x, y, tileSize);
		drawHead(g, x, y, tileSize);
	}

	@Override
	public Collection<MovementVector> getMovementVectors()
	{
		return movementVectors;
	}

	private void drawHead(Graphics g, int x, int y, int size)
	{
		int sixtSize = size / 6;
		int forthSize = (int) Math.floor(size / 2.6);
		int ovalX = x + forthSize;
		int ovalY = y + sixtSize;

		int width = size / 4;
		int height = width;
		g.fillOval(ovalX, ovalY, width, height);

	}

	private void drawBody(Graphics g, int x, int y, int size)
	{
		int thirdTileSize = size / 3;
		int halfTileSize = size / 2;

		int triangleX1 = x + halfTileSize;
		int triangleY1 = y + thirdTileSize;

		int triangleX2 = x + thirdTileSize;
		int triangleY2 = y + 2 * thirdTileSize;

		int triangleX3 = x + 2 * thirdTileSize;
		int triangleY3 = triangleY2;

		int[] xpoints = { triangleX1, triangleX2, triangleX3 };
		int[] ypoints = { triangleY1, triangleY2, triangleY3 };
		g.fillPolygon(xpoints, ypoints, 3);
	}
}

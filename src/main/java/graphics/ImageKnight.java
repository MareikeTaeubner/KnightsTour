package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageKnight extends Knight
{
	private static final BufferedImage KNIGHT_IMAGE;

	static
	{
		try
		{
			KNIGHT_IMAGE = ImageIO.read(
					ImageKnight.class.getResourceAsStream("/chessknight.png"));
		} catch (IOException e)
		{
			throw new ExceptionInInitializerError(e);
		}
	}

	public ImageKnight()
	{
		super(null);
	}

	@Override
	public void draw(Graphics g, int x, int y, int tileSize)
	{
		g.drawImage(KNIGHT_IMAGE, x, y, tileSize, tileSize, null);
	}
}

package avs.utils;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

/************************************************************************
	This class will be responsible for loading images used in the game
*************************************************************************/

public class BufferedImageLoader{
	private BufferedImage image;

	public BufferedImage loadImage(String path) throws IOException{
        image = ImageIO.read(getClass().getResource(path));
		return image;
	}
}

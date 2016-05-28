package avs.utils;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public class BufferedImageLoader{
	private BufferedImage image;

	public BufferedImage loadImage(String path) throws IOException{
		image = ImageIO.read(getClass().getResource(path));
		return image;
	}
}

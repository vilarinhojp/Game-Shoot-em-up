package com.uncry.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheettiles {
	
	public static BufferedImage sheet;
	
	public Spritesheettiles(String path){
		try {
			sheet=ImageIO.read(getClass().getResource(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public BufferedImage getSprite(int x,int y,int w,int h) {
		return sheet.getSubimage(x, y, w, h);
	}
}

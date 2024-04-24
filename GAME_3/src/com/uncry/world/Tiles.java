package com.uncry.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.uncry.main.Game;

public class Tiles {
	
	public BufferedImage sheet;
	public int x,y,w,h;
	
	public static BufferedImage FLOOR_PLANET_BLUE=Game.sheetTiles.getSprite(0, 0, 64, 64);
	public static BufferedImage FLOOR_PLANET_ORANGE=Game.sheetTiles.getSprite(128, 0, 64, 64);
	public static BufferedImage FLOOR_PLANET_BLUE_2=Game.sheetTiles.getSprite(128+64, 0, 64, 64);
	public static BufferedImage FLOOR_PLANET_SKY=Game.sheetTiles.getSprite(128*2, 0, 64, 64);
	public static BufferedImage FLOOR_MOON=Game.sheetTiles.getSprite(64, 0, 64, 64);
	
	public static BufferedImage WALL_GRASS_FRONT=Game.sheetTiles.getSprite(0, 64, 64, 64);

	public Tiles(int x,int y,int w,int h,BufferedImage sheet) {	
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.sheet=sheet;
	}
	public void render(Graphics g) {
		g.drawImage(sheet,x-Camera.x,y-Camera.y,w,h,null);
	}
}

package com.uncry.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.uncry.main.Game;
import com.uncry.world.Camera;

public class Turbinas extends Entity{
	
	public static BufferedImage[]elice;
	public static BufferedImage caser;


	int frames=0;
	int maxFrames=1;
	int index=0;
	int maxIndex=5;
	public void tick() {
		frames++;
		if(frames==maxFrames) {
			frames=0;
			index++;
			if(index>=maxIndex) {
				index=0;
			}
		}
		
	}
	public void render(Graphics g) {
		g.setColor(new Color(85,107,60));
		g.fillRect((getX()+40)-Camera.x, (getY()+57)-Camera.y,50,28);
		g.drawImage(elice[index], getX()-Camera.x, (getY()-34)-Camera.y, 128,128,null);
		g.drawImage(caser, (getX()+33)-Camera.x, (getY()+50)-Camera.y, 64,64,null);

		
	}
	public Turbinas(int x, int y, int w, int h, BufferedImage sheet) {
		super(x, y, w, h, sheet);
		elice=new BufferedImage[6];
		caser=Game.sheetEntity.getSprite(6*64, 0,64,64);
		for(int i=0;i<elice.length;i++) {
			elice[i]=Game.sheetEntity.getSprite(64*i, 0, 64,64);
		}
	}

}

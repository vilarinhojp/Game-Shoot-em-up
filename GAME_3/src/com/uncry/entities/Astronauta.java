package com.uncry.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.uncry.main.Game;
import com.uncry.world.Camera;

public class Astronauta extends Entity{
	
	public static BufferedImage[]astronauta;
	int cont=0;
	int index=0;
	public void tick() {
	cont++;	
	if(Game.rad.nextInt(100)<80) {
		if(cont<60) {
			index=0;
		}else if(cont>=60&&cont<63) {
			index=1;
		}else {
			cont=0;
		}
	}else {
		if(cont<30) {
			index=0;
		}else if(cont>=30&&cont<33) {
			index=1;
		}else {
			cont=0;
		}
	}
	}
	public void render(Graphics g) {
		g.drawImage(astronauta[index], getX()-Camera.x,getY()-Camera.y,null);
	}
	public Astronauta(int x, int y, int w, int h, BufferedImage sheet) {
		super(x, y, w, h, sheet);
		astronauta=new BufferedImage[2];
		astronauta[0]=Game.sheetEntity.getSprite(0, 64, 64, 64);
		astronauta[1]=Game.sheetEntity.getSprite(64, 64, 64, 64);

	}

}

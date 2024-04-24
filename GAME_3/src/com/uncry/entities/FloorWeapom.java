package com.uncry.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.uncry.main.Game;
import com.uncry.world.Camera;

public class FloorWeapom extends Entity{
	
	public static BufferedImage weapom;
	public static int cont=0;
	public void tick() {
		if(cont==0) {
			Bullet bullet=new Bullet(getX()+29,getY()+52,10,10,null);
			bullet.setMask(0, 0, 10, 10);
			Game.bullets.add(bullet);
			cont++;
		}else if(cont>0) {
			cont++;
			if(cont>130) {
				cont=0;
			}
		}
	}
	public void render(Graphics g) {
		g.drawImage(weapom, getX()-Camera.x,getY()-Camera.y,64,64,null);
	}
	public FloorWeapom(int x, int y, int w, int h, BufferedImage sheet) {
		super(x, y, w, h, sheet);
		weapom= Game.sheetEntity.getSprite(0, 128, 64, 64);
	}

}

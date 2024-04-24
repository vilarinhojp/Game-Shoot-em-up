package com.uncry.entities;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.uncry.main.Game;
import com.uncry.world.Camera;

public class ShootMouse extends Entity {
	public double dx,dy;
	public double speed;
	int life=10;
	int curLife;
	public void render(Graphics g) {
		g.drawImage(Entity.SHOOT, getX()-Camera.x,getY()-Camera.y, null);
	}
	public void tick() {
		x+=dx*speed;
		y+=dy*speed;
		curLife++;
		
		if(curLife>=80) {
			Game.elementos.remove(this);
		}
	}

	public ShootMouse(int x, int y, int w, int h, BufferedImage sheet,double dx,double dy) {
		super(x, y, w, h, sheet);
		this.dx=dx;
		this.dy=dy;
		speed=4;
		curLife=0;
	}

}

package com.uncry.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.uncry.main.Game;
import com.uncry.world.Camera;

public class Entity {
	
	
	protected int w,h;
	protected double x,y;
	
	public static BufferedImage bomb=Game.sheetEntity.getSprite(64, 128, 8,8);
	public static BufferedImage REPARO=Game.sheetEntity.getSprite(128+64, 64, 64,64);
	public static BufferedImage ACTIVE=Game.sheetEntity.getSprite(64*4, 64, 64,64);
	public static BufferedImage SHOOT=Game.sheetPlayer.getSprite(128, 0, 4,4);

	public BufferedImage sheet;
	int maskx=0;
	int masky=0;
	int maskw=0;
	int maskh=0;
	public void setX(int newX) {
		x=newX;
	}
	public void setY(int newY) {
		y=newY;
	}
	public void setMask(int maskx,int masky,int maskw,int maskh) {
		this.maskh=maskh;
		this.maskw=maskw;
		this.maskx=maskx;
		this.masky=masky;
	}
	public int getX() {
		return (int)this.x;
	}
	public int getY() {
		return (int)this.y;
	}
	public int getW() {
		return this.w;
	}
	public int getH() {
		return this.h;
	}
	
	public void tick() {
		
	}
	
	public static boolean isColliding(Entity e1, Entity e2) {
		Rectangle rect1=new Rectangle((e1.getX()+e1.maskx),(e1.getY()+e1.masky),e1.maskw,e1.maskh);
		Rectangle rect2=new Rectangle((e2.getX()+e2.maskx),(e2.getY()+e2.masky),e2.maskw,e2.maskh);
		return rect1.intersects(rect2);
	}
	public void render(Graphics g) {

		g.drawImage(sheet, getX()-Camera.x,getY()-Camera.y,getW(),getH(),null);
		
	}
	public Entity(int x,int y,int w,int h,BufferedImage sheet) {
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.sheet=sheet;
	}
}

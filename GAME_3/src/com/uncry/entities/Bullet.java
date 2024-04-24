package com.uncry.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.uncry.main.Game;
import com.uncry.world.Camera;
import com.uncry.world.World;

public class Bullet extends Entity {
	
	public int intervalo=0;
	public int xp,yp;
	int dx;
	int dy;
	public boolean area;
	public boolean color;
	int colorTimer=0;
	int speed=1;
	public void tick() {
		area=false;
		//coloração
		if(colorTimer>=0 && colorTimer<10) {
			color=true;
			colorTimer++;
		}else if(colorTimer>=10 && colorTimer<20) {
			color=false;
			colorTimer++;
		}else {
			colorTimer=0;
		}
		
		if(Game.player.getY()-getY()<64*5) {
			area=true;
		}else {
			area=false;
		}
		
		//moved
		if(intervalo==0) {
			xp=Game.player.getX()-32;
			yp=Game.player.getY()+120;
			intervalo++;
		}else {
			if(area) {
				if(x<xp+32 ) {
					dx=-1;
				}else if(x>xp) {
					dx=1;
				}
				if(y<yp) {
					dy=1;
				}			
			}
			
		}
		if(isColliding(getX(),(int)(y+(dy*speed)))) {
			y+=dy*speed;
		}else {
			Game.bullets.remove(this);
		}
		if(isColliding((int)(x+(dx*speed)),getY())){
			x-=dx*speed;	
		}else {
			Game.bullets.remove(this);
		}
		
		
	}
	public boolean isColliding(int nextx,int nexty) {
		Rectangle current=new Rectangle(nextx,nexty,10,10);
		for(int i=0;i<Game.bullets.size();i++) {
			Entity e=Game.bullets.get(i);
			if(e==this) {
				continue;
				
			}else {
			Rectangle enemy=new Rectangle(e.getX(),e.getY(),10,10);
			if(current.intersects(enemy)) {
				return false;
			}
			}
		}
		return true;
	}
	public void render(Graphics g) {
		
		if(color) {
			g.setColor(new Color(255,99,71));
			g.fillOval((getX())-Camera.x, (getY())-Camera.y,10,10);
			
		}else if(color==false){
			g.setColor(new Color(255,0,0));
			g.fillOval((getX())-Camera.x, (getY())-Camera.y,10,10);
			
		}
		/*
		g.setColor(Color.black);
		g.fillRect((getX()+this.maskx)-Camera.x, (getY()+this.masky)-Camera.y, this.maskw,this.maskh);
		
		g.drawImage(Entity.bomb,(getX()+30)-Camera.x, (getY()+53)-Camera.y, null);
		*/
	}
	public Bullet(int x, int y, int w, int h, BufferedImage sheet) {
		super(x, y, w, h, sheet);
	}

}

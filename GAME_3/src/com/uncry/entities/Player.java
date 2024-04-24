package com.uncry.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.uncry.graphics.Spritesheetentities;
import com.uncry.graphics.Spritesheetplayer;
import com.uncry.graphics.Spritesheettiles;
import com.uncry.main.Game;
import com.uncry.world.Camera;
import com.uncry.world.World;

public class Player extends Entity {
	
	public static BufferedImage budyShip;
	public static BufferedImage []budyShipWeapom;
	public static BufferedImage [] protectShip;
	public static BufferedImage [] protectShipBreak;
	public static BufferedImage [] airShip;
	public static BufferedImage[]player;
	
	int sizeP=64;
	
	int frames=0;
	int maxFrames=1;
	int index=0;
	int maxIndex=5;
	
	int framesB=0;
	int maxFramesB=4;
	int indexB=0;
	int maxIndexB=19;
	
	int framesBreak=0;
	int maxFramesBreak=1;
	int indexBreak=0;
	int maxIndexBreak=9;
	
	public int mx,my;
	double speed,speedAuto=2;
	
	int indexP=0;
	
	public static boolean right,left,gun,weapom=false,shoot=false,breakProtect=false;
	
	public static int lifeProtectedShip=7;
	public int cont;
	public void tick() {
		//Speed System		
		speed=2;
		//move
		if(Game.world.isFree((int)(getX())+3, (int)(getY()-speedAuto)+51,59,4) || Game.world.isFree((getX())+4, (int)(getY()-speedAuto)+51,59,4)) {
			y-=speedAuto;
		}
		if(right && getX()<Game.world.WIDTH*64-64) {
			if(Game.world.isFree((int)(getX()+speed)+3, (int)(getY())+51,59,4)) {
				x+=speed;
			}	
		}else if(left && getX()>0-4) {
			if(Game.world.isFree((int)(getX()-speed)+4, (int)(getY())+51,59,4)) {
				x-=speed;
			}
		}else {
			speed=2;
		}
	
		//renderização
		frames++;
		if(frames==maxFrames) {
			index++;
			frames=0;
			if(index>maxIndex) {
				index=0;
			}
		}
		if(breakProtect) {
			framesBreak++;
			if(framesBreak==maxFramesBreak) {
				indexBreak++;
				framesBreak=0;
				if(indexBreak>maxIndexBreak) {
					indexBreak=9;
				}
			}
		}
		cont++;
		if(cont==1) {
			if(Game.rad.nextInt(100)<30) {
				indexP=0;
			}else if(Game.rad.nextInt(100)>=30 && Game.rad.nextInt(100)<60) {
				indexP=1;
			}else if(Game.rad.nextInt(100)>=60 && Game.rad.nextInt(100)<100) {
				indexP=2;
			}
		}else if(cont>1 && cont <=150) {
			cont++;
		}else if(cont>150) {
			cont=0;
		}
		
		if(weapom==true) {
			framesB++;
			if(framesB==maxFramesB) {
				indexB++;
				framesB=0;
				if(indexB>maxIndexB) {
					indexB=19;
					
					if(shoot) {
						shoot=false;
						int pxRight=14;
						int pyRight=38;
						int pxLeft=50;
						int pyLeft=38;
						double dxR,dyR,dxL,dyL;
						
						double angleRight=Math.atan2(my-((this.getY()+pyRight)-Camera.y), mx-((this.getX()+pxRight)-Camera.x));
						dxR=Math.cos(angleRight);
						dyR=Math.sin(angleRight);
						ShootMouse shootR=new ShootMouse(this.getX()+pxRight,this.getY()+pyRight,8,8,Entity.SHOOT,dxR,dyR);
						Game.elementos.add(shootR);
			
						double angleLeft=Math.atan2(my-((this.getY()+pyLeft)-Camera.y), mx-((this.getX()+pxLeft)-Camera.x));
						dxL=Math.cos(angleLeft);
						dyL=Math.sin(angleLeft);
						ShootMouse shootL=new ShootMouse(this.getX()+pxLeft,this.getY()+pyLeft,8,8,Entity.SHOOT,dxL,dyL);
						Game.elementos.add(shootL);
					}
				}
			}
			
		}
		
		
		isCollidingWidthPlayer();
		isCollidingReparo();
		isCollidingAct();
		Camera.x=Camera.Clamp(this.getX()-(Game.WIDTH/2-(getW()/2)), 0,Game.world.WIDTH*64-Game.WIDTH);
		Camera.y=Camera.Clamp(this.getY()-(Game.HEIGHT/2+(getH()/6)),0,Game.world.HEIGHT*64-Game.HEIGHT);

	}
	public void isCollidingAct() {
		for(int i=0;i<Game.elementos.size();i++) {
			Entity e=Game.elementos.get(i);
			if(e instanceof Active) {
				if(Entity.isColliding(this, e)) {
					weapom=true;
					Game.elementos.remove(e);
				}
			}
		}
	}
	public void isCollidingReparo() {
		for(int i=0;i<Game.elementos.size();i++) {
			Entity e=Game.elementos.get(i);
			if(e instanceof Reparo) {
				if(Entity.isColliding(this, e)) {
					if(lifeProtectedShip <7) {
						lifeProtectedShip++;
					}
					//System.out.println("a");
					Game.elementos.remove(e);;
				}
			}
		}
	}
	public void isCollidingWidthPlayer() {
		for(int i=0;i<Game.bullets.size();i++) {
			Entity e=Game.bullets.get(i);
			if(Entity.isColliding(this, e)) {
				Game.bullets.remove(e);
				if(lifeProtectedShip==1) {
					lifeProtectedShip=1;
				}else {
					lifeProtectedShip--;
				}
			  //dano: reiniciar
				/*
				Game.entities=new ArrayList<Entity>();
				Game.elementos=new ArrayList<Entity>();
				Game.bullets=new ArrayList<Entity>();
				Game.sheetPlayer=new Spritesheetplayer("/spritesheetplayer.png");
				Game.sheetTiles=new Spritesheettiles("/spritesheettiles.png");
				Game.sheetEntity=new Spritesheetentities("/spritesheetentities.png");
				Game.world=new World("/map.png");
				*/
				
			}
		}
	}
	public void render(Graphics g) {
		
		//renderização do player;
		
		g.setColor(new Color(163,105,78));
		g.fillRect((getX()+25+2)-Camera.x,(getY()+50)-Camera.y,15,5);
		if(lifeProtectedShip<2) {
			g.drawImage(player[indexP], getX()-Camera.x,(getY()-3)-Camera.y, null);
		}
		g.drawImage(airShip[index],getX()-Camera.x,(getY()-3)-Camera.y,sizeP,sizeP,null);
		if(weapom==false) {
			g.drawImage(budyShip,getX()-Camera.x,getY()-Camera.y,sizeP,sizeP,null);
		}else {
			g.drawImage(budyShipWeapom[indexB],getX()-Camera.x,getY()-Camera.y,sizeP,sizeP,null);
		}
		if(lifeProtectedShip==7) {
			g.drawImage(protectShip[0],getX()-Camera.x,getY()-Camera.y,sizeP,sizeP,null);
			
		}else if(lifeProtectedShip==6) {
			g.drawImage(protectShip[1],getX()-Camera.x,getY()-Camera.y,sizeP,sizeP,null);
			
		}else if(lifeProtectedShip==5) {
			g.drawImage(protectShip[2],getX()-Camera.x,getY()-Camera.y,sizeP,sizeP,null);
			
		}else if(lifeProtectedShip==4) {
			g.drawImage(protectShip[3],getX()-Camera.x,getY()-Camera.y,sizeP,sizeP,null);
			
		}else if(lifeProtectedShip==3) {
			g.drawImage(protectShip[4],getX()-Camera.x,getY()-Camera.y,sizeP,sizeP,null);
			
		}else if(lifeProtectedShip==2) {
			g.drawImage(protectShip[5],getX()-Camera.x,getY()-Camera.y,sizeP,sizeP,null);
			
		}else if(lifeProtectedShip<2) {
			breakProtect=true;
			g.drawImage(protectShipBreak[indexBreak],getX()-Camera.x,getY()-Camera.y,sizeP,sizeP,null);
		}
		
	}
	public Player(int x, int y, int w, int h, BufferedImage sheet) {

		super(x, y, w, h, sheet);
		budyShip =  Game.sheetPlayer.getSprite(0, 0, 64, 64);
		protectShip = new BufferedImage[6];
		airShip = new BufferedImage[6];
		budyShipWeapom = new BufferedImage[20];
		protectShipBreak = new BufferedImage[10];
		player=new BufferedImage[3];
		for(int i=0;i<3;i++) {
			player[i]=Game.sheetPlayer.getSprite(64*i,7*64,64,64);
		}
		for(int ii=0;ii<2;ii++) {
			for(int i=0;i<10;i++) {
				budyShipWeapom[(ii*10)+i]=Game.sheetPlayer.getSprite(i*64, (4*64)+(ii*64),64,64);
			}
		}
		for(int i=0;i<10;i++) {
			protectShipBreak[i]=Game.sheetPlayer.getSprite(64*i, 6*64, 64,64);
		}
		for(int i=0;i<6;i++) {
			protectShip[i]=Game.sheetPlayer.getSprite(i*64,128,sizeP,sizeP);
			airShip[i]=Game.sheetPlayer.getSprite(i*64,64,sizeP,sizeP);
		}
		
	}

}

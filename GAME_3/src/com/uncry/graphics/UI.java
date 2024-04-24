package com.uncry.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.uncry.entities.Player;
import com.uncry.main.Game;


public class UI{
	protected int x,y;
	public static BufferedImage protectBar;
	public static BufferedImage[]buttonsMove;
	public static BufferedImage[]buttonsMoveA;
	public void tick() {
		
	}
	int xx=50;
	int yy=4;
	public void render(Graphics g) {
		g.setColor(new Color(0,0,0,20));
		g.fillRect(0, 0, Game.WIDTH,20);
		g.setColor(new Color(248,248,255,125));
		g.fillRect(2,1,43,17);
		//protection
		g.setColor(Color.white);
		if(Player.lifeProtectedShip==7) {
			g.fillRect(2,1,43,17);
		}else if(Player.lifeProtectedShip==6) {
			g.fillRect(2,1,43-9,17);
		}else if(Player.lifeProtectedShip==5) {
			g.fillRect(2,1,43-16,17);
		}else if(Player.lifeProtectedShip==4) {
			g.fillRect(2,1,43-23,17);
		}else if(Player.lifeProtectedShip==3) {
			g.fillRect(2,1,43-30,17);
		}else if(Player.lifeProtectedShip==2) {
			g.fillRect(2,1,43-37,17);
		}else if(Player.lifeProtectedShip==1) {
			g.fillRect(2,1,0,17);
		}else {
			g.fillRect(2,1,0,17);
		}
		g.drawImage(this.protectBar,1,1,48,17,null);
		
		if(Game.player.left==false) {
			g.drawImage(buttonsMove[0],Game.WIDTH/3-16,Game.HEIGHT-33,32,32,null);

		}
		if(Game.player.right==false) {
			g.drawImage(buttonsMove[1],Game.WIDTH/3-16+34+34,Game.HEIGHT-33,32,32,null);

		}	
		if(Game.player.left) {
			g.drawImage(buttonsMoveA[0],Game.WIDTH/3-16,Game.HEIGHT-33,32,32,null);
		}else if(Game.player.right) {
			g.drawImage(buttonsMoveA[1],Game.WIDTH/3-16+34+34,Game.HEIGHT-33,32,32,null);
		}
		
		if(Game.player.gun) {
			g.drawImage(buttonsMoveA[2],Game.WIDTH/3-16+34,Game.HEIGHT-33,32,32,null); 
		}else if(Game.player.gun==false){
			g.drawImage(buttonsMove[2],Game.WIDTH/3-16+34,Game.HEIGHT-33,32,32,null); 
		}
		
	}
	public UI(int x, int y) {
		this.x=x;
		this.y=y;
		protectBar=Game.sheetEntity.getSprite(128, 64,48,17);
		buttonsMove=new BufferedImage[3];
		buttonsMoveA=new BufferedImage[3];
		buttonsMove[0]=Game.sheetPlayer.getSprite(0, 128+64, 32,32);
		buttonsMove[1]=Game.sheetPlayer.getSprite(32, 128+64, 32,32);
		buttonsMove[2]=Game.sheetPlayer.getSprite(0, 128+64+32, 32,32);
		
		buttonsMoveA[0]=Game.sheetPlayer.getSprite(64, 128+64, 32,32);
		buttonsMoveA[1]=Game.sheetPlayer.getSprite(64+32, 128+64, 32,32);
		buttonsMoveA[2]=Game.sheetPlayer.getSprite(64, 128+64+32, 32,32);


	}

}

package com.uncry.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.uncry.entities.Active;
import com.uncry.entities.Astronauta;
import com.uncry.entities.Entity;
import com.uncry.entities.FloorWeapom;
import com.uncry.entities.Player;
import com.uncry.entities.Reparo;
import com.uncry.entities.Turbinas;
import com.uncry.main.Game;

public class World {
	
	public static int WIDTH;
	public int HEIGHT;
	public static Tiles[]tiles;
	public static  int TILE_SIZE=64;
	public World(String path) {
		try {
	
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			WIDTH=map.getWidth();
			HEIGHT=map.getHeight();
			int[]pixels=new int[WIDTH*HEIGHT];
			tiles=new Tiles[WIDTH*HEIGHT];
			map.getRGB(0,0, WIDTH, HEIGHT,pixels, 0, WIDTH);
			
			for(int xx=0;xx<WIDTH;xx++) {
				for(int yy=0;yy<HEIGHT;yy++) {
					int pixelAtual=xx+(yy*WIDTH);
					switch(pixels[pixelAtual]) {
					case(0xFF3287E7):
						tiles[pixelAtual] = new FloorTile(xx*64,yy*64,64,64,Tiles.FLOOR_PLANET_BLUE);
					break;
					case(0xFF32A5E7):
						tiles[pixelAtual] = new FloorTile(xx*64,yy*64,64,64,Tiles.FLOOR_MOON);
					break;
					case(0xFF32B2E7):
						tiles[pixelAtual] = new FloorTile(xx*64,yy*64,64,64,Tiles.FLOOR_PLANET_ORANGE);
					break;
					case(0xFF32BEE7):
						tiles[pixelAtual] = new FloorTile(xx*64,yy*64,64,64,Tiles.FLOOR_PLANET_BLUE_2);
					break;
					case(0xFF705845):
						//astronauta
						tiles[pixelAtual] = new WallTile(xx*64,yy*64,64,64,Tiles.WALL_GRASS_FRONT);
					    Astronauta astronauta=new Astronauta(xx*64,yy*64,64,64,Game.sheetEntity.getSprite(0, 64, 64, 64));
						Turbinas turbinaA=new Turbinas((xx*64)-33,(yy*64)-50,64,64,Tiles.FLOOR_MOON);
						Game.elementos.add(turbinaA);
					    Game.entities.add(astronauta);
					break;
					case(0xFFFFFF45):
						//blockweapom
						tiles[pixelAtual] = new WallTile(xx*64,yy*64,64,64,Tiles.WALL_GRASS_FRONT);
						FloorWeapom floorW=new FloorWeapom(xx*64,yy*64,64,64,Game.sheetEntity.getSprite(0, 128, 64, 64));
						Game.elementos.add(floorW);
						Turbinas turbinaS=new Turbinas((xx*64)-33,(yy*64)-50,64,64,Tiles.FLOOR_MOON);
						Game.elementos.add(turbinaS);
					break;
					case(0xFF70FF48):
						tiles[pixelAtual] = new WallTile(xx*64,yy*64,64,64,Tiles.WALL_GRASS_FRONT);
						Turbinas turbina=new Turbinas((xx*64)-33,(yy*64)-50,64,64,Tiles.FLOOR_MOON);
						Game.elementos.add(turbina);
					break;
					case(0xFFE81C7A):
						tiles[pixelAtual] = new FloorTile(xx*64,yy*64,64,64,Tiles.FLOOR_MOON);
						Active act=new Active(xx*64,yy*64,64,64,Entity.ACTIVE);
						act.setMask(22, 25, 19,15);
						Game.elementos.add(act);
					break;
					case(0xFFE8007A):
						tiles[pixelAtual] = new FloorTile(xx*64,yy*64,64,64,Tiles.FLOOR_MOON);
						Reparo reparo=new Reparo(xx*64,yy*64,64,64,Entity.REPARO);
						reparo.setMask(18, 26, 15, 15);
						Game.elementos.add(reparo);
					break;
					case(0xFFEF4040):
						tiles[pixelAtual] = new FloorTile(xx*64,yy*64,64,64,Tiles.FLOOR_MOON);
						Game.player=new Player(xx*64,yy*64,64,64,Game.sheetPlayer.getSprite(64, 0, 64,64));
						Game.player.setMask(4,50,60, 4);
						Game.entities.add(Game.player);
					

					break;
					default:
						tiles[pixelAtual] = new FloorTile(xx*64,yy*64,64,64,Tiles.FLOOR_PLANET_SKY);
					break;
						
					}
	
				}
			}
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	public static boolean isFree(int nextx,int nexty,int w,int h) {
		int x1=nextx/TILE_SIZE;
		int y1=nexty/TILE_SIZE;
		
		int x2=(nextx+w-1)/TILE_SIZE;
		int y2=nexty/TILE_SIZE;
		
		int x3=nextx/TILE_SIZE;
		int y3=(nexty+h-1)/TILE_SIZE;
		
		int x4=(nextx+w-1)/TILE_SIZE;
		int y4=(nexty+h-1)/TILE_SIZE;

		return !(tiles[x1+(y1*WIDTH)]instanceof WallTile ||
				tiles[x2+(y2*WIDTH)]instanceof WallTile ||
				tiles[x3+(y3*WIDTH)]instanceof WallTile ||
				tiles[x4+(y4*WIDTH)]instanceof WallTile);
	}
	public void render(Graphics g) {
		
		int startx=Camera.x/64;
		int starty=Camera.y/64;
		int finalx=startx+(Game.WIDTH/64);
		int finaly=starty+(Game.HEIGHT/64);

		for(int xx=startx;xx<finalx+64;xx++) {
			for(int yy=starty;yy<finaly+64;yy++) {
				
				if(xx<0||yy<0||xx>=this.WIDTH||yy>=this.HEIGHT) {
					continue;
				}else {
					int pixelAtual=xx+(yy*WIDTH);
					Tiles e=tiles[pixelAtual];
					e.render(g);
				}
			}
		}
	}
	
}

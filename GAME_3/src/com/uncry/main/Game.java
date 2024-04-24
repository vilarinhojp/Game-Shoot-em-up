package com.uncry.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import com.uncry.entities.Entity;
import com.uncry.entities.Player;
import com.uncry.graphics.Spritesheetentities;
import com.uncry.graphics.Spritesheetplayer;
import com.uncry.graphics.Spritesheettiles;
import com.uncry.graphics.UI;
import com.uncry.world.World;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener{
	
	public JFrame frame;
	public static final int WIDTH=192;
	public static final int HEIGHT=300;
	public static final int SCALE=2;
	
	public static BufferedImage image;
	public static Thread thread;
	public boolean isRunning=true;
	
	public static Spritesheetplayer sheetPlayer;
	public static Spritesheettiles sheetTiles;
	public static Spritesheetentities sheetEntity;

	public static World world;
	public static Player player;
	public static UI ui;
	
	public static ArrayList<Entity> entities;
	public static ArrayList<Entity> elementos;
	public static ArrayList<Entity> bullets;
	
	public static Random rad;
	
	public void initFrame() {
		
		frame=new JFrame("SPACE SHOOT");
		frame.add(this);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		addKeyListener(this);
		addMouseListener(this);

	}

	public synchronized void start() {
		thread= new Thread(this);
		thread.start();
		isRunning=true;
		
	}
	public synchronized void stop() {
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
			
		}
		isRunning=false;
		
	}
	
	public void tick() {
		
		for(int i=0;i<elementos.size();i++) {	
			Entity e= elementos.get(i);
			e.tick();
			
		}
		for(int i=0;i<bullets.size();i++) {	
			Entity e= bullets.get(i);
			e.tick();
			
		}
		for(int i=0;i<entities.size();i++) {	
			Entity e= entities.get(i);
			e.tick();
			
		}

	}
	public void render() {
		BufferStrategy bs= getBufferStrategy();
		if(bs==null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g=image.getGraphics();
		g.setColor(Color.blue);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		world.render(g);

		for(int i=0;i<elementos.size();i++) {	
			Entity e= elementos.get(i);
			e.render(g);
			
		}
		for(int i=0;i<bullets.size();i++) {	
			Entity e= bullets.get(i);
			e.render(g);
			
		}
		for(int i=0;i<entities.size();i++) {	
			Entity e= entities.get(i);
			e.render(g);
			
		}
		ui.render(g);
		
		g.dispose();
		g=bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE,HEIGHT*SCALE,null);
		bs.show();
		
	}
	public void run() {
		requestFocus();
		double amountOfTicks=60;
		double lastTime=System.nanoTime();
		double ns=1000000000/amountOfTicks;
		double delta=0;
		int frames=0;
		double timer=System.currentTimeMillis();
		
		while(isRunning) {
			double now=System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			
			if(delta>=1) {
				tick();
				render();
				frames++;
				delta--;
				
			}
			if(System.currentTimeMillis()-timer>=1000) {
				timer+=1000;
				System.out.println("FPS: "+frames);
				frames=0;
				
			}
			
		}
		stop();
		
	}
	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_D ) {
			player.right=true;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_A) {
			player.left=true;
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_D) {
			player.right=false;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_A) {
			player.left=false;
		}
	}
	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		if(player.weapom) {
			Game.player.shoot=true;
		}
		Game.player.mx=e.getX()/SCALE;
		Game.player.my=e.getY()/SCALE;
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
	
	}
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		rad=new Random();
		entities=new ArrayList<Entity>();
		elementos=new ArrayList<Entity>();
		bullets=new ArrayList<Entity>();
		sheetPlayer=new Spritesheetplayer("/spritesheetplayer.png");
		sheetTiles=new Spritesheettiles("/spritesheettiles.png");
		sheetEntity=new Spritesheetentities("/spritesheetentities.png");
		world=new World("/map.png");
		ui=new UI(0,-20);
		
	}
	public static void main(String[]args) {
		Game game=new Game();
		game.start();
	}

}

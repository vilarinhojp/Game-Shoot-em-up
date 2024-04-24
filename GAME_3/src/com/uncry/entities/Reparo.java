package com.uncry.entities;

import java.awt.image.BufferedImage;

public class Reparo extends Entity{
	private int cont=0;
	public void tick() {
				
		if(cont<10) {
			y--;
			cont++;
		}else if(cont >=10 && cont<20) {
			y++;
			cont++;
		}else if(cont==20) {
			cont=0;
		}
	}
	public Reparo(int x, int y, int w, int h, BufferedImage sheet) {
		super(x, y, w, h, sheet);
	}

}

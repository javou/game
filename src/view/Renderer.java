package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.math.*;

import model.actor.Hero;
import resources.Constants;
import resources.Textures;


public class Renderer {
	public static int ativarPaint = 0;
	public Renderer() {
		// TODO Auto-generated constructor stub
	}
	public void firstScreen(Graphics graphics) {
		
		graphics.setColor(Color.WHITE);
		graphics.drawRoundRect(50, 50, Window.WIDTH-100, Window.HEIGHT-100, 10, 10);
		graphics.setFont(new Font("Verdana", Font.PLAIN, 40));
		graphics.drawString("THE HAUNTED CASTLE", 100, 100);
		graphics.setFont(new Font("Verdana", Font.PLAIN, 20));
		graphics.drawString("Do you wanna play a game?", 100, 150);
	}
	public void drawMap(Graphics graphics, String[] map) {
		for(int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length(); j++) {
				if(map[i].charAt(j) == '#') {
					BufferedImage texture = Textures.getTexture("wall");
					graphics.drawImage(texture, j*Constants.CELL_SIZE*Constants.ZOOM + (Window.WIDTH - map[i].length()*Constants.CELL_SIZE*Constants.ZOOM)/2 ,
							                   i*Constants.CELL_SIZE*Constants.ZOOM + (Window.HEIGHT - map.length*Constants.CELL_SIZE*Constants.ZOOM)/2 , texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM, null);
					//graphics.drawImage(texture, (j-(Hero.posX ))*Constants.CELL_SIZE +Window.WIDTH/2 ,(i-(Hero.posY ))*Constants.CELL_SIZE +Window.HEIGHT/2, texture.getWidth(), texture.getHeight(), null);
					
				}
				
			}
		}
		BufferedImage texture = Textures.getTexture("hero");
		graphics.drawImage(texture, Window.WIDTH/2 - Constants.CELL_SIZE, Window.HEIGHT/2 - Constants.CELL_SIZE, texture.getWidth(), texture.getHeight(), null);
		
	}
	public void messageBox(Graphics graphics, String message) {
		if(message != null) {
			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font("Verdana", Font.PLAIN, 40));
			graphics.drawString(message,Constants.CELL_SIZE*7, Constants.CELL_SIZE*29);
		}
	}
	public void heroStatus(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.drawRoundRect(10, 10, Constants.CELL_SIZE*5, Constants.CELL_SIZE*5, 10, 10);
		graphics.setFont(new Font("Verdana", Font.PLAIN, 15));
		graphics.drawString("HP:",20,25 );
		graphics.drawString("Strength:",20,40 );
		graphics.drawString("Armour:",20,55);
		//if the hero has a shield equipped
		BufferedImage texture = Textures.getTexture("shield");
		graphics.drawImage(texture, 0 ,Window.HEIGHT/2, texture.getWidth(), texture.getHeight(), null);
		//if the hero has a sword equipped
		texture = Textures.getTexture("sword");
		graphics.drawImage(texture, 0 ,Window.HEIGHT/2+Constants.CELL_SIZE, texture.getWidth(), texture.getHeight(), null);
	}

}

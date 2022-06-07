package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.math.*;

import model.actor.Hero;
import model.castle.Floor;
import resources.Constants;
import resources.Textures;


public class Renderer {
	public static int ativarPaint = 0;
	
	//just for tests
	public static int posHeroX = 0;
	public static int posHeroY = 0;
	public static void setPosHero(int dx, int dy) {
		Renderer.posHeroX += dx;
		Renderer.posHeroY += dy;
	}
	
	
	public void firstScreen(Graphics graphics) {
		
		graphics.setColor(Color.WHITE);
		graphics.drawRoundRect(50, 50, Window.WIDTH-100, Window.HEIGHT-100, 10, 10);
		graphics.setFont(new Font("Verdana", Font.PLAIN, 40));
		graphics.drawString("THE HAUNTED CASTLE", 100, 100);
		graphics.setFont(new Font("Verdana", Font.PLAIN, 20));
		graphics.drawString("Do you wanna play a game?", 100, 150);
	}
	public void drawFloor(Graphics graphics, Floor floor) {
		
		BufferedImage texture;
		for(int i = 0; i < floor.getHeight(); i++) {
			for (int j = 0; j < floor.getWidth(); j++) {
				if(floor.getTile(i, j).isOccupiableSpace()) {
					
					texture = Textures.getTexture("corridor");
					graphics.drawImage(texture, offset(j,posHeroX),
												offset(i,posHeroY),
												texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM, null);
				}
				else {
					if(floor.getTile(i, j).getId() == "door")
						texture = Textures.getTexture("door");
					else
						texture = Textures.getTexture("wall");
					graphics.drawImage(texture, offset(j,posHeroX),
												offset(i,posHeroY) ,
							                    texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM, null);	
				}
			
		}
		texture = Textures.getTexture("hero");
		//graphics.drawImage(texture, Window.WIDTH/2 - 3/2*Constants.CELL_SIZE*Constants.ZOOM, Window.HEIGHT/2 - Constants.CELL_SIZE*Constants.ZOOM, texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM, null);
		graphics.drawImage(texture, Window.WIDTH/2 , Window.HEIGHT/2 , texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM, null);
		
	}
	}
	public int offset(int k, int posHero) {
		return (k-posHero)*Constants.CELL_SIZE*Constants.ZOOM + (Window.WIDTH)/2 ;
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

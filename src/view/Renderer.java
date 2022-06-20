package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.math.*;
import java.util.ArrayList;

import controller.God;
import model.actor.Hero;
import model.actor.Monster;
import model.castle.Floor;
import resources.Constants;
import resources.Textures;


public class Renderer {
	public static int ativarPaint = 0;
	public static Renderer renderer; //singleton pattern, lazy way
	
	private Renderer() {};
	
	public static Renderer getInstance() {
		if(renderer == null)
			renderer = new Renderer();
		return renderer;
	}
	
	public void firstScreen(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.drawRoundRect(50, 50, Window.WIDTH-100, Window.HEIGHT-100, 10, 10);
		graphics.setFont(new Font("Verdana", Font.PLAIN, 40));
		graphics.drawString("THE HAUNTED CASTLE", 100, 100);
		graphics.setFont(new Font("Verdana", Font.PLAIN, 20));
		graphics.drawString("Do you wanna play a game?", 100, 150);
	}
	public void drawFloor(Graphics graphics, Floor floor, Hero hero) {
		int posHeroX = hero.getPosX();
		int posHeroY = hero.getPosY();
		BufferedImage texture;
		for(int i = 0; i < floor.getHeight(); i++) {
			for (int j = 0; j < floor.getWidth(); j++) {
				
					
					texture = Textures.getTexture("corridor");
					graphics.drawImage(texture, offset(j,posHeroX),
												offset(i,posHeroY),
												texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM, null);

					if(floor.getTile(i, j).getId() == "door")
						texture = Textures.getTexture("door");
					else if(floor.getTile(i, j).getId() == "wall")
						texture = Textures.getTexture("wall");
					else if(floor.getTile(i, j).getId() == "chest")
						texture = Textures.getTexture("chest");
					else if(floor.getTile(i, j).getId() == "potion")
						texture = Textures.getTexture("potion");
					else if(floor.getTile(i, j).getId() == "error")
						texture = Textures.getTexture("error");
					
					graphics.drawImage(texture, offset(j,posHeroX),
												offset(i,posHeroY) ,
							                    texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM, null);	
				
			
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
	public void heroStatus(Graphics graphics, Hero hero) {
		graphics.setColor(Color.WHITE);
		graphics.drawRoundRect(10, 10, Constants.CELL_SIZE*5, Constants.CELL_SIZE*3, 10, 10);
		graphics.setFont(new Font("Verdana", Font.PLAIN, 15));
		graphics.drawString("HP:" + String.valueOf(hero.getHp()),20,25 );
		graphics.drawString("Speed: " + String.valueOf(hero.getSpeed()),20,40 );
		graphics.drawString("Armour: " + String.valueOf(hero.getArmour()),20,55);
		//if the hero has a shield equipped
		if(hero.isArmorIsEquipped()) {
			BufferedImage texture = Textures.getTexture("shield");
			graphics.drawImage(texture, 0 ,Window.HEIGHT*1/4, texture.getWidth(), texture.getHeight(), null);
		}
			//if the hero has a sword equipped
		if(hero.isWeaponIsEquipped()) {
			BufferedImage texture = Textures.getTexture("sword");
			graphics.drawImage(texture, 0 ,Window.HEIGHT*1/4+Constants.CELL_SIZE, texture.getWidth(), texture.getHeight(), null);
	
		}
	}
	public void drawMonsters(Graphics graphics, Floor floor, Hero hero) {
		ArrayList<Monster> monsters = God.getInstance().getCastle().getCurrentFloor().getMonsters();
		if(monsters != null) {
			BufferedImage texture;
			int posHeroX = hero.getPosX();
			int posHeroY = hero.getPosY();
			for(Monster monster : monsters) {
				System.out.println(monster.getName());
				texture = Textures.getTexture(monster.getName());
				graphics.drawImage(texture, offset(monster.getPosX(),posHeroX),
						offset(monster.getPosY(),posHeroY) ,
	                    texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM, null);
			}
		}
	}

}

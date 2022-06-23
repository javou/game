package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.math.*;
import java.util.ArrayList;

import controller.God;
import model.actor.Hero;
import model.actor.IActor;
import model.actor.Enemy;
import model.castle.Floor;
import model.castle.IFloor;
import resources.Constants;
import resources.Textures;

public class Renderer {
	public int ativarPaint = 0;
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
	
	public void drawFloor(Graphics graphics, IFloor floor) {
		int posHeroX = floor.getHero().getPosX();
		int posHeroY = floor.getHero().getPosY();
		BufferedImage texture;
		for(int i = 0; i < floor.getHeight(); i++) {
			for (int j = 0; j < floor.getWidth(); j++) {
				
					
					texture = Textures.getTexture("corridor");
					graphics.drawImage(texture, offset(j, posHeroX),
												offset(i, posHeroY),
												texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM,
												null);

					if(floor.getTileId(j, i) == "door")
						texture = Textures.getTexture("door");
					else if(floor.getTileId(j, i) == "wall")
						texture = Textures.getTexture("wall");
					else if(floor.getTileId(j, i) == "chest")
						texture = Textures.getTexture("chest");
					else if(floor.getTileId(j, i) == "potion")
						texture = Textures.getTexture("potion");
					else if(floor.getTileId(j, i) == "error")
						texture = Textures.getTexture("error");
					
					graphics.drawImage(texture, offset(j, posHeroX),
												offset(i, posHeroY) ,
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
	
	public void heroStatus(Graphics graphics, IActor hero) {
		graphics.setColor(Color.WHITE);
		graphics.drawRoundRect(10, 10, Constants.CELL_SIZE*5, Constants.CELL_SIZE*3, 10, 10);
		graphics.setFont(new Font("Verdana", Font.PLAIN, 15));
		graphics.drawString("HP:" + String.valueOf(hero.getHp()),20,25 );
		graphics.drawString("Speed: " + String.valueOf(hero.getSpeed()),20,40 );
		graphics.drawString("Armour: " + String.valueOf(hero.getArmour()),20,55);
		/*
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
		*/
	}
	public void drawEnemies(Graphics graphics, IFloor floor, IHero hero) { // drawActors
		ArrayList<Enemy> enemies = God.getInstance().getCastle().getCurrentFloor().getMonsters(); // gameScreen should pass actors
		if(enemies != null) {
			BufferedImage texture;
			int posHeroX = hero.getPosX();
			int posHeroY = hero.getPosY();
			for(Enemy enemy : enemies) {
				System.out.println(enemy.getName());
				texture = Textures.getTexture(enemy.getName());
				graphics.drawImage(texture, offset(enemy.getPosX(),posHeroX),
						offset(enemy.getPosY(),posHeroY) ,
	                    texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM, null);
			}
		}
	}
	
	public void drawActors(Graphics graphics, IFloor floor) {
		
	}

}

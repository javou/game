package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import model.actor.Hero;
import model.actor.IActor;
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
	}
	}
	
	public int offset(int k, int posHero) {
		return (k-posHero)*Constants.CELL_SIZE*Constants.ZOOM + (Window.WIDTH)/2 ;
	}
	
	public void messageBox(Graphics graphics, String message) {
		if(message != null) {
			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font("Verdana", Font.PLAIN, 30));
			graphics.drawString(message,Constants.CELL_SIZE*7, Window.HEIGHT/2 + Constants.CELL_SIZE*9);
			
		}
	}
	
	public void heroStatus(Graphics graphics, IActor hero) {
		graphics.setColor(Color.WHITE);
		graphics.drawRoundRect(10, 10, Constants.CELL_SIZE*5, Constants.CELL_SIZE*3, 10, 10);
		graphics.setFont(new Font("Verdana", Font.BOLD, 15));
		graphics.drawString("HP:" + String.valueOf(hero.getHp()),20,25 );
		graphics.drawString("Speed: " + String.valueOf(hero.getSpeed()),20,40 );
		graphics.drawString("Armour: " + String.valueOf(hero.getArmour()),20,55);
		
		BufferedImage armourTexture = Textures.getTexture("shield");
		graphics.drawImage(armourTexture, 50 ,70, armourTexture.getWidth(), armourTexture.getHeight(), null);
		// graphics.drawString(String.valueof(hero.getArmourLevel()), x, y);
		
		BufferedImage weaponTexture = Textures.getTexture("sword");
		graphics.drawImage(weaponTexture, 20 ,70, weaponTexture.getWidth(), weaponTexture.getHeight(), null);
		// graphics.drawString(String.valueof(hero.getWeaponLevel()), x, y)
		
		
		if (hero.countPotions()>0) {
			BufferedImage texture = Textures.getTexture("potion");
			graphics.drawImage(texture, 80 ,70, texture.getWidth(), texture.getHeight(), null);
			graphics.drawString(String.valueOf(hero.countPotions()), 91,96);
		}
		
	}
	/*
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
	*/
	public void drawActors(Graphics graphics, IFloor floor) {
		ArrayList<IActor> actors = floor.getActors(); 
		if(actors != null) {
			BufferedImage texture;
			
			int posHeroX = floor.getHero().getPosX();
			int posHeroY = floor.getHero().getPosY();
			//System.out.println(posHeroX);
			//System.out.println(posHeroY);
			for(IActor actor : actors) {
				if(actor instanceof Hero) {
					texture = Textures.getTexture("hero");
					graphics.drawImage(texture, Window.WIDTH/2 , Window.HEIGHT/2 , texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM, null);
				}
				else {	
					
					texture = Textures.getTexture(actor.getName());
					graphics.drawImage(texture, offset(actor.getPosX(),posHeroX),
							offset(actor.getPosY(),posHeroY) ,
		                    texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM, null);
				}
			}
		}
	}

}

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
	public static int len = Constants.CELL_NUMBER * Constants.CELL_SIZE;
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
		graphics.drawString("Type the hero name and press enter to play", 3*32, 17*32);
		graphics.drawString("Hero name: " + Hero.getInstance().getName(), 5*32, 18*32);
	}
	
	public void gameOverScreen(Graphics graphics,long time){
		graphics.setColor(Color.WHITE);
		String s;
		graphics.setFont(new Font("Verdana", Font.PLAIN, 20));
		s = "Monster: so weak, I was expecting more fun";
		graphics.drawString(s,(len-s.length()*10)/2, 150);
		graphics.setFont(new Font("Verdana", Font.PLAIN, 40));
		
		s = String.valueOf(time/60) + "minutes : " + String.valueOf(time%60 + "seconds");
		graphics.drawString(s,(len-s.length()*20)/2,32*8 );
		s = "GAME OVER!";
		graphics.drawString(s, (len-s.length()*20)/2, 32*10);
		s = "Press R to restart";
		graphics.drawString(s, (len-s.length()*20)/2, 32*18);
	}
	
	public void victoryScreen(Graphics graphics, long time){
		graphics.setColor(Color.WHITE);
		String s;
		graphics.setFont(new Font("Verdana", Font.PLAIN, 40));
		s = "Congratualations!!!";
		graphics.drawString(s, (len-s.length()*20)/2, 32*11);
		s = "You saved the castle!";
		graphics.drawString(s, (len-s.length()*20)/2, 32*15);
		s = "You defeated the boss in:";
		graphics.drawString(s,(len-s.length()*20)/2,32*7 );
		s = String.valueOf(time/60) + "minutes : " + String.valueOf(time%60 + "seconds");
		graphics.drawString(s,(len-s.length()*20)/2,32*8 );	
		s = "Press R to restart";
		graphics.drawString(s, (len-s.length()*20)/2, 32*18);
		
	}
	
	public void bestTime(Graphics graphics, long bt) {
		String s;
		graphics.setFont(new Font("Verdana", Font.PLAIN, 20));
		s = "Best time: " + String.valueOf(bt/60) + "minutes : " + String.valueOf(bt%60 + "seconds");
		graphics.drawString(s, (len-s.length()*10)/2, 32*2);
	}
	
	private int[] readDirection(char direction, int posX, int posY) {
		if (direction == 'u') {
			posY--; // ?
		} else if (direction == 'r') {
			posX++;
		} else if (direction == 'd') {
			posY++;
		} else {
			posX--;
		}
		int[] newCoordinates = {posX, posY}; 
		return newCoordinates;
	}
	
	private void createRay1(Graphics graphics, IFloor floor, char direction, int posHeroX, int posHeroY, ArrayList<IActor> actors) {
		int posX = posHeroX;
		int posY = posHeroY;
		while (true) {
			BufferedImage texture;
			texture = Textures.getTexture("corridor");
			graphics.drawImage(texture, offset(posX, posHeroX),
										offset(posY, posHeroY),
										texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM,
										null);
			if (Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("wall") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("chest") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("potion") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("door") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("error")) {
				texture = Textures.getTexture(floor.getTileId(posX, posY));
				graphics.drawImage(texture, offset(posX, posHeroX),
											offset(posY, posHeroY),
											texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM,
											null);
				if (texture == Textures.getTexture("wall") || texture == Textures.getTexture("door"))
					break;
			}
			for (IActor actor : actors) {
				if (actor.getPosX() == posX && actor.getPosY() == posY) {
					texture = Textures.getTexture(actor.getId());
					graphics.drawImage(texture, offset(posX, posHeroX),
							offset(posY, posHeroY),
							texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM,
							null);
				}
			}
			posX = readDirection(direction, posX, posY)[0];
			posY = readDirection(direction, posX, posY)[1];
		}
	}
	
	private void createRay11(Graphics graphics, IFloor floor, char direction1, char direction2, int posHeroX, int posHeroY, ArrayList<IActor> actors) {
		int posX = posHeroX;
		int posY = posHeroY;
		char[] instructions = {direction1, direction2};
		int i = 0;
		while (true) {
			BufferedImage texture;
			texture = Textures.getTexture("corridor");
			graphics.drawImage(texture, offset(posX, posHeroX),
										offset(posY, posHeroY),
										texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM,
										null);
			if (Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("wall") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("chest") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("potion") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("door") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("error")) {
				texture = Textures.getTexture(floor.getTileId(posX, posY));
				graphics.drawImage(texture, offset(posX, posHeroX),
											offset(posY, posHeroY),
											texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM,
											null);
				if (texture == Textures.getTexture("wall") || texture == Textures.getTexture("door"))
					break;
			}
			for (IActor actor : actors) {
				if (actor.getPosX() == posX && actor.getPosY() == posY) {
					texture = Textures.getTexture(actor.getId());
					graphics.drawImage(texture, offset(posX, posHeroX),
							offset(posY, posHeroY),
							texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM,
							null);
				}
			}
			posX = readDirection(instructions[i], posX, posY)[0];
			posY = readDirection(instructions[i], posX, posY)[1];
			i = (i + 1) % 2;
		}
	}
	
	private void createRay21(Graphics graphics, IFloor floor, char direction1, char direction2, int posHeroX, int posHeroY, ArrayList<IActor> actors) {
		int posX = posHeroX;
		int posY = posHeroY;
		char[] instructions = {direction1, direction1, direction2};
		int i = 0;
		while (true) {
			BufferedImage texture;
			texture = Textures.getTexture("corridor");
			graphics.drawImage(texture, offset(posX, posHeroX),
										offset(posY, posHeroY),
										texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM,
										null);
			if (Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("wall") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("chest") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("potion") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("door") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("error")) {
				texture = Textures.getTexture(floor.getTileId(posX, posY));
				graphics.drawImage(texture, offset(posX, posHeroX),
											offset(posY, posHeroY),
											texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM,
											null);
				if (texture == Textures.getTexture("wall") || texture == Textures.getTexture("door"))
					break;
			}
			for (IActor actor : actors) {
				if (actor.getPosX() == posX && actor.getPosY() == posY) {
					texture = Textures.getTexture(actor.getId());
					graphics.drawImage(texture, offset(posX, posHeroX),
							offset(posY, posHeroY),
							texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM,
							null);
				}
			}
			posX = readDirection(instructions[i], posX, posY)[0];
			posY = readDirection(instructions[i], posX, posY)[1];
			i = (i + 1) % 3;
		}
	}
	
	private void createRay12(Graphics graphics, IFloor floor, char direction1, char direction2, int posHeroX, int posHeroY, ArrayList<IActor> actors) {
		int posX = posHeroX;
		int posY = posHeroY;
		char[] instructions = {direction1, direction2, direction2};
		int i = 0;
		while (true) {
			BufferedImage texture;
			texture = Textures.getTexture("corridor");
			graphics.drawImage(texture, offset(posX, posHeroX),
										offset(posY, posHeroY),
										texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM,
										null);
			if (Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("wall") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("chest") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("potion") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("door") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("error")) {
				texture = Textures.getTexture(floor.getTileId(posX, posY));
				graphics.drawImage(texture, offset(posX, posHeroX),
											offset(posY, posHeroY),
											texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM,
											null);
				if (texture == Textures.getTexture("wall") || texture == Textures.getTexture("door"))
					break;
			}
			for (IActor actor : actors) {
				if (actor.getPosX() == posX && actor.getPosY() == posY) {
					texture = Textures.getTexture(actor.getId());
					graphics.drawImage(texture, offset(posX, posHeroX),
							offset(posY, posHeroY),
							texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM,
							null);
				}
			}
			posX = readDirection(instructions[i], posX, posY)[0];
			posY = readDirection(instructions[i], posX, posY)[1];
			i = (i + 1) % 3;
		}
	}
	private void createRay31(Graphics graphics, IFloor floor, char direction1, char direction2, int posHeroX, int posHeroY, ArrayList<IActor> actors) {
		int posX = posHeroX;
		int posY = posHeroY;
		char[] instructions = {direction1, direction1, direction1, direction2};
		int i = 0;
		while (true) {
			BufferedImage texture;
			texture = Textures.getTexture("corridor");
			graphics.drawImage(texture, offset(posX, posHeroX),
										offset(posY, posHeroY),
										texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM,
										null);
			if (Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("wall") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("chest") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("potion") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("door") ||
					Textures.getTexture(floor.getTileId(posX, posY)) == Textures.getTexture("error")) {
				texture = Textures.getTexture(floor.getTileId(posX, posY));
				graphics.drawImage(texture, offset(posX, posHeroX),
											offset(posY, posHeroY),
											texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM,
											null);
				if (texture == Textures.getTexture("wall") || texture == Textures.getTexture("door"))
					break;
			}
			for (IActor actor : actors) {
				if (actor.getPosX() == posX && actor.getPosY() == posY) {
					texture = Textures.getTexture(actor.getId());
					graphics.drawImage(texture, offset(posX, posHeroX),
							offset(posY, posHeroY),
							texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM,
							null);
				}
			}
			posX = readDirection(instructions[i], posX, posY)[0];
			posY = readDirection(instructions[i], posX, posY)[1];
			i = (i + 1) % 4;
		}
	}
	
	public void rayTracing (Graphics graphics, IFloor floor) {
		int posHeroX = floor.getHero().getPosX();
		int posHeroY = floor.getHero().getPosY();
		ArrayList<IActor> actors = floor.getActors();
		
		char[] directions = {'u', 'r', 'd', 'l'};
		for (int i = 0; i < 4; i++) {
			createRay1(graphics, floor, directions[i], posHeroX, posHeroY, actors);
			createRay11(graphics, floor, directions[i], directions[(i + 1) % 4], posHeroX, posHeroY, actors);
			createRay11(graphics, floor, directions[(i + 1) % 4], directions[i], posHeroX, posHeroY, actors);
			createRay21(graphics, floor, directions[i], directions[(i + 1) % 4], posHeroX, posHeroY, actors);
			createRay21(graphics, floor, directions[(i + 1) % 4], directions[i], posHeroX, posHeroY, actors);
			createRay12(graphics, floor, directions[i], directions[(i + 1) % 4], posHeroX, posHeroY, actors);
			createRay12(graphics, floor, directions[(i + 1) % 4], directions[i], posHeroX, posHeroY, actors);
			createRay31(graphics, floor, directions[i], directions[(i + 1) % 4], posHeroX, posHeroY, actors);
			createRay31(graphics, floor, directions[(i + 1) % 4], directions[i], posHeroX, posHeroY, actors);
		}
		
	}
	
	public void drawFloor(Graphics graphics, IFloor floor) {
		int posHeroX = floor.getHero().getPosX();
		int posHeroY = floor.getHero().getPosY();
		floor.setHeroTrail(posHeroX, posHeroY);	
		BufferedImage texture;
		for(int i = 0; i < floor.getHeight(); i++) {
			for (int j = 0; j < floor.getWidth(); j++) {
						if(floor.getHeroTrail(j, i) || floor.getHeroTrail(j, i + 1) || floor.getHeroTrail(j, i - 1)
													|| floor.getHeroTrail(j + 1, i) || floor.getHeroTrail(j - 1, i)
													|| floor.getHeroTrail(j + 1, i + 1) || floor.getHeroTrail(j + 1, i - 1)
													|| floor.getHeroTrail(j + 1, i + 1) || floor.getHeroTrail(j - 1, i + 1)
													|| floor.getHeroTrail(j - 1, i + 1) || floor.getHeroTrail(j - 1, i - 1)
													|| floor.getHeroTrail(j + 1, i - 1) || floor.getHeroTrail(j - 1, i - 1)) {
						
							
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
	
	public void heroStatus(Graphics graphics, IActor hero, long time) {
		graphics.setColor(Color.WHITE);
		graphics.drawRoundRect(10, 10, Constants.CELL_SIZE*4, Constants.CELL_SIZE*4-10, 10, 10);
		graphics.setFont(new Font("Verdana", Font.BOLD, 15));
		graphics.drawString(hero.getName(),20,25 );
		graphics.drawString(String.valueOf(time/60) + " : ",32*10-25,25 );
		graphics.drawString(String.valueOf(time%60),32*10+10,25 );
		graphics.drawString("HP:" + String.valueOf(hero.getHp()),20,40 );
		graphics.drawString("Speed: " + String.valueOf(hero.getSpeed()),20,55 );
		graphics.drawString("Damage: " + String.valueOf(hero.getDamage()),20,70);
		graphics.drawString("Armour: " + String.valueOf(hero.getArmour()),20,85);
		if(hero.isArmorIsEquipped()) {
			BufferedImage armourTexture = Textures.getTexture("shield");
			graphics.drawImage(armourTexture, 50 ,90, armourTexture.getWidth(), armourTexture.getHeight(), null);
			graphics.drawString(String.valueOf(hero.getArmourLevel()), 61,112);
		}
		
		if(hero.isWeaponIsEquipped()) {
			BufferedImage weaponTexture = Textures.getTexture("sword");
			graphics.drawImage(weaponTexture, 20 ,90, weaponTexture.getWidth(), weaponTexture.getHeight(), null);
			graphics.drawString(String.valueOf(hero.getWeaponLevel()), 32,112);
		}
		// graphics.drawString(String.valueof(), x, y)
		
		
		if (hero.countPotions()>0) {
			BufferedImage texture = Textures.getTexture("potion");
			graphics.drawImage(texture, 80 ,90, texture.getWidth(), texture.getHeight(), null);
			graphics.drawString(String.valueOf(hero.countPotions()), 91,116);
		}
		
	}
	
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
						if(floor.getHeroTrail(actor.getPosX(),actor.getPosY()) || floor.getHeroTrail(actor.getPosX(), actor.getPosY() + 1) || floor.getHeroTrail(actor.getPosX(), actor.getPosY() - 1)
								|| floor.getHeroTrail(actor.getPosX() + 1,actor.getPosY()) || floor.getHeroTrail(actor.getPosX() - 1,actor.getPosY())
								|| floor.getHeroTrail(actor.getPosX() + 1,actor.getPosY() + 1) || floor.getHeroTrail(actor.getPosX() + 1,actor.getPosY() - 1)
								|| floor.getHeroTrail(actor.getPosX() + 1,actor.getPosY() + 1) || floor.getHeroTrail(actor.getPosX() - 1,actor.getPosY() + 1)
								|| floor.getHeroTrail(actor.getPosX() - 1,actor.getPosY() + 1) || floor.getHeroTrail(actor.getPosX() - 1,actor.getPosY() - 1)
								|| floor.getHeroTrail(actor.getPosX() + 1,actor.getPosY() - 1) || floor.getHeroTrail(actor.getPosX() - 1,actor.getPosY() - 1)) {
								
							texture = Textures.getTexture(actor.getId());
							graphics.drawImage(texture, offset(actor.getPosX(),posHeroX),
									offset(actor.getPosY(),posHeroY) ,
				                    texture.getWidth()*Constants.ZOOM, texture.getHeight()*Constants.ZOOM, null);
						}
				}
			}
		}
	}

}

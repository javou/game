package view;

import javax.swing.JPanel;

import controller.God;
import controller.IGod;
import model.castle.Castle;
import model.castle.ICastleView;
import resources.Textures;

import java.awt.Color;
import java.awt.Graphics;


public class GameScreen extends JPanel implements IGameScreen{
	
	private static final long serialVersionUID = -1683041340076218616L;
	
	private static GameScreen gameScreen;
	private Renderer renderer;
	private static String message = "";
	private ICastleView castle;
	private IGod god = God.getInstance();
	private long startTime = 0;
	private long stopTime = 0;
	private boolean running = false;
	private static long bestTime = 999999999;
	
	private GameScreen() {
		super();
		this.addKeyListener(NextAction.getInstance());
		this.setFocusable(true);
		Textures.init();
		renderer = Renderer.getInstance();
		castle = Castle.getInstance();
	}
	
	public static GameScreen getInstance() {
		if (gameScreen == null) {
			gameScreen = new GameScreen();
		}
		return gameScreen;
	}
	
	public void restart() {
		castle = Castle.getInstance();
		god = God.getInstance();
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponents(graphics);
		try {
			if(god.getGameState() == 0) {
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
			renderer.firstScreen(graphics);
			}
			else if(god.getGameState() == 1) {
				if(!running) {
					this.startTime = System.currentTimeMillis();
					running = true;
				}
				graphics.setColor(Color.BLACK);
				graphics.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
				//renderer.drawFloor(graphics, castle.getCurrentFloor());
				//renderer.drawActors(graphics, castle.getCurrentFloor());
				renderer.rayTracing(graphics, castle.getCurrentFloor());
				renderer.messageBox(graphics, message);
				renderer.heroStatus(graphics, castle.getCurrentFloor().getHero(),(System.currentTimeMillis()-startTime)/1000);
			}
			else if(god.getGameState() == -1) {
				//gameover
				if(running) {
					this.stopTime = System.currentTimeMillis();
					this.running = false;
				}
				renderer.gameOverScreen(graphics, (stopTime - startTime)/1000);
				if(bestTime > 99999999)
					renderer.bestTime(graphics, 0);
				else
					renderer.bestTime(graphics, bestTime);
				
			}
			else if(god.getGameState() == 2) {
				//win
				if(running) {
					this.stopTime = System.currentTimeMillis();
					this.running = false;
				}
				renderer.victoryScreen(graphics, (stopTime - startTime)/1000);
				if((stopTime - startTime)/1000 < bestTime)
					bestTime = (stopTime - startTime)/1000;
				renderer.bestTime(graphics, bestTime);
				
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		repaint();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		GameScreen.message = message;
	}
}

package view;

import javax.swing.JPanel;

import controller.God;
import controller.NextAction;
import model.castle.Castle;
import model.castle.ICastleView;
import resources.Textures;

import java.awt.Color;
import java.awt.Graphics;


public class GameScreen extends JPanel implements IGameScreen{
	
	private static final long serialVersionUID = -1683041340076218616L;
	
	private static GameScreen gameScreen;
	private Renderer renderer;
	private static boolean printOnce = true;
	private static String message = "";
	private ICastleView castle;
	private God god = God.getInstance();
	private long startTime = 0;
	private long stopTime = 0;
	private boolean running = false;
	
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
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponents(graphics);
		
		try {
			System.out.println(god.getGameState());
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
				renderer.drawFloor(graphics, castle.getCurrentFloor());
				renderer.drawActors(graphics, castle.getCurrentFloor());
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
				
			}
			else if(god.getGameState() == 2) {
				//win
				if(running) {
					this.stopTime = System.currentTimeMillis();
					this.running = false;
				}
				renderer.victoryScreen(graphics, (stopTime - startTime)/1000);
				
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		repaint();
	}

	public void disablePrintOnce() {
		printOnce = false;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		GameScreen.message = message;
	}
}

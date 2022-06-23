package view;

import javax.swing.JPanel;

import controller.NextAction;
import model.castle.Castle;
import model.castle.ICastleView;
import resources.Textures;

import java.awt.Color;
import java.awt.Graphics;

public class GameScreen extends JPanel {
	
	private static final long serialVersionUID = -1683041340076218616L;
	
	private static GameScreen gameScreen;
	private Renderer renderer;
	private static boolean printOnce = false;
	private static String message = "";
	private ICastleView castle;
	
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

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponents(graphics);
		
		try {
			if(printOnce) {
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
			renderer.firstScreen(graphics);
			}
			else {
				graphics.setColor(Color.BLACK);
				graphics.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
				renderer.drawFloor(graphics, castle.getCurrentFloor());
				renderer.drawActors(graphics, castle.getCurrentFloor());
				renderer.messageBox(graphics, message);
				renderer.heroStatus(graphics, castle.getCurrentFloor().getHero());
				
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

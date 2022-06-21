package view;

import javax.swing.JPanel;

import controller.God;
import controller.NextAction;
import resources.Constants;
import resources.Textures;

import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class GameScreen extends JPanel implements IGameScreen{
	private static final long serialVersionUID = -1683041340076218616L;
	private Renderer renderer;
	private static boolean printOnce = false;
	private static String message = "";
	private God god;
	
	public GameScreen() {
		super();
		this.addKeyListener(new NextAction());
		this.setFocusable(true);
		Textures.init();
		renderer = Renderer.getInstance();
		god = God.getInstance();
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
				renderer.drawFloor(graphics, god.getCastle().getCurrentFloor(), god.getHero());
				renderer.drawMonsters(graphics, god.getCastle().getCurrentFloor(), god.getHero());
				renderer.messageBox(graphics, message);
				renderer.heroStatus(graphics, god.getHero());
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		repaint();
	}

	public static void disablePrintOnce() {
		printOnce = false;
	}

	public static String getMessage() {
		return message;
	}

	public static void setMessage(String message) {
		GameScreen.message = message;
	}
	

}

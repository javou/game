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
	private Renderer renderer;
	private static boolean printOnce = true;
	private static String message = "";
	
	public GameScreen() {
		super();
		this.addKeyListener(new NextAction());
		this.setFocusable(true);
		Textures.init();
		renderer = new Renderer();
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
				renderer.drawFloor(graphics, God.getCastle().getCurrentFloor());
				renderer.messageBox(graphics, message);
				renderer.heroStatus(graphics);
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

package view;

import java.awt.*;
import javax.swing.JFrame;

import resources.Constants;

public class Window implements IWindow {
	public static final int HEIGHT = Constants.CELL_NUMBER * Constants.CELL_SIZE;
	public static final int WIDTH = Constants.CELL_NUMBER * Constants.CELL_SIZE;
	private static JFrame jframe;
	private static Window window;
	
	private Window() {};
	
	public static Window getInstance() {
		if(window == null)
			window = new Window();
		return window;
	}
		
	public void create() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Haunted Castle");
		jframe.setBounds(((int)size.getWidth()-WIDTH)/2, ((int)size.getHeight()-HEIGHT)/2 -32, WIDTH, HEIGHT+32);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(GameScreen.getInstance());
		jframe.setVisible(true);
		jframe.setResizable(false);
	}
}

package view;
import java.awt.*;
import javax.swing.JFrame;

public class Window {
	public static final int HEIGHT = 32*20;
	public static final int WIDTH = 32*20;
	private static JFrame window;
	public static void create() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		window = new JFrame("Haunted Castle");
		window.setBounds(((int)size.getWidth()-WIDTH)/2, ((int)size.getHeight()-HEIGHT)/2 -32, WIDTH, HEIGHT+32);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(new GameScreen());
		window.setVisible(true);
		window.setResizable(false);
	}
}

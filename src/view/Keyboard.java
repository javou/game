package view;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controller.God;
import model.actor.Hero;
public class Keyboard implements  KeyListener {
	private static boolean keys[] = new boolean[256];//is it bigger enough to store all keys?
	private static int delayToMoveSmoothly = 0; // is it the best way?
	private static final int DELAY = 00;
	public int count =0;
	public Keyboard() {
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		 if(e.getKeyCode() == KeyEvent.VK_LEFT)
				Renderer.setPosHero(-1,0);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	public static boolean isKeyPressed(int keyCode) {
		boolean status;
		if(keys[keyCode] == true && delayToMoveSmoothly <= 0) {
			delayToMoveSmoothly = DELAY;
			keys[keyCode] = false;
			status = true;
		}
		else {
			delayToMoveSmoothly --;
			status = false;
		}
		return status;
	}
}
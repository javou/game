package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NextAction implements KeyListener {
	private static NextAction nextAction;
	private int key;
	
	private NextAction() {}
	
	public static NextAction getInstance() {
		if (nextAction == null) {
			nextAction = new NextAction();
		}
		return nextAction;
	}
	
	public int getKey() {	
		int aux = key;
		key = 0; // invalid key
		return aux;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		key = e.getKeyCode();
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
}
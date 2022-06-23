package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NextAction implements KeyListener {
	private static NextAction nextAction;
	private boolean heroIsReady;
	private int key;
	
	private NextAction() {}
	
	public static NextAction getInstance() {
		if (nextAction == null) {
			nextAction = new NextAction();
		}
		return nextAction;
	}
	
	public int getKey() {	
		return key;
	}
	
	public void setHeroIsReady(boolean heroIsReady) {
		this.heroIsReady = heroIsReady;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(heroIsReady) {
			key = e.getKeyCode();
			if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_SPACE)
				heroIsReady = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
}
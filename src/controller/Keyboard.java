package controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.actor.Hero;
import resources.Music;
import view.GameScreen;
import view.Renderer;
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
		//keys[e.getKeyCode()] = true;
		 
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//keys[e.getKeyCode()] = false;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			Renderer.setPosHero(-1,0);
			God.moveHero();
			GameScreen.setMessage("Up là!");
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			God.moveHero();
			GameScreen.setMessage("Up là!");
			
			Music.setSong("songs/boss.wav");
			Renderer.setPosHero(1,0);
			//Music.loop();
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP) {
			God.moveHero();
			GameScreen.setMessage(null);
			Renderer.setPosHero(0,-1);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			God.moveHero();
			Renderer.setPosHero(0,1);
		}
		else if(e.getKeyCode() == KeyEvent.VK_E) {
			 //equip an item
		}
		else if(e.getKeyCode() == KeyEvent.VK_F) {
			 //use potion
		}
		else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			 //attack
			Music.setSong("songs/attack.wav");
		}
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
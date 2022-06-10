package controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.actor.Hero;
import resources.Music;
import view.GameScreen;
import view.Renderer;
public class NextAction implements  KeyListener {
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
		
		
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			Renderer.setPosHero(-1,0);
			God.moveHero();
			//GameScreen.setMessage("Up là!");
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			God.moveHero();
			//GameScreen.setMessage("Up là!");
			
			//Music.setSong("songs/boss.wav");
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
			//Music.setSong("songs/attack.wav");
		}
		God.checkInteractionsWithObjects();
	}
}
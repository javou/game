package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import resources.Music;
import view.GameScreen;
import view.Keyboard;
import view.Renderer;

public class NextAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(Keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
			God.moveHero();
			GameScreen.setMessage("Up là!");
			
			Music.setSong("songs/boss.wav");
			
			//Music.loop();
		}
		else if(Keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
			God.moveHero();
			GameScreen.setMessage("Up là!");

		}
		else if(Keyboard.isKeyPressed(KeyEvent.VK_UP)) {
			God.moveHero();
			GameScreen.setMessage(null);
		}
		else if(Keyboard.isKeyPressed(KeyEvent.VK_DOWN)) {
			God.moveHero();
		}
		else if(Keyboard.isKeyPressed(KeyEvent.VK_E)) {
			 //equip an item
		}
		else if(Keyboard.isKeyPressed(KeyEvent.VK_F)) {
			 //use potion
		}
		else if(Keyboard.isKeyPressed(KeyEvent.VK_ENTER)) {
			 //attack
			
			Music.setSong("songs/attack.wav");
			
		}
	}

}

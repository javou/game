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
			Renderer.setPosHero(1,0);
			//Music.loop();
		}
		else if(Keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
			God.moveHero();
			GameScreen.setMessage("Up là!");
			//Renderer.setPosHero(-1,0);
			

		}
		else if(Keyboard.isKeyPressed(KeyEvent.VK_UP)) {
			God.moveHero();
			GameScreen.setMessage(null);
			Renderer.setPosHero(0,-1);
		}
		else if(Keyboard.isKeyPressed(KeyEvent.VK_DOWN)) {
			God.moveHero();
			Renderer.setPosHero(0,1);
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

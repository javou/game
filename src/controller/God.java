package controller;

import javax.swing.Timer;

import resources.Music;
import view.GameScreen;

public class God {

	public static void newWorld() {
		//create hero, castle, monster....
		Timer timer = new Timer(10, new NextAction());
		timer.start();
		
	}
	public static void moveHero() {
		GameScreen.disablePrintOnce();
		
		
	}
	public static void handleInteraction() {
		
	}

}

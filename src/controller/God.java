package controller;

import javax.swing.Timer;

import model.castle.Castle;
import view.GameScreen;

public class God {
	private static Castle castle;
	
	public static void newWorld() {
		//create hero, castle, monster....
		castle = new Castle();
		Timer timer = new Timer(10, new NextAction());
		timer.start();
		
	}
	public static void moveHero() {
		GameScreen.disablePrintOnce();
		
		
	}
	public static void handleInteraction() {
		
	}
	public static Castle getCastle() {
		return castle;
	}
	

}

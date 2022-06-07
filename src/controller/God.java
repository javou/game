package controller;

import javax.swing.Timer;

import model.castle.Castle;
import view.GameScreen;
import view.Renderer;

public class God {
	private static Castle castle;
	public static void newWorld() {
		//create hero, castle, monster....
		castle = new Castle();

		
	}
	public static void moveHero() {
		GameScreen.setMessage("");//always reset
		GameScreen.disablePrintOnce();
		
		
	}
	public static void handleInteraction() {
		
	}
	public static Castle getCastle() {
		return castle;
	}
	public static void checkInteractionsWithObjects() {
		//get hero position
		//create a method in floor (is there a item in)
		if(castle != null)
			castle.getCurrentFloor().checkInteractions(Renderer.posHeroX , Renderer.posHeroY );
	}
	

}

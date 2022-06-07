package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.Timer;

import model.actor.Actor;
import model.castle.Castle;
import view.GameScreen;
import view.Renderer;

public class God {
	private ArrayList<Actor> actors = new ArrayList<Actor>();
	private static Castle castle;
	
	public static void newWorld() {
		//create hero, castle, monster....
		castle = new Castle();

		
	}
	
	private void gameLoop() {
		Collections.sort(actors, new Comparator<Actor>() {
			@Override
			public int compare(Actor actor1, Actor actor2) {
				return actor1.getSpeed() > actor2.getSpeed() ? -1 : actor1.getSpeed() == actor2.getSpeed() ? 0 : 1; 
 			}
		}); // ordena a lista com base no atributo speed dos atores.
		
		// loop
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
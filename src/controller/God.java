package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import exception.*;
import model.actor.Actor;
import model.actor.Hero;
import model.castle.Castle;
import view.GameScreen;
import view.Renderer;

public class God { // montador + controle // separar?
	// ambos
	private static ArrayList<Actor> actors = new ArrayList<Actor>();
	private static Castle castle;
	
	// montador
	public static void newWorld() {
		//create hero, castle, monster....
		castle = new Castle();
	}
	
	// controle
	public static void gameLoop() {
		Collections.sort(actors, new Comparator<Actor>() {
			@Override
			public int compare(Actor actor1, Actor actor2) {
				return actor1.getSpeed() > actor2.getSpeed() ? -1 : actor1.getSpeed() == actor2.getSpeed() ? 0 : 1; 
 			}
		}); // ordena a lista com base no atributo speed dos atores.
		
		int i = 0;
		while (true) { // mudar condição.
			Actor actor = actors.get(i);
			actor.setEnergy(actor.getEnergy() + actor.getSpeed() * 10);
			if (actor.getEnergy() >= 1000) {
				while (true) {
					try {
						act(actor);
						break;
					} catch (InvalidMovement e) {
						e.printStackTrace();
					}
				}
				actor.setEnergy(actor.getEnergy() - 1000);
			}
			i = (i + 1) % actors.size();
		}
	}
	
	private static void act(Actor actor) throws InvalidMovement {
		if (actor.getClass() == Hero.class) {
			NextAction.setHeroIsReady(true);
			int action = NextAction.getKey();
			readAction(action, actor);
		} else {
			// executar melhor ação.
		}
	}
	
	private static void readAction(int action, Actor actor) throws InvalidMovement {
		if (action == 87) 
			moveActorUp(actor);
		else if (action == 65)
			moveActorLeft(actor);
		else if (action == 83)
			moveActorDown(actor);
		else if (action == 68)
			moveActorRight(actor);
	}
	
	private static void moveActorUp(Actor actor) throws InvalidMovement {
		int xAtual = actor.getPosX(); // WET?
		int yAtual = actor.getPosY();
		if (castle.getCurrentFloor().getTile(xAtual, yAtual - 1).isOccupiableSpace()) {
			actor.setPosY(yAtual - 1);
		} else {
			throw new exception.InvalidMovement("Célula ocupada!"); 
		}
	}
	
	private static void moveActorLeft(Actor actor) throws InvalidMovement {
		int xAtual = actor.getPosX();
		int yAtual = actor.getPosY();
		if (castle.getCurrentFloor().getTile(xAtual - 1, yAtual).isOccupiableSpace()) {
			actor.setPosX(xAtual - 1);
		} else {
			throw new exception.InvalidMovement("Célula ocupada!"); 
		}
	}
	
	private static void moveActorDown(Actor actor) throws InvalidMovement {
		int xAtual = actor.getPosX();
		int yAtual = actor.getPosY();
		if (castle.getCurrentFloor().getTile(xAtual, yAtual + 1).isOccupiableSpace()) {
			actor.setPosY(yAtual + 1);
		} else {
			throw new exception.InvalidMovement("Célula ocupada!"); 
		}
	}
	
	private static void moveActorRight(Actor actor) throws InvalidMovement {
		int xAtual = actor.getPosX();
		int yAtual = actor.getPosY();
		if (castle.getCurrentFloor().getTile(xAtual + 1, yAtual).isOccupiableSpace()) {
			actor.setPosX(xAtual + 1);
		} else {
			throw new exception.InvalidMovement("Célula ocupada!"); 
		}
	}
	
	
	// teste
	public static void moveHero() {
		GameScreen.setMessage("");//always reset
		GameScreen.disablePrintOnce();
	}
	
	public static Castle getCastle() {
		return castle;
	}
	
	public static void checkInteractionsWithObjects() {
		//get hero position
		//create a method in floor (is there a item in)
		if(castle != null)
			if(castle.getCurrentFloor().checkDoor(Renderer.posHeroX , Renderer.posHeroY ))
				castle.moveHeroNextFloor();	
	}
	

}

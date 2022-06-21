package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import exception.*;
import model.actor.Actor;
import model.actor.Hero;
import model.castle.Castle;
import model.castle.Floor;
import view.GameScreen;

public class God { // montador + controle // separar
	// ambos
	private static God god; //lazy way, singleton pattern
	private static ArrayList<Actor> actors = new ArrayList<Actor>(); // get from floor (or use castle.getFloor().getActors() every time?)
	// private Assembler assembler = Assembler.getInstance();
	private static Castle castle; // private ICastle castle = assembler.getCastle();
	private static Hero hero; // remover
	
	private God() {};
	
	public static God getInstance() { 
		if (god == null)
			god = new God();
		return god;
	}
	
	public Hero getHero() { // remover
		return hero;
	}

	// montador
	public void newWorld() { // remover
		//create hero, castle, monster....
		castle = new Castle();
		hero = new Hero();
		actors.add(hero);
	}
	
	// controle
	public void gameLoop() {
		Collections.sort(actors, new Comparator<Actor>() {
			@Override
			public int compare(Actor actor1, Actor actor2) {
				return actor1.getSpeed() > actor2.getSpeed() ? -1 : actor1.getSpeed() == actor2.getSpeed() ? 0 : 1; 
 			}
		}); // ordena a lista com base no atributo speed dos atores.
		
		int i = 0;
		while (true) { // mudar condi��o.
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
			// executar melhor a��o.
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
		if (castle.getCurrentFloor().getTile(actor.getPosX(), actor.getPosY() - 1).isOccupiableSpace()) {
			actor.setPosY(actor.getPosY() - 1);
		} else { // if there is a monster: attack; else:
			throw new exception.InvalidMovement("C�lula ocupada!"); 
		}
	}
	
	private static void moveActorLeft(Actor actor) throws InvalidMovement {
		if (castle.getCurrentFloor().getTile(actor.getPosX() - 1, actor.getPosY()).isOccupiableSpace()) {
			actor.setPosX(actor.getPosX() - 1);
		} else {
			throw new exception.InvalidMovement("C�lula ocupada!"); 
		}
	}
	
	private static void moveActorDown(Actor actor) throws InvalidMovement {
		if (castle.getCurrentFloor().getTile(actor.getPosX(), actor.getPosY() + 1).isOccupiableSpace()) {
			actor.setPosY(actor.getPosY() + 1);
		} else {
			throw new exception.InvalidMovement("C�lula ocupada!"); 
		}
	}
	
	private static void moveActorRight(Actor actor) throws InvalidMovement {
		if (castle.getCurrentFloor().getTile(actor.getPosX() + 1, actor.getPosY()).isOccupiableSpace()) {
			actor.setPosX(actor.getPosX() + 1);
		} else {
			throw new exception.InvalidMovement("C�lula ocupada!"); 
		}
	}

	// teste
	public static void moveHero() {
		GameScreen.setMessage("");//always reset
		GameScreen.disablePrintOnce();
	}
	
	public Castle getCastle() {
		return castle;
	}
	
	//if we want to that the player has the decision power whether take it or not we should put this method together with keyboard method
	public static void checkInteractionsWithObjects() {
		//get hero position
		//create a method in floor (is there a item in)
		if(castle != null)
			if(castle.getCurrentFloor().lookFor(hero.getPosX(), hero.getPosY(), "door" )) {
				castle.moveHeroNextFloor();
				//set the initial position of the hero when he moves to the next floor
				Floor f = castle.getCurrentFloor();
				for(int y = 0; y < f.getHeight(); y++) {
					for(int x = 0; x < f.getWidth(); x++) {
						if(f.getTile(x, y).getId() == "hero") {
							hero.setPosX(x);
							hero.setPosY(y);
							break;			
						}	
					}
				}
			}
		if(castle.getCurrentFloor().lookFor(hero.getPosX(), hero.getPosY(), "potion" )) {
			castle.getCurrentFloor().removeItem(hero.getPosX(), hero.getPosY());
			hero.addPotion();
		}
	}
	
	


	

}

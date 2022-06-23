package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import exception.*;
import model.actor.Hero;
import model.actor.IActor;
import model.castle.ICastle;

public class God {
	private static God god; //lazy way, singleton pattern
	private Assembler assembler = Assembler.getInstance();
	private ICastle castle = assembler.getCastle();
	private NextAction nextAction = NextAction.getInstance();
	private ArrayList<IActor> floorActors = castle.getFloorActors();
	
	private God() {};
	
	public static God getInstance() { 
		if (god == null)
			god = new God();
		return god;
	}
	
	public void gameLoop() {
		Collections.sort(floorActors, new Comparator<IActor>() {
			@Override
			public int compare(IActor actor1, IActor actor2) {
				return actor1.getSpeed() > actor2.getSpeed() ? -1 : actor1.getSpeed() == actor2.getSpeed() ? 0 : 1; 
 			}
		}); // ordena a lista com base no atributo speed dos atores.
		
		int i = 0;
		while (true) { // mudar condição.
			IActor actor = floorActors.get(i);
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
			i = (i + 1) % floorActors.size();
		}
	}
	
	private void act(IActor actor) throws InvalidMovement {
		if (actor.getClass() == Hero.class) {
			nextAction.setHeroIsReady(true);
			int action = nextAction.getKey();
			readAction(action, actor);
		} else {
			// executar melhor ação.
		}
	}
	
	private void readAction(int action, IActor actor) throws InvalidMovement {
		if (action == 87) 
			moveActorUp(actor);
		else if (action == 65)
			moveActorLeft(actor);
		else if (action == 83)
			moveActorDown(actor);
		else if (action == 68)
			moveActorRight(actor);
		else if (action == 32);
			holdPosition(actor); // pass turn
	}
	
	private void moveActorUp(IActor actor) throws InvalidMovement {
		if (castle.isTileAtCurrentFloorOccupiable(actor.getPosX(), actor.getPosY() - 1)) { // item and door.
			actor.setPosY(actor.getPosY() - 1);
		} else { // if there is an enemy: attack; else:
			throw new exception.InvalidMovement("Você não é um fantasma!"); 
		}
	}
	
	private void moveActorLeft(IActor actor) throws InvalidMovement {
		if (castle.isTileAtCurrentFloorOccupiable(actor.getPosX() - 1, actor.getPosY())) {
			actor.setPosX(actor.getPosX() - 1);
		} else {
			throw new exception.InvalidMovement("Você não é um fantasma!"); 
		}
	}
	
	private void moveActorDown(IActor actor) throws InvalidMovement {
		if (castle.isTileAtCurrentFloorOccupiable(actor.getPosX(), actor.getPosY() + 1)) {
			actor.setPosY(actor.getPosY() + 1);
		} else {
			throw new exception.InvalidMovement("Você não é um fantasma!"); 
		}
	}
	
	private void moveActorRight(IActor actor) throws InvalidMovement {
		if (castle.isTileAtCurrentFloorOccupiable(actor.getPosX() + 1, actor.getPosY())) {
			actor.setPosX(actor.getPosX() + 1);
		} else {
			throw new exception.InvalidMovement("Você não é um fantasma!"); 
		}
	}
	
	private void holdPosition(IActor actor) {}
	
	/*
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
	*/


	

}

package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import exception.*;
import model.actor.Hero;
import model.actor.IActor;
import model.castle.ICastleController;

public class God {
	private static God god; //lazy way, singleton pattern
	private Assembler assembler = Assembler.getInstance();
	private ICastleController castle = assembler.getCastle();
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
		while (true) { // mudar condi��o. // hp > 0; bossAlive.
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
			while (true) {
				try {
					int action = nextAction.getKey();
					readAction(action, actor);
					break;
				} catch (InvalidKey e) {
					// e.printStackTrace();
				}
			}
		} else {
			// executar melhor a��o.
		}
	}
	
	private void readAction(int action, IActor actor) throws InvalidMovement, InvalidKey {
		if (action == 87) 
			moveActorUp(actor);
		else if (action == 65)
			moveActorLeft(actor);
		else if (action == 83)
			moveActorDown(actor);
		else if (action == 68)
			moveActorRight(actor);
		else if (action == 32)
			holdPosition(actor); // pass turn
		else if (action == 81)
			consumePotion();
		else
			throw new exception.InvalidKey("Tecla inválida.");
	}
	
	private void moveActorUp(IActor actor) throws InvalidMovement {
		if (castle.isTileAtCurrentFloorOccupiable(actor.getPosX(), actor.getPosY() - 1)) {
			actor.setPosY(actor.getPosY() - 1);
			interactWithDestination(actor.getPosX(), actor.getPosY());
		} else { // if there is an enemy: attack; else:
			if (castle.getActorAtTile(actor.getPosX(), actor.getPosY()) != null)
				attack(actor, castle.getActorAtTile(actor.getPosX(), actor.getPosY()));
			else
				throw new exception.InvalidMovement("Voc� n�o � um fantasma!"); 
		}
	}
	
	private void moveActorLeft(IActor actor) throws InvalidMovement {
		if (castle.isTileAtCurrentFloorOccupiable(actor.getPosX() - 1, actor.getPosY())) {
			actor.setPosX(actor.getPosX() - 1);
			interactWithDestination(actor.getPosX(), actor.getPosY());
		} else {
			if (castle.getActorAtTile(actor.getPosX(), actor.getPosY()) != null)
				attack(actor, castle.getActorAtTile(actor.getPosX(), actor.getPosY()));
			else
				throw new exception.InvalidMovement("Voc� n�o � um fantasma!"); 
		}
	}
	
	private void moveActorDown(IActor actor) throws InvalidMovement {
		if (castle.isTileAtCurrentFloorOccupiable(actor.getPosX(), actor.getPosY() + 1)) {
			actor.setPosY(actor.getPosY() + 1);
			interactWithDestination(actor.getPosX(), actor.getPosY());
		} else {
			if (castle.getActorAtTile(actor.getPosX(), actor.getPosY()) != null)
				attack(actor, castle.getActorAtTile(actor.getPosX(), actor.getPosY()));
			else
				throw new exception.InvalidMovement("Voc� n�o � um fantasma!"); 
		}
	}
	
	private void moveActorRight(IActor actor) throws InvalidMovement {
		if (castle.isTileAtCurrentFloorOccupiable(actor.getPosX() + 1, actor.getPosY())) {
			actor.setPosX(actor.getPosX() + 1);
			interactWithDestination(actor.getPosX(), actor.getPosY());
		} else {
			if (castle.getActorAtTile(actor.getPosX(), actor.getPosY()) != null)
				attack(actor, castle.getActorAtTile(actor.getPosX(), actor.getPosY()));
			else
				throw new exception.InvalidMovement("Voc� n�o � um fantasma!"); 
		}
	}
	
	private void holdPosition(IActor actor) {}
	
	private void consumePotion() {}
	
	private void attack(IActor attacker, IActor target) {
		target.setHp(target.getHp() - calculateDamage(attacker, target));
	}
	
	private int calculateDamage(IActor attacker, IActor target) {
		return attacker.getDamage() - target.getArmour();
	}
	
	private void moveHeroNextFloor() {
		castle.updateCurrentFloor();
		floorActors = castle.getFloorActors();
	}
	
	// teste
	/*
	public static void moveHero() {
		GameScreen.setMessage("");//always reset
		GameScreen.disablePrintOnce();
	}
	*/
	

	private void interactWithDestination(int x, int y) {
		if (castle.typeAtTileInCurrentFloor(x, y) == "door") {
			moveHeroNextFloor();
		}
		else if(castle.typeAtTileInCurrentFloor(x, y) == "potion" ) {
			castle.removeItemAtCurrentFloor(x, y);
			// hero.addPotion();
		}
	}
}

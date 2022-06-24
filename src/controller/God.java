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
		while (true) { // mudar condiï¿½ï¿½o. // hp > 0; bossAlive.
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
			// executar melhor aï¿½ï¿½o.
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
			actor.usePotion();
		else
			throw new exception.InvalidKey("Tecla invÃ¡lida");
	}
	
	private void moveActorUp(IActor actor) throws InvalidMovement {
		if (castle.isTileAtCurrentFloorOccupiable(actor.getPosX(), actor.getPosY() - 1)) { // make new tile unoccupiable and the previous one occupiable
			actor.setPosY(actor.getPosY() - 1);
			castle.setTileAtCurrentFloorOccupiable(actor.getPosX(), actor.getPosY() + 1, true);
			castle.setTileAtCurrentFloorOccupiable(actor.getPosX(), actor.getPosY(), false);
			interactWithDestination(actor.getPosX(), actor.getPosY(),actor);
		} else { // if there is an enemy: attack; else:
			if (castle.getActorAtTile(actor.getPosX(), actor.getPosY() - 1) != null)
				attack(actor, castle.getActorAtTile(actor.getPosX(), actor.getPosY() - 1));
			else
				throw new exception.InvalidMovement("Movimento inválido"); 
		}
	}
	
	private void moveActorLeft(IActor actor) throws InvalidMovement {
		if (castle.isTileAtCurrentFloorOccupiable(actor.getPosX() - 1, actor.getPosY())) {
			actor.setPosX(actor.getPosX() - 1);
			castle.setTileAtCurrentFloorOccupiable(actor.getPosX() + 1, actor.getPosY(), true);
			castle.setTileAtCurrentFloorOccupiable(actor.getPosX(), actor.getPosY(), false);
			interactWithDestination(actor.getPosX(), actor.getPosY(),actor);
		} else {
			if (castle.getActorAtTile(actor.getPosX() - 1, actor.getPosY()) != null)
				attack(actor, castle.getActorAtTile(actor.getPosX() - 1, actor.getPosY()));
			else
				throw new exception.InvalidMovement("Movimento inválido"); 
		}
	}
	
	private void moveActorDown(IActor actor) throws InvalidMovement {
		if (castle.isTileAtCurrentFloorOccupiable(actor.getPosX(), actor.getPosY() + 1)) {
			actor.setPosY(actor.getPosY() + 1);
			castle.setTileAtCurrentFloorOccupiable(actor.getPosX(), actor.getPosY() - 1, true);
			castle.setTileAtCurrentFloorOccupiable(actor.getPosX(), actor.getPosY(), false);
			interactWithDestination(actor.getPosX(), actor.getPosY() + 1, actor);
		} else {
			if (castle.getActorAtTile(actor.getPosX(), actor.getPosY() + 1) != null)
				attack(actor, castle.getActorAtTile(actor.getPosX(), actor.getPosY() + 1));
			else
				throw new exception.InvalidMovement("Movimento inválido"); 
		}
	}
	
	private void moveActorRight(IActor actor) throws InvalidMovement {
		if (castle.isTileAtCurrentFloorOccupiable(actor.getPosX() + 1, actor.getPosY())) {
			actor.setPosX(actor.getPosX() + 1);
			castle.setTileAtCurrentFloorOccupiable(actor.getPosX() - 1, actor.getPosY(), true);
			castle.setTileAtCurrentFloorOccupiable(actor.getPosX(), actor.getPosY(), false);
			interactWithDestination(actor.getPosX() + 1, actor.getPosY(), actor);
		} else {
			if (castle.getActorAtTile(actor.getPosX() + 1, actor.getPosY()) != null)
				attack(actor, castle.getActorAtTile(actor.getPosX() + 1, actor.getPosY()));
			else
				throw new exception.InvalidMovement("Movimento inválido"); 
		}
	}
	
	private void holdPosition(IActor actor) {}
	
	
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
	

	private void interactWithDestination(int x, int y, IActor actor) {
		if (castle.typeAtTileInCurrentFloor(x, y) == "door") {
			moveHeroNextFloor();
		}
		else if(castle.typeAtTileInCurrentFloor(x, y) == "potion" ) {
			castle.removeItemAtCurrentFloor(x, y);
			actor.addPotion();
		}
		else if(castle.typeAtTileInCurrentFloor(x, y) == "chest" ) {
			castle.removeItemAtCurrentFloor(x, y);
			if(Math.random() < 0.5) { //better weapon, +damage
				// actor.setWeaponIsEquipped(true);
				actor.improveDamage();
			}
			else { //better armor, +defense
				// actor.setArmorIsEquipped(true);
				actor.improveArmour();
			}
		}
	}
}

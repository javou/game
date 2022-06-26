package controller;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import exception.*;
import model.actor.Hero;
import model.actor.IActor;
import model.castle.ICastleController;
import view.GameScreen;
import view.IGameScreen;

public class God {
	private static God god; //lazy way, singleton pattern
	private Assembler assembler = Assembler.getInstance();
	private ICastleController castle = assembler.getCastle();
	private NextAction nextAction = NextAction.getInstance();
	private ArrayList<IActor> floorActors = castle.getFloorActors();
	private IActor hero = castle.getHero();
	private int gameState = 0; //0 initial screen, -1 hero is dead, 1 playing, 2 boss is defeated
	
	private God() {};
	
	public static God getInstance() { 
		if (god == null)
			god = new God();
		return god;
	}
	
	public int getGameState() {
		return gameState;
	}
	
	

	public void setGameState(int gameState) {
		this.gameState = gameState;
	}

	private void sortFloorActors() {
		Collections.sort(floorActors, new Comparator<IActor>() {
			@Override
			public int compare(IActor actor1, IActor actor2) {
				return actor1.getSpeed() > actor2.getSpeed() ? -1 : actor1.getSpeed() == actor2.getSpeed() ? 0 : 1; 
 			}
		});
	}
	
	public void nameHero() {
		int key = 0;
		IGameScreen gs = GameScreen.getInstance(); // remove?
		while(key != KeyEvent.VK_ENTER) {
			key = nextAction.getKey();
			if(key == KeyEvent.VK_BACK_SPACE)
				hero.removeLetterName();
			else if(key >= 65 && key <= 90) {
				hero.setName(KeyEvent.getKeyText(key));
			}
			System.out.print("");//it needs something here to make the rendering works
		}
		setGameState(1);	
	}
	
	public void gameLoop() {
		sortFloorActors();
		
		int i = 0;
		
		
		while (gameState == 1) { // mudar condi��o. // hp > 0; bossAlive.
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
			if (hero.getHp() <= 0)
				gameState = -1;
		}
		if (gameState == -1) {
			// gameOver
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
			enemyPathFinding(actor.getPosX(), actor.getPosY(), actor);
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
		else if (action == 32) {}
		else if (action == 81)
			actor.usePotion();
		else
			throw new exception.InvalidKey("Tecla inválida");
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
				throw new exception.InvalidMovement("Movimento inv�lido"); 
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
				throw new exception.InvalidMovement("Movimento inv�lido"); 
		}
	}
	
	private void moveActorDown(IActor actor) throws InvalidMovement {
		if (castle.isTileAtCurrentFloorOccupiable(actor.getPosX(), actor.getPosY() + 1)) {
			actor.setPosY(actor.getPosY() + 1);
			castle.setTileAtCurrentFloorOccupiable(actor.getPosX(), actor.getPosY() - 1, true);
			castle.setTileAtCurrentFloorOccupiable(actor.getPosX(), actor.getPosY(), false);
			interactWithDestination(actor.getPosX(), actor.getPosY(), actor);
		} else {
			if (castle.getActorAtTile(actor.getPosX(), actor.getPosY() + 1) != null)
				attack(actor, castle.getActorAtTile(actor.getPosX(), actor.getPosY() + 1));
			else
				throw new exception.InvalidMovement("Movimento inv�lido"); 
		}
	}
	
	private void moveActorRight(IActor actor) throws InvalidMovement {
		if (castle.isTileAtCurrentFloorOccupiable(actor.getPosX() + 1, actor.getPosY())) {
			actor.setPosX(actor.getPosX() + 1);
			castle.setTileAtCurrentFloorOccupiable(actor.getPosX() - 1, actor.getPosY(), true);
			castle.setTileAtCurrentFloorOccupiable(actor.getPosX(), actor.getPosY(), false);
			interactWithDestination(actor.getPosX(), actor.getPosY(), actor);
		} else {
			if (castle.getActorAtTile(actor.getPosX() + 1, actor.getPosY()) != null)
				attack(actor, castle.getActorAtTile(actor.getPosX() + 1, actor.getPosY()));
			else
				throw new exception.InvalidMovement("Movimento inv�lido"); 
		}
	}
	
	private void attack(IActor attacker, IActor target) {
		if (target.getClass() != attacker.getClass()) {
			target.setHp(target.getHp() - calculateDamage(attacker, target));
			if (target.getHp() <= 0) {
				castle.setTileAtCurrentFloorOccupiable(target.getPosX(), target.getPosY(), true);
				castle.removeActorAtCurrentFloor(target);
				floorActors = castle.getFloorActors();
				sortFloorActors();
			}
		}
	}
	
	private int calculateDamage(IActor attacker, IActor target) {
		return attacker.getDamage() - target.getArmour();
	}
	
	private void enemyPathFinding(int enemyPosX, int enemyPosY, IActor enemy) throws InvalidMovement {
		if (!enemy.isHeroSeen() && Math.sqrt((Math.pow((enemyPosX - hero.getPosX()), 2) + Math.pow((enemyPosY - hero.getPosY()), 2))) < 4)
			enemy.setHeroSeen(true);
		if (enemy.isHeroSeen()) {
			if (Math.random() < 0.5) {
				if (enemyPosX > hero.getPosX()) {
					try {
						moveActorLeft(enemy);
					} catch (InvalidMovement e) {
						if (enemyPosY > hero.getPosY()) {
							try {
								moveActorUp(enemy);
							} catch (InvalidMovement f) {
								f.printStackTrace();
							}
						} else if (enemyPosY < hero.getPosY()) {
							try {
								moveActorDown(enemy);
							} catch (InvalidMovement f) {
								f.printStackTrace();
							}
						} else {
							e.printStackTrace();
						}
					}
				} else if (enemyPosX < hero.getPosX()) {
					try {
						moveActorRight(enemy);
					} catch (InvalidMovement e) {
						if (enemyPosY > hero.getPosY()) {
							try {
								moveActorUp(enemy);
							} catch (InvalidMovement f) {
								f.printStackTrace();
							}
						} else if (enemyPosY < hero.getPosY()) {
							try {
								moveActorDown(enemy);
							} catch (InvalidMovement f) {
								f.printStackTrace();
							}
						} else {
							e.printStackTrace();
						}
					}
				} else {
					if (enemyPosY > hero.getPosY()) {
						try {
							moveActorUp(enemy);
						} catch (InvalidMovement e) {
							e.printStackTrace();
						}
					} else {
						try {
							moveActorDown(enemy);
						} catch (InvalidMovement e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				if (enemyPosY > hero.getPosY()) {
					try {
						moveActorUp(enemy);
					} catch (InvalidMovement e) {
						if (enemyPosX > hero.getPosX()) {
							try {
								moveActorLeft(enemy);
							} catch (InvalidMovement f) {
								f.printStackTrace();
							}
						} else if (enemyPosX < hero.getPosX()) {
							try {
								moveActorRight(enemy);
							} catch (InvalidMovement f) {
								f.printStackTrace();
							}
						} else {
							e.printStackTrace();
						}
					}
				} else if (enemyPosY < hero.getPosY()) {
					try {
						moveActorDown(enemy);
					} catch (InvalidMovement e) {
						if (enemyPosX > hero.getPosX()) {
							try {
								moveActorLeft(enemy);
							} catch (InvalidMovement f) {
								f.printStackTrace();
							}
						} else if (enemyPosX < hero.getPosX()) {
							try {
								moveActorRight(enemy);
							} catch (InvalidMovement f) {
								f.printStackTrace();
							}
						} else {
							e.printStackTrace();
						}
					}
				} else {
					if (enemyPosX > hero.getPosX()) {
						try {
							moveActorLeft(enemy);
						} catch (InvalidMovement e) {
							e.printStackTrace();
						}
					} else {
						try {
							moveActorRight(enemy);
						} catch (InvalidMovement e) {
							e.printStackTrace();
						}
					}
				}
			}
			
			/*
			if (Math.abs(enemyPosX - hero.getPosX()) >= Math.abs((enemyPosY - hero.getPosY()))) {
				if (enemyPosX > hero.getPosX()) {
					if(castle.isTileAtCurrentFloorOccupiable(enemyPosX - 1, enemyPosY) || ((enemyPosX - 1) == hero.getPosX() && enemyPosY == hero.getPosY()))
						moveActorLeft(enemy);
				} else {
					if(castle.isTileAtCurrentFloorOccupiable(enemyPosX + 1, enemyPosY) || ((enemyPosX + 1) == hero.getPosX() && enemyPosY == hero.getPosY()))
						moveActorRight(enemy);
				}
			} else {
				if (enemyPosY > hero.getPosY()) {
					if(castle.isTileAtCurrentFloorOccupiable(enemyPosX, enemyPosY - 1) || (enemyPosX == hero.getPosX() && (enemyPosY - 1) == hero.getPosY()))
						moveActorUp(enemy);
				} else {
					if(castle.isTileAtCurrentFloorOccupiable(enemyPosX, enemyPosY + 1) || (enemyPosX == hero.getPosX() && (enemyPosY + 1) == hero.getPosY()))
						moveActorDown(enemy);
				}
			}
			*/
		}
	}
	
	private void moveHeroNextFloor() {
		castle.updateCurrentFloor();
		floorActors = castle.getFloorActors();
		sortFloorActors();
	}
	
	// teste
	/*
	public static void moveHero() {
		GameScreen.setMessage("");//always reset
		
	}
	*/
	

	private void interactWithDestination(int x, int y, IActor actor) {
		if (actor instanceof Hero) {
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
					actor.setWeaponIsEquipped(true);
					actor.improveDamage();
				}
				else { //better armor, +defense
					actor.setArmorIsEquipped(true);
					actor.improveArmour();
				}
			}
		}
	}
}

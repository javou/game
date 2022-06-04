package controller.battle;

public interface IBattle {
	public boolean detectBattle(); //is there a monster in front of the hero?
	public void calculateDamage();//if the hero is attacking the monster or vice-versa
	
}

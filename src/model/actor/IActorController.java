package model.actor;

public interface IActorController {
	public int getSpeed();
	public int getEnergy();
	public void setEnergy(int energy);
	public int getPosX();
	public void setPosX(int posx);
	public int getPosY();
	public void setPosY(int posy);
	public int getHp();
	public void setHp(int hp);
	public int getArmour();
	public int getDamage();
}

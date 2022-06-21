package controller;

import model.actor.Hero;
import model.castle.Castle;
import model.castle.ICastle;

public class Assembler {
	private static Assembler assembler;
	private ICastle castle;
	
	
	public static Assembler getInstance() {
		if (assembler == null) {
			assembler = new Assembler();
		}
		return assembler;
	}
	
	
	public void newWorld() {
		//create hero, castle, monster....
		castle = new Castle();
	}
}

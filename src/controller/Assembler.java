package controller;

import model.actor.Hero;
import model.castle.Castle;
import model.castle.ICastle;
import view.IWindow;
import view.Window;

public class Assembler {
	private static Assembler assembler;
	private IWindow window;
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
	
	public void createWindow() {
		window = Window.getInstance();
		window.create();
	}
	
	public ICastle getCastle() {
		return castle;
	}
}

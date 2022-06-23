package controller;

import model.castle.Castle;
import model.castle.ICastle;
import view.IWindow;
import view.Window;

public class Assembler {
	private static Assembler assembler;
	private IWindow window;
	private ICastle castle;
	
	private Assembler() {}
	
	public static Assembler getInstance() {
		if (assembler == null) {
			assembler = new Assembler();
		}
		return assembler;
	}
	
	public void newWorld() {
		//create hero, castle, monster....
		castle = Castle.getInstance();
	}
	
	public void createWindow() {
		window = Window.getInstance();
		window.create();
	}
	
	public ICastle getCastle() {
		return castle;
	}
}

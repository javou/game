package controller;

import model.castle.Castle;
import model.castle.ICastle;
// import resources.Music;
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
	
	public void newGame() {
		castle = Castle.getInstance();
		window = Window.getInstance();
		window.create();
		// Music.setSong("songs/suspense.wav"); // música gera erro
		// Music.loop();
	}
	
	public ICastle getCastle() {
		return castle;
	}
}

package controller;

import java.util.Timer;
import java.util.TimerTask;

import model.castle.Castle;
import model.castle.ICastleController;
// import resources.Music;
import view.GameScreen;
import view.IGameScreen;
import view.IWindow;
import view.Window;

public class Assembler {
	private static Assembler assembler;
	private IWindow window;
	private ICastleController castle;
	
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
		IGameScreen gmv = GameScreen.getInstance();
		// Music.setSong("songs/suspense.wav"); // m�sica gera erro
		// Music.loop();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				gmv.setMessage("");
			}
		}, 0,1500);
	}
	
	public void restart() {
		castle.restart();
		castle = Castle.getInstance();
	}
	
	public ICastleController getCastle() {
		return castle;
	}
}

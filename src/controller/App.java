package controller;

import view.Window;

public class App {
	
	public static void main(String[] args) {
		executeGame();
	}
	
	private static void executeGame() {
		Window.create();
		God.newWorld();
		God.gameLoop();
	}
}

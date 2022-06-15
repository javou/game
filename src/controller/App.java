package controller;

import view.Window;

public class App {
	private static God god;
	private static Window window;
	public static void main(String[] args) {
		executeGame();//facade pattern
	}
	
	private static void executeGame() {
		god = God.getInstance();
		window = Window.getInstance();
		window.create();
		god.newWorld();
		god.gameLoop();
	}
}

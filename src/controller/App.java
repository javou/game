package controller;

import view.Window;

public class App {
	private static God god;
	private static Assembler assembler;
	private static Window window;
	
	public static void main(String[] args) {
		executeGame(); //facade pattern
	}
	
	private static void executeGame() {
		assembler = Assembler.getInstance();
		god = God.getInstance();
		window = Window.getInstance(); // passar para assembler
		window.create(); // assembler.create();
		god.newWorld(); // assembler.newWorld();
		god.gameLoop();
	}
}

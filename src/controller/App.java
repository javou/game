package controller;

public class App {
	private static God god;
	private static Assembler assembler;
	
	public static void main(String[] args) {
		executeGame(); //facade pattern
	}
	
	private static void executeGame() {
		assembler = Assembler.getInstance();
		assembler.newGame();
		god = God.getInstance();
		god.nameHero();
		god.gameLoop();
	}
}

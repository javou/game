package controller;

import javax.swing.Timer;

import resources.Music;
import view.Window;

public class App {
	
	public static void main(String[] args) {
		try {
		Window.create();
		God.newWorld();
		
		}
		catch(Exception e) {
			System.err.println("[App] Uncaught expection in initialization");
			e.printStackTrace();
			System.exit(-1);
		}
	}

}

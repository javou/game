package exception;


import view.GameScreen;
import view.IGameScreen;
import view.IGameScreen;


public class InvalidMovement extends GameException {
	// Indica que o usu�rio tentou mover o her�i para uma c�lula n�o ocup�vel (ex: parede).
	
	private static final long serialVersionUID = -5023345320519284560L;
	private IGameScreen gmv = GameScreen.getInstance();
	public InvalidMovement(String errorMessage) {
        super(errorMessage);
        gmv.setMessage(errorMessage);
    }
}

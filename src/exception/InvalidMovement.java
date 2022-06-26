package exception;


import view.GameScreen;
import view.IGameScreen;
import view.IGameScreen;


public class InvalidMovement extends GameException {
	// Indica que um ator tentou se mover para uma c�lula n�o ocup�vel (ex: parede).
	
	private static final long serialVersionUID = -5023345320519284560L;
	private IGameScreen gmv = GameScreen.getInstance();
	public InvalidMovement(String errorMessage) {
        super(errorMessage);
        gmv.setMessage(errorMessage);
    }
}

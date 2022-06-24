package exception;

/*
import view.GameScreen;
import view.IGameScreenView;
*/

public class InvalidKey extends GameException {
	// Indica que a ultima tecla apertada é inválida.
	
	private static final long serialVersionUID = 5166517148764999980L;
	// private IGameScreen gmv = GameScreen.getInstance();
	public InvalidKey(String errorMessage) {
        super(errorMessage);
        // gmv.setMessage(errorMessage);
        
    }
}

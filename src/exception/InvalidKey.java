package exception;

public class InvalidKey extends GameException {
	// Indica que a ultima tecla apertada � inv�lida.
	
	private static final long serialVersionUID = 5166517148764999980L;
	public InvalidKey(String errorMessage) {
        super(errorMessage);
    }
}

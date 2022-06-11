package exception;

public class InvalidMovement extends GameException {
	// Indica que o usuário tentou mover o herói para uma célula não ocupável (ex: parede).
	
	private static final long serialVersionUID = -5023345320519284560L;

	public InvalidMovement(String errorMessage) {
        super(errorMessage);
    }
}

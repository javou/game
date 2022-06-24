package exception;



public class GameException extends Exception {
	// Engloba todas as exce��es relacionadas ao jogo.

	private static final long serialVersionUID = -6177436124288420062L;

	public GameException(String errorMessage) {
        super(errorMessage);
       
    }
}

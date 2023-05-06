package armas;

public class Errores extends Exception{
	public Errores() {}
	public Errores(String error) {
		super(error);
	}
}

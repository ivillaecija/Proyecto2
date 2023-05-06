package armas;

public class Nan extends Warrior{
	public Nan(int id,String nombre,String url) {
		this.setId(id);
		this.setNom(nombre);
		this.setPuntsDeVida(60);
		this.setFuerza(6);
		this.setVelocitat(3);
		this.setAgilitat(5);
		this.setDefensa(4);
		this.setUrl(url);
	}
}

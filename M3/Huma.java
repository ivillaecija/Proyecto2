package armas;

public class Huma extends Warrior{
	public Huma(int id,String nombre,String url) {
		this.setId(id);
		this.setNom(nombre);
		this.setPuntsDeVida(50);
		this.setFuerza(5);
		this.setVelocitat(5);
		this.setAgilitat(6);
		this.setDefensa(3);
		this.setUrl(url);
	}
}

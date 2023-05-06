package armas;

public class Elf extends Warrior{
	public Elf(int id,String nombre,String url) {
		this.setId(id);
		this.setNom(nombre);
		this.setPuntsDeVida(40);
		this.setFuerza(4);
		this.setVelocitat(7);
		this.setAgilitat(7);
		this.setDefensa(2);
		this.setUrl(url);
	}
}

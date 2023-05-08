package armas;

public class Sword extends Weapon{
	public Sword(String url,int id) {
		this.setId(id);
		this.setUrl(url);
		this.setVelocidad(1);
		this.setFuerza(1);
	}
}

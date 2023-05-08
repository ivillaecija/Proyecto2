package armas;

public class Katana extends Weapon{
	public Katana(String url,int id) {
		this.setId(id);
		this.setUrl(url);
		this.setVelocidad(3);
		this.setFuerza(2);
	}
}

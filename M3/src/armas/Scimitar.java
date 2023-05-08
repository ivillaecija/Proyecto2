package armas;

public class Scimitar extends Weapon{
	public Scimitar(String url,int id) {
		this.setId(id);
		this.setUrl(url);
		this.setFuerza(2);
		this.setVelocidad(1);
	}
}

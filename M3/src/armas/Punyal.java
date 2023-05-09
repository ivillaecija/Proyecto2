package armas;

public class Punyal extends Weapon{
	public Punyal(String url,int id) {
		this.setId(id);
		this.setUrl(url);
		this.setVelocidad(4);
		this.setFuerza(0);
	}
}

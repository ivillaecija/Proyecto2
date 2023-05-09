package armas;

public class TwoHandedswords extends Weapon{
	public TwoHandedswords(String url,int id) {
		this.setId(id);
		this.setUrl(url);
		this.setFuerza(2);
		this.setVelocidad(2);
	}
}

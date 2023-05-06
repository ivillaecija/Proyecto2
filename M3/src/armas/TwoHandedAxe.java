package armas;

public class TwoHandedAxe extends Weapon{
	public TwoHandedAxe(String url,int id) {
		this.setId(id);
		this.setUrl(url);
		this.setFuerza(5);
		this.setVelocidad(0);
	}
}

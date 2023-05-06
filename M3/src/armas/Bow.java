package armas;

public class Bow extends Weapon{
	public Bow(String url,int id) {
		this.setId(id);
		this.setUrl(url);			
		this.setVelocidad(5);
		this.setFuerza(1);
	}
}

package armas;

public class Dagger extends Weapon{
	
	public Dagger(String url,int id) {
		this.setId(id);
		this.setUrl(url);		
		this.setVelocidad(4);
		this.setFuerza(0);
	}
}

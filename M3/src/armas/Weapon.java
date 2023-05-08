package armas;

public abstract class Weapon {
	private int id;
	private int velocidad, fuerza;
	private String url;

	public int getVelocidad() {
		return velocidad;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Weapon [id=" + id + ", velocidad=" + velocidad + ", fuerza=" + fuerza + ", url=" + url + "]";
	}
	



	
}

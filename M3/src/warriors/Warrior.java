package warriors;
import armas.Weapon;

public abstract class Warrior {
	private boolean humano = false;
	private int id;
	private String nom;
	private int puntsDeVida; 
	private int fuerza;
	private int velocitat; 
	private int agilitat; 
	private int defensa;
	private String url;
	private Weapon arma;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getPuntsDeVida() {
		return puntsDeVida;
	}
	public void setPuntsDeVida(int puntsDeVida) {
		this.puntsDeVida = puntsDeVida;
	}
	public int getFuerza() {
		return fuerza;
	}
	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}
	public int getVelocitat() {
		return velocitat;
	}
	public void setVelocitat(int velocitat) {
		this.velocitat = velocitat;
	}
	public int getAgilitat() {
		return agilitat;
	}
	public void setAgilitat(int agilitat) {
		this.agilitat = agilitat;
	}
	public int getDefensa() {
		return defensa;
	}
	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Weapon getArma() {
		return arma;
	}
	public void setArma(Weapon arma) {
		this.arma = arma;
	}
	
	public boolean getHumano() {
		return humano;
	}
	public void setHumano(boolean humano) {
		this.humano = humano;
	}
	
	public void recuperarVida() {
		
	}
	@Override
	public String toString() {
		return "Warrior [id=" + id + ", nom=" + nom + ", puntsDeVida=" + puntsDeVida + ", fuerza=" + fuerza
				+ ", velocitat=" + velocitat + ", agilitat=" + agilitat + ", defensa=" + defensa + ", url=" + url
				+ ", arma=" + arma + "]";
	}
	
	
}

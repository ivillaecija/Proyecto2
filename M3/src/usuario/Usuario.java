package usuario;

import armas.*;
import warriors.*;

public class Usuario {
	private Warrior warrior;
	private Weapon weapon;
	private int puntos = 0;
	private String nombre = "";
	private int id;
	private int partidasJugadas = 0;
	
	public int getPartidasJugadas() {
		return partidasJugadas;
	}
	public void setPartidasJugadas(int partidasJugadas) {
		this.partidasJugadas = partidasJugadas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Usuario() {
	}
	public Warrior getWarrior() {
		return warrior;
	}
	public void setWarrior(Warrior warrior) {
		this.warrior = warrior;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	public int getPuntos() {
		return puntos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	@Override
	public String toString() {
		return "Usuario [warrior=" + warrior + ", weapon=" + weapon + ", puntos=" + puntos + ", nombre=" + nombre
				+ ", id=" + id + ", partidasJugadas=" + partidasJugadas + "]";
	}
	
	
}

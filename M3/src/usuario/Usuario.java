package usuario;

import armas.*;
import warriors.*;

public class Usuario {
	private Warrior warrior;
	private Weapon weapon;
	private int puntos;
	
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
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	@Override
	public String toString() {
		return "Usuario [warrior=" + warrior + ", weapon=" + weapon + ", puntos=" + puntos + "]";
	}
	
	
}

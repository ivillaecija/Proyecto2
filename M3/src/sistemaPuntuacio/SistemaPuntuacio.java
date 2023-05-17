package sistemaPuntuacio;

import usuario.*;
import warriors.Warrior;

public class SistemaPuntuacio {
	public SistemaPuntuacio() {
	}
 	public int puntuacio(Usuario enemigo) {
		int puntos = 0;
		// raza del enemigo
		if (enemigo.getWarrior().getId()==0) {
			puntos +=19;
		}
		if (enemigo.getWarrior().getId()==1) {
			puntos +=20;
		}
		if (enemigo.getWarrior().getId()==2) {
			puntos +=21;
		}
		
		// arma del enemigo
		if (enemigo.getWeapon().getId()==1) {
			puntos +=10;
		}
		if (enemigo.getWeapon().getId()==2) {
			puntos +=10;
		}
		if (enemigo.getWeapon().getId()==3) {
			puntos +=10;
		}
		if (enemigo.getWeapon().getId()==4) {
			puntos +=14;
		}
		if (enemigo.getWeapon().getId()==5) {
			puntos +=14;
		}
		if (enemigo.getWeapon().getId()==6) {
			puntos +=15;
		}
		if (enemigo.getWeapon().getId()==7) {
			puntos +=18;
		}
		if (enemigo.getWeapon().getId()==8) {
			puntos +=12;
		}
		if (enemigo.getWeapon().getId()==9) {
			puntos +=20;
		}
		return puntos;
	}
}

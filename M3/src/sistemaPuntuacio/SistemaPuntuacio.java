package sistemaPuntuacio;

import warriors.Warrior;

public class SistemaPuntuacio {
	public SistemaPuntuacio() {
	}
 	public int puntuacio(Warrior enemigo) {
		int puntos = 0;
		// raza del enemigo
		if (enemigo.getId()==0) {
			puntos +=19;
		}
		if (enemigo.getId()==1) {
			puntos +=20;
		}
		if (enemigo.getId()==2) {
			puntos +=21;
		}
		
		// arma del enemigo
		if (enemigo.getArma().getId()==1) {
			puntos +=10;
		}
		if (enemigo.getArma().getId()==2) {
			puntos +=10;
		}
		if (enemigo.getArma().getId()==3) {
			puntos +=10;
		}
		if (enemigo.getArma().getId()==4) {
			puntos +=14;
		}
		if (enemigo.getArma().getId()==5) {
			puntos +=14;
		}
		if (enemigo.getArma().getId()==6) {
			puntos +=15;
		}
		if (enemigo.getArma().getId()==7) {
			puntos +=18;
		}
		if (enemigo.getArma().getId()==8) {
			puntos +=12;
		}
		if (enemigo.getArma().getId()==9) {
			puntos +=20;
		}
		return puntos;
	}
}

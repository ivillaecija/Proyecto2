package armas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MecanicaLluita {
	public ArrayList<Warrior> OrdernarLuchadores(ArrayList<Warrior> luchadores){
		if (luchadores.get(0).getVelocitat() < luchadores.get(1).getVelocitat()) {
			Collections.reverse(luchadores);
		}else if (luchadores.get(0).getVelocitat() == luchadores.get(1).getVelocitat()) {
			if (luchadores.get(0).getAgilitat() < luchadores.get(1).getAgilitat()) {
				Collections.reverse(luchadores);
			}else if (luchadores.get(0).getAgilitat() == luchadores.get(1).getAgilitat()) {
				Collections.shuffle(luchadores);
			}
		}
		return luchadores;
	}
	
	public int ataque(Warrior atacante, Warrior defensor) {
		// 
	    int dany = atacante.getFuerza()+atacante.getArma().getFuerza()-defensor.getDefensa();
	    boolean acertar = acertar(atacante);
	    if (!acertar) {
	        return 0;
	    }else {
	    	boolean esquivar = esquivar(defensor);
	    	if (esquivar) {
	    		return 0;
	    	}
	    }
	    return dany;
	}
	
	public boolean acertar(Warrior atacante) {
		boolean ataque = true;
        Random rand = new Random();
        int randomNum = rand.nextInt(100) + 1;
        
        if(atacante.getAgilitat()*10<randomNum) {
        	ataque = false;
        }
        
        return ataque;
	}
	
	public boolean esquivar(Warrior defensor) {
		boolean esquiva = false;
		Random rand = new Random();
        int randomNum = rand.nextInt(50) + 1;
        
        if (defensor.getAgilitat()>randomNum) {
        	esquiva = true;
        }
        return false;
	}

	
	
	
	
}

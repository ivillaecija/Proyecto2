package mecanicaLluita;
import warriors.Warrior;
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
		
		System.out.println("\nTorn de "+atacante.getNom());
		
	    
	    boolean acertar = acertar(atacante);
	    if (!acertar) {
	    	System.out.println("L'atac no ha tingut exit.");
	        return 0;
	    }else {
	    	System.out.println("L'atac ha tingut exit.");
	    	boolean esquivar = esquivar(defensor);
	    	if (esquivar) {
	    		System.out.println("L'atac va ser esquivat.");
	    		return 0;
	    	}
	    }
	    int dany = atacante.getFuerza()+atacante.getArma().getFuerza()-defensor.getDefensa();
	    // actualizar la vida del atacante y el defensor en la interfaz grafica
	    System.out.println("Dany = "+dany);
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
        return esquiva;
	}

	public boolean repetiAtac(Warrior atacante, Warrior defensor) {
		boolean repetiAtac = true;
		
		if (atacante.getVelocitat()+atacante.getArma().getVelocidad()<=defensor.getVelocitat()+defensor.getArma().getVelocidad()) {
			repetiAtac = false;
		}else {
			Random rand = new Random();
	        int randomNum = rand.nextInt(100) + 1;
			
	        if ((atacante.getVelocitat()+atacante.getArma().getVelocidad())-(defensor.getVelocitat()+defensor.getArma().getVelocidad())*10 > randomNum) {
	        	repetiAtac = true;
	        }else {
	        	repetiAtac = false;
	        }
			
		}
		
		return repetiAtac;
	}
	
	
	
}

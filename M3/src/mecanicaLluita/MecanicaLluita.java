package mecanicaLluita;
import warriors.Warrior;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import usuario.*;
// swingWorkes
public class MecanicaLluita {
	public void OrdernarLuchadores(ArrayList<Usuario> luchadores){
		
		if (luchadores.get(0).getWarrior().getVelocitat() < luchadores.get(1).getWarrior().getVelocitat()) {
			Collections.reverse(luchadores);
		}else if (luchadores.get(0).getWarrior().getVelocitat() == luchadores.get(1).getWarrior().getVelocitat()) {
			if (luchadores.get(0).getWarrior().getAgilitat() < luchadores.get(1).getWarrior().getAgilitat()) {
				Collections.reverse(luchadores);
			}else if (luchadores.get(0).getWarrior().getAgilitat() == luchadores.get(1).getWarrior().getAgilitat()) {
				Collections.shuffle(luchadores);
			}
		}
	}
	
	
	public int ataque(Warrior atacante, Warrior defensor,JTextArea txtArea,Usuario usuario) {
		
		if (atacante.getHumano()) {
			txtArea.setText(txtArea.getText()+"\n\nTorn de "+usuario.getNombre());
		}else {
			txtArea.setText(txtArea.getText()+"\n\nTorn de "+atacante.getNom());
		}

		System.out.println("\nTorn de "+atacante.getNom());
	    
	    boolean acertar = acertar(atacante);
	    if (!acertar) {
	    	txtArea.setText(txtArea.getText()+"\nL'atac no ha tingut exit."+"\n\n");
	    	System.out.println("L'atac no ha tingut exit."+"\n\n");
	        return 0;
	    }else {
	    	System.out.println("L'atac ha tingut exit.");
	    	txtArea.setText(txtArea.getText()+"\nL'atac ha tingut exit.");
	    	boolean esquivar = esquivar(defensor);
	    	if (esquivar) {
	    		txtArea.setText(txtArea.getText()+"\nL'atac va ser esquivat."+"\n\n");
	    		System.out.println("L'atac va ser esquivat.");
	    		return 0;
	    	}
	    }
	    int dany = atacante.getFuerza()+atacante.getArma().getFuerza()-defensor.getDefensa();
	    
	    if (atacante.getHumano()) {
	    	txtArea.setText(txtArea.getText()+"\n"+usuario.getNombre()+" inflingio "+dany+"\n\n");
		} else {
			txtArea.setText(txtArea.getText()+"\n"+atacante.getNom()+" inflingio "+dany+"\n\n");
		}
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

	public boolean repetiAtac(Usuario atacante, Usuario defensor) {
		boolean repetiAtac = true;
		if (atacante.getWarrior().getVelocitat()+atacante.getWeapon().getVelocidad()<=defensor.getWarrior().getVelocitat()+defensor.getWeapon().getVelocidad()) {
			repetiAtac = false;
		}else {
			Random rand = new Random();
	        int randomNum = rand.nextInt(100) + 1;
	        if ((atacante.getWarrior().getVelocitat()+atacante.getWeapon().getVelocidad())-(defensor.getWarrior().getVelocitat()+defensor.getWeapon().getVelocidad())*10 > randomNum) {
	        	repetiAtac = true;
	        }else {
	        	repetiAtac = false;
	        }
		}
		return repetiAtac;
	}	
}

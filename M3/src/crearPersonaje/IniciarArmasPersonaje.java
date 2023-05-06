package crearPersonaje;
import connectionBBDD.*;
import error.Errores;
import warriors.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import armas.Weapon;



public class IniciarArmasPersonaje {
	public void iniciarPersonaje(Warrior persona, Weapon armaPersona) throws Errores{
		ConnectionBBDD con= new ConnectionBBDD();
		ArrayList<Weapon> lista = con.compatibilidadDeArmas(persona);
		
		boolean valido = false;
		for (Weapon arma:lista) {
			if (arma.getId() == armaPersona.getId()) {
				valido =true;				
			}
		}
		if (valido) {
			persona.setArma(armaPersona);
		}else {
			throw new Errores("Arma no valida para el pesonaje");
		}		
	}
	
	public Warrior iniciarRival(ArrayList<Warrior> listaWarrior, ArrayList<Weapon> listaWeapon) {
		Random rand = new Random();
		int indiceAleatorioWarrior = rand.nextInt(listaWarrior.size());
		ConnectionBBDD con= new ConnectionBBDD();
		Warrior enemigo = listaWarrior.get(indiceAleatorioWarrior);
		ArrayList<Weapon> lista = con.compatibilidadDeArmas(enemigo);
		int indiceAleatorioWeapon = rand.nextInt(lista.size());
		Weapon armaEnemigo = lista.get(indiceAleatorioWeapon);
		enemigo.setArma(armaEnemigo);
		return enemigo;
		
	}
	
	public ArrayList<Weapon> comprobarCompatibilidadDeArma(Warrior luchador) {		
		ConnectionBBDD con= new ConnectionBBDD();
		ArrayList<Weapon> lista= con.compatibilidadDeArmas(luchador);
		return lista;
	}
	
}
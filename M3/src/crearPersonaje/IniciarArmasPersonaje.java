package crearPersonaje;
import connectionBBDD.*;
import error.Errores;
import warriors.*;
import java.util.ArrayList;
import java.util.Collections;

import armas.Weapon;



public class IniciarArmasPersonaje {
	public void iniciarPersonaje(Warrior persona, Weapon armaPersona) throws Errores{
		ConnectionBBDD con= new ConnectionBBDD();
		ArrayList<Weapon> lista = con.compatibilidadDeArmas(persona);
		if (lista.contains(armaPersona)) {
			persona.setArma(armaPersona);
		}else {
			throw new Errores("Arma no valida para el pesonaje");
		}
	}
	
	public Warrior iniciarRival(ArrayList<Warrior> listaWarrior, ArrayList<Weapon> listaWeapon) {
		ConnectionBBDD con= new ConnectionBBDD();
		Collections.shuffle(listaWarrior);
		Collections.shuffle(listaWeapon);
		Warrior enemigo = listaWarrior.get(0);
		
		int idEnemigo = enemigo.getId();
		ArrayList<Weapon> lista = con.compatibilidadDeArmas(enemigo);
		System.out.println("longitud ="+lista.size());
		for (Weapon ele:lista) {
			System.out.println(ele.toString());
		}
		Collections.shuffle(lista);
		Weapon armaEnemigo = lista.get(0);
		enemigo.setArma(armaEnemigo);
		
		return enemigo;
		
	}
	
	public ArrayList<Weapon> comprobarCompatibilidadDeArma(Warrior luchador) {		
		ConnectionBBDD con= new ConnectionBBDD();
		ArrayList<Weapon> lista= con.compatibilidadDeArmas(luchador);
		return lista;
	}
	
}
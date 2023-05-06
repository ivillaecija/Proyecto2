package Main;
import warriors.*;
import java.util.ArrayList;

import armas.TwoHandedAxe;
import armas.Weapon;
import crearPersonaje.IniciarArmasPersonaje;
import crearPersonaje.WarriorContainer;
import crearPersonaje.WeaponContainer;
import error.Errores;



public class Pruebas {
	public static void main(String[] args) {
		Warrior personaje1 = new Elf(1, "nombre", "url");
		Weapon personaje1Arma = new TwoHandedAxe("url", 1);
		
		WarriorContainer listaLuchadores = new WarriorContainer();
		ArrayList<Warrior> listaLuchadoresFinal = listaLuchadores.WarriorContainer();
		
		
		for (Warrior luchador : listaLuchadores.WarriorContainer()) {
			//System.out.println(luchador.toString());
		}
		
		WeaponContainer listaArmas = new WeaponContainer();
		ArrayList<Weapon> listaArmas2 = listaArmas.WeaponContainer();
		
		for (Weapon arma : listaArmas.WeaponContainer()) {
			//System.out.println(arma.toString());
		}

		for (Weapon arma : listaArmas2) {
			//System.out.println(arma.toString());
		}
		
		IniciarArmasPersonaje iap = new IniciarArmasPersonaje();
		Warrior rival = iap.iniciarRival(listaLuchadoresFinal, listaArmas2);
		
		System.out.println(rival.toString());
		
		try {
			iap.iniciarPersonaje(personaje1, personaje1Arma);
		} catch (Errores e) {
			System.out.println(e.getMessage());
		}		
	}
}

package main;
import warriors.*;
import java.util.ArrayList;
import java.util.Collections;

import armas.*;
import crearPersonaje.IniciarArmasPersonaje;
import crearPersonaje.*;
import error.Errores;
import mecanicaLluita.*;


public class Main {
	public static void main (String [] args) {
		MecanicaLluita ml = new MecanicaLluita();
		IniciarArmasPersonaje iap = new IniciarArmasPersonaje();
		WarriorContainer wac = new WarriorContainer();
		WeaponContainer wec = new WeaponContainer();
		
		ArrayList<Warrior> warriorContainer = wac.WarriorContainer();
		ArrayList<Weapon> weaponContainer = wec.WeaponContainer();
		
		// Crea de forma aleatoria un rival
		Warrior enemigo1 = iap.iniciarRival(warriorContainer, weaponContainer);
		//Warrior enemigo2 = iap.iniciarRival(warriorContainer, weaponContainer);
		
		// Crear un personaje 
		Warrior personaje = new Huma(2, "nombre para personaje", "huma.png");  //id,nombre,url
		Weapon arma1 = new TwoHandedAxe("destral_de_dues_mans.png",9); //url,id 
		Weapon arma2 = new Katana("katana.png",7);
		
		// genera un error si el el personaje no es compatible con el arma.
		try {
			iap.iniciarPersonaje(personaje, arma2);
			// si no se produce ningún error, el arma será añadida al personaje.
			System.out.println(personaje.toString());
		} catch (Errores e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(enemigo1.toString());
		System.out.println(personaje.toString());
		
		ArrayList<Warrior> luchadores = new ArrayList<>();
		luchadores.add(enemigo1);
		luchadores.add(personaje);
		ArrayList<Warrior> luchadoresOrdenados = ml.OrdernarLuchadores(luchadores);
		
		while(true) {
			int ataque = ml.ataque(luchadoresOrdenados.get(0), luchadoresOrdenados.get(1));
			luchadoresOrdenados.get(1).setPuntsDeVida(luchadoresOrdenados.get(1).getPuntsDeVida()-ataque);
			boolean repetir = ml.repetiAtac(luchadoresOrdenados.get(0), luchadoresOrdenados.get(1));
			
			if (luchadoresOrdenados.get(1).getPuntsDeVida()<=0) {
				System.out.println("**********************************************************************");
				System.out.println("El ganador es : " + luchadoresOrdenados.get(0).getNom());
				System.out.println("**********************************************************************");
				break;
			}else {
				if (!repetir) {
					Collections.reverse(luchadoresOrdenados);
					System.out.println("No se repetira el ataque");
				}else {
					System.out.println("Se repetira el ataque");
				}
			}
			System.out.println(luchadores.get(0).toString());
			System.out.println(luchadores.get(1).toString());
		}
	}
}

package main;
import warriors.*;
import java.util.ArrayList;
import java.util.Collections;

import armas.*;
import crearPersonaje.IniciarArmasPersonaje;
import crearPersonaje.*;
import error.Errores;
import mecanicaLluita.*;



public class Pruebas {
	public static void main(String[] args) {
		Warrior personaje1 = new Huma(2, "pepe", "url");
		Weapon personaje1Arma = new Ax("destral.png", 3);
		
		Warrior personaje2 = new Huma(2, "lucas", "url");
		Weapon personaje2Arma = new Ax("destral.png", 3);
		
		/*
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
		*/
		IniciarArmasPersonaje iap = new IniciarArmasPersonaje();
		
		try {
			iap.iniciarPersonaje(personaje1, personaje1Arma);
		} catch (Errores e) {
			System.out.println(e.getMessage());
			//cuando el usuario selecciona un arma que no puede portal
		}
		
		try {
			iap.iniciarPersonaje(personaje2, personaje2Arma);
		} catch (Errores e) {
			System.out.println(e.getMessage());
			//cuando el usuario selecciona un arma que no puede portal
		}
		
		MecanicaLluita ml = new MecanicaLluita();
		
		ArrayList<Warrior> luchadores = new ArrayList<Warrior>();
		luchadores.add(personaje2);
		luchadores.add(personaje1);
		for (Warrior orden: luchadores) {
			System.out.println(orden.toString());
		}
		
		ArrayList<Warrior> luchadoresOrdenados = new ArrayList<Warrior>();
		luchadoresOrdenados = ml.OrdernarLuchadores(luchadores);
		
		for (Warrior orden: luchadoresOrdenados) {
			System.out.println(orden.toString());
		}
		
		while(true) {
			int ataque = ml.ataque(luchadoresOrdenados.get(0), luchadoresOrdenados.get(1));
			luchadoresOrdenados.get(1).setPuntsDeVida(luchadoresOrdenados.get(1).getPuntsDeVida()-ataque);
			boolean repetir = ml.repetiAtac(luchadoresOrdenados.get(0), luchadoresOrdenados.get(1));
			
			if (luchadoresOrdenados.get(1).getPuntsDeVida()<=0) {
				System.out.println("El ganador es : " + luchadoresOrdenados.get(0).getNom());
				break;
			}else {
				if (!repetir) {
					Collections.reverse(luchadoresOrdenados);
				}
			}
			System.out.println(luchadoresOrdenados.get(0).toString());
			System.out.println(luchadoresOrdenados.get(1).toString());
			
		}
		
		
		
		
		
		
		
		
		
	}
}

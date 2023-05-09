package main;
import warriors.*;
import java.util.ArrayList;
import java.util.Collections;

import armas.*;
import connectionBBDD.ConnectionBBDD;
import crearPersonaje.IniciarArmasPersonaje;
import crearPersonaje.*;
import error.Errores;
import mecanicaLluita.*;
import usuario.*;



public class Pruebas {
	public static void main(String[] args) {
		Usuario usuario = new Usuario();
		usuario.setId(1);
		Warrior personaje1 = new Huma(2, "pepe", "url");
		Weapon personaje1Arma = new Ax("destral.png", 3);
		personaje1.setArma(personaje1Arma);
		
		Warrior personaje2 = new Huma(2, "lucas", "url");
		Weapon personaje2Arma = new Ax("destral.png", 3);
		personaje2.setArma(personaje2Arma);
		
		usuario.setWarrior(personaje1);
		
		ConnectionBBDD con= new ConnectionBBDD();
		
		//con.insertarBattleInicio(personaje2,usuario);
		
		//int max = con.recuperarMaxIdBattle();
		//System.out.println("Este es el max = "+max);
		
		
		//con.insertarDatosRonda(20, 10, usuario, personaje2);
		
		con.actualizarDatosBattle(40, 100, 20);
		
		
		
		
		
		/*
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
		
		*/
		
		
		
		
		
		
		
	}
}

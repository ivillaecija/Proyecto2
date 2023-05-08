// añadir en Battle una nueva row con:
/*
 * jugador_id, guerrero_id, guerrero_arma_id, oponente_id, oponente_arma_id,
 * lesiones_causadas, lesiones_sufridas, puntos_de_batalla
 */

// insert into battle (player_id, warrior_id, warrior_weapon_id, opponent_id,
// opponent_weapon_id, injuries_caused, injuries_suffered, battle_points)
// values(1, 2, 2, 3, 9, 30, 10, 86);

package main;

import warriors.*;
import java.util.ArrayList;
import java.util.Collections;
import connectionBBDD.*;
import armas.*;
import crearPersonaje.IniciarArmasPersonaje;
import crearPersonaje.*;
import error.Errores;
import mecanicaLluita.*;
import usuario.*;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Usuario prueba = new Usuario();
		System.out.println(prueba.getWeapon());
		if (prueba.getWeapon() == null) {
			System.out.println("null");
		} else {
			System.out.println("no null");
		}
		ConnectionBBDD dBBD = new ConnectionBBDD();
		MecanicaLluita ml = new MecanicaLluita();
		IniciarArmasPersonaje iap = new IniciarArmasPersonaje();
		WarriorContainer wac = new WarriorContainer();
		WeaponContainer wec = new WeaponContainer();
		Scanner sc = new Scanner(System.in);

		// lista de todos los guerreros
		ArrayList<Warrior> warriorContainer = wac.WarriorContainer();
		// lista de todas las armas
		ArrayList<Weapon> weaponContainer = wec.WeaponContainer();

		Usuario usuario = new Usuario();

		// menu de escoger personaje

		// segun el personaje que escoja se pondra el numero dentro del setWarrior
		usuario.setWarrior(warriorContainer.get(1));

		// Crear un ArrayList con los 2 luchadores que se enfrentaran
		ArrayList<Warrior> luchadores = new ArrayList<Warrior>();
		
		
		// menu escoger arma
		// genera un error cada vez que no se selecciona un arma compatible
		while (true) {
			try {
				iap.iniciarPersonaje(usuario.getWarrior(), weaponContainer.get(8)); // colocar index seleccionado
				Warrior warriorDeUsuario = dBBD.crearWarrior(usuario.getWarrior().getId());
				warriorDeUsuario.setArma(weaponContainer.get(8));
				warriorDeUsuario.setHumano(true);
				luchadores.add(warriorDeUsuario);
				break;
			} catch (Errores e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// Genera un rival de forma aleatoria
		Warrior enemigo1 = iap.iniciarRival(warriorContainer, weaponContainer);
		luchadores.add(enemigo1);
		
		// Ordenara los luchadores segun su su velocidad
		ArrayList<Warrior> luchadoresOrdenados = ml.OrdernarLuchadores(luchadores);

		int danyPersonaje = 0;
		int danyEnemigo = 0;
		// comienza el combate

		while (true) {

			// escoger personaje
			// volver al menu de escoger personaje
			// si el usuario escoge un luchador incompatible con el arma que porta genera un
			// error
			// para que esto funcione asi, se tendra que comprobar que
			// usuario.getWeapon()==null.

			// escoger arma
			// volver al menu de escoger arma

			// ranking

			// el luchador ataca
			int ataque = ml.ataque(luchadoresOrdenados.get(0), luchadoresOrdenados.get(1));
			luchadoresOrdenados.get(1).setPuntsDeVida(luchadoresOrdenados.get(1).getPuntsDeVida() - ataque);

			// si el denfensor de queda sin vida entrara en el IF
			if (luchadoresOrdenados.get(1).getPuntsDeVida() <= 0) {
				// el atacante ha vencido
				System.out.println("**********************************************************************");
				System.out.println("El ganador es : " + luchadoresOrdenados.get(0).getNom());
				System.out.println("**********************************************************************");
				break;

			} else {
				// operacion para intentar repetir ataque
				boolean repetir = ml.repetiAtac(luchadoresOrdenados.get(0), luchadoresOrdenados.get(1));
				// si el atacante no repite el ataque entrara en el IF
				if (!repetir) {
					Collections.reverse(luchadoresOrdenados);
					System.out.println("No se repetira el ataque");
				} else {
					System.out.println("Se repetira el ataque");
				}
			}
			System.out.println(luchadores.get(0).toString());
			System.out.println(luchadores.get(1).toString());
		}

		//
	}
}

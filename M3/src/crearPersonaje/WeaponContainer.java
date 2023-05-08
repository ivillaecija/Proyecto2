package crearPersonaje;
import connectionBBDD.*;
import java.util.ArrayList;

import armas.Weapon;

public class WeaponContainer {
	public ArrayList<Weapon> WeaponContainer(){
		ArrayList<Weapon> weapons = new ArrayList<Weapon>();
		
		ConnectionBBDD con= new ConnectionBBDD();
		
		weapons = con.showWeapons();
		
		return weapons;
	}
}

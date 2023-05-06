package armas;

import java.util.ArrayList;

public class WeaponContainer {
	public ArrayList<Weapon> WeaponContainer(){
		ArrayList<Weapon> weapons = new ArrayList<Weapon>();
		
		ConnectionBBDD con= new ConnectionBBDD();
		
		weapons = con.showWeapons();
		
		return weapons;
	}
}

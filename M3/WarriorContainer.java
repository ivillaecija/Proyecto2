package armas;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WarriorContainer {
	
	public ArrayList<Warrior> WarriorContainer(){
		
		ArrayList<Warrior> warriors = new ArrayList<Warrior>();
		

		
		ConnectionBBDD con= new ConnectionBBDD();
		
		warriors = con.showWarriors();
		
		return warriors;
	}
	
}

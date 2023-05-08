package connectionBBDD;
import armas.*;
import crearPersonaje.WeaponContainer;
import warriors.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionBBDD {
	Connection con;

	public ConnectionBBDD() {
		String url= "jdbc:mysql://localhost/battle?serverTimezone=UTC";
		String user= "root";
		String password= "juan";

		try {
			// Buscamos si existe el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Drive cargado correctamente");

			// Creamos la conexion
			con = DriverManager.getConnection(url, user, password);
			System.out.println("La conexion se ha cargado correctamentes");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No se ha podido crear la conexion");

		} catch (ClassNotFoundException e) {
			System.out.println("El driver no se ha cargado correctamente");
		}

	}
	
	public ArrayList<Warrior> showWarriors() {
		ArrayList<Warrior> list = new ArrayList<Warrior>();
		try {
			String query= "SELECT warrior_id, warrior_name, warrior_image_PATH FROM warriors;";
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String raca = rs.getString(2);				
				String url = rs.getString(3);

				
				if (raca.equals("Nan")) {
					Warrior nan = new Nan(id,raca,url);	
					list.add(nan);
				}
				
				if (raca.equals("Human")) {
					Warrior huma = new Huma(id,raca,url);
					list.add(huma);
				}
				if (raca.equals("Elf")) {
					Warrior elf = new Elf(id,raca,url);
					list.add(elf);
				}
			}
		}catch (SQLException e) {
			System.out.println("No se ha podido crear la conexion");
		}
		return list;	
	}
	
	
	public ArrayList<Weapon> showWeapons() {
		ArrayList<Weapon> list= new ArrayList<Weapon>();
		try {
			String query= "SELECT weapon_name, weapon_image_path,weapon_id FROM weapons;"; 
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while(rs.next()) {
				String name = rs.getString(1);
				String url = rs.getString(2);
				int id = rs.getInt(3);
				if (name.equals("Daga")) {
					Weapon dagger = new Dagger(url,id);
					list.add(dagger);
				}
				if (name.equals("Espasa")) {
					Weapon espasa = new Sword(url,id);
					list.add(espasa);
				}
				if (name.equals("Destral")) {
					Weapon destral = new Ax(url,id);
					list.add(destral);
				}
				if (name.equals("Espasas Dobles")) {
					Weapon espasasDobles = new TwoHandedswords(url,id);
					list.add(espasasDobles);
				}
				if (name.equals("Simitarra")) {
					Weapon simitarra = new Scimitar(url,id);
					list.add(simitarra);
				}
				if (name.equals("Arc")) {
					Weapon arc = new Bow(url,id);
					list.add(arc);
				}
				if (name.equals("Katana")) {
					Weapon katana = new Katana(url,id);
					list.add(katana);
				}
				if (name.equals("Punyal")) {
					Weapon punyal = new Punyal(url,id);
					list.add(punyal);
				}
				if (name.equals("Destral de dues mans")){
					Weapon destralDeDuesMans = new TwoHandedAxe(url,id);
					list.add(destralDeDuesMans);
				}
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No se ha podido crear la conexion");
		}
		return list;	
	}
	
	
	public ArrayList<Warrior> prueba() {
		ArrayList<Warrior> lista= new ArrayList<Warrior>();
		//Warrior w1 = new Nan(1, "2", 3, 4, 10, 10, 7, "8");
		//Warrior w2 = new Nan(2, "2", 3, 4, 10, 16, 7, "8");
		
		/*
		Warrior w3 = new Nan(3, "2", 3, 4, 5, 6, 7, "8");
		Warrior w4 = new Nan(4, "2", 3, 4, 5, 6, 7, "8");
		*/
		
		//lista.add(w1);
		//lista.add(w2);
		
		/*
		lista.add(w3);
		lista.add(w4);
		*/
		
		return lista;	
	}
	
	public ArrayList<Weapon> compatibilidadDeArmas(Warrior luchador){
		WeaponContainer listaArmas2 = new WeaponContainer();
		
		
		ArrayList<Weapon> listaArmas = listaArmas2.WeaponContainer();
		
		
		for (Weapon arm:listaArmas ) {
			System.out.println(arm.toString());
		}
		
		ArrayList<Weapon> armasCompatibles = new ArrayList<>();
		ArrayList<Integer> armas = new ArrayList<>();
		
		
		try {
			String query= "SELECT weapon_id FROM weapons_available where warrior_id=?;";
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setInt(1, luchador.getId());

			Statement st = con.createStatement();

			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				int idWeapon = rs.getInt(1);
				armas.add(idWeapon);	
			}
			
			for (Weapon arm:listaArmas) {
				if ( armas.contains(arm.getId())) {
					armasCompatibles.add(arm);
				}
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No se ha podido crear la conexion");
		}
		return armasCompatibles;
	}
}















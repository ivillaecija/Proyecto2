package connectionBBDD;

import armas.*;
import crearPersonaje.WeaponContainer;
import usuario.*;
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
		String url = "jdbc:mysql://localhost/battle?serverTimezone=UTC";
		String user = "root";
		String password = "juan";

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
			String query = "SELECT warrior_id, warrior_name, warrior_image_PATH FROM warriors;";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {
				int id = rs.getInt(1);
				String raca = rs.getString(2);
				String url = rs.getString(3);

				if (raca.equals("Nan")) {
					Warrior nan = new Nan(id, raca, url);
					list.add(nan);
				}

				if (raca.equals("Human")) {
					Warrior huma = new Huma(id, raca, url);
					list.add(huma);
				}
				if (raca.equals("Elf")) {
					Warrior elf = new Elf(id, raca, url);
					list.add(elf);
				}
			}
		} catch (SQLException e) {
			System.out.println("No se ha podido crear la conexion");
		}
		return list;
	}

	public Warrior crearWarrior(int id) {
		Warrior noDevolver = new Nan(0,"a","a");

		try {
			String query = "SELECT warrior_id, warrior_name, warrior_image_PATH FROM warriors where warrior_id = ?;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			Statement st = con.createStatement();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idW = rs.getInt(1);
				String raca = rs.getString(2);
				String url = rs.getString(3);
				
				if (id == 1) {
					Warrior nanCreado = new Nan(id, raca, url);
					return nanCreado;
				}

				if (id == 2) {
					Warrior humaCreado = new Huma(id, raca, url);
					return humaCreado;
				}
				if (id == 3) {
					Warrior elfCreado = new Elf(id, raca, url);
					return elfCreado;
				}
			}

		} catch (SQLException e) {
			System.out.println("No se ha podido crear la conexion");
		}
		return noDevolver;

	}

	public ArrayList<Weapon> showWeapons() {
		ArrayList<Weapon> list = new ArrayList<Weapon>();
		try {
			String query = "SELECT weapon_name, weapon_image_path,weapon_id FROM weapons;";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {
				String name = rs.getString(1);
				String url = rs.getString(2);
				int id = rs.getInt(3);
				if (name.equals("Daga")) {
					Weapon dagger = new Dagger(url, id);
					list.add(dagger);
				}
				if (name.equals("Espasa")) {
					Weapon espasa = new Sword(url, id);
					list.add(espasa);
				}
				if (name.equals("Destral")) {
					Weapon destral = new Ax(url, id);
					list.add(destral);
				}
				if (name.equals("Espasas Dobles")) {
					Weapon espasasDobles = new TwoHandedswords(url, id);
					list.add(espasasDobles);
				}
				if (name.equals("Simitarra")) {
					Weapon simitarra = new Scimitar(url, id);
					list.add(simitarra);
				}
				if (name.equals("Arc")) {
					Weapon arc = new Bow(url, id);
					list.add(arc);
				}
				if (name.equals("Katana")) {
					Weapon katana = new Katana(url, id);
					list.add(katana);
				}
				if (name.equals("Punyal")) {
					Weapon punyal = new Punyal(url, id);
					list.add(punyal);
				}
				if (name.equals("Destral de dues mans")) {
					Weapon destralDeDuesMans = new TwoHandedAxe(url, id);
					list.add(destralDeDuesMans);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No se ha podido crear la conexion");
		}
		return list;
	}

	public ArrayList<Weapon> compatibilidadDeArmas(Warrior luchador) {
		WeaponContainer listaArmas2 = new WeaponContainer();

		ArrayList<Weapon> listaArmas = listaArmas2.WeaponContainer();


		ArrayList<Weapon> armasCompatibles = new ArrayList<>();
		ArrayList<Integer> armas = new ArrayList<>();

		try {
			String query = "SELECT weapon_id FROM weapons_available where warrior_id=?;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, luchador.getId());
			Statement st = con.createStatement();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idWeapon = rs.getInt(1);
				armas.add(idWeapon);
			}

			for (Weapon arm : listaArmas) {
				if (armas.contains(arm.getId())) {
					armasCompatibles.add(arm);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No se ha podido crear la conexion");
		}
		return armasCompatibles;
	}
	
	
	public void insertarBattleInicio ( Warrior enemigo, Usuario usuario) {
		try {
			String query = "INSERT INTO BATTLE "
					+ "(player_id , warrior_id , warrior_weapon_id , opponent_id , opponent_weapon_id , injuries_caused , injuries_suffered ,battle_points ) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?) ";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, usuario.getId());
			ps.setInt(2, usuario.getWarrior().getId());
			ps.setInt(3, usuario.getWarrior().getArma().getId());
			ps.setInt(4, enemigo.getId());
			ps.setInt(5, enemigo.getArma().getId());
			ps.setInt(6, 0);
			ps.setInt(7, 0);
			ps.setInt(8, 0);
			int filasAfectadas = ps.executeUpdate();
			if (filasAfectadas > 0) {
	            System.out.println("Se ha insertado el registro correctamente.");
	        } else {
	            System.out.println("No se ha insertado ningun registro.");
	        }
			ps.close();
		} catch (SQLException e) {
			System.out.println("No se ha podido crear la conexion");
			e.printStackTrace();
		}
	}
	
	public int recuperarMaxIdBattle() {
		int maxId = 1;
		try {
			String query = "SELECT MAX(battle_id) from battle";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery(query);
			if (rs.next()) {
				 maxId = rs.getInt(1);
			}
			return maxId;
		} catch (SQLException e) {
			System.out.println("No se ha podido crear la conexion");
			e.printStackTrace();
		}
		return maxId;
	}
	
	public void insertarDatosRonda(int danyRealizado, int danyRecibido, Usuario usuario, Warrior enemigo) {
		try {
			String query = "INSERT INTO BATTLE_ROUNDS "
					+ "(battle_id,player_id , warrior_id , warrior_weapon_id , opponent_id , opponent_weapon_id , injuries_caused , injuries_suffered) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?) ";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, recuperarMaxIdBattle());
			ps.setInt(2, usuario.getId());
			ps.setInt(3, usuario.getWarrior().getId());
			ps.setInt(4, usuario.getWarrior().getArma().getId());
			ps.setInt(5, enemigo.getId());
			ps.setInt(6, enemigo.getArma().getId());
			ps.setInt(7, danyRealizado);
			ps.setInt(8, danyRecibido);
			int filasAfectadas = ps.executeUpdate();
			if (filasAfectadas > 0) {
	            System.out.println("Se ha insertado el registro correctamente.");
	        } else {
	            System.out.println("No se ha insertado ningun registro.");
	        }
			ps.close();
		} catch (SQLException e) {
			System.out.println("No se ha podido crear la conexion");
			e.printStackTrace();
		}

	}
	
	
	public void actualizarDatosBattle(int danyRealizadoTotal, int danyRecibidoTotal, int puntos) {
		try {
		    String query = "UPDATE battle SET injuries_caused=?, injuries_suffered=?, battle_points=? WHERE battle_id=?";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, danyRealizadoTotal);
		    ps.setInt(2, danyRecibidoTotal);
		    ps.setInt(3, puntos);
		    ps.setInt(4, recuperarMaxIdBattle());
		    int filasAfectadas = ps.executeUpdate();
		    if (filasAfectadas > 0) {
		        System.out.println("Se ha actualizado la fila correctamente.");
		    } else {
		        System.out.println("No se ha actualizado ninguna fila.");
		    }
		} catch (SQLException e) {
		    System.out.println("No se ha podido actualizar la fila: " + e.getMessage());
		}

	}
	
	
	public void insertarUsuario(Usuario usuario) {
		try {
			String query = "INSERT INTO PLAYERS "
					+ "(player_name) "
					+ "VALUES "
					+ "(?) ";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, usuario.getNombre());
			int filasAfectadas = ps.executeUpdate();
			if (filasAfectadas > 0) {
	            System.out.println("Se ha insertado el registro correctamente.");
	        } else {
	            System.out.println("No se ha insertado ningun registro.");
	        }
			ps.close();
		} catch (SQLException e) {
			System.out.println("No se ha podido crear la conexion");
			e.printStackTrace();
		}
	}
	
	
	public int maxIdUsuario(Usuario usuario) {
		int id = 0;
		try {
			String query = "select max(player_id) from players; ";
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt(1)+1;
				return id;
			}
			
		} catch (SQLException e) {
			System.out.println("No se ha podido crear la conexion");
			e.printStackTrace();
		}
		return id;
	}
	
	public void borrarUsuario(Usuario usuario) {
		try {
			String query = "DELETE FROM battle where player_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, usuario.getId());

		} catch (SQLException e) {
			System.out.println("No se ha podido crear la conexion");
			e.printStackTrace();
		}
	}
	
	public void borrarBattleRounds(Usuario usuario){
		try {
			String query = "DELETE FROM battle_rounds where player_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, usuario.getId());
		} catch (SQLException e) {
			System.out.println("No se ha podido crear la conexion");
			e.printStackTrace();
		}
	}
	
	
	
	
	
}

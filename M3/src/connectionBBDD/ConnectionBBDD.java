package connectionBBDD;

import armas.*;
import crearPersonaje.WeaponContainer;
import rankings.*;
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
		String password = "1234";

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
			String query = "INSERT INTO BATTLE VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, recuperarMaxIdBattle()+1);
			ps.setInt(2, usuario.getId());
			ps.setInt(3, usuario.getWarrior().getId());
			ps.setInt(4, usuario.getWarrior().getArma().getId());
			ps.setInt(5, enemigo.getId());
			ps.setInt(6, enemigo.getArma().getId());
			ps.setInt(7, 0);
			ps.setInt(8, 0);
			ps.setInt(9, 0);
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
	
	public void insertarDatosRonda(int danyRealizado, int danyRecibido, Usuario usuario, Usuario enemigo) {
		int battle_rounds_id = 1;
		try {
			String query = "select count(*) from battle_rounds where battle_id = " + recuperarMaxIdBattle() + ";";
			Statement stmnt = con.createStatement();
			ResultSet rs = stmnt.executeQuery(query);
			rs.next();
			
			if (rs.getInt(1) > 0) {
				try {
					query = "select max(battle_rounds_id) from battle_rounds where battle_id = " + recuperarMaxIdBattle() + ";";
					rs = stmnt.executeQuery(query);
					rs.next();
					battle_rounds_id += rs.getInt(1);
				} catch (SQLException e1) {
					System.out.println("No se han encontrado rondas con el battle_id especificado");
					e1.printStackTrace();
				}
			}
			query = "INSERT INTO BATTLE_ROUNDS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, battle_rounds_id);
			ps.setInt(2, recuperarMaxIdBattle());
			ps.setInt(3, usuario.getId());
			ps.setInt(4, usuario.getWarrior().getId());
			ps.setInt(5, usuario.getWarrior().getArma().getId());
			ps.setInt(6, enemigo.getWarrior().getId());
			ps.setInt(7, enemigo.getWeapon().getId());
			ps.setInt(8, danyRealizado);
			ps.setInt(9, danyRecibido);
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
		    String query = "UPDATE battle SET injuries_caused=?, injuries_suffered=?, battle_points=? WHERE battle_id=?;";
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
	
	public void borrarUsuarioDeBattle(Usuario usuario) {
		try {
			String query = "DELETE FROM battle where player_id = ?;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, usuario.getId());
			int filasAfectadas = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("No se ha podido crear la conexion");
			e.printStackTrace();
		}
	}
	
	public void borrarBattleRounds(Usuario usuario){
		try {
			String query = "DELETE FROM battle_rounds where player_id = ?;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, usuario.getId());
			int filasAfectadas = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("No se ha podido crear la conexion");
			e.printStackTrace();
		}
	}
	
	

	public void borrarPartidaIncompleta(Usuario usuario) {
		try {
			String query1 = "select battle_id from battle where player_id=? and injuries_caused = 0 and injuries_suffered = 0;";

			PreparedStatement ps = con.prepareStatement(query1);

			// Establecer el valor para el marcador de posición
			ps.setInt(1, usuario.getId());

			// Ejecutar la consulta
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				System.out.println("entro");
				int battleId = rs.getInt(1);
				borrar(battleId);
				
			}

		} catch (SQLException e) {
			System.out.println("No se ha podido crear la conexion");
			e.printStackTrace();
		}
	}
	
	public void borrar(int battleId) {
		try {
			String query2 = "DELETE FROM battle_rounds where battle_id = ?;";
			PreparedStatement ps = con.prepareStatement(query2);
			ps.setInt(1, battleId);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			
		}

	}
	
	

	
	public void borrarUsuario(Usuario usuario) {
		try {
			String query = "DELETE FROM players where player_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, usuario.getId());
			int filasAfectadas = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("No se ha podido crear la conexion");
			e.printStackTrace();
		}
	}
	
	public ArrayList<Ranking> rankings() {
		ArrayList<Ranking> a_rankings = new ArrayList<Ranking>();
		try {
			String query = "select * from rankings;";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				a_rankings.add(new Ranking(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5)));
			}
			
		} catch (SQLException e) {
			System.out.println("No se ha podido crear la conexion");
		}
		finally {
			return a_rankings;
		}
	}
	
	public int insertarUsuario(String s) {
		String query = "select count(*) from players where player_name like '" + s + "';";
		int player_id = 1;
		try {
			Statement stmnt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmnt.executeQuery(query);
			rs.next();
			int contador = rs.getInt(1);
			if (contador == 0) {
				query = "select max(player_id) from players;";
				try {
					rs = stmnt.executeQuery(query);
					rs.next();
					player_id += rs.getInt(1);
				}
				catch (SQLException e1) {
					System.out.println("No se ha podido encontrar usuarios");
					e1.printStackTrace();
				}
				finally {
					query = "select * from players;";
					rs = stmnt.executeQuery(query);
					rs.last();
					rs.moveToInsertRow();
					rs.updateInt(1, player_id);
					rs.updateString(2, s);
					rs.insertRow();
				}
			}
			else {
				query = "select player_id from players where player_name like '" + s + "';";
				rs = stmnt.executeQuery(query);
				rs.next();
				player_id = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("No se ha podido insertar usuario");
			e.printStackTrace();
		}
		return player_id;
	}
	
	
}

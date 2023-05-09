package interficieGrafica;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import armas.Weapon;
import connectionBBDD.*;
import crearPersonaje.IniciarArmasPersonaje;
import crearPersonaje.WarriorContainer;
import crearPersonaje.WeaponContainer;
import error.Errores;
import rankings.Ranking;
import usuario.Usuario;
import warriors.Warrior;

public class VentanaPrincipal {

	public static void main(String[] args) {
		new mainWindow();
	}

}

class mainWindow extends JFrame{
	private JPanel principalPanel, titlePanel, buttonsPanel, usernamePanel, enterPanel, errorPanel;
	private JLabel title, error;
	private JButton userB, chooseCharacter, chooseWeapon, checkRanking, fight, enterB;
	private JTextField userTF;
	private String username = "";
	
	ConnectionBBDD dBBD = new ConnectionBBDD();

	Usuario usuario = new Usuario();
	

	private WarriorContainer wac = new WarriorContainer();
	private ArrayList<Warrior> warriorContainer = wac.WarriorContainer();
	
	private IniciarArmasPersonaje iap = new IniciarArmasPersonaje();
	private WeaponContainer wec = new WeaponContainer();
	private ArrayList<Weapon> weaponContainer = wec.WeaponContainer();


	public mainWindow() {
		this.setTitle("Ventana Principal");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		principalPanel = new JPanel();
		principalPanel.setLayout(new BoxLayout(principalPanel, BoxLayout.Y_AXIS));

		buttonsPanel = new JPanel();
		chooseCharacter = new JButton("Choose Character");
		chooseCharacter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new chooseWarriorWindow("character");
			}
		});
		chooseWeapon = new JButton("Choose Weapon");
		chooseWeapon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (usuario.getWarrior()==null) {
					error.setText("Choose a chracter first!");
				} else {
					new chooseWeaponWindow("weapon");
				}
			}
		});
		chooseWeapon.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				error.setText("");
			}

			@Override
			public void focusGained(FocusEvent e) {}
		});
		checkRanking = new JButton("Check Ranking");
		checkRanking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new viewRankingsWindow();
			}
		});
		fight = new JButton("Fight");
		fight.addActionListener(new fightPanel());
		fight.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				error.setText("");
			}

			@Override
			public void focusGained(FocusEvent e) {}
		});
		buttonsPanel.add(chooseCharacter);
		buttonsPanel.add(chooseWeapon);
		buttonsPanel.add(checkRanking);
		buttonsPanel.add(fight);


		titlePanel = new JPanel();
		title = new JLabel("PROYECTO 2 IZAN BRIAN JOEL");
		titlePanel.add(title);

		usernamePanel = new JPanel();
		userB = new JButton("Username");
		userB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new enterUsernameWindow();
			}
		});
		usernamePanel.add(userB);

		errorPanel = new JPanel();
		error = new JLabel();
		errorPanel.add(error);





		principalPanel.add(buttonsPanel);
		principalPanel.add(titlePanel);
		principalPanel.add(usernamePanel);
		principalPanel.add(errorPanel);

		this.add(principalPanel);

		this.pack();
		this.setSize(500,350);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	class enterUsernameWindow extends JFrame {

		public enterUsernameWindow() {
			enterPanel = new JPanel();
			userTF = new JTextField(12);
			enterB = new JButton("Enter");
			enterB.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					username = userTF.getText();
					usuario.setName(username);
					dispose();
				}
			});
			enterPanel.add(userTF);
			enterPanel.add(enterB);
			this.add(enterPanel);

			this.setTitle("Enter username");
			this.setSize(270,80);
			Toolkit pantalla = Toolkit.getDefaultToolkit();
			Dimension grandaria = pantalla.getScreenSize();
			int ancho = grandaria.width; // ancho pantalla
			int alto = grandaria.height; // alto pantalla
			this.setLocation(((ancho/2)-(this.getWidth()/2)),((alto/2)-(this.getHeight()/2))+150);
			this.setResizable(false);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
	}

	class chooseWarriorWindow extends JFrame {
		private JPanel choosePanel, imagePanel, statsPanel, confirmPanel;
		private JButton confirmB;
		private JLabel name, healthPoints, power, agility, speed, defense;
		private int choosedWarrior;


		public chooseWarriorWindow(String type) {

			imagePanel = new JPanel();
			imagePanel.setLayout(new GridLayout(1,3));
			// ***  Imagenes de los personajes ***
			for (Warrior wa : warriorContainer) {
				ImageIcon imagen = new ImageIcon("img/warriorImg/"+wa.getUrl());
				JButton wb = new JButton(imagen);
				wb.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// ***  Estadisticas de los personajes ***
						name.setText("Name: "+wa.getNom());
						healthPoints.setText("Health points: " + wa.getPuntsDeVida());
						power.setText("Power: " + wa.getFuerza());
						agility.setText("Agility: " + wa.getAgilitat());
						speed.setText("Speed: " + wa.getVelocitat());
						defense.setText("Defense: " + wa.getDefensa());

						choosedWarrior = wa.getId()-1;
					}
				});
				imagePanel.add(wb);
			}

			statsPanel = new JPanel();
			statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
			name 		 = new JLabel("Name:");
			healthPoints = new JLabel("Health points:");
			power 	     = new JLabel("Power:");
			agility      = new JLabel("Agility:");
			speed	     = new JLabel("Speed:");
			defense      = new JLabel("Defense:");
			statsPanel.add(name);
			statsPanel.add(healthPoints);
			statsPanel.add(power);
			statsPanel.add(agility);
			statsPanel.add(speed);
			statsPanel.add(defense);

			confirmPanel = new JPanel();
			confirmB = new JButton("Confirm");
			confirmB.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// ***  Guardar seleccion de personaje  ***
					usuario.setWarrior(warriorContainer.get(choosedWarrior));
					System.out.println("Chosed: "+choosedWarrior);
					dispose();
				}
			});
			confirmPanel.add(confirmB);

			choosePanel = new JPanel();
			choosePanel.setLayout(new BoxLayout(choosePanel, BoxLayout.Y_AXIS));

			choosePanel.add(imagePanel);
			choosePanel.add(statsPanel);
			choosePanel.add(confirmPanel);

			this.add(choosePanel);

			this.setTitle("Choose "+type);
			this.setSize(450,350);
			Toolkit pantalla = Toolkit.getDefaultToolkit();
			Dimension grandaria = pantalla.getScreenSize();
			int ancho = grandaria.width; // ancho pantalla
			int alto = grandaria.height; // alto pantalla
			this.setLocation(((ancho/2)-(this.getWidth()/2))+470,((alto/2)-(this.getHeight()/2)-22));
			this.setResizable(false);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
	}

	class chooseWeaponWindow extends JFrame {
		private JPanel choosePanel, imagePanel, statsPanel, confirmPanel, errorPanel2;
		private JButton confirmB;
		private JLabel errorMsg;
		private int choosed;

		public chooseWeaponWindow(String type) {

			imagePanel = new JPanel();
			imagePanel.setLayout(new GridLayout(3,3));
			// ***  Imagenes de los personajes ***
			for (Weapon wp : weaponContainer) {
				ImageIcon imagen = new ImageIcon("img/weaponImg/"+wp.getUrl());
				JButton wb = new JButton(imagen);
				wb.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						choosed = wp.getId()-1;
					}
				});
				imagePanel.add(wb);
			}

			statsPanel = new JPanel();
			statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));

			confirmPanel = new JPanel();
			confirmB = new JButton("Confirm");
			confirmB.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e)  {
					try {
						iap.iniciarPersonaje(usuario.getWarrior(), weaponContainer.get(choosed));
						Warrior warriorDeUsuario = dBBD.crearWarrior(usuario.getWarrior().getId());
						warriorDeUsuario.setArma(weaponContainer.get(choosed));
						warriorDeUsuario.setHumano(true);
						usuario.setWeapon(weaponContainer.get(choosed));
						dispose();
					} catch (Errores e1) {
						errorMsg.setText("This Weapon isn't usable with your Character!");
					}
					
				}
			});
			confirmPanel.add(confirmB);
			
			errorPanel2 = new JPanel();
			errorMsg = new JLabel();
			errorPanel2.add(errorMsg);

			choosePanel = new JPanel();
			choosePanel.setLayout(new BoxLayout(choosePanel, BoxLayout.Y_AXIS));

			choosePanel.add(imagePanel);
			choosePanel.add(statsPanel);
			choosePanel.add(confirmPanel);
			choosePanel.add(errorPanel2);

			this.add(choosePanel);

			this.setTitle("Choose "+type);
			this.setSize(500,500);
			Toolkit pantalla = Toolkit.getDefaultToolkit();
			Dimension grandaria = pantalla.getScreenSize();
			int ancho = grandaria.width, alto = grandaria.height;
			this.setLocation(((ancho/2)-(this.getWidth()/2))+500,((alto/2)-(this.getHeight()/2)));
			this.setResizable(false);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
	}

	class fightPanel implements ActionListener {
		private JPanel battlePanel, userPanel, enemyPanel, generatedPanel, infoPanel, statsPanel, actionButtonsPanel, consolePanel;
		private JButton buttonFight, buttonClearConsole;
		private JTextArea console;
		private JProgressBar healthBar, powerBar, agilityBar, speedBar, defenseBar;
		private JLabel warriorImg, weaponImg, powerLbl, agilityLbl, speedLbl, defenseLbl;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (username == "") {
				error.setText("Choose an username first!");
			} else if (usuario.getWarrior()==null) {
				error.setText("Choose a Warrior first!");
			} else if (usuario.getWeapon()==null){
				error.setText("Choose a Weapon first!");
			} else {
				
				Warrior enemy = iap.iniciarRival(warriorContainer, weaponContainer);

				battlePanel = new JPanel();
				battlePanel.setLayout(new GridLayout(1,2,10,0));
				
				userPanel = generatePanel(usuario.getWarrior());
				
				enemyPanel = generatePanel(enemy);
				
				battlePanel.add(userPanel);
				battlePanel.add(enemyPanel);
				
				/**
				 * Barra de vida
				 * Imagen personaje y arma
				 * Stats:
				 * - poder
				 * - agilidad
				 * - velocidad
				 * - defensa
				 * 
				 * De jugador y bot
				 */

				actionButtonsPanel = new JPanel();
				buttonFight = new JButton("Fight");
				//buttonFight.addActionListener(   );
				buttonClearConsole = new JButton("Clear Console");
				buttonClearConsole.addActionListener( new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						console.setText("");
					}
				} );
				actionButtonsPanel.add(buttonFight);
				actionButtonsPanel.add(buttonClearConsole);

				consolePanel = new JPanel();
				console = new JTextArea(5, 70);
				consolePanel.add(console);


				principalPanel.add(battlePanel);
				principalPanel.add(actionButtonsPanel);
				principalPanel.add(consolePanel);



				titlePanel.removeAll();
				usernamePanel.removeAll();
				buttonsPanel.remove(fight);
				setSize(900,600);
				setLocationRelativeTo(null);
			}
		}
		
		
		private JPanel  generatePanel(Warrior warr) {
			generatedPanel = new JPanel();
			generatedPanel.setLayout(new BoxLayout(generatedPanel, BoxLayout.Y_AXIS));
			
			healthBar = new JProgressBar(0,warr.getPuntsDeVida());
			healthBar.setValue(warr.getPuntsDeVida());
			healthBar.setForeground(Color.green);
			healthBar.setStringPainted(true);
			generatedPanel.add(healthBar);
			
			ImageIcon warrImg = new ImageIcon("img/warriorImg/"+warr.getUrl());
			warriorImg = new JLabel(warrImg);
			generatedPanel.add(warriorImg);
			
			
			infoPanel = new JPanel();
			infoPanel.setLayout(new GridLayout(1,2));
			ImageIcon weapImg = new ImageIcon("img/weaponImg/"+warr.getArma().getUrl());
			weaponImg = new JLabel(weapImg);
			
			statsPanel = new JPanel();
			statsPanel.setLayout(new GridLayout(4,2));
			
			powerBar = new JProgressBar(0,10);
			powerBar.setValue(warr.getFuerza());
			powerBar.setForeground(Color.red);
			
			agilityBar = new JProgressBar(0,10);
			agilityBar.setValue(warr.getAgilitat());
			agilityBar.setForeground(Color.magenta);
			
			speedBar = new JProgressBar(0,10);
			speedBar.setValue(warr.getVelocitat());
			speedBar.setForeground(Color.orange);
			
			defenseBar = new JProgressBar(0,10);
			defenseBar.setValue(warr.getDefensa());
			defenseBar.setForeground(Color.blue);
			
			powerLbl = new JLabel("Power");
			agilityLbl = new JLabel("Agility");
			speedLbl = new JLabel("Speed");
			defenseLbl = new JLabel("Defense");
			
			statsPanel.add(powerLbl);
			statsPanel.add(powerBar);
			statsPanel.add(agilityLbl);
			statsPanel.add(agilityBar);
			statsPanel.add(speedLbl);
			statsPanel.add(speedBar);
			statsPanel.add(defenseLbl);
			statsPanel.add(defenseBar);
			
			
			infoPanel.add(weaponImg);
			infoPanel.add(statsPanel);
			
			
			generatedPanel.add(infoPanel);
			
			return generatedPanel;
			
		}
	}
	
	class viewRankingsWindow extends JFrame {
		
		private JPanel todo, superior, central, inferior;
		private JLabel mensaje_error, player_name, global_points, defeated_enemies, injuries_caused, injuries_suffered;
		private JLabel pn, gb, de, ic, is;
		private JComboBox player;
		private JButton mostrar;
		private ArrayList<Ranking> players;
		public viewRankingsWindow() {
			//Instancias
			this.setLayout(new FlowLayout());
			todo = new JPanel();
			todo.setLayout(new BoxLayout(todo, BoxLayout.Y_AXIS));
			superior = new JPanel();
			central = new JPanel();
			central.setLayout(new GridLayout(2,5,10,10));
			inferior = new JPanel();
			
			mostrar = new JButton("VIEW RANKINGS");
			player_name = new JLabel("Player Name");
			global_points = new JLabel("Global Points");
			defeated_enemies = new JLabel("Defeated Enemies");
			injuries_caused = new JLabel("Injuries Caused");
			injuries_suffered = new JLabel("Injuries Suffered");
			mensaje_error = new JLabel("");
			
			pn = new JLabel("");
			gb = new JLabel("");
			de = new JLabel("");
			ic = new JLabel("");
			is = new JLabel("");
			
			ConnectionBBDD con = new ConnectionBBDD();
			players = con.rankings();
			player = new JComboBox();;
			
			for (int i=0;i<players.size();i++) {
				player.addItem(players.get(i).getPlayer_name());
			}
			
			superior.add(player);
			superior.add(mostrar);
			
			//CAMPOS RANKINGS
			central.add(player_name);
			central.add(global_points);
			central.add(defeated_enemies);
			central.add(injuries_caused);
			central.add(injuries_suffered);
			
			//VALORES RANKINGS
			central.add(pn);
			central.add(gb);
			central.add(de);
			central.add(ic);
			central.add(is);
			
			inferior.add(mensaje_error);
			
			todo.add(superior);
			todo.add(central);
			central.setVisible(false);
			inferior.setVisible(false);
			todo.add(inferior);
			this.add(todo);
			
			//MOSTRAR RANKINGS EVENTO
			mostrar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					inferior.setVisible(false);
					players.clear();
					players = con.rankings();
					if (player.getSelectedIndex() == -1) {
						mensaje_error.setText("Choose a player before trying to display rankings");
						inferior.setBackground(Color.yellow);
						inferior.setVisible(true);
					}
					else {
						for (int i=0;i<players.size();i++) {
							if (players.get(i).getPlayer_name().equals(player.getSelectedItem().toString())) {
								pn.setText(players.get(i).getPlayer_name());
								gb.setText(Integer.toString(players.get(i).getGlobal_points()));
								de.setText(Integer.toString(players.get(i).getDefeated_enemies()));
								ic.setText(Integer.toString(players.get(i).getInjuries_caused()));
								is.setText(Integer.toString(players.get(i).getInjuries_suffered()));
							}
						}
						central.setVisible(true);
					}
				}
			});	
			
			
			this.setTitle("Rankings");
			this.setResizable(false);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			Toolkit pantalla = Toolkit.getDefaultToolkit();
			Dimension grandaria = pantalla.getScreenSize();
			int ancho = grandaria.width; // ancho pantalla
			int alto = grandaria.height; // alto pantalla
			this.setLocation(((ancho/2)-(this.getWidth()/2))+500,((alto/2)-(this.getHeight()/2)));
			this.setSize(800, 200);
			this.setVisible(true);
		}
	}
	
}
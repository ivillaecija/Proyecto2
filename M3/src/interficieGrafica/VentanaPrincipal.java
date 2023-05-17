package interficieGrafica;

import java.awt.BorderLayout;
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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import rankings.*;
import armas.Weapon;
import connectionBBDD.*;
import crearPersonaje.IniciarArmasPersonaje;
import crearPersonaje.WarriorContainer;
import crearPersonaje.WeaponContainer;
import error.Errores;
import mecanicaLluita.MecanicaLluita;
import sistemaPuntuacio.SistemaPuntuacio;
import usuario.Usuario;
import warriors.Warrior;

public class VentanaPrincipal {

	public static void main(String[] args) {
		new mainWindow();
	}

}

class mainWindow extends JFrame implements WindowListener{
	
	private JPanel principalPanel, titlePanel, buttonsPanel, usernamePanel, enterPanel, errorPanel;
	private JLabel title, error;
	private JButton userB, chooseCharacter, chooseWeapon, checkRanking, fight, enterB;
	private JTextField userTF;
	private String username = "";


	ConnectionBBDD cBBDD = new ConnectionBBDD();
	Usuario usuario = new Usuario();
	

	
	private WarriorContainer wac = new WarriorContainer();
	private ArrayList<Warrior> warriorContainer = wac.WarriorContainer();
	private ArrayList<Warrior> warriorContainerEnemy = wac.WarriorContainer();

	private IniciarArmasPersonaje iap = new IniciarArmasPersonaje();
	private WeaponContainer wec = new WeaponContainer();
	private ArrayList<Weapon> weaponContainer = wec.WeaponContainer();
	private ArrayList<Weapon> weaponContainerEnemy = wec.WeaponContainer();

	private MecanicaLluita ml = new MecanicaLluita();
	private SistemaPuntuacio sp = new SistemaPuntuacio();

	public mainWindow() {
		// BORRAR 
		this.addWindowListener(this);

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
				if (!usuario.getGameOver()) {
					new chooseWarriorWindow("character");
					
				}else {
					int option = JOptionPane.showOptionDialog(null, "If you want to change characters, you will have to create a character. Do you want to do it?", "Change Characters", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				    if (option == JOptionPane.YES_OPTION) {
				    	new mainWindow();
				    	dispose();
				    } else if (option == JOptionPane.NO_OPTION) {

				    }
				}	
			}
		});
		chooseWeapon = new JButton("Choose Weapon");
		chooseWeapon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!usuario.getGameOver()) {
					if (usuario.getWarrior() == null) {
						error.setText("Choose a chracter first!");
					} else {
						new chooseWeaponWindow("weapon");
					}
				}else {
					int option = JOptionPane.showOptionDialog(null, "If you want to change weapons, you will have to recreate a character. Do you want to do it?", "Change Weapon", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				    if (option == JOptionPane.YES_OPTION) {
				    	new mainWindow();
				    	dispose();
				    } else if (option == JOptionPane.NO_OPTION) {

				    }
				}
			}
		});
		chooseWeapon.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				error.setText("");
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
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
			public void focusGained(FocusEvent e) {
			}
		});
		buttonsPanel.add(chooseCharacter);
		buttonsPanel.add(chooseWeapon);
		buttonsPanel.add(checkRanking);
		buttonsPanel.add(fight);

		titlePanel = new JPanel();
		title = new JLabel(new ImageIcon("img/logo.png"));
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
		this.setSize(500, 350);
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
					ConnectionBBDD con = new ConnectionBBDD();
					username = userTF.getText();
					usuario.setNombre(username);
					dispose();
				}
			});
			enterPanel.add(userTF);
			enterPanel.add(enterB);
			this.add(enterPanel);

			this.setTitle("Enter username");
			this.setSize(270, 80);
			Toolkit pantalla = Toolkit.getDefaultToolkit();
			Dimension grandaria = pantalla.getScreenSize();
			int ancho = grandaria.width; // ancho pantalla
			int alto = grandaria.height; // alto pantalla
			this.setLocation(((ancho / 2) - (this.getWidth() / 2)), ((alto / 2) - (this.getHeight() / 2)) + 150);
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
			imagePanel.setLayout(new GridLayout(1, 3));
			// *** Imagenes de los personajes ***
			for (Warrior wa : warriorContainer) {
				JButton wb = new JButton(new ImageIcon("img/warriorImg/" + wa.getUrl()));
				wb.setPreferredSize(new Dimension(40, 40));
				wb.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// *** Estadisticas de los personajes ***
						name.setText("Name: " + wa.getNom());
						healthPoints.setText("Health points: " + wa.getPuntsDeVida());
						power.setText("Power: " + wa.getFuerza());
						agility.setText("Agility: " + wa.getAgilitat());
						speed.setText("Speed: " + wa.getVelocitat());
						defense.setText("Defense: " + wa.getDefensa());

						choosedWarrior = wa.getId() - 1;
					}
				});
				imagePanel.add(wb);
			}

			statsPanel = new JPanel();
			statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
			name = new JLabel("Name:");
			healthPoints = new JLabel("Health points:");
			power = new JLabel("Power:");
			agility = new JLabel("Agility:");
			speed = new JLabel("Speed:");
			defense = new JLabel("Defense:");
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

					// *** Guardar seleccion de personaje ***
					usuario.setWarrior(warriorContainer.get(choosedWarrior));
					System.out.println("Chosed: " + choosedWarrior);					
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

			this.setTitle("Choose " + type);
			this.setSize(450, 350);
			Toolkit pantalla = Toolkit.getDefaultToolkit();
			Dimension grandaria = pantalla.getScreenSize();
			int ancho = grandaria.width; // ancho pantalla
			int alto = grandaria.height; // alto pantalla
			this.setLocation(((ancho / 2) - (this.getWidth() / 2)) + 470, ((alto / 2) - (this.getHeight() / 2) - 22));
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
			imagePanel.setLayout(new GridLayout(3, 3));
			// *** Imagenes de los personajes ***
			for (Weapon wp : weaponContainer) {
				JButton wb = new JButton(new ImageIcon("img/weaponImg/" + wp.getUrl()));
				wb.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						choosed = wp.getId() - 1;
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
				public void actionPerformed(ActionEvent e) {
					try {
						iap.iniciarPersonaje(usuario.getWarrior(), weaponContainer.get(choosed));

						Warrior warriorDeUsuario = cBBDD.crearWarrior(usuario.getWarrior().getId());
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

			this.setTitle("Choose " + type);
			this.setSize(500, 500);
			Toolkit pantalla = Toolkit.getDefaultToolkit();
			Dimension grandaria = pantalla.getScreenSize();
			int ancho = grandaria.width, alto = grandaria.height;
			this.setLocation(((ancho / 2) - (this.getWidth() / 2)) + 500, ((alto / 2) - (this.getHeight() / 2)));
			this.setResizable(false);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
	}

	class fightPanel implements ActionListener  {
		private JPanel battlePanel, userPanel, enemyPanel, generatedPanel, infoPanel, statsPanel, actionButtonsPanel,consolePanel;
		private JButton buttonFight, buttonClearConsole;
		private JTextArea console;
		
		private JProgressBar userHealthBar;
		private JProgressBar enemyHealthBar;
		
		private JProgressBar healthBar, powerBar, agilityBar, speedBar, defenseBar;
		private JLabel warriorImg, weaponImg, powerLbl, agilityLbl, speedLbl, defenseLbl;
		
		private boolean ataque1 = false, ataque2 = false;
		private boolean gameOver = false;
		
		private int danyPersonaje = 0;
		private int danyEnemigo = 0;
		private int puntuacio = 0;
		private int danyPersonajeRonda = 0;
		private int danyEnemigoRonda = 0;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (username == "") {
				error.setText("Choose an username first!");
			} else if (usuario.getWarrior() == null) {
				error.setText("Choose a Warrior first!");
			} else if (usuario.getWeapon() == null) {
				error.setText("Choose a Weapon first!");
			} else {
				usuario.setGameOver(true);
				// se le agrega arma al warrior
				usuario.getWarrior().setArma(usuario.getWeapon());
				usuario.getWarrior().setHumano(true);
				
				// cambiar esto a Usuario
				ArrayList<Usuario> listaLuchadoresOrdenada = new ArrayList();
				// agregar usuario
				listaLuchadoresOrdenada.add(usuario);
				
				// insertar usuario en la bbdd
				usuario.setId(cBBDD.insertarUsuario(usuario.getNombre()));
				
				// generate enemy
				Usuario enemy = new Usuario();
				enemy.setWarrior(iap.iniciarRival(warriorContainerEnemy, weaponContainerEnemy));
				enemy.setWeapon(enemy.getWarrior().getArma());
				listaLuchadoresOrdenada.add(enemy);
				
				ml.OrdernarLuchadores(listaLuchadoresOrdenada);
				
				// insert battle
				cBBDD.insertarBattleInicio(enemy.getWarrior(), usuario);

				battlePanel = new JPanel();
				battlePanel.setLayout(new GridLayout(1, 2, 70, 0));

				// create user warrior panel
				userPanel = generatePanel(usuario.getWarrior());
				// create enemy warrior panel
				enemyPanel = generatePanel(enemy.getWarrior());

				battlePanel.add(userPanel);
				battlePanel.add(enemyPanel);

				actionButtonsPanel = new JPanel();

				buttonFight = new JButton("Fight");
				System.out.println(usuario.toString());
				System.out.println(enemy.toString());
				buttonFight.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
					cBBDD.insertarUsuario(usuario.getNombre());
						if (gameOver) {
							ml.OrdernarLuchadores(listaLuchadoresOrdenada);
							gameOver = false;
							cBBDD.insertarBattleInicio(enemy.getWarrior(), usuario);
							
							usuario.getWarrior().recuperarVida();
							System.out.println(usuario.toString());
							cambiarImagen(enemy.getWarrior());
							
							
							
							resetUserHealthBar(usuario.getWarrior());
							resetUserHealthBar(enemy.getWarrior());
						}
						if (listaLuchadoresOrdenada.get(0).getWarrior().getPuntsDeVida() > 0 && listaLuchadoresOrdenada.get(1).getWarrior().getPuntsDeVida() > 0) {

							int atack = ml.ataque(listaLuchadoresOrdenada.get(0).getWarrior(), listaLuchadoresOrdenada.get(1).getWarrior(),console, usuario);
							
							listaLuchadoresOrdenada.get(1).getWarrior().setPuntsDeVida(listaLuchadoresOrdenada.get(1).getWarrior().getPuntsDeVida() - atack);

							if (listaLuchadoresOrdenada.get(0).getWarrior().getHumano()) {
								danyPersonaje += atack;
								danyPersonajeRonda += atack;
								ataque1 = true;
								System.out.println("usuario ataco a enemigo");
								System.out.println("usuario ataco a enemigo");
								System.out.println("usuario ataco a enemigo");
								
								userHealthBar.setValue(userHealthBar.getValue()-atack);
							} else {
								danyEnemigo += atack;
								danyEnemigoRonda += atack;
								ataque2 = true;
								System.out.println("al reves");
								System.out.println("al reves");
								System.out.println("al reves");
								
								enemyHealthBar.setValue(enemyHealthBar.getValue()-atack);
							}

							if ((ataque1 && ataque2)|| listaLuchadoresOrdenada.get(1).getWarrior().getPuntsDeVida() <= 0) {
								cBBDD.insertarDatosRonda(danyPersonajeRonda,danyEnemigoRonda,usuario,enemy);
								ataque1 = false;
								ataque2 = false;
								danyPersonajeRonda = 0;
								danyEnemigoRonda = 0;
							}
							
							// si el denfensor de queda sin vida entrara en el IF
							if (listaLuchadoresOrdenada.get(1).getWarrior().getPuntsDeVida() <= 0) {
								usuario.setPartidasJugadas(usuario.getPartidasJugadas()+1);
						    	enemy.getWarrior().recuperarVida();
						    	usuario.getWarrior().recuperarVida();
								if (listaLuchadoresOrdenada.get(0).getWarrior().getHumano()) {									
									// gana humano
									puntuacio = sp.puntuacio(enemy);
									cBBDD.actualizarDatosBattle(danyPersonaje, danyEnemigo, puntuacio);
									puntuacio = 0;
									System.out.println("Puntos conseguidos: " + puntuacio);
									console.setText(console.getText() 
											+ "\n*************************************\n" 
											+ usuario.getNombre()+ " wins the battle"
											+"\n*************************************");
								    int option = JOptionPane.showOptionDialog(null, "Do you want to keep the fighter?", "Winner", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
								    if (option == JOptionPane.YES_OPTION) {
								    	enemy.setWarrior(iap.iniciarRival(warriorContainerEnemy, weaponContainerEnemy));
										enemy.setWeapon(enemy.getWarrior().getArma());								
										
										usuario.getWarrior().recuperarVida();
										System.out.println(usuario.toString());
										System.out.println(enemy.toString());
										gameOver=true;
										
								    } else if (option == JOptionPane.NO_OPTION) {
								    	// volver al menu principal de creacion de personaje
								    	new mainWindow();
								    	dispose();
								    }
								}else {
									// gana enemigo
									cBBDD.actualizarDatosBattle(danyPersonaje, danyEnemigo, puntuacio);
									console.setText(console.getText() 
											+ "\n*************************************\nEnemy wins the battle"
											+ "\n*************************************");
									
							        int result = JOptionPane.showOptionDialog(null, "Game Over", "Mensaje", JOptionPane.DEFAULT_OPTION,
							                JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Aceptar"}, null);

							        if (result == JOptionPane.OK_OPTION) {
							            // volver al menu principal de creacion de personaje
							        	new mainWindow();
								    	dispose();
							        }
								}
								danyPersonaje = 0;
								danyEnemigo = 0;
								danyPersonajeRonda = 0;
								danyEnemigoRonda = 0;
							} else {
								// operacion para intentar repetir ataque
								boolean repetir = ml.repetiAtac(listaLuchadoresOrdenada.get(0),
										listaLuchadoresOrdenada.get(1));
								// si el atacante no repite el ataque entrara en el IF
								if (!repetir) {
									Collections.reverse(listaLuchadoresOrdenada);
									System.out.println("No se volvera a atacar");
								} else {
									System.out.println("Se volvera a atacar");
								}
							}
							System.out.println(listaLuchadoresOrdenada.get(0).toString());
							System.out.println(listaLuchadoresOrdenada.get(1).toString());
						}
					}
				});
				
				buttonClearConsole = new JButton("Clear Console");
				buttonClearConsole.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						console.setText("");

					}
				});
				actionButtonsPanel.add(buttonFight);
				actionButtonsPanel.add(buttonClearConsole);

				consolePanel = new JPanel();
				console = new JTextArea(10, 66);

				JScrollPane scroll = new JScrollPane(console);
				consolePanel.add(scroll);

				principalPanel.add(battlePanel);
				principalPanel.add(actionButtonsPanel);
				principalPanel.add(consolePanel);

				principalPanel.remove(titlePanel);
				principalPanel.remove(usernamePanel);
				buttonsPanel.remove(fight);
				setSize(750, 550);
				setLocationRelativeTo(null);
			}
		}

		private JPanel generatePanel(Warrior warr) {

			generatedPanel = new JPanel();
			generatedPanel.setLayout(new BoxLayout(generatedPanel, BoxLayout.Y_AXIS));
			
			if (!warr.getHumano()) {
				userHealthBar = new JProgressBar(0, warr.getPuntsDeVida());
				userHealthBar.setValue(warr.getPuntsDeVida());
				userHealthBar.setForeground(Color.green);
				userHealthBar.setStringPainted(true);
				userHealthBar.setPreferredSize(new Dimension(0, 60));
				
				generatedPanel.add(userHealthBar);
			}else {
				enemyHealthBar = new JProgressBar(0, warr.getPuntsDeVida());
				enemyHealthBar.setValue(warr.getPuntsDeVida());
				enemyHealthBar.setForeground(Color.green);
				enemyHealthBar.setStringPainted(true);
				enemyHealthBar.setPreferredSize(new Dimension(0, 60));
				
				generatedPanel.add(enemyHealthBar);
			}


			JPanel imgPanel = new JPanel();
			ImageIcon warrImg = new ImageIcon("img/warriorImg/" + warr.getUrl());
			warriorImg = new JLabel(warrImg);
			imgPanel.add(warriorImg);
			generatedPanel.add(imgPanel);

			infoPanel = new JPanel();
			infoPanel.setLayout(new GridLayout(1, 2));
			ImageIcon weapImg = new ImageIcon("img/weaponImg/" + warr.getArma().getUrl());
			weaponImg = new JLabel(weapImg);

			statsPanel = new JPanel();
			statsPanel.setLayout(new GridLayout(4, 2));

			powerBar = new JProgressBar(0, 10);
			powerBar.setValue(warr.getFuerza());
			powerBar.setForeground(Color.red);

			agilityBar = new JProgressBar(0, 10);
			agilityBar.setValue(warr.getAgilitat());
			agilityBar.setForeground(Color.magenta);

			speedBar = new JProgressBar(0, 10);
			speedBar.setValue(warr.getVelocitat());
			speedBar.setForeground(Color.orange);

			defenseBar = new JProgressBar(0, 10);
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
		
		private void resetUserHealthBar(Warrior warr) {
		    if (!warr.getHumano()) {
		        userHealthBar.setMaximum(warr.getPuntsDeVida());
		        userHealthBar.setValue(warr.getPuntsDeVida());
		    } else {
		        enemyHealthBar.setMaximum(warr.getPuntsDeVida());
		        enemyHealthBar.setValue(warr.getPuntsDeVida());
		    }
		}
		
		
		public void cambiarImagen(Warrior warr) {
		    ImageIcon warrImg = new ImageIcon("img/warriorImg/" + warr.getUrl());
		    warriorImg.setIcon(warrImg);
		}
		
		


		
	}
	
	class viewRankingsWindow extends JFrame {
		
		private JPanel todo, superior, central, central2, central3, inferior;
		private JLabel mensaje_error, player_name, global_points, defeated_enemies, injuries_caused, injuries_suffered;
		private JButton mostrar;
		private ArrayList<Ranking> players;
		public viewRankingsWindow() {
			//Instancias
			this.setLayout(new FlowLayout());
			todo = new JPanel();
			todo.setLayout(new BoxLayout(todo, BoxLayout.Y_AXIS));
			superior = new JPanel();
			central = new JPanel();
			central2 = new JPanel();
			central3 = new JPanel();
			central.setLayout(new BoxLayout(central, BoxLayout.Y_AXIS));
			inferior = new JPanel();
			
			mostrar = new JButton("VIEW RANKINGS");
			player_name = new JLabel("Player Name");
			global_points = new JLabel("Global Points");
			defeated_enemies = new JLabel("Defeated Enemies");
			injuries_caused = new JLabel("Injuries Caused");
			injuries_suffered = new JLabel("Injuries Suffered");
			mensaje_error = new JLabel("");
			
			ConnectionBBDD con = new ConnectionBBDD();
			players = con.rankings();
			
			superior.add(mostrar);
			
			central2.add(player_name);
			central2.add(global_points);
			central2.add(defeated_enemies);
			central2.add(injuries_caused);
			central2.add(injuries_suffered);

			
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
					central.removeAll();
					inferior.setVisible(false);
					players.clear();
					players = con.rankings();
					if (players.size() > 0) {
						central.setLayout(new GridLayout(2,1,0,0));
						central2.setLayout(new GridLayout(1,5,10,5));
						central.add(central2);
						for (int i=0;i<players.size();i++) {
							central3.setLayout(new GridLayout(i+1,5,20,5));
							central3.add(new JLabel(players.get(i).getPlayer_name()));
							central3.add(new JLabel(Integer.toString(players.get(i).getGlobal_points(),JLabel.CENTER)));
							central3.add(new JLabel(Integer.toString(players.get(i).getDefeated_enemies())));
							central3.add(new JLabel(Integer.toString(players.get(i).getInjuries_caused())));
							central3.add(new JLabel(Integer.toString(players.get(i).getInjuries_suffered())));
						}
						central.add(central3);
						central.setVisible(true);
					}
					else {
						mensaje_error.setText("Choose a player before trying to display rankings");
						inferior.setBackground(Color.yellow);
						inferior.setVisible(true);
					}
				}
			});	
			this.setTitle("Rankings");
			this.setResizable(false);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setSize(800, 600);
			this.setLocationRelativeTo(null);;;
			this.setVisible(true);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (usuario.getPartidasJugadas()==0) {
			cBBDD.borrarBattleRounds(usuario);
			cBBDD.borrarUsuarioDeBattle(usuario);
			cBBDD.borrarUsuario(usuario);
		}else {
			cBBDD.borrarPartidaIncompleta(usuario);
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}
}

package interficieGrafica;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import armas.Weapon;
import connectionBBDD.*;
import crearPersonaje.IniciarArmasPersonaje;
import crearPersonaje.WarriorContainer;
import crearPersonaje.WeaponContainer;
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
				// TODO Auto-generated method stub

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
					usuario.setNombre(username);
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
		private WarriorContainer wac = new WarriorContainer();
		private ArrayList<Warrior> warriorContainer = wac.WarriorContainer();
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
		private IniciarArmasPersonaje iap = new IniciarArmasPersonaje();
		private WeaponContainer wec = new WeaponContainer();
		private ArrayList<Weapon> weaponContainer = wec.WeaponContainer();
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
					System.out.println(usuario.getWarrior().toString());
					try {
						Warrior warriorDeUsuario = dBBD.crearWarrior(usuario.getWarrior().getId());
						warriorDeUsuario.setArma(weaponContainer.get(choosed));
						warriorDeUsuario.setHumano(true);
						usuario.setWeapon(weaponContainer.get(choosed));
						
					} catch (Error e1) {
						errorMsg.setText("This Weapon isn't usable with your Character!");
						e1.printStackTrace();
						System.out.println(usuario.getWarrior().toString());
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
		private JPanel battlePanel, actionButtonsPanel, consolePanel;
		private JButton buttonFight, buttonClearConsole;
		private JTextArea console;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (username == "") {
				error.setText("Choose an username first!");
			} else if (usuario.getWarrior()==null) {
				error.setText("Choose a Warrior first!");
			} else if (usuario.getWeapon()==null){
				error.setText("Choose a Weapon first!");
			} else {

				battlePanel = new JPanel();
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
				//buttonClearConsole.addActionListener(  );
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
				setSize(800,700);
				setLocationRelativeTo(null);
			}
		}
	}
}

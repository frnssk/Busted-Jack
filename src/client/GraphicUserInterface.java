package client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GraphicUserInterface extends JPanel {
	private UserController controller; 
	private JButton btnLogIn = new JButton("Logga in"); //Log in display
	private JButton btnCreateUser = new JButton("Skapa ny anvädnare"); //Log in display
	private JButton btnConfirmUser = new JButton("Skapa användare"); //Creates user after entering name and password 
	private JButton btnConfirmLogIn = new JButton("Logga in"); //Check user name and password and logs in  
	private JButton btnGoToTable = new JButton("Gå till bord"); //From main menu
	private JButton btnCreateTable = new JButton("Skapa bord"); //From main menu
	private JButton btnQuit = new JButton("Avsluta"); //Sends form main menu back to log in screen 
	private JButton btnAchievements = new JButton("Achievements"); //From main menu
	private JButton btnRank = new JButton("Rank"); //From main menu, shows curretn rank
	private JButton btnEnterTable = new JButton("Gå till bord"); //Confirm after entering table code 
	private JButton btnRandomTable = new JButton("Slumpa bord"); //Send user to random table 
	private JButton btnConfirmTable = new JButton("Skapa bord"); //After making settings for creating a table 
	private JButton btnMenu = new JButton("Meny"); //back to menu after a game 

	//buttons visible in game 
	private JButton btnGameX2 = new JButton("X2");
	private JButton btnGameStop = new JButton("Stop");
	private JButton btnGameHit = new JButton("Kort");
	private JButton btnCheat = new JButton("Fuska!");
	
	//Textfields 
	private JTextField tfUsernameCreate = new JTextField();
	private JTextField tfPasswordCreate = new JTextField();
	private JTextField tfRepeatPasswordCreate = new JTextField();
	private JTextField tfEmailCreate = new JTextField();
	private JTextField tfUsernameLogIn = new JTextField();
	private JTextField tfPasswordLogIn = new JTextField();

	public GraphicUserInterface(UserController controller) {
		this.controller = controller;
//		this.controller.setUI(this);
		setLayout(new BorderLayout());
//		addListners();
		add(mainMenuScreen(), BorderLayout.CENTER);
	}

	public void updateUI(JPanel pane) {
		removeAll();
		repaint();
		revalidate();
		add(pane);
	}

	public JPanel startScreen() {
		JLabel logo = new JLabel(new ImageIcon(new ImageIcon("images/bustedjack.jpg").getImage().getScaledInstance(240, 180, Image.SCALE_DEFAULT)));
		logo.setPreferredSize(new Dimension(240, 180));
		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());
		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(10,10,10,10);

		cont.gridx = 1;
		cont.gridy = 0;
		cont.gridwidth = 2;
		pane.add(logo, cont);

		cont.anchor = GridBagConstraints.EAST;
		cont.gridx = 0;
		cont.gridy = 1;
		pane.add(btnLogIn, cont);

		cont.gridx = 2;
		cont.gridy = 1;
		pane.add(btnCreateUser, cont);

		return pane;
	}
	
	public JPanel createUserScreen() {
		JLabel lblUsername = new JLabel("Användarnamn");
		JLabel lblPassword = new JLabel("Lösenord");
		JLabel lblRepeatPassword = new JLabel("Upprepa lösenord");
		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());
//		username.setPreferredSize(new Dimension(200,20));
//		password.setPreferredSize(new Dimension(200,20));
//		repPassword.setPreferredSize(new Dimension(200,20));
		tfUsernameCreate.setPreferredSize(new Dimension(200,20));
		tfPasswordCreate.setPreferredSize(new Dimension(200,20));
		tfEmailCreate.setPreferredSize(new Dimension(200,20));
		tfRepeatPasswordCreate.setPreferredSize(new Dimension(200,20));
		
		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(10,10,10,10);
		
		cont.gridx = 0;
		cont.gridy = 0;
		pane.add(lblUsername, cont);
		
		cont.gridx = 1;
		cont.gridy = 0;
//		cont.gridwidth = 3;
		pane.add(tfUsernameCreate);
		
		cont.gridx = 0;
		cont.gridy = 1;
		pane.add(lblEmail, cont);
		
		cont.gridx = 1;
		cont.gridy = 1;
		pane.add(tfEmailCreate, cont);
		
		cont.gridx = 0;
		cont.gridy = 2;
		pane.add(lblPassword, cont);
		
		cont.gridx = 1;
		cont.gridy = 2;
		pane.add(tfPasswordCreate, cont);
		
		cont.gridx = 0;
		cont.gridy = 3;
		pane.add(lblRepeatPassword, cont);
		
		cont.gridx = 1;
		cont.gridy = 3; 
		pane.add(tfRepeatPasswordCreate, cont);
		
		cont.gridx = 1;
		cont.gridy = 4;
		pane.add(btnConfirmUser, cont);
		
		return pane;
	}
	
	public JPanel logInScreen() {
		JLabel lblUsername = new JLabel("Användarnamn");
		JLabel lblPassword = new JLabel("Lösenord");
		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());
		tfUsernameLogIn.setPreferredSize(new Dimension(200,20));
		tfPasswordLogIn.setPreferredSize(new Dimension(200,20));
		
		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(10,10,10,10);
		
		cont.gridx = 0;
		cont.gridy = 0;
		pane.add(lblUsername, cont);
		
		cont.gridx = 1;
		cont.gridy = 0;
		pane.add(tfUsernameLogIn,cont);
		
		cont.gridx = 0;
		cont.gridy = 1;
		pane.add(lblPassword,cont);
		
		cont.gridx = 1;
		cont.gridy = 1;
		pane.add(tfPasswordLogIn,cont);
		
		cont.gridx = 1;
		cont.gridy = 2;
		pane.add(btnConfirmLogIn,cont);
		
		return pane;
	}
	
	public JPanel mainMenuScreen() {
		JLabel lblPlay = new JLabel("Spela");
		JLabel lblProfile = new JLabel("Profil");
		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());
		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(10,10,10,10);
		
		cont.gridx = 0;
		cont.gridy = 0;
		pane.add(lblPlay,cont);
		
		cont.gridx = 1;
		cont.gridy = 0;
		pane.add(lblProfile,cont);
		
		cont.gridx = 0;
		cont.gridy = 1;
		pane.add(btnGoToTable,cont);
		
		cont.gridx = 1;
		cont.gridy = 1;
		pane.add(btnAchievements,cont);
		
		cont.gridx = 0;
		cont.gridy = 2;
		pane.add(btnCreateTable,cont);
		
		cont.gridx = 1;
		cont.gridy = 2;
		pane.add(btnRank,cont);
		
		cont.gridx = 0;
		cont.gridy = 3;
		pane.add(btnQuit,cont);
		
		return pane;
	}
	
	public void addListener() {
		ActionL listner = new ActionL();
	}
	
	private class ActionL implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			
		}
	}

	public static void main(String[] args) {
		UserController controller = new UserController();
		GraphicUserInterface gui = new GraphicUserInterface(controller);
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(500,300));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		frame.add(gui);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

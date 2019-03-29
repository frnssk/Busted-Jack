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
	private JButton btnRank = new JButton("Rank"); //From main menu, shows current rank
	private JButton btnEnterTable = new JButton("Gå till bord"); //Confirm after entering table code 
	private JButton btnRandomTable = new JButton("Slumpa bord"); //Send user to random table 
	private JButton btnConfirmTable = new JButton("Skapa bord"); //After making settings for creating a table 
	private JButton btnMenu = new JButton("Meny"); //back to menu after a game 

	private JRadioButton radioBtnTime = new JRadioButton();
	private JRadioButton radioBtnRounds = new JRadioButton();
	private JRadioButton radioBtnPrivate = new JRadioButton();

	private String[] arrayTime = { "5","10","15","20" };
	private String[] arrayRounds = { "5","10","15","20" };
	private String[] arrayBalance = { "100","200","300","400","500" };
	private String[] arrayMinBet = { "10","50","100","150" };
	private JComboBox comboBoxTime = new JComboBox(arrayTime);
	private JComboBox comboBoxRounds = new JComboBox(arrayRounds);
	private JComboBox comboBoxBalance = new JComboBox(arrayBalance);
	private JComboBox comboBoxMinBet = new JComboBox(arrayMinBet);


	//buttons visible in game 
	private JButton btnGameX2 = new JButton("X2");
	private JButton btnGameStop = new JButton("Stop");
	private JButton btnGameHit = new JButton("Kort");
	private JButton btnGameCheat = new JButton("Fuska!");
	private JButton btnGameExit = new JButton("Exit game");

	//Textfields 
	private JTextField tfUsernameCreate = new JTextField();
	private JTextField tfPasswordCreate = new JTextField();
	private JTextField tfRepeatPasswordCreate = new JTextField();
	private JTextField tfEmailCreate = new JTextField();
	private JTextField tfUsernameLogIn = new JTextField();
	private JTextField tfPasswordLogIn = new JTextField();
	private JTextField tfRoomCode = new JTextField();

	private JTextArea taExitScreenPlayers = new JTextArea();
	private JTextArea taExitScreenPlayerRanks = new JTextArea();
	private JTextArea taExitScreenAchievements = new JTextArea();
	private JTextArea taExitScreenNewRank = new JTextArea();

	public GraphicUserInterface(UserController controller) {
		this.controller = controller;
		//		this.controller.setUI(this);
		setLayout(new BorderLayout());
		//		addListners();
		add(exitScreen(), BorderLayout.CENTER);
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
		pane.add(tfUsernameCreate);

		cont.gridx = 0;
		cont.gridy = 1;
		pane.add(lblEmail, cont);

		cont.gridx = 1;
		pane.add(tfEmailCreate, cont);

		cont.gridx = 0;
		cont.gridy = 2;
		pane.add(lblPassword, cont);

		cont.gridx = 1;
		pane.add(tfPasswordCreate, cont);

		cont.gridx = 0;
		cont.gridy = 3;
		pane.add(lblRepeatPassword, cont);

		cont.gridx = 1; 
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
		pane.add(tfUsernameLogIn,cont);

		cont.gridx = 0;
		cont.gridy = 1;
		pane.add(lblPassword,cont);

		cont.gridx = 1;
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
		pane.add(lblProfile,cont);

		cont.gridx = 0;
		cont.gridy = 1;
		pane.add(btnGoToTable,cont);

		cont.gridx = 1;
		pane.add(btnAchievements,cont);

		cont.gridx = 0;
		cont.gridy = 2;
		pane.add(btnCreateTable,cont);

		cont.gridx = 1;
		pane.add(btnRank,cont);

		cont.gridx = 0;
		cont.gridy = 3;
		pane.add(btnQuit,cont);

		return pane;
	}

	public JPanel joinScreen() {
		JLabel lblRoomCode = new JLabel("Room Code");		
		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());
		tfRoomCode.setPreferredSize(new Dimension(200,20));

		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(10,10,10,10);

		cont.gridx = 0;
		cont.gridy = 0;
		pane.add(lblRoomCode, cont);

		cont.gridy = 1;
		pane.add(tfRoomCode, cont);

		cont.gridy = 2;
		pane.add(btnEnterTable, cont);

		cont.gridy = 3;
		pane.add(btnRandomTable, cont);

		return pane;
	}

	public JPanel createTableScreen() {
		JLabel lblTime = new JLabel("Time");
		JLabel lblRounds = new JLabel("Rounds");
		JLabel lblBalance = new JLabel("Balance");
		JLabel lblMinBet = new JLabel("Minimum Bet");
		JLabel lblPrivateMatch = new JLabel("Privte Match");
		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());
		comboBoxTime.setPreferredSize(new Dimension(60,20));
		comboBoxRounds.setPreferredSize(new Dimension(60,20));
		comboBoxBalance.setPreferredSize(new Dimension(60,20));
		comboBoxMinBet.setPreferredSize(new Dimension(60,20));

		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(10,10,10,10);

		cont.gridx = 0;
		cont.gridy = 0;
		pane.add(radioBtnTime, cont);

		cont.gridx = 1;
		pane.add(lblTime, cont);

		cont.gridx = 2;
		pane.add(comboBoxTime, cont);

		cont.gridx = 3;
		pane.add(lblPrivateMatch, cont);

		cont.gridx = 4;
		pane.add(radioBtnPrivate, cont);

		cont.gridx = 0;
		cont.gridy = 1;
		pane.add(radioBtnRounds, cont);

		cont.gridx = 1;
		pane.add(lblRounds, cont);

		cont.gridx = 2;
		pane.add(comboBoxRounds, cont);

		cont.gridx = 1;
		cont.gridy = 2;
		pane.add(lblBalance, cont);

		cont.gridx = 2;
		pane.add(comboBoxBalance, cont);

		cont.gridx = 1;
		cont.gridy = 3;
		pane.add(lblMinBet, cont);

		cont.gridx = 2;
		pane.add(comboBoxMinBet, cont);

		cont.gridx = 4;
		pane.add(btnConfirmTable, cont);

		return pane;
	}

	public JPanel gameScreen() {
		JLabel lblGameScreen = new JLabel(new ImageIcon(new ImageIcon("images/bustedjack.jpg").getImage().getScaledInstance(800, 500, Image.SCALE_DEFAULT)));//Not code to be used later. This is just to get an idea of the game size

		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());
		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(10,10,10,10);

		cont.gridx = 0;
		cont.gridy = 0;
		pane.add(btnGameX2, cont);

		cont.gridy = 1;
		pane.add(btnGameStop, cont);

		cont.gridy = 2;
		pane.add(btnGameHit, cont);

		cont.gridy = 3;
		pane.add(btnGameCheat, cont);

		cont.gridx = 1;
		cont.gridy = 4;
		pane.add(btnGameExit, cont);

		cont.gridy = 0;
		cont.gridheight = 4;
		pane.add(lblGameScreen, cont);

		return pane;
	}

	public JPanel exitScreen() {
		JLabel lblPlayers = new JLabel("Players");
		JLabel lblPlayerRanks = new JLabel("Rank");
		JLabel lblAchievements = new JLabel("Achievements");
		JLabel lblNewRank = new JLabel("Rank");

		taExitScreenPlayers.setPreferredSize(new Dimension(160, 470));
		taExitScreenPlayerRanks.setPreferredSize(new Dimension(160, 470));
		taExitScreenAchievements.setPreferredSize(new Dimension(180, 160));
		taExitScreenNewRank.setPreferredSize(new Dimension(180, 160));

		taExitScreenPlayers.setEditable(false);
		taExitScreenPlayerRanks.setEditable(false);
		taExitScreenAchievements.setEditable(false);
		taExitScreenNewRank.setEditable(false);

		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());
		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(10,10,10,10);

		cont.gridx = 0;
		cont.gridy = 0;
		pane.add(lblPlayers, cont);

		cont.gridx = 1;
		pane.add(lblPlayerRanks, cont);

		cont.gridx = 2;
		pane.add(lblAchievements, cont);

		cont.gridx = 0;
		cont.gridy = 1;
		cont.gridheight = 4;
		pane.add(taExitScreenPlayers, cont);

		cont.gridx = 1;
		cont.gridheight = 4;
		pane.add(taExitScreenPlayerRanks, cont);

		cont.gridx = 2;
		cont.gridheight = 1;
		pane.add(taExitScreenAchievements, cont);

		cont.gridy = 2;
		pane.add(lblNewRank, cont);

		cont.gridy = 3;
		pane.add(taExitScreenNewRank, cont);

		cont.gridy = 4;
		pane.add(btnMenu, cont);

		return pane;
	}

	public void addListener() {
		ActionL listner = new ActionL();
		btnLogIn.addActionListener(listner);
		btnCreateUser.addActionListener(listner);
		btnConfirmUser.addActionListener(listner);
		btnConfirmLogIn.addActionListener(listner);
		btnGoToTable.addActionListener(listner);
		btnCreateTable.addActionListener(listner);
		btnQuit.addActionListener(listner);
		btnAchievements.addActionListener(listner);
		btnRank.addActionListener(listner);
		btnEnterTable.addActionListener(listner);
		btnRandomTable.addActionListener(listner);
		btnConfirmTable.addActionListener(listner);
		btnMenu.addActionListener(listner);
	}

	private class ActionL implements ActionListener{

		public void actionPerformed(ActionEvent e) {

		}
	}

	public static void main(String[] args) {
		UserController controller = new UserController();
		GraphicUserInterface gui = new GraphicUserInterface(controller);
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(1000,600));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		frame.add(gui);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

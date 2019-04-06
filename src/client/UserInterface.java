package client;

import java.awt.*;
import resources.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import resources.Table;


/**
 * Holds the Graphic User Interface
 * Minimal logic should be done here. Main purpose is to change and update the UI 
 * @author Isak Eklund
 *
 */
public class UserInterface extends JPanel {
	private UserController controller; 

	private JButton btnLogIn; //Log in display
	private JButton btnCreateUser; //Log in display
	
	private JButton btnConfirmUser; //Creates user after entering name and password 
	
	private JButton btnConfirmLogIn; //Check user name and password and logs in  
	
	private JButton btnGoToTable;//From main menu
	private JButton btnCreateTable;//From main menu
	private JButton btnQuit;//Sends form main menu back to log in screen 
	private JButton btnAchievements;//From main menu
	private JButton btnRank;//From main menu, shows current rank
	
	private JButton btnEnterTable; //Confirm after entering table code 
	private JButton btnRandomTable; //Send user to random table 
	
	private JButton btnConfirmTable; //After making settings for creating a table 
	
	private JButton btnMenu; //back to menu after a game 

	private JButton btnGameDouble;
	private JButton btnGameStop;
	private JButton btnGameHit;
	private JButton btnGameCheat;
	private JButton btnGameExit;

	private JRadioButton radioBtnTime;
	private JRadioButton radioBtnRounds;
	private JRadioButton radioBtnPrivate;

	private JTextField tfTime;
	private JTextField tfRounds;
	private JTextField tfBalance;
	private JTextField tfMinBet;

	private JTextField tfUsernameCreate;
	private JPasswordField pfPasswordCreate;
	private JPasswordField pfRepeatPasswordCreate;
	private JTextField tfEmailCreate;
	
	private JTextField tfUsernameLogIn;
	private JPasswordField pfPasswordLogIn;
	
	private JTextField tfRoomCode;

	private JTextArea taExitScreenPlayers;
	private JTextArea taExitScreenPlayerRanks;
	private JTextArea taExitScreenAchievements;
	private JTextArea taExitScreenNewRank;

	private String strUsername; //During development to show user name in menu



	/**
	 * Constructor for the user interface.
	 * Connects it to a controller and add the listeners for buttons
	 * @param controller - the controller in the application
	 */
	public UserInterface(UserController controller) {
		this.controller = controller;
		this.controller.setUI(this);
		setLayout(new BorderLayout());
		addListeners();
		add(startScreen(), BorderLayout.CENTER);
	}

	/**
	 * Used to update the UI
	 * @param pane - new Panel with updated UI
	 */
	public void updateUI(JPanel pane) {
		removeAll();
		repaint();
		revalidate();
		add(pane);
	}

	public char[] getPassword() {
		return pfPasswordCreate.getPassword();
	}

	public JPanel startScreen() {
		JLabel logo = new JLabel(new ImageIcon(new ImageIcon("images/bustedjack.jpg").getImage().getScaledInstance(240, 180, Image.SCALE_DEFAULT)));
		logo.setPreferredSize(new Dimension(240, 180));

		btnLogIn = new JButton("Login");
		btnCreateUser = new JButton("Create new user");

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

		pane.setBackground(new Color(0,153,0));

		return pane;
	}

	public JPanel createUserScreen() {
		JLabel lblUsername = new JLabel("Username");
		JLabel lblPassword = new JLabel("Password");
		JLabel lblRepeatPassword = new JLabel("Repeat password");
		JLabel lblEmail = new JLabel("Email");

		btnConfirmUser = new JButton("Create user");
		
		tfUsernameCreate = new JTextField();
		pfPasswordCreate = new JPasswordField();
		pfRepeatPasswordCreate = new JPasswordField();
		tfEmailCreate = new JTextField();
		tfUsernameCreate.setPreferredSize(new Dimension(200,20));
		pfPasswordCreate.setPreferredSize(new Dimension(200,20));
		tfEmailCreate.setPreferredSize(new Dimension(200,20));
		pfRepeatPasswordCreate.setPreferredSize(new Dimension(200,20));

		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());

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
		pane.add(pfPasswordCreate, cont);

		cont.gridx = 0;
		cont.gridy = 3;
		pane.add(lblRepeatPassword, cont);

		cont.gridx = 1; 
		pane.add(pfRepeatPasswordCreate, cont);

		cont.gridx = 1;
		cont.gridy = 4;
		pane.add(btnConfirmUser, cont);

		pane.setBackground(new Color(0,153,0));

		return pane;
	}

	public JPanel logInScreen() {
		JLabel lblUsername = new JLabel("Username");
		JLabel lblPassword = new JLabel("Password");

		btnConfirmLogIn = new JButton("Login");

		tfUsernameLogIn = new JTextField();
		pfPasswordLogIn = new JPasswordField();
		tfUsernameLogIn.setPreferredSize(new Dimension(200,20));
		pfPasswordLogIn.setPreferredSize(new Dimension(200,20));

		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());

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
		pane.add(pfPasswordLogIn,cont);

		cont.gridx = 1;
		cont.gridy = 2;
		pane.add(btnConfirmLogIn,cont);

		pane.setBackground(new Color(0,153,0));

		return pane;
	}

	public JPanel mainMenuScreen() {
		JLabel lblPlay = new JLabel("Play");
		JLabel lblProfile = new JLabel("Profile");
		JLabel lblUsername = new JLabel("Welcome " + strUsername + "!"); 

		btnGoToTable = new JButton("Go to table"); 
		btnCreateTable = new JButton("Create table");
		btnQuit = new JButton("Quit");
		btnAchievements = new JButton("Achievements");
		btnRank = new JButton("Rank");

		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());

		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(10,10,10,10);

		cont.gridx = 0;
		cont.gridy = 0;
		pane.add(lblUsername, cont);

		cont.gridx = 0;
		cont.gridy = 1;
		pane.add(lblPlay,cont);

		cont.gridx = 1;
		pane.add(lblProfile,cont);

		cont.gridx = 0;
		cont.gridy = 2;
		pane.add(btnGoToTable,cont);

		cont.gridx = 1;
		pane.add(btnAchievements,cont);

		cont.gridx = 0;
		cont.gridy = 3;
		pane.add(btnCreateTable,cont);

		cont.gridx = 1;
		pane.add(btnRank,cont);

		cont.gridx = 0;
		cont.gridy = 4;
		pane.add(btnQuit,cont);

		pane.setBackground(new Color(0,153,0));

		return pane;
	}

	public JPanel joinScreen() {
		JLabel lblRoomCode = new JLabel("Room Code");	

		btnEnterTable = new JButton("Go to table");
		btnRandomTable = new JButton("Random table");

		tfRoomCode = new JTextField();
		tfRoomCode.setPreferredSize(new Dimension(200,20));

		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());

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

		pane.setBackground(new Color(0,153,0));

		return pane;
	}

	public JPanel createTableScreen() {		
		JLabel lblTime = new JLabel("Time");
		JLabel lblRounds = new JLabel("Rounds");
		JLabel lblBalance = new JLabel("Balance");
		JLabel lblMinBet = new JLabel("Minimum Bet");
		JLabel lblPrivateMatch = new JLabel("Private Match");

		tfTime = new JTextField("0");
		tfRounds = new JTextField("0");
		tfBalance = new JTextField("0");
		tfMinBet = new JTextField("0");
		tfTime.setPreferredSize(new Dimension(80,20));
		tfRounds.setPreferredSize(new Dimension(80,20));
		tfBalance.setPreferredSize(new Dimension(80,20));
		tfMinBet.setPreferredSize(new Dimension(80,20));
		
		radioBtnTime = new JRadioButton();
		radioBtnRounds = new JRadioButton();
		radioBtnPrivate = new JRadioButton();
		
		btnConfirmTable = new JButton("Create table"); 

		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());

		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(10,10,10,10);

		cont.gridx = 0;
		cont.gridy = 0;
		pane.add(radioBtnTime, cont);

		cont.gridx = 1;
		pane.add(lblTime, cont);

		cont.gridx = 2;
		pane.add(tfTime, cont);

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
		pane.add(tfRounds, cont);

		cont.gridx = 1;
		cont.gridy = 2;
		pane.add(lblBalance, cont);

		cont.gridx = 2;
		pane.add(tfBalance, cont);

		cont.gridx = 1;
		cont.gridy = 3;
		pane.add(lblMinBet, cont);

		cont.gridx = 2;
		pane.add(tfMinBet, cont);

		cont.gridx = 4;
		pane.add(btnConfirmTable, cont);

		pane.setBackground(new Color(0,153,0));

		return pane;
	}

	public JPanel gameScreen() {
		JLabel lblGameScreen = new JLabel(new ImageIcon(new ImageIcon("images/bustedjack.jpg").getImage().getScaledInstance(800, 500, Image.SCALE_DEFAULT)));//Not code to be used later. This is just to get an idea of the game size

		btnGameDouble = new JButton("Double");
		btnGameStop = new JButton("Stay");
		btnGameHit = new JButton("Hit");
		btnGameCheat = new JButton("Cheat!");
		btnGameExit = new JButton("Exit game");

		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());

		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(10,10,10,10);

		cont.gridx = 0;
		cont.gridy = 0;
		pane.add(btnGameDouble, cont);

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

		pane.setBackground(new Color(0,153,0));

		return pane;
	}

	public JPanel exitScreen() {
		JLabel lblPlayers = new JLabel("Players");
		JLabel lblPlayerRanks = new JLabel("Rank");
		JLabel lblAchievements = new JLabel("Achievements");
		JLabel lblNewRank = new JLabel("Rank");

		btnMenu = new JButton("Main Menu");

		taExitScreenPlayers = new JTextArea();
		taExitScreenPlayerRanks = new JTextArea();
		taExitScreenAchievements = new JTextArea();
		taExitScreenNewRank = new JTextArea();
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

		pane.setBackground(new Color(0,153,0));

		return pane;
	}

	public void errorMessageUsername() {
		JLabel errorMessage = new JLabel("Username already taken. Try again.");

		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());

		cont.anchor = GridBagConstraints.FIRST_LINE_START;
		cont.insets = new Insets(10,10,10,10);

		pane.add(errorMessage, cont);

		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(400,200));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		frame.add(pane);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void errorMessagePassword() {
		JLabel errorMessage = new JLabel("Invalid password. Have to be between 6-12 characters");

		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());

		cont.anchor = GridBagConstraints.FIRST_LINE_START;
		cont.insets = new Insets(10,10,10,10);

		pane.add(errorMessage, cont);

		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(400,200));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		frame.add(pane);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void addListeners() {
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
		btnGameDouble.addActionListener(listner);
		btnGameStop.addActionListener(listner);
		btnGameHit.addActionListener(listner);
		btnGameCheat.addActionListener(listner);
		btnGameExit.addActionListener(listner);
	}

	private class ActionL implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnLogIn) {
				updateUI(logInScreen());
			}
			if(e.getSource() == btnCreateUser) {
				updateUI(createUserScreen());
			}
			if(e.getSource() == btnConfirmUser) {
				strUsername = tfUsernameCreate.getText();
				controller.checkNameAvailability(strUsername);
			}
			if(e.getSource() == btnConfirmLogIn) {
				strUsername = tfUsernameLogIn.getText(); //During development to show user name in menu
				updateUI(mainMenuScreen());
			}
			if(e.getSource() == btnGoToTable) {
				updateUI(joinScreen());
			}
			if(e.getSource() == btnCreateTable) {
				updateUI(createTableScreen());
			}
			if(e.getSource() == btnQuit) {
				updateUI(startScreen());
			}
			if(e.getSource() == btnAchievements) {
				//code to come
			}
			if(e.getSource() == btnRank) {
				//code to come
			}
			if(e.getSource() == btnEnterTable) {
				updateUI(gameScreen());
			}
			if(e.getSource() == btnRandomTable) {
				updateUI(gameScreen());
			}
			if(e.getSource() == btnConfirmTable) {
				Table table = new Table(Integer.parseInt(tfTime.getText()), Integer.parseInt(tfRounds.getText()), Integer.parseInt(tfBalance.getText()), Integer.parseInt(tfMinBet.getText()));
				controller.sendNewTable(table);
				updateUI(gameScreen());
			}
			if(e.getSource() == btnMenu) {
				updateUI(mainMenuScreen());
			}
			if(e.getSource() == btnGameDouble) {
				//code to come
			}
			if(e.getSource() == btnGameStop) {
				//code to come
			}
			if(e.getSource() == btnGameHit) {
				//code to come
			}
			if(e.getSource() == btnGameCheat) {
				//code to come
			}
			if(e.getSource() == btnGameExit) {
				updateUI(mainMenuScreen());
			}

		}
	}


}

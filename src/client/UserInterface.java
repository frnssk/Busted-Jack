package client;

import java.awt.*;
import resources.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;


/**
 * Holds the Graphic User Interface
 * Minimal logic should be done here. Main purpose is to change and update the UI 
 * @author Isak Eklund
 *
 */
public class UserInterface extends JPanel {
	private UserController controller; 

	private JButton btnLogIn = new JButton("Login"); //Log in display
	private JButton btnCreateUser = new JButton("Create new user"); //Log in display
	private JButton btnConfirmUser = new JButton("Create user"); //Creates user after entering name and password 
	private JButton btnConfirmLogIn = new JButton("Login"); //Check user name and password and logs in  
	private JButton btnBackToStart = new JButton("Back"); //From login screen back to start screen
	private JButton btnGoToTable = new JButton("Go to table");//From main menu
	private JButton btnCreateTable = new JButton("Create table");//From main menu
	private JButton btnLogOut = new JButton("Log out");//Sends form main menu back to log in screen 
	private JButton btnAchievements = new JButton("Achievements");;//From main menu
	private JButton btnRank = new JButton("Rank");//From main menu, shows current rank
	private JButton btnEnterTable = new JButton("Go to table"); //Confirm after entering table code 
	private JButton btnRandomTable = new JButton("Random table"); //Send user to random table 
	private JButton btnConfirmTable = new JButton("Create table"); //After making settings for creating a table 
	private JButton btnMenu = new JButton("Main Menu"); //back to menu after a game 
	private JButton btnGameDouble = new JButton("Double");
	private JButton btnGameStop = new JButton("Stay");
	private JButton btnGameHit = new JButton("Hit");
	private JButton btnGameCheat = new JButton("Cheat!");
	//	private JButton btnGameExit = new JButton("Exit game");

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

	private int currentRank = 0;
	private String currentTitle = "";
	private String nextTitle = "";

	private String strUsername; //During development to show user name in menu
	private Color green = new Color(65,136,14);
	private Color gold = new Color(245, 230, 93);

	private ImageIcon menuBackground = new ImageIcon(new ImageIcon("images/BJ_menu.png").getImage().getScaledInstance(1000, 600, Image.SCALE_DEFAULT));

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
		pane.setBackground(green);
		add(pane);
	}

	public char[] getPassword() {
		return pfPasswordCreate.getPassword();
	}

	public void setRank(int rank) {
		this.currentRank = rank;
	}

	public void setTitle(String title) {
		this.currentTitle = title;
	}

	public void setNextTitle(String nextTitle) {
		this.nextTitle = nextTitle;
	}

	public JPanel startScreen() {
		ImageIcon image = new ImageIcon(new ImageIcon("images/BJ_Logo_AD.png").getImage().getScaledInstance(1000, 600, Image.SCALE_DEFAULT));
		JLabel logo = new JLabel();
		logo.setIcon(image);

		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());
		logo.setLayout(new GridBagLayout());

		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(70,70,70,70);

		cont.gridx = 0;
		cont.gridy = 1;
		logo.add(btnLogIn, cont);

		cont.gridx = 0;
		cont.gridy = 2;
		logo.add(btnCreateUser, cont);

		pane.add(logo);

		return pane;
	}

	public JPanel createUserScreen() {
		JLabel lblUsername = new JLabel("Username");
		JLabel lblPassword = new JLabel("Password (between 6-12 characters)");
		lblUsername.setForeground(gold);
		lblPassword.setForeground(gold);
		JLabel lblBackground = new JLabel(menuBackground);
		lblBackground.setLayout(new GridBagLayout());

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

		cont.anchor = GridBagConstraints.EAST;
		cont.insets = new Insets(20,20,20,20);

		cont.gridx = 0;
		cont.gridy = 0;
		lblBackground.add(lblUsername, cont);

		cont.gridx = 1;
		lblBackground.add(tfUsernameCreate);

		cont.gridx = 0;
		cont.gridy = 1;
		lblBackground.add(lblPassword, cont);

		cont.gridx = 1;
		lblBackground.add(pfPasswordCreate, cont);

		cont.anchor = GridBagConstraints.CENTER;
		cont.gridx = 1;
		cont.gridy = 2;
		lblBackground.add(btnConfirmUser, cont);

		cont.gridy = 3;
		lblBackground.add(btnBackToStart, cont);

		pane.add(lblBackground);

		return pane;
	}

	public JPanel logInScreen() {
		JLabel lblUsername = new JLabel("Username");
		JLabel lblPassword = new JLabel("Password");
		lblUsername.setForeground(gold);
		lblPassword.setForeground(gold);
		JLabel lblBackground = new JLabel(menuBackground);
		lblBackground.setLayout(new GridBagLayout());

		tfUsernameLogIn = new JTextField();
		pfPasswordLogIn = new JPasswordField();
		tfUsernameLogIn.setPreferredSize(new Dimension(200,20));
		pfPasswordLogIn.setPreferredSize(new Dimension(200,20));

		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());

		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(20,20,20,20);

		cont.gridx = 0;
		cont.gridy = 0;
		lblBackground.add(lblUsername, cont);

		cont.gridx = 1;
		lblBackground.add(tfUsernameLogIn,cont);

		cont.gridx = 0;
		cont.gridy = 1;
		lblBackground.add(lblPassword,cont);

		cont.gridx = 1;
		lblBackground.add(pfPasswordLogIn,cont);

		cont.gridx = 1;
		cont.gridy = 2;
		lblBackground.add(btnConfirmLogIn,cont);

		cont.gridy = 3;
		lblBackground.add(btnBackToStart, cont);

		pane.add(lblBackground);

		return pane;
	}

	public JPanel mainMenuScreen() {
		JLabel lblPlay = new JLabel("Play");
		JLabel lblProfile = new JLabel("Profile");
		JLabel lblUsername = new JLabel("Welcome " + strUsername + "!"); 
		lblPlay.setForeground(gold);
		lblProfile.setForeground(gold);
		lblUsername.setForeground(gold);

		JLabel lblBackground = new JLabel(menuBackground);
		lblBackground.setLayout(new GridBagLayout());

		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());

		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(15,15,15,15);

		cont.gridx = 0;
		cont.gridy = 0;
		lblBackground.add(lblUsername, cont);

		cont.gridx = 0;
		cont.gridy = 1;
		lblBackground.add(lblPlay,cont);

		cont.gridx = 1;
		lblBackground.add(lblProfile,cont);

		cont.gridx = 0;
		cont.gridy = 2;
		lblBackground.add(btnGoToTable,cont);

		cont.gridx = 1;
		lblBackground.add(btnAchievements,cont);

		cont.gridx = 0;
		cont.gridy = 3;
		lblBackground.add(btnCreateTable,cont);

		cont.gridx = 1;
		lblBackground.add(btnRank,cont);

		cont.gridx = 0;
		cont.gridy = 4;
		lblBackground.add(btnLogOut,cont);

		pane.add(lblBackground);

		return pane;
	}

	public JPanel rankScreen() {
		JLabel lblCurrentRank = new JLabel("Your Rank: " + currentRank);
		JLabel lblCurrentTitle = new JLabel("Your Title: " + currentTitle);
		JLabel lblNextTitle = new JLabel("Next Title: " + nextTitle);
		lblCurrentTitle.setForeground(gold);
		lblCurrentRank.setForeground(gold);
		lblNextTitle.setForeground(gold);

		JLabel lblBackground = new JLabel(menuBackground);
		lblBackground.setLayout(new GridBagLayout());

		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());

		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(20,20,20,20);

		cont.gridx = 0;
		cont.gridy = 0;
		lblBackground.add(lblCurrentRank, cont);

		cont.gridx = 1;
		lblBackground.add(lblCurrentTitle, cont);

		cont.gridy = 1;
		lblBackground.add(lblNextTitle, cont);

		cont.gridy = 2;
		lblBackground.add(btnMenu, cont);

		pane.add(lblBackground);

		return pane;
	}

	public JPanel achievementsScreen() {
		JLabel lblAchievements = new JLabel("Achievements:");
		lblAchievements.setForeground(gold);
		JLabel[] lblArray = {new JLabel("Play one game"), new JLabel("Win one game"), new JLabel("Cheat in a game"), 
				new JLabel("Get Busted"), new JLabel("Bust a friend"), new JLabel("Win 10 games"), 
				new JLabel("Win without cheating"), new JLabel("Win with a CheatHeat above 50%"), 
				new JLabel("Win with a CheatHeat above 70%"), new JLabel("Win with a CheatHeat above 90%")};
		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());

		JLabel lblBackground = new JLabel(menuBackground);
		lblBackground.setLayout(new GridBagLayout());

		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(10,10,10,10);

		cont.gridx = 0;
		cont.gridy = 0;
		lblBackground.add(lblAchievements, cont);

		for(int i = 1; i < lblArray.length; i++ ){
			lblArray[i].setForeground(gold);
			cont.gridy = i;
			lblBackground.add(lblArray[i], cont);
			if(i == (lblArray.length - 1)) {
				cont.gridy = i + 1;
				pane.add(btnMenu, cont);
			}
		}

		pane.add(lblBackground);

		return pane;
	}

	public JPanel joinScreen() {
		JLabel lblRoomCode = new JLabel("Room Code");	
		lblRoomCode.setForeground(gold);

		JLabel lblBackground = new JLabel(menuBackground);
		lblBackground.setLayout(new GridBagLayout());
		
		tfRoomCode = new JTextField();
		tfRoomCode.setPreferredSize(new Dimension(200,20));

		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());

		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(20,20,20,20);

		cont.gridx = 0;
		cont.gridy = 0;
		lblBackground.add(lblRoomCode, cont);

		cont.gridy = 1;
		lblBackground.add(tfRoomCode, cont);

		cont.gridy = 2;
		lblBackground.add(btnEnterTable, cont);

		cont.gridy = 3;
		lblBackground.add(btnRandomTable, cont);

		pane.add(lblBackground);
		
		return pane;
	}

	public JPanel createTableScreen() {		
		JLabel lblTime = new JLabel("Minutes");
		JLabel lblRounds = new JLabel("Rounds");
		JLabel lblBalance = new JLabel("Balance");
		JLabel lblMinBet = new JLabel("Minimum Bet");
		JLabel lblPrivateMatch = new JLabel("Private Match");
		lblTime.setForeground(gold);
		lblRounds.setForeground(gold);
		lblBalance.setForeground(gold);
		lblMinBet.setForeground(gold);
		lblPrivateMatch.setForeground(gold);
		
		JLabel lblBackground = new JLabel(menuBackground);
		lblBackground.setLayout(new GridBagLayout());

		tfTime = new JTextField("0");
		tfRounds = new JTextField("0");
		tfBalance = new JTextField("0");
		tfMinBet = new JTextField("0");
		tfTime.setPreferredSize(new Dimension(80,20));
		tfRounds.setPreferredSize(new Dimension(80,20));
		tfBalance.setPreferredSize(new Dimension(80,20));
		tfMinBet.setPreferredSize(new Dimension(80,20));
		
		radioBtnPrivate = new JRadioButton();

		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());

		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(15,15,15,15);

		cont.gridx = 1;
		lblBackground.add(lblTime, cont);

		cont.gridx = 2;
		lblBackground.add(tfTime, cont);

		cont.gridx = 3;
		lblBackground.add(lblPrivateMatch, cont);

		cont.gridx = 4;
		lblBackground.add(radioBtnPrivate, cont);

		cont.gridx = 1;
		lblBackground.add(lblRounds, cont);

		cont.gridx = 2;
		lblBackground.add(tfRounds, cont);

		cont.gridx = 1;
		cont.gridy = 2;
		lblBackground.add(lblBalance, cont);

		cont.gridx = 2;
		lblBackground.add(tfBalance, cont);

		cont.gridx = 1;
		cont.gridy = 3;
		lblBackground.add(lblMinBet, cont);

		cont.gridx = 2;
		lblBackground.add(tfMinBet, cont);

		cont.gridx = 4;
		lblBackground.add(btnConfirmTable, cont);
		
		pane.add(lblBackground);

		return pane;
	}

	public JPanel gameScreen() {
		JLabel lblGameScreen = new JLabel(new ImageIcon(new ImageIcon("images/BJ_Logo_AD.png").getImage().getScaledInstance(1000, 600, Image.SCALE_DEFAULT)));//Not code to be used later. This is just to get an idea of the game size

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
		lblPlayers.setForeground(gold);
		lblPlayerRanks.setForeground(gold);
		lblAchievements.setForeground(gold);
		lblNewRank.setForeground(gold);
		
		JLabel lblBackground = new JLabel(menuBackground);
		lblBackground.setLayout(new GridBagLayout());

		taExitScreenPlayers = new JTextArea();
		taExitScreenPlayerRanks = new JTextArea();
		taExitScreenAchievements = new JTextArea();
		taExitScreenNewRank = new JTextArea();
		taExitScreenPlayers.setPreferredSize(new Dimension(150, 250));
		taExitScreenPlayerRanks.setPreferredSize(new Dimension(150, 250));
		taExitScreenAchievements.setPreferredSize(new Dimension(150, 60));
		taExitScreenNewRank.setPreferredSize(new Dimension(150, 60));

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
		lblBackground.add(lblPlayers, cont);

		cont.gridx = 1;
		lblBackground.add(lblPlayerRanks, cont);

		cont.gridx = 2;
		lblBackground.add(lblAchievements, cont);

		cont.gridx = 0;
		cont.gridy = 1;
		cont.gridheight = 3;
		lblBackground.add(taExitScreenPlayers, cont);

		cont.gridx = 1;
		cont.gridheight = 3;
		lblBackground.add(taExitScreenPlayerRanks, cont);

		cont.gridx = 2;
		cont.gridheight = 1;
		lblBackground.add(taExitScreenAchievements, cont);

		cont.gridy = 2;
		lblBackground.add(lblNewRank, cont);

		cont.gridy = 3;
		lblBackground.add(taExitScreenNewRank, cont);

		cont.gridy = 4;
		lblBackground.add(btnMenu, cont);
		
		pane.add(lblBackground);

		return pane;
	}

	public void errorMessageUsername() {
		JLabel errorMessage = new JLabel("Username already taken. Try again.");
		errorMessage.setForeground(gold);

		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());
		pane.setBackground(Color.BLACK);

		
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
		JLabel errorMessage = new JLabel("Invalid password");
		errorMessage.setForeground(gold);

		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());
		pane.setBackground(Color.BLACK);

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
		ActionL listener = new ActionL();
		btnLogIn.addActionListener(listener);
		btnBackToStart.addActionListener(listener);
		btnCreateUser.addActionListener(listener);
		btnConfirmUser.addActionListener(listener);
		btnConfirmLogIn.addActionListener(listener);
		btnGoToTable.addActionListener(listener);
		btnCreateTable.addActionListener(listener);
		btnLogOut.addActionListener(listener);
		btnAchievements.addActionListener(listener);
		btnRank.addActionListener(listener);
		btnEnterTable.addActionListener(listener);
		btnRandomTable.addActionListener(listener);
		btnConfirmTable.addActionListener(listener);
		btnMenu.addActionListener(listener);
		btnGameDouble.addActionListener(listener);
		btnGameStop.addActionListener(listener);
		btnGameHit.addActionListener(listener);
		btnGameCheat.addActionListener(listener);
	}

	private class ActionL implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnLogIn) {
				updateUI(logInScreen());
			}
			if(e.getSource() == btnBackToStart) {
				updateUI(startScreen());
			}
			if(e.getSource() == btnCreateUser) {
				updateUI(createUserScreen());
			}
			if(e.getSource() == btnConfirmUser) {
				controller.createRegisterRequest(tfUsernameCreate.getText(), pfPasswordCreate.getPassword());
				strUsername = tfUsernameCreate.getText();
			}
			if(e.getSource() == btnConfirmLogIn) {
				controller.createLoginRequest(tfUsernameLogIn.getText(), pfPasswordLogIn.getPassword());
				strUsername = tfUsernameLogIn.getText(); //During development to show user name in menu
			}
			if(e.getSource() == btnGoToTable) {
				updateUI(joinScreen());
			}
			if(e.getSource() == btnCreateTable) {
				updateUI(createTableScreen());
			}
			if(e.getSource() == btnLogOut) {
				controller.createLogOutRequest(strUsername);
				updateUI(startScreen());
			}
			if(e.getSource() == btnAchievements) {
				updateUI(achievementsScreen());
			}
			if(e.getSource() == btnRank) {
				updateUI(rankScreen());
			}
			if(e.getSource() == btnEnterTable) {
				controller.checkTableId(Integer.parseInt(tfRoomCode.getText()));
				updateUI(gameScreen());
			}
			if(e.getSource() == btnRandomTable) {
				updateUI(gameScreen());
			}
			if(e.getSource() == btnConfirmTable) {
				controller.createGameInfo(Integer.parseInt(tfTime.getText()), Integer.parseInt(tfRounds.getText()), Integer.parseInt(tfBalance.getText()), Integer.parseInt(tfMinBet.getText()));
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

		}
	}


}

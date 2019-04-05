package client;

import java.awt.*;
import resources.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import resources.Table;

public class UserInterface extends JPanel {
	private UserController controller; 
	private User user;
	private boolean usernameAvailability = false;

	private JButton btnLogIn = new JButton("Login"); //Log in display
	private JButton btnCreateUser = new JButton("Create new user"); //Log in display
	private JButton btnConfirmUser = new JButton("Create user"); //Creates user after entering name and password 
	private JButton btnConfirmLogIn = new JButton("Login"); //Check user name and password and logs in  
	private JButton btnGoToTable = new JButton("Go to table"); //From main menu
	private JButton btnCreateTable = new JButton("Create table"); //From main menu
	private JButton btnQuit = new JButton("Quit"); //Sends form main menu back to log in screen 
	private JButton btnAchievements = new JButton("Achievements"); //From main menu
	private JButton btnRank = new JButton("Rank"); //From main menu, shows current rank
	private JButton btnEnterTable = new JButton("Go to table"); //Confirm after entering table code 
	private JButton btnRandomTable = new JButton("Random table"); //Send user to random table 
	private JButton btnConfirmTable = new JButton("Create table"); //After making settings for creating a table 
	private JButton btnMenu = new JButton("Main Menu"); //back to menu after a game 

	private JRadioButton radioBtnTime = new JRadioButton();
	private JRadioButton radioBtnRounds = new JRadioButton();
	private JRadioButton radioBtnPrivate = new JRadioButton();

	//TextFields for creating a new table
	private JTextField tfTime = new JTextField("0");
	private JTextField tfRounds = new JTextField("0");
	private JTextField tfBalance = new JTextField("0");
	private JTextField tfMinBet = new JTextField("0");

	private JTextField tfUsernameCreate = new JTextField();
	private JPasswordField pfPasswordCreate = new JPasswordField();
	private JPasswordField pfRepeatPasswordCreate = new JPasswordField();
	private JTextField tfEmailCreate = new JTextField();
	private JTextField tfUsernameLogIn = new JTextField();
	private JPasswordField pfPasswordLogIn = new JPasswordField();
	private JTextField tfRoomCode = new JTextField();

	private JTextArea taExitScreenPlayers = new JTextArea();
	private JTextArea taExitScreenPlayerRanks = new JTextArea();
	private JTextArea taExitScreenAchievements = new JTextArea();
	private JTextArea taExitScreenNewRank = new JTextArea();

	private String strUsername; //During development to show user name in menu

	//buttons visible in game 
	private JButton btnGameDouble = new JButton("Double");
	private JButton btnGameStop = new JButton("Stay");
	private JButton btnGameHit = new JButton("Hit");
	private JButton btnGameCheat = new JButton("Cheat!");
	private JButton btnGameExit = new JButton("Exit game");


	public UserInterface(UserController controller) {
		this.controller = controller;
		this.controller.setUI(this);
		setLayout(new BorderLayout());
		addListeners();
		add(startScreen(), BorderLayout.CENTER);
	}

	public void updateUI(JPanel pane) {
		removeAll();
		repaint();
		revalidate();
		add(pane);
	}

	public void setUsernameAvailability(boolean bool) {
		usernameAvailability = bool;
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

		pane.setBackground(new Color(0,153,0));

		return pane;
	}

	public JPanel createUserScreen() {
		JLabel lblUsername = new JLabel("Username");
		JLabel lblPassword = new JLabel("Password");
		JLabel lblRepeatPassword = new JLabel("Repeat password");
		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());

		tfUsernameCreate.setPreferredSize(new Dimension(200,20));
		pfPasswordCreate.setPreferredSize(new Dimension(200,20));
		tfEmailCreate.setPreferredSize(new Dimension(200,20));
		pfRepeatPasswordCreate.setPreferredSize(new Dimension(200,20));

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
		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());
		tfUsernameLogIn.setPreferredSize(new Dimension(200,20));
		pfPasswordLogIn.setPreferredSize(new Dimension(200,20));

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

		pane.setBackground(new Color(0,153,0));

		return pane;
	}

	public JPanel createTableScreen() {
		JLabel lblTime = new JLabel("Time");
		JLabel lblRounds = new JLabel("Rounds");
		JLabel lblBalance = new JLabel("Balance");
		JLabel lblMinBet = new JLabel("Minimum Bet");
		JLabel lblPrivateMatch = new JLabel("Private Match");
		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());
		tfTime.setPreferredSize(new Dimension(80,20));
		tfRounds.setPreferredSize(new Dimension(80,20));
		tfBalance.setPreferredSize(new Dimension(80,20));
		tfMinBet.setPreferredSize(new Dimension(80,20));

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

	public void errorMessage() {
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
				user = new User(tfUsernameCreate.getText());
				updateUI(createUserScreen());
			}
			if(e.getSource() == btnConfirmUser) {
				strUsername = tfUsernameCreate.getText(); //During development to show user name in menu
				controller.checkNameAvailability(strUsername);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
=======
				
>>>>>>> c279896efdb5f0e592f4b7889a95aba742373413
=======
				
>>>>>>> c279896efdb5f0e592f4b7889a95aba742373413
=======
				
>>>>>>> c279896efdb5f0e592f4b7889a95aba742373413
				if(usernameAvailability == true) {
					updateUI(mainMenuScreen());
				} else {
					errorMessage();
				}
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
				System.out.println("1");
				Table table = new Table(Integer.parseInt(tfTime.getText()), Integer.parseInt(tfRounds.getText()), Integer.parseInt(tfBalance.getText()), Integer.parseInt(tfMinBet.getText()));
				System.out.println("2");
				controller.sendNewTable(table);
				System.out.println("3");
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

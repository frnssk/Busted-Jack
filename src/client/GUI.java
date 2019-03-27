package client;

import java.awt.*;

import javax.swing.*;

public class GUI extends JPanel {
	private UserController controller; 
	private JButton btnLogIn = new JButton("Logga in"); //Log in display
	private JButton btnCreateUser = new JButton("Skapa ny anvädnare"); //Log in display
	private JButton btnConfirmUser = new JButton("Skapa användare"); //Creates user after entering name and password 
	private JButton btnConfirmLogIn = new JButton("Logga in"); //Check user name and password and logs in  
	private JButton btnGoToTabel = new JButton("Gå till bord"); //From main menu
	private JButton btnCreateTabel = new JButton("Skapa bord"); //From main menu
	private JButton btnQuit = new JButton("Avsluta"); //Sends form main menu back to log in screen 
	private JButton btnAchievements = new JButton("Achievements"); //From main menu
	private JButton btnRank = new JButton("Rank"); //From main menu, shows curretn rank
	private JButton btnEnterTable = new JButton("Gå till bord"); //Confirm after entering table code 
	private JButton btnRandomTable = new JButton("Slumpa bord"); //Send user to random table 
	private JButton btnConfirmTabel = new JButton("Skapa bord"); //After making settings for creating a table 
	private JButton btnMenu = new JButton("Meny"); //back to menu after a game 

	//buttons visible in game 
	private JButton btnGameX2 = new JButton("X2");
	private JButton btnGameStop = new JButton("Stop");
	private JButton btnGameHit = new JButton("Kort");
	private JButton btnChrat = new JButton("Fuska!");
	
	//Textfields 
	private JTextField tfUserName = new JTextField();
	private JTextField tfPassword = new JTextField();
	private JTextField tfRepeatPassword = new JTextField();
	private JTextField tfEmail = new JTextField();

	public GUI(UserController controller) {
		this.controller = controller;
//		this.controller.setUI(this);
		setLayout(new BorderLayout());
//		addListners();
		add(createUser(), BorderLayout.CENTER);
	}

	public void updateUI(JPanel pane) {
		removeAll();
		repaint();
		revalidate();
		add(pane);
	}

	public JPanel logInScreen() {
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
	
	public JPanel createUser() {
		JLabel username = new JLabel("Användarnamn");
		JLabel password = new JLabel("Lösenord");
		JLabel repeatPassword = new JLabel("Upprepa lösenord");
		JLabel email = new JLabel("Email");
		GridBagConstraints cont = new GridBagConstraints();
		JPanel pane = new JPanel(new GridBagLayout());
//		username.setPreferredSize(new Dimension(200,20));
//		password.setPreferredSize(new Dimension(200,20));
//		repPassword.setPreferredSize(new Dimension(200,20));
		tfUserName.setPreferredSize(new Dimension(200,20));
		tfPassword.setPreferredSize(new Dimension(200,20));
		tfEmail.setPreferredSize(new Dimension(200,20));
		tfRepeatPassword.setPreferredSize(new Dimension(200,20));
		
		cont.anchor = GridBagConstraints.CENTER;
		cont.insets = new Insets(10,10,10,10);
		
		cont.gridx = 0;
		cont.gridy = 0;
		pane.add(username, cont);
		
		cont.gridx = 1;
		cont.gridy = 0;
//		cont.gridwidth = 3;
		pane.add(tfUserName);
		
		cont.gridx = 0;
		cont.gridy = 1;
		pane.add(email, cont);
		
		cont.gridx = 1;
		cont.gridy = 1;
		pane.add(tfEmail, cont);
		
		cont.gridx = 0;
		cont.gridy = 2;
		pane.add(password, cont);
		
		cont.gridx = 1;
		cont.gridy = 2;
		pane.add(tfPassword, cont);
		
		cont.gridx = 0;
		cont.gridy = 3;
		pane.add(repeatPassword, cont);
		
		cont.gridx = 1;
		cont.gridy = 3; 
		pane.add(tfRepeatPassword, cont);
		
		cont.gridx = 1;
		cont.gridy = 4;
		pane.add(btnConfirmUser, cont);
		
		return pane;
	}

	public static void main(String[] args) {
		UserController controller = new UserController();
		GUI gui = new GUI(controller);
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(500,300));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		frame.add(gui);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

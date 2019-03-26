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

	public GUI(UserController controller) {
		this.controller = controller;
//		this.controller.setUI(this);
		setLayout(new BorderLayout());
//		addListners();
		add(logInScreen(), BorderLayout.CENTER);
	}

	private void updateUI(JPanel pane) {
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

package client;

import java.io.IOException;

import resources.*;

/**
 * The main logic in the user application 
 * Hold the logic and connects the UI to the client 
 * @author Isak Eklund
 *
 */
public class UserController {
	private UserClient client;
	private UserInterface ui;
	private User user;
	private boolean nameOk = false;
	private String[] titles = {"Godfather", "Boss", "Gentleman", 
								"Grinder", "Gambler", "Peasant"}; 
	
	public UserController(UserClient client) {
		this.client = client;
		client.setUserController(this);
	}
	
	public void setUI(UserInterface ui) {
		this.ui = ui;
	}
	
	public void setRankAndTitle(int rank) {
		ui.setRank(rank);
		
		if(rank >= 1500) {
			ui.setTitle(titles[1]);
		} else if (rank >= 800) {
			ui.setTitle(titles[2]);
			ui.setNextTitle(titles[1]);
		} else if (rank >= 400) {
			ui.setTitle(titles[3]);
			ui.setNextTitle(titles[2]);
		} else if(rank >= 200) {
			ui.setTitle(titles[4]);
			ui.setNextTitle(titles[3]);
		} else if(rank >= 100) {
			ui.setTitle(titles[5]);
			ui.setNextTitle(titles[4]);
		} else if (rank < 100) {
			ui.setTitle(titles[6]);
			ui.setNextTitle(titles[5]);
		}
	}
	public void createRegisterRequest(String username, char[] password) {
		RegisterRequest request = new RegisterRequest(username, password);
		client.sendRegisterRequest(request);
	}
	
	public void createLoginRequest(String username, char[] password) {
		LoginRequest request = new LoginRequest(username, password);
		client.sendLoginRequest(request);
	}
	
	/**
	 * Send the entered user name form the UI to the client to check availability 
	 * Sleeps for 0.5 seconds while waiting for response from the server 
	 * @param username -  a String with the entered user name
	 */
//	public void checkNameAvailability(String username) {
//		client.checkNameAvailability(username);
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
//	}
	
	/**
	 * If user name comes back ok, checks if the user name fulfills the requirements.
	 * Shows error message if name is taken 
	 * @param available - an int that is checked and changes are made based on its outcome 
	 */
//	public void setUsernameAvailability(int available) {
//		if(available == 2) {
//			nameOk = true;
//			client.checkPassword(ui.getPassword());
//
//		} else if(available == 1) {
//			ui.errorMessageUsername();
//		}
//	}
	
	/**
	 * Checks if the password fulfills requirements.
	 * If it does the user is sent to the main menu. If not they get an error message 
	 * @param passwordOk - an int that is checked and changes are made based on its outcome 
	 */
	public void setPassword(int passwordOk) {
		if(passwordOk == 3) {
			ui.updateUI(ui.mainMenuScreen());
		}else if(passwordOk == 4) {
			ui.errorMessagePassword();
		}
	}
	
	public void connect(User user) {
		try {
			client.connect(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}

package client;

import java.io.IOException;

import resources.*;

public class UserController {
	private UserClient client;
	private UserInterface ui;
	private User user;
	
	public UserController(UserClient client) {
		this.client = client;
	}
	
	public void setUI(UserInterface ui) {
		this.ui = ui;
	}
	
	public void sendNewTable(Table table) {
		System.out.println("4");
		client.sendTable(table);
	}
	
	public void checkNameAvailability(String username) {
		client.checkNameAvailability(username);
	}
	
	public void setUsernameAvailability(boolean bool) {
		ui.setUsernameAvailability(bool);
	}
	
	public void connect(User user) {
		try {
			client.connect(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}

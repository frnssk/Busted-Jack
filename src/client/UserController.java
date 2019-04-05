package client;

import java.io.IOException;

import resources.*;

public class UserController {
	private UserClient client;
	private UserInterface ui;
	private User user;
	private boolean nameOk = false;
	
	public UserController(UserClient client) {
		this.client = client;
		client.setUserController(this);
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
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void setUsernameAvailability(int available) {
		if(available == 2) {
			nameOk = true;
			client.checkPassword(ui.getPassword());
			
//			ui.updateUI(ui.mainMenuScreen());
		} else if(available == 1) {
			ui.errorMessageUsername();
		}
	}
	
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

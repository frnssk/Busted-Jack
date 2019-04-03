package client;

import resources.Table;

public class UserController {
	private UserClient client;
	private UserInterface ui;
	
	public UserController(UserClient client) {
		this.client = client;
	}
	
	public void setUI(UserInterface ui) {
		this.ui = ui;
	}
	
	public void sendNewTable(Table table) {
		System.out.println("2");
		client.sendTable(table);
	}
	
	

}

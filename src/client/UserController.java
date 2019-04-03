package client;

import resources.Table;

public class UserController {
	private UserClient client;
	private UserInterface ui;
	private Table table;
	
	public UserController(UserClient client) {
		this.client = client;
	}
	
	public void setUI(UserInterface ui) {
		this.ui = ui;
	}
	
	public void sendNewTable(Table table) {
		this.table = table;
		System.out.println("4");
		client.sendTable(this.table);
	}
	
	

}

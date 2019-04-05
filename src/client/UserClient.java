package client;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

import resources.Table;
import resources.User;

public class UserClient {
	private Socket socket;
	private UserController userController;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String ip;
	private int port;
	private User user;
	private boolean receiving = false;
	private Connection connection;
	private LinkedList<User> allRegisteredUsers;

	public UserClient(String ip, int port) throws IOException{
		this.ip = ip;
		this.port = port;
		try {
			socket = new Socket(ip, port);
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
		}catch(IOException ioException) {
			ioException.printStackTrace();
		}
		if(connection == null) {
			connection = new Connection();
			connection.start();
		}

	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public void connect(User user) throws IOException {
		if(!receiving) {
			this.user = user;

			try {
				socket = new Socket(ip, port);
				output = new ObjectOutputStream(socket.getOutputStream());
				input = new ObjectInputStream(socket.getInputStream());
			}catch(IOException ioException) {
				ioException.printStackTrace();
			}
			if(connection == null) {
				connection = new Connection();
				connection.start();
			}
		}else{
			setUser(user);
		}

	}

	public void checkNameAvailability(String username) {
		System.out.println("Sending name to server"); //For testing
		try {
			output.writeObject(username);
			output.flush();
		} catch(IOException  e) {
			e.printStackTrace();
		}
	}

	public void checkPassword(char[] password) {
		System.out.println("Sending password to server"); //For testing
		try {
			output.writeObject(password);
			output.flush();
		} catch(IOException  e) {
			e.printStackTrace();
		}
	}


	public void sendTable(Table table) {
		System.out.println("5");
		try {
			output.writeObject(table);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("4");
	}

	private class Connection extends Thread {
		public void run() {

			while(true) {
				try {
					Object obj = input.readObject();

					//For checking user name availability
					if(obj instanceof Integer) {
						int available = (Integer) obj;
						if(available == 1 || available == 2) {
							System.out.println("Username: ok");
							userController.setUsernameAvailability(available);
						}
						
						if(available == 3 || available == 4) {
							System.out.println("Password: ok");
							userController.setPassword(available);
						}
					}


				}catch(IOException | ClassNotFoundException exception) {
					exception.printStackTrace();
				}
			}
		}
	}

}

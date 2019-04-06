package client;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

import resources.Table;
import resources.User;

/**
 *  Class responsible for all connection to the server, from the user side.  
 *  Contains one inner class for handling incoming data from the server 
 *  @author Isak Eklund
 */
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

	
	/**
	 * Constructs the UserCLient object and connects to server on give IP and port
	 * @param ip - What IP address to connect to
	 * @param port - what port on the given IP address to connect to
	 * @throws IOException
	 */
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

	/**
	 * Connects to the server and sends a User object
	 * @param user - Tells the server what user is connecting 
	 * @throws IOException
	 */
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

	/**
	 * Sends a user name to the server and make sure that user name is available 
	 * @param username - String with the entered user name
	 */
	public void checkNameAvailability(String username) {
		System.out.println("Sending name to server"); //For testing
		try {
			output.writeObject(username);
			output.flush();
		} catch(IOException  e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends the given password to the server to make sure it fulfills the requirement for a password
	 * @param password - a char array with the entered password
	 */
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
		try {
			output.writeObject(table);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inner class that let the client listen for incoming data from the server
	 * Runs on a separate thread 
	 * @author Isak Eklund
	 *
	 */
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

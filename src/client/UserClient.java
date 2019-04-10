package client;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

import resources.*;

/**
 *  Class responsible for all connection to the server, from the user side.  
 *  Contains one inner class for handling incoming data from the server 
 *  @author Isak Eklund
 */
public class UserClient {
	private Socket socket;
	private UserController controller;
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
		this.controller = userController;
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
			username = null;
		} catch(IOException  e) {
			e.printStackTrace();
		}
	}

	public void sendTable(Table table) {
		try {
			output.writeObject(table);
			output.flush();
			table = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendLoginRequest(LoginRequest request) {
		try {
			output.writeObject(request);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendRegisterRequest(RegisterRequest request) {
		try {
			output.writeObject(request);
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
					if(obj instanceof String) {
						String available = (String) obj;
						controller.checkCreatedUser(available);
					}

				}catch(IOException | ClassNotFoundException exception) {
					exception.printStackTrace();
				}
			}
		}
	}

}

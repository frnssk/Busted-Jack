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
	private boolean receiving = true;
	private Connection connection;

	
	/**
	 * Constructs the UserCLient object and connects to server on give IP and port
	 * @param ip - What IP address to connect to
	 * @param port - what port on the given IP address to connect to
	 * @throws IOException
	 */
	public UserClient(String ip, int port) throws IOException{
		this.ip = ip;
		this.port = port;
//		try {
//			socket = new Socket(ip, port);
//			output = new ObjectOutputStream(socket.getOutputStream());
//			input = new ObjectInputStream(socket.getInputStream());
//		}catch(IOException ioException) {
//			ioException.printStackTrace();
//		}
//		if(connection == null) {
//			connection = new Connection();
//			connection.start();
//		}

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
	public void connect() throws IOException {
			try {
				socket = new Socket(ip, port);
				output = new ObjectOutputStream(socket.getOutputStream());
				input = new ObjectInputStream(socket.getInputStream());
				receiving = true;
			}catch(IOException ioException) {
				ioException.printStackTrace();
			}
			if(connection == null) {
				connection = new Connection();
				connection.start();
			}
	}
	
	public void disconnect() {
		try {
			receiving = false;
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendLoginRequest(LoginRequest request) {
		try {
			connect();
			output.writeObject(request);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendRegisterRequest(RegisterRequest request) {
		try {
			connect();
			output.writeObject(request);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendLogOutRequest(LogOutRequest logOutRequest) {
		try {
			output.writeObject(logOutRequest);
			output.flush();
			disconnect();
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
			while(receiving) {
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

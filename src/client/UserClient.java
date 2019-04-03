package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;


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
		
	}
	
	public void setUserController(UserController userController) {
		this.userController = userController;
	}
	
	private void connect(User user) throws IOException {
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
		}
		
	}
	
	private class Connection extends Thread {
		public void run() {
			try {
				Object obj = input.readObject();
				System.out.println(obj.toString());
			
			}catch(IOException | ClassNotFoundException exception) {
				exception.printStackTrace();
			}
		}
	}

}

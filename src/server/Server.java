package server;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import resources.*;

/*
 * @author RasmusOberg
 */
public class Server {

	private LinkedList<User> registeredUsers = new LinkedList<>(); //LinkedList to hold all registered users
	private UserHandler clients;
	private ArrayList<Table> activeTables = new ArrayList<>();
	private int roomIdCounter;
	private LinkedList<Callback> listeners = new LinkedList<>();

	/*
	 * Constructor to instantiate the server
	 */
	public Server(int port) {
		clients = new UserHandler();
		new ClientReceiver(port).start();
		}


	/*
	 * Inner class which listens after new connections / clients trying to connect
	 * When a client connects, it creates a new instance of ClientHandler to 
	 * handle the client
	 */
	private class ClientReceiver extends Thread{
		private int port;

		public ClientReceiver(int port) {
			this.port = port;
		}

		public void run() {
			Socket socket = null;

			try(ServerSocket serverSocket = new ServerSocket(port)){
				TextWindow.println(("Server started, listening for clients on port nr " + serverSocket.getLocalPort())); //Assistance
				while(true) {
					try {
						socket = serverSocket.accept();
						System.out.println("Client connected");
						new ClientHandler(socket);
					}catch(IOException ioException) {
						ioException.printStackTrace();
						if(socket!=null) {
							socket.close();
						}
					}
				}

			}catch(IOException ioException) {
				ioException.printStackTrace();
			}

		}

	}

	/*
	 * Starts a new Thread for every client
	 * @author RasmusOberg
	 */
	private class ClientHandler extends Thread{
		private Socket socket;
		private ObjectOutputStream output;
		private ObjectInputStream input;
		private User user;
		private Object object;
		private UserHandler userHandler;

		public ClientHandler(Socket socket) throws IOException {
			this.socket = socket;
			start();
			TextWindow.println("ClientHandler started");
		}

		public void updateActiveUsers(LinkedList<User> activeUsers) {
			try {
				output.writeObject(activeUsers);
				output.flush();
			}catch(IOException ioException) {
				ioException.printStackTrace();
			}
		}

		public void run() {
			try {
				output = new ObjectOutputStream(socket.getOutputStream());
				input = new ObjectInputStream(socket.getInputStream());

				try {
					Object obj = input.readObject();
					if(obj instanceof Table) {
						Table table = (Table)obj;
						int roomID = table.getRoomID();
						TextWindow.println("HEUREKA");
//						if(roomID = null) {
//							roomID.setID();
//						}
					}
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}

//				userHandler.newClientConnect(user, this); 
				//Adds this ClientHandler to the UserHandlerList of online users


			}catch(IOException ioException) {
				ioException.printStackTrace();
			}
		}

	}

	/*
	 * @author RasmusOberg
	 */
	private class UserHandler {
		private HashMap<User, ClientHandler> activeUsers = new HashMap<>();

		//connects a new client
		public synchronized void newClientConnect(User user, ClientHandler clientHandler) {
			if(userIsRegistered(user) == false) {
				registerNewUser(user);
			}
			addNewActiveUser(user, clientHandler);
		}

		//adds new user to activeUsers-HashMap
		public synchronized void addNewActiveUser(User user, ClientHandler clientHandler) {
			activeUsers.put(user, clientHandler);
			System.out.println(user.getUsername() + " aktiv");
			updateActiveUsers();
		}

		//register new user to registeredUsers-LinkedList
		public synchronized void registerNewUser(User user) {
			registeredUsers.add(user);
			System.out.println(user.getUsername() + " registrerad");
			updateActiveUsers();
		}

		////Return whether or not user already is registered
		public synchronized boolean userIsRegistered(User user) {
			return registeredUsers.contains(user);
		}

		//returns whether or not a user is online
		public synchronized boolean userIsOnline(User user) {
			return activeUsers.containsKey(user);
		}

		public synchronized LinkedList<User> getActiveUsers(){
			LinkedList<User> currentActiveUsers = new LinkedList<>(activeUsers.keySet());
			return currentActiveUsers;
		}

		public void updateActiveUsers() {
			LinkedList<User> currentActiveUsers = new LinkedList<>();
			for(int i = 0; i < currentActiveUsers.size(); i++) {
				this.activeUsers.get(currentActiveUsers.get(i)).updateActiveUsers(currentActiveUsers);
			}
		}

	}

}



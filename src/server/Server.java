package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import resources.User;

/*
 * @author RasmusOberg
 */
public class Server {
	private static final int DEFAULT_PORT = 15000;
	private static final int DEFAULT_NUMBER_OF_PLAYERS = 2;
	private static final int DEFAULT_STARTING_MONEY = 5000;
	private static final int DEAFAULT_MINIMUM_BET = 500;
	private static final int DEFAULT_NUMBER_OF_DECKS = 6;
	private static final int DEFAULT_MINIMUM_CARDS_BEFORE_RESHUFFLE = 78;
	private int serverPort;
	private int playersPerTable;
	private int startingMoney;
	private int minimumBet;
	private int numberOfDecks;
	private int minimumCardsBeforeReshuffle;
	
	private LinkedList<User> registeredUsers = new LinkedList<>(); //LinkedList to hold all registered users
	private UserHandler clients;

	/*
	 * Constructor to instantiate the server
	 */
	public Server(int port) {
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
				System.out.println("Lyssnar på port nr " + serverSocket.getLocalPort()); //Assistance
				while(true) {
					try {
						socket = serverSocket.accept();
						new ClientHandler(socket).start();
//						new UserHandler(socket).start();
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
			System.out.println("Server startad");
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
				
				output.writeObject(registeredUsers);
				output.flush();
				
				userHandler.newClientConnect(user, this); //Adds this ClientHandler to the UserHandlerList of online users
				
				
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

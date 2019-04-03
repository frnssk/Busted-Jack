package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import resources.*;

/*
 * @author RasmusOberg
 */
public class Server {
//	private static final int DEFAULT_PORT = 15000;
//	private static final int DEFAULT_NUMBER_OF_PLAYERS = 2;
//	private static final int DEFAULT_STARTING_MONEY = 5000;
//	private static final int DEAFAULT_MINIMUM_BET = 500;
//	private static final int DEFAULT_NUMBER_OF_DECKS = 6;
//	private static final int DEFAULT_MINIMUM_CARDS_BEFORE_RESHUFFLE = 78;
//	private int serverPort;
//	private int playersPerTable;
//	private int startingMoney;
//	private int minimumBet;
//	private int numberOfDecks;
//	private int minimumCardsBeforeReshuffle;
	
	private LinkedList<User> registeredUsers = new LinkedList<>(); //LinkedList to hold all registered users
	private UserHandler clients;
	private ArrayList<Table> activeTables = new ArrayList<>();
	private int roomIdCounter;
	private ServerUI serverUI;
	private LinkedList<Callback> listeners = new LinkedList<>();

	/*
	 * Constructor to instantiate the server
	 */
	public Server(int port) {
		clients = new UserHandler();
		new ClientReceiver(port).start();
	}
	
	public void addServerListener(Callback callback) {
		listeners.add(callback);
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
				serverUI.logActivity("Lyssnar på port nr " + serverSocket.getLocalPort()); //Assistance
				while(true) {
					try {
						serverUI.logActivity("1");
						socket = serverSocket.accept();
						serverUI.logActivity("2");
						new ClientHandler(socket);
						serverUI.logActivity("3");
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
			System.out.println("4");
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
				
				try {
					Object obj = input.readObject();
					if(obj instanceof Table) {
						Table table = (Table)obj;
						int roomID = table.getRoomID();
						System.out.println("HEUREKA");
//						if(roomID = null) {
//							roomID.setID();
//						}
					}
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				
				
//				userHandler.newClientConnect(user, this); //Adds this ClientHandler to the UserHandlerList of online users
				
				
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

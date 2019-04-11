package server;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
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

	private HashMap<String, char[]> userPasswords = new HashMap<>(); //HashMap that holds all usernames and passwords
	private UserHandler userHandler;
	private ArrayList<Table> activeTables = new ArrayList<>();
	private int tableIdCounter;
	//	private LinkedList<Callback> listeners = new LinkedList<>();

	/*
	 * Constructor to instantiate the server
	 */
	public Server(int port) {
		//		clients = new UserHandler();
		new ClientReceiver(port).start();
	}


	public synchronized void setTableId(Table table) {
		table.setTableId(tableIdCounter);
		tableIdCounter++;
	}

	public void readUsersFromDatabase() {
		User user;
		boolean read = true;

		try (FileInputStream fis = new FileInputStream("files/userlist.txt");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			while(read) {
				user = (User) ois.readObject();
				if(user != null) {
					registeredUsers.add(user);
					user = null;
				} else {
					read = false;
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addUserToDatabase(User user) {
		try(FileOutputStream fos = new FileOutputStream("files/userlist.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos)){
			oos.writeObject(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
						TextWindow.println("Client connected");
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
		private Object obj;
		private boolean isOnline = true;
		//		private UserHandler userHandler;

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

		
		/*
		 * Checks whether or not a username is registered
		 */
		public boolean isUserRegistered(String name) {
			for(int i = 0; i < registeredUsers.size(); i++) {
				User compare = registeredUsers.get(i);
				String compareName = compare.getUsername();
				if(compareName.equals(name)) {
					return true;
				}
			}
			return false;
		}

		
		/*
		 * Checks if the given password matches the password that is stored
		 */
		public boolean passwordMatchUser(String username, char[] password) {
			char[] array = userPasswords.get(username);
			return Arrays.equals(array, password);
		}

		/*
		 * Checks whether or not a username is already in use
		 */
		public boolean checkUsernameAvailability(String name) {
			for(int i = 0; i < registeredUsers.size(); i++) {
				User compare = registeredUsers.get(i);
				String compareName = compare.getUsername();
				if(compareName.equals(name)) {
					return false;
				}

			}
			return true;
		}
		
		public User getUser(String name) {
			for(int i = 0; i < registeredUsers.size(); i++) {
				User compare = registeredUsers.get(i);
				String compareName = compare.getUsername();
				if(compareName.equals(name)) {
					return compare;
				}
			}
			return null;
		}

		/*
		 * Checks whether or not a password is the required length
		 */
		public boolean isPasswordOkay(char[] password) {
			if(password.length < 6 || password.length >= 12) {
				return false;
			}else{
				return true;
			}
		}
		

		/*
		 * Keeps on running as long as the client is still connected
		 * Reads objects from the client and depending on the type of object - the server acts accordingly
		 */
		public void run() {
			try(ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
					ObjectInputStream input = new ObjectInputStream(socket.getInputStream())){
				while(isOnline) {
					try {
						obj = input.readObject();
						String choice = "";
						
						/**
						 * This if-statement is used to register new users, so they
						 * are able to login and play the game
						 */
						if(obj instanceof RegisterRequest) {
							RegisterRequest registerRequest = (RegisterRequest)obj;
							if(checkUsernameAvailability(registerRequest.getUsername())) {
								TextWindow.println(registerRequest.getUsername() + " är ledigt."); //Assistance
								if(isPasswordOkay((registerRequest.getPassword()))) {
									TextWindow.println((registerRequest.getUsername()) + " har angett ett godkänt lösenord."); //Assistance
									User temporary = new User(registerRequest.getUsername());
									temporary.setPassword(registerRequest.getPassword());
									registeredUsers.add(temporary);
									userPasswords.put(registerRequest.getUsername(), registerRequest.getPassword());

									addUserToDatabase(temporary);
									TextWindow.println("User-objekt skapat för " + registerRequest.getUsername());//Assistance
                  
									choice = "USER_TRUE";
								}else { 
									TextWindow.println(registerRequest.getUsername() + " har angett ett icke godkänt lösenord."); //Assistance
									choice = "PASSWORD_FALSE";
								}

							}else {
								choice = "USERNAME_FALSE";
							}
							output.writeObject(choice);
							output.flush();
						}
						
						/*
						 * Logins user
						 */
						else if(obj instanceof LoginRequest) {
							choice = "";
							LoginRequest loginRequest = (LoginRequest)obj;
							if(isUserRegistered(loginRequest.getUsername())) {
								TextWindow.println(loginRequest.getUsername() + " finns."); //Assistance
								if(passwordMatchUser(loginRequest.getUsername(), loginRequest.getPassword())){
									choice = "LOGIN_SUCCES";
									TextWindow.println(loginRequest.getUsername() + " är inloggad."); //Assistance
									output.writeObject(choice);
									output.flush();
									
									//get User-object for the name and add in userHandler.addNewActiveUser
								}else {
									choice = "LOGIN_FAIL";
									output.writeObject(choice);
									output.flush();
									TextWindow.println(loginRequest.getUsername() + " kan inte sitt lösenord HAHAHA"); //Assistance
								}
							}
						}
						/*
						 * Disconnects the client, and stops the current clienthandler-loop
						 */
						else if(obj instanceof LogOutRequest) {
							isOnline = false;
							TextWindow.println("Client disconnected.");

							
						}
						/*
						 * Take the information stored in the GameInfo-object, extracts it and creates a new Table-object
						 */
						else if(obj instanceof GameInfo) {
							GameInfo gameInfo = (GameInfo)obj;
							Table table = new Table(gameInfo.getTime(), gameInfo.getRounds(), gameInfo.getBalance(), gameInfo.getMinBet());
							setTableId(table);
						}

					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
						break;
					}
				}
				TextWindow.println("DEAD");

				userHandler.newUserConnect(user, this); 
//				Adds this ClientHandler to the UserHandlerList of online users

			}catch(Exception ioException) {
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
		public synchronized void newUserConnect(User user, ClientHandler clientHandler) {
			activeUsers.put(user, clientHandler);
		}

		//adds new user to activeUsers-HashMap
		public synchronized void addNewActiveUser(User user) {
//			activeUsers.add(user);
			TextWindow.println(user.getUsername() + " aktiv");
			updateActiveUsers();
		}

		//returns whether or not a user is online
//		public synchronized boolean userIsOnline(User user) {
//			return activeUsers.contains(user);
//		}

		public void updateActiveUsers() {
			
		}

	}
}



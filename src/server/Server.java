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
	private HashMap<String, char[]> userPasswords = new HashMap<>();
	private UserHandler clients;
	private ArrayList<Table> activeTables = new ArrayList<>();
	private int roomIdCounter;
	//	private LinkedList<Callback> listeners = new LinkedList<>();

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
		
		public boolean passwordMatchUser(String username, char[] password) {
			return (password.equals(userPasswords.get(username)));
		}

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

		public boolean isPasswordOkay(char[] password) {
			if(password.length < 6 || password.length >= 12) {
				return false;
			}else{
				return true;
			}
		}

		public void run() {
			try(ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
					ObjectInputStream input = new ObjectInputStream(socket.getInputStream())){
				//				output = new ObjectOutputStream(socket.getOutputStream());
				//				input = new ObjectInputStream(socket.getInputStream());
				//				Object obj = null;
				while(true) {
					try {
						obj = input.readObject();
						String choice = "";
						if(obj instanceof RegisterRequest) {
							RegisterRequest registerRequest = (RegisterRequest)obj;
							if(checkUsernameAvailability(registerRequest.getUsername())) {
								TextWindow.println(registerRequest.getUsername() + " är ledigt.");
								if(isPasswordOkay((registerRequest.getPassword()))) {
									TextWindow.println((registerRequest.getUsername()) + " har angett ett godkänt lösenord.");
									User temporary = new User(registerRequest.getUsername());
									temporary.setPassword(registerRequest.getPassword());
									registeredUsers.add(temporary);
									userPasswords.put(registerRequest.getUsername(), registerRequest.getPassword());
									TextWindow.println("User-objekt skapat för " + registerRequest.getUsername());
									choice = "USER_TRUE";
								}else { 
									TextWindow.println(registerRequest.getUsername() + " har angett ett icke godkänt lösenord.");
									choice = "PASSWORD_FALSE";
								}

							}else {
								choice = "USERNAME_FALSE";
							}
							output.writeObject(choice);
							output.flush();
						}else if(obj instanceof LoginRequest) {
							choice = "";
							LoginRequest loginRequest = (LoginRequest)obj;
							if(isUserRegistered(loginRequest.getUsername())) {
								TextWindow.println(loginRequest.getUsername() + " finns.");
								if(passwordMatchUser(loginRequest.getUsername(), loginRequest.getPassword())){
									choice = "LOGIN_SUCCES";
									TextWindow.println(loginRequest.getUsername() + " är inloggad.");
									output.writeObject(choice);
									output.flush();
								}
							}
						}



						//							if(obj instanceof String) {
						//								name = (String)obj;
						//								int a = 0;
						//								if(registeredUsers.contains(name)) {
						//									a = 1;
						//									output.writeObject(a);
						//									output.flush();
						//									TextWindow.println(name + " finns redan.");
						//								}else {
						//									a = 2;
						//									output.writeObject(a);
						//									output.flush();
						//									//								registeredUsers.add(name);
						//									TextWindow.println(name + " är ledigt.");
						//								}
						//							}
						//							if(obj instanceof char[]) {
						//								char[] password = (char[])obj;
						//								if(isPasswordOkay(password)) {
						//									TextWindow.println(name + " har angett ett godkänt lösenord.");
						//									User temporary = new User(name);
						//									TextWindow.println("User-objekt skapat för " + name);
						//									temporary.setPassword(password);
						//									registeredUsers.add(temporary.getUsername());
						//									TextWindow.println(name + " tilllagd i RegisteredUsers.");
						//									int a = 3;
						//									output.writeObject(a);
						//									output.flush();
						//									TextWindow.println("Hit kommer vi");
						//								}else{
						//									TextWindow.println(name + " har angett ett icke godkänt lösenord.");
						//									int a = 4;
						//									output.writeObject(a);
						//									output.flush();
						//								}
						//							}

					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
						TextWindow.println("Client disconnected.");
						break;
					}
				}

				//				userHandler.newClientConnect(user, this); 
				//Adds this ClientHandler to the UserHandlerList of online users


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
		public synchronized void newClientConnect(User user, ClientHandler clientHandler) {
			if(userIsRegistered(user) == false) {
				registerNewUser(user);
			}
			addNewActiveUser(user, clientHandler);
		}

		//adds new user to activeUsers-HashMap
		public synchronized void addNewActiveUser(User user, ClientHandler clientHandler) {
			activeUsers.put(user, clientHandler);
			TextWindow.println(user.getUsername() + " aktiv");
			updateActiveUsers();
		}

		//register new user to registeredUsers-LinkedList
		public synchronized void registerNewUser(User user) {
			//			registeredUsers.add(user);
			TextWindow.println(user.getUsername() + " registrerad");
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

		//		public synchronized LinkedList<User> getActiveUsers(){
		//			LinkedList<User> currentActiveUsers = new LinkedList<>(activeUsers.keySet());
		//			return currentActiveUsers;
		//		}

		public void updateActiveUsers() {
			LinkedList<User> currentActiveUsers = new LinkedList<>();
			for(int i = 0; i < currentActiveUsers.size(); i++) {
				this.activeUsers.get(currentActiveUsers.get(i)).updateActiveUsers(currentActiveUsers);
			}
		}

	}

}



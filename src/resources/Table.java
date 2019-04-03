package resources;

import java.util.*;

public class Table {
	private int numberOfPlayers;
	private int numberOfMinutes;
	private int numberOfRounds;
	private int startingMoney;
	private int minimumBet;
	private Shoe regularShoe = new Shoe(6);
	private CheatShoe cheatShoe = new CheatShoe(6);
	private DealerHand dealer;
	private int roomID;
	
	private ArrayList<Player> playerList = new ArrayList<>(); //Holds all the players for the game
	
	public Table(Player creator, int numberOfPlayers, int numberOfMinutes, int numberOfRounds, int startingMoney, int minimumBet) {
		playerList.add(creator);
		this.numberOfPlayers = numberOfPlayers;
		this.numberOfMinutes = numberOfMinutes;
		this.numberOfRounds = numberOfRounds;
		this.startingMoney = startingMoney;
		this.minimumBet = minimumBet;
	}
	
	public void addPlayer(Player player) {
		playerList.add(player);
	}
	
	public void run() {
		
	}
	
	public int getRoomID() {
		return roomID;
	}
	
	
	
	
	

}

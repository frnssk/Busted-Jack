package resources;

import java.io.Serializable;
import java.util.*;

public class Table implements Serializable {

	private static final long serialVersionUID = 8653911597750749092L;
	private int numberOfPlayers;
	private int numberOfMinutes;
	private int numberOfRounds;
	private int startingMoney;
	private int minimumBet;
	private Shoe regularShoe = new Shoe(6);
	private CheatShoe cheatShoe = new CheatShoe(6);
	private DealerHand dealer;
	private int tableID;
	
	private ArrayList<Player> playerList = new ArrayList<>(); //Holds all the players for the game
	
	public Table(int numberOfMinutes, int numberOfRounds, int startingMoney, int minimumBet) {
//		this.numberOfPlayers = numberOfPlayers;
		this.numberOfMinutes = numberOfMinutes;
		this.numberOfRounds = numberOfRounds;
		this.startingMoney = startingMoney;
		this.minimumBet = minimumBet;
	}
	
	public void addPlayer(Player player) {
		playerList.add(player);
	}
	
	public void setTableId(int id) {
		this.tableID = id;
	}
	public void run() {
		
	}
	
	
	
	
	
	
	

}

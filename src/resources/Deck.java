package resources;

import java.util.*;

/**
 * Class to simulate a regular deck of cards, containing 52 different cards
 * @author rasmusoberg
 *
 */

public class Deck {
	private ArrayList<Card> deck = new ArrayList<>();
	
	public Deck() {
		for(Card.Suit suit : Card.Suit.values()) {
			for(Card.Rank rank : Card.Rank.values()) {
				deck.add(new Card(rank, suit));
			}
		}
	}
	
	/*
	 * Deals the top card of the deck
	 */
	public Card dealCard() {
		Card card = deck.get(deck.size() - 1);
		deck.remove(card);
		return card;
	}
	
	/*
	 * Returns the size of the deck
	 */
	public int getSize() {
		return deck.size();
	}

}

package server;

import java.util.Stack;

public class Deck {
	private Stack<Card> deck = new Stack<>();

	public Stack<Card> getRegularDeck(){
		if(deck != null) {
			deck.clear();
		}

		for(int i = 1; i < 5; i++) {
			for(int j = 1; j < 14; j++) {
				deck.add(new Card(i, j));
			}
		}

		return deck;
	}

	public Stack<Card> getCheatDeck(){
		if(deck != null) {
			deck.clear();
		}

		for(int i = 1; i < 5; i++) {
			for(int j = 10; j < 14; j++) {
				deck.add(new Card(i, j));
			}
		}
		return deck;
	}

	public static void main(String[] args) {
		Deck deck = new Deck();
		Stack<Card> regDeck = deck.getRegularDeck();

		System.out.println(regDeck.toString());

	}

}

package server;

import javax.swing.ImageIcon;

public class Card {
	private int suit;
	private int value;
	private ImageIcon image;
	
	public Card(int suit, int value) { //ImageIcon image){
		this.suit = suit;
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getSuit() {
		return suit;
	}
	
	public ImageIcon getImage() {
		return image;
	}
	
	public String toString() {
		return "Suit = " + suit + " - Value = " + value + "\n";
	}
}

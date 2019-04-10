package resources;

import java.util.ArrayList;
import java.util.Random;

public class TableID {
	private int id;
//	private ArrayList<Integer> listOfID = new ArrayList<>();
	
	public TableID() {
		this.id = getID();
	}
	
	private int getID() {
		Random rand = new Random();
		return rand.nextInt(1000);
	}

}

package shop;

import java.util.ArrayList;

public class ItemManager {
	private ArrayList<Item> itemlist;

	private ItemManager() {
		itemlist = new ArrayList<>();
	}

	private static ItemManager instance = new ItemManager();

	public static ItemManager getInstance() {
		return instance;
	}
	
//item crud

}

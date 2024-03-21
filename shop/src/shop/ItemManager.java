package shop;

import java.util.ArrayList;
import java.util.Random;

public class ItemManager {
	private Random random = new Random();
	private ArrayList<Item> itemList;

	private ItemManager() {
		itemList = new ArrayList<>();
	}

	private static ItemManager instance = new ItemManager();

	public static ItemManager getInstance() {
		return instance;
	}

	// item crud

	public Item createItem(String itemName, int itemCount, int price) {
		int code = generateCode();
		Item item = new Item(code, itemName, itemCount, price);
		itemList.add(item);
		return item.clone();
	}

	private int generateCode() {
		int code = 0;
		while (true) {
			code = random.nextInt(9000) + 1000;
			Item item = findItemCode(code);
			if (item.getItemCode() == 0)
				break;
		}
		return code;
	}

	private Item findItemCode(int code) {
		for (Item item : itemList) {
			if (item.getItemCode() == code)
				return item.clone();
		}
		return new Item();
	}

	public int getitemListSize() {
		return itemList.size();
	}
}
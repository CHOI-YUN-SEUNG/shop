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
			Item item = findItemByCode(code);
			if (item.getItemCode() == 0)
				break;
		}
		return code;
	}

	public Item findItemByCode(int code) {
		for (Item item : itemList) {
			if (item.getItemCode() == code)
				return item.clone();
		}
		return new Item();
	}

	public void updateItemCount(Item item, int count) {
		int code = item.getItemCode();
		Item targetItem = getItemByCode(code);
		targetItem.setItemCount(count);
	}

	public void updateItemName(Item item, String name) {
		int code = item.getItemCode();
		Item targetItem = getItemByCode(code);
		targetItem.setItemName(name);
	}

	public boolean deleteItem(Item item) {
		int code = item.getItemCode();
		Item targetItem = getItemByCode(code);
		return itemList.remove(targetItem);
	}

	private Item getItemByCode(int code) {// 원본 수정
		for (Item item : itemList) {
			if (item.getItemCode() == code)
				return item;
		}
		return new Item();
	}

	public ArrayList<Item> getItemList() {
		ArrayList<Item> temp = new ArrayList<>();
		for (Item item : itemList)
			temp.add(item.clone());
		return temp;
	}

	public int getitemListSize() {
		return itemList.size();
	}
}
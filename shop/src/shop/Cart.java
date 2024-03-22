package shop;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Item> list;

	public Cart() {
		list = new ArrayList<>();
	}

	public void setList(ArrayList<Item> list) {
		this.list = list;
	}

	public int getCartSize() {
		return list.size();
	}

	public ArrayList<Item> getItemList() {
		ArrayList<Item> temp = new ArrayList<>();
		for (Item item : list)
			temp.add(item.clone());
		return temp;
		
	}

	@Override
	public String toString() {
		String info = "";
		for (int i = 0; i < list.size(); i++)
			info += list.get(i);
		return info;
	}
}

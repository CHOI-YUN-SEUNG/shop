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

	public ArrayList<Item> getList() {//임시
		return list;
	}

	@Override
	public String toString() {
		String info = "";
		for (int i = 0; i < list.size(); i++)
			info += list.get(i);
		return info;
	}
}

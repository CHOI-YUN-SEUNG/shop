package shop;

import java.util.ArrayList;

public class Cart {
	private int userCode;
	private ArrayList<Item> list;

	public Cart(int userCode) {
		this.userCode = userCode;
		list = new ArrayList<>();
	}

	public int getCartSize() {
		return list.size();
	}
}

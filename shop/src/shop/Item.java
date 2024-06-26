package shop;

public class Item {
	private int itemCode;
	private int itemCount;
	private String itemName;
	private int price;

	Item() {

	}

	public Item(int itemCode, String itemName, int itemCount, int price) {
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemCount = itemCount;
		this.price = price;
	}

	public Item clone() {
		Item item = new Item(this.itemCode, this.itemName, this.itemCount, this.price);
		return item;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemCode() {
		return itemCode;
	}

	public int getItemCount() {
		return itemCount;
	}

	public String getItemName() {
		return itemName;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		String info = String.format("[%d] %s: %d원 (남은 수량 %d)",itemCode,itemName,price, itemCount);
		return info;
	}
}
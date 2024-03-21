package shop;

public class User {
	private int userCode;
	private String id;
	private String passWord;
	private String name;
	private Cart cart;

	User(String id, String passWord, String name) {
		this.id = id;
		this.passWord = passWord;
		this.name = name;
	}

	public User clone() {
		User user = new User(this.id, this.passWord, this.name);
		user.setUserCode(this.userCode);
		return user;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public Cart getCart() {
		return cart;
	}

	public String getName() {
		return name;
	}

	public String getPassWord() {
		return passWord;
	}

	public String getId() {
		return id;
	}

	public int getUserCode() {
		return userCode;
	}
}

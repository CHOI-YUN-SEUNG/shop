package shop;

public class User {
	private String id;
	private String passWord;
	private String name;
	private Cart cart;

	public User() {
		
	}
	
	public User(String id, String passWord, String name) {
		this.id = id;
		this.passWord = passWord;
		this.name = name;
	}

	public User clone() {
		User user = new User(this.id, this.passWord, this.name);
		user.setCart(this.cart);
		return user;
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
}

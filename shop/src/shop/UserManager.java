package shop;

import java.util.ArrayList;

public class UserManager {
	private ArrayList<User> userlist;

	private UserManager() {
		userlist = new ArrayList<>();
	}

	private static UserManager instance = new UserManager();

	public static UserManager getInstance() {
		return instance;
	}

	public User createUser(String id, String passWord, String name) {
		if (checkId(id)) {
			User user = new User(id, passWord, name);
			userlist.add(user);
			return user.clone();
		}
		return new User();
	}

	private boolean checkId(String id) {
		for (User user : userlist) {
			if (user.getId().equals(id))
				return false;
		}
		return true;
	}

	public User findUserByUserID(String id) {
		for (User user : userlist) {
			if (user.getId().equals(id))
				return user.clone();
		}
		return new User();
	}

	public User findUserByUserPW(String passWord) {
		for (User user : userlist) {
			if (user.getPassWord().equals(passWord))
				return user.clone();
		}
		return new User();
	}

	public void updateUserCart(User user, Cart cart) {
		String id = user.getId();
		User targetUser = getUserById(id);
		targetUser.setCart(cart);
	}

	public boolean deleteUser(User user) {
		String userId = user.getId();
		User targetUser = getUserById(userId);
		return userlist.remove(targetUser);
	}

	private User getUserById(String Id) {
		for (User user : userlist) {
			if (user.getId() == Id)
				return user;
		}
		return new User();
	}

	public int getUserSize() {
		return userlist.size();
	}
}
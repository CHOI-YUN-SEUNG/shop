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
		if (findUserByUserID(id) != null) {
			User user = new User(id, passWord, name);
			userlist.add(user);
			return user.clone();
		}
		return new User();
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

	public void updateUserCart() {// 임시

	}


	public int getUserSize() {
		return userlist.size();
	}
}
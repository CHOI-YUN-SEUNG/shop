package shop;

import java.util.ArrayList;
import java.util.Random;

public class UserManager {
	private Random random = new Random();
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
			int code = generateUserCode();
			User user = new User(code, id, passWord, name);
			userlist.add(user);
			return user.clone();
		}
		return new User();
	}

	private int generateUserCode() {
		int code = 0;
		while (true) {
			code = random.nextInt(9000) + 1000;

			User user = findUserByUserCode(code);
			if (user.getUserCode() == 0)
				break;
		}
		return code;
	}

	public User findUserByUserCode(int code) {
		for (User user : userlist) {
			if (user.getUserCode() == code)
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

	public int getUserSize() {
		return userlist.size();
	}

	
	public void updateUserCart() {

	}
	
	public boolean deleteUser(User user) {
		int userCode = user.getUserCode();
		User targetUser = getUserByUserCode(userCode);
		return userlist.remove(targetUser);
	}
	
	private User getUserByUserCode(int code) {//원본 수정
		for (User user : userlist) {
			if (user.getUserCode() == code)
				return user;
		}
		return new User();
	}	
}
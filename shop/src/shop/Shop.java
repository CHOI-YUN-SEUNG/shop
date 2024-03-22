package shop;

import java.util.Scanner;

public class Shop {
	private UserManager userManager = UserManager.getInstance();
	private ItemManager itemManager = ItemManager.getInstance();
	private FileManager fileManager = FileManager.getInstance();
	private Scanner scanner = new Scanner(System.in);

	private int log = -1;
	private final int ADMIN = 0;

	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int LOG_IN = 3;
	private final int LOG_OUT = 4;
	private final int SHOPPING = 5;
	private final int MYPAGE = 6;
	private final int BACK = 0;

	private final int TYPE_OUT = 1;
	private final int TYPE_IN = 2;

	private final int ITEM_ENROLL = 1;
	private final int ITEM_REMOVE = 2;
	private final int ITEM_UPDATE = 3;
	private final int ITEM_TOTAL_SALED = 4;

	public void run() {
		while (true) {
			printStatusForCheck();
			printMenu();
			int select = inputNumber("[MENU]");
			processMenu(select);
		}
	}

	private void printStatusForCheck() {
		int userSize = userManager.getUserSize();
		int itemSize = itemManager.getitemListSize();
		String status = String.format("Users : %d\nItems: %d", userSize, itemSize);
		System.out.println(status);
	}

	private int inputNumber(String message) {
		int number = -1;
		while (number < 0) {
			try {
				System.out.print(message + " : ");
				String input = scanner.nextLine();
				number = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.err.println("숫자를 입력해주시길 바랍니다.");
			}
		}
		return number;
	}

	private String inputString(String message) {
		System.out.print(message + ": ");
		String input = scanner.nextLine();
		return input;
	}

	private boolean checkLog(int typeCode) {
		boolean result = false;
		if (typeCode == TYPE_OUT) {
			if (log == -1)
				result = true;
			else
				System.err.println("로그아웃 후 이용가능합니다.");
		} else if (typeCode == TYPE_IN) {
			if (log != -1)
				result = true;
			else
				System.err.println("로그인 후 이용가능합니다");
		}

		return result;
	}

	private void printMenu() {
		if (log == ADMIN)
			printAdminMenu();
		else
			printUserMenu();
	}

	private void printUserMenu() {
		System.out.println("================");
		System.out.println("[1] 회원가입");
		System.out.println("[2] 회원탈퇴");
		System.out.println("[3] 로그인");
		System.out.println("[4] 로그아웃");
		System.out.println("[5] 쇼핑하기");
		System.out.println("[6] 마이페이지");
		System.out.println("================");
	}

	private void printMapageMenu() {
		System.out.println("================");
		System.out.println("[내 장바구니");
		System.out.println("[1] 항목 삭제");
		System.out.println("[2] 수량 수정");
		System.out.println("[3] 결제");
		System.out.println("[0] 뒤로 가기");
		System.out.println("================");
	}

	private void printAdminMenu() {
		System.out.println("================");
		System.out.println("[1] 아이템 등록");
		System.out.println("[2] 아이템 삭제");
		System.out.println("[3] 아이템 수정");
		System.out.println("[4] 총 매출 확인");
		System.out.println("[0] 로그아웃");
		System.out.println("================");
	}

	private void processMenu(int select) {
		if (log == ADMIN)
			processAdminMenu(select);
		else
			processUserMenu(select);
	}

	private void processUserMenu(int select) {
		if (select == JOIN && checkLog(TYPE_OUT)) {
			join();
		} else if (select == LEAVE && checkLog(TYPE_IN)) {
			leave();
		} else if (select == LOG_IN && checkLog(TYPE_OUT)) {
			login();
		} else if (select == LOG_OUT && checkLog(TYPE_IN)) {
			logout();
		} else if (select == SHOPPING && checkLog(TYPE_IN)) {
			shopping();
		} else if (select == MYPAGE && checkLog(TYPE_IN)) {
			mypage();
		}
	}

	private void processAdminMenu(int select) {
		if (select == ITEM_ENROLL) {// 아이템 등록
			itemEnroll();
		} else if (select == ITEM_REMOVE) {// 아이템 삭제
			itemRemove();
		} else if (select == ITEM_UPDATE) {// 아이템 수정
			itemUpdate();
		} else if (select == ITEM_TOTAL_SALED) {// 총 매출 확인
			itemTotalSaled();
		} else if (select == BACK) {
			logout();
		}
	}

	private void itemEnroll() {
		String name = inputString("상품명");
		int count = inputNumber("상품갯수");
		int price = inputNumber("상품가격");
		Item item = itemManager.createItem(name, count, price);
		System.out.printf("상품이 등록 되었습니다. 아이템 코드는 %d입니다.", item.getItemCode());
	}

	private void itemRemove() {

	}

	private void itemUpdate() {
		String id = inputString("ID");
		String password = inputString("P/W");
		String name = inputString("이름");
		User user = userManager.createUser(id, password, name);
		printJoinMessage(user);

	}

	private void itemTotalSaled() {

	}

	private void join() {
		String id = inputString("ID");
		String password = inputString("P/W");
		String name = inputString("이름");
		User user = userManager.createUser(id, password, name);
		printJoinMessage(user);
	}

	private void printJoinMessage(User user) {
		if (user.getId() != null)
			System.out.printf("%s(%s) 회원님 환영합니다.\n", user.getId(), user.getName());
		else
			System.out.println("회원가입 실패. 중복된 아이디입니다");
	}

	private void leave() {

	}

	private void login() {
		String id = inputString("ID");
		String password = inputString("password");

		for (int i = 0; i < userManager.getUserSize(); i++) {
			User temp = userManager.findUserByIndex(i);
			if (temp.getId().equals(id) && temp.getPassWord().equals(password)) {
				log = i;
				System.out.println(temp.getName() + "님 로그인 되었습니다.");
				break;
			}
		}
		if (log == -1)
			System.err.println("회원정보를 다시 확인하세요.");
	}

	private void logout() {
		log = -1;
		System.out.println("로그아웃 완료");
	}

	private void shopping() {

	}

	private void mypage() {

	}
}
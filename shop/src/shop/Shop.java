package shop;

import java.util.Scanner;

public class Shop {
	private UserManager userManager = UserManager.getInstance();
	private ItemManager itemManager = ItemManager.getInstance();
	private FileManager fileManager = FileManager.getInstance();
	private Scanner scanner = new Scanner(System.in);

	private boolean isSubRun;
	private int total;
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

	private final int ITEM_UPDATE_NAME = 1;
	private final int ITEM_UPDATE_COUNT = 2;

	private final int SHOPPING_BUY = 1;

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

	private void printShoppingMenu() {
		System.out.println("================");
		System.out.println("[1] 구매");
		System.out.println("[0] 뒤로 가기");
		System.out.println("================");
	}

	private void printMapageMenu() {
		System.out.println("================");
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

	private void printItemUpdateSubMenu() {
		System.out.println("================");
		System.out.println("[1] 아이템 이름 수정");
		System.out.println("[2] 아이템 갯수 수정");
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
		System.out.printf("상품이 등록 되었습니다. 아이템 코드는 %d입니다.\n", item.getItemCode());
	}

	private void itemRemove() {
		int code = inputNumber("삭제할 상품코드");
		Item item = itemManager.findItemByCode(code);
		if (item.getItemName() != null) {
			System.out.printf("현재 상품명은 %s입니다.\n", item.getItemName());
			itemManager.deleteItem(item);
			System.out.println("삭제완료");
		} else
			System.out.println("해당 상품은 존재하지 않습니다.");
	}

	private void itemUpdate() {
		printItemUpdateSubMenu();
		int select = inputNumber("[MENU]");
		processItemUpdateSubMenu(select);
	}

	private void processItemUpdateSubMenu(int select) {
		if (select == ITEM_UPDATE_NAME) {
			itemUpdateName();
		} else if (select == ITEM_UPDATE_COUNT) {
			itemUpdateCount();
		}
	}

	private void itemUpdateName() {
		int code = inputNumber("상품번호");
		Item item = itemManager.findItemByCode(code);
		if (item.getItemName() != null) {
			System.out.printf("현재 상품명은 %s입니다.\n", item.getItemName());
			String name = inputString("변경할 상품명");
			itemManager.updateItemName(item, name);
			System.out.println("변경완료");
		} else
			System.out.println("해당 상품은 존재하지 않습니다.");
	}

	private void itemUpdateCount() {
		int code = inputNumber("상품번호");
		Item item = itemManager.findItemByCode(code);
		if (item.getItemCount() != 0) {
			System.out.printf("현재 상품의 개수는 %d입니다.\n", item.getItemCount());
			int count = inputNumber("변경할 상품 개수");
			itemManager.updateItemCount(item, count);
		} else
			System.out.println("해당 상품은 존재하지 않습니다.");
	}

	private void itemTotalSaled() {
		System.out.println("총 매출액은" + total + "원 입니다.");
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
		for (int i = 0; i < itemManager.getitemListSize(); i++)
			System.out.println(itemManager.getItemList().get(i));
		this.isSubRun = true;
		while (this.isSubRun) {
			printShoppingMenu();
			int select = inputNumber("[MENU]");
			processShoppingSubMenu(select);
		}
	}

	private void processShoppingSubMenu(int select) {
		if (select == SHOPPING_BUY) {
			shoppingBuy();
		} else if (select == BACK) {
			this.isSubRun = false;
		}
	}

	private void shoppingBuy() { //장바구니의 카운트도 세서 처리해야함...
		int code = inputNumber("구매할 상품코드");
		int count = inputNumber("구매할 개수");
		Item want = itemManager.findItemByCode(code);

		if (want.getItemCount() < count) {
			System.err.println("개수초과");
			return;
		}

		User user = userManager.findUserByIndex(log);
		want.setItemCount(count);
		System.out.println(want);

		Cart cart = new Cart();
		cart.getList().add(want);
		System.out.println(cart);
		user.setCart(cart);
		System.out.println(user);
	}

	private void mypage() {

	}
}
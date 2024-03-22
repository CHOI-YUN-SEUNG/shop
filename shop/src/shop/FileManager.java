package shop;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
	private File userFile;
	private File itemFile;
	private String userFileName;
	private String itemFileName;
	private FileWriter fileWriter;
	private FileReader fileReader;
	private BufferedReader bufferedReader;

	private FileManager() {
		String path = System.getProperty("user.home");
		String sep = System.getProperty("file.separator");

		userFileName = "UserData.txt";
		userFileName = path + sep + "desktop" + sep + userFileName;
		userFile = new File(userFileName);

		itemFileName = "itemData.txt";
		itemFileName = path + sep + "desktop" + sep + itemFileName;
		itemFile = new File(itemFileName);
	}

	private static FileManager instance = new FileManager();

	public static FileManager getInstance() {
		return instance;
	}

	private void loadData() {
		loadUserData();
		loadItemData();
	}

	private void loadUserData() {
		if (userFile.exists()) {
			String data = "";
			try {
				fileReader = new FileReader(userFile);
				bufferedReader = new BufferedReader(fileReader);

				while (bufferedReader.ready()) {
					data += bufferedReader.readLine() + "\n";
				}
				bufferedReader.close();
				fileReader.close();

				parseLoadUserData(data);
				System.out.println("파일 로드 성공");

			} catch (Exception e) {
				System.err.println("파일 로드 실패");
			}
		}
	}

	private void parseLoadUserData(String data) {

	}

	private void loadItemData() {
		if (itemFile.exists()) {
			String data = "";
			try {
				fileReader = new FileReader(itemFile);
				bufferedReader = new BufferedReader(fileReader);

				while (bufferedReader.ready()) {
					data += bufferedReader.readLine() + "\n";
				}

				bufferedReader.close();
				fileReader.close();

				parseLoadItemData(data);
				System.out.println("파일 로드 성공");

			} catch (Exception e) {
				System.err.println("파일 로드 실패");
			}
		}
	}

	private void parseLoadItemData(String data) {

	}

	private void saveUserData() {
		String data = "";
		try {
			fileWriter = new FileWriter(userFile);
			System.out.println("파일 저장 성공");
		} catch (Exception e) {
			System.err.println("파일 저장 실패");
		}
	}
	
	private void saveItemData() {
		String data = "";
		try {
			fileWriter = new FileWriter(itemFile);
			System.out.println("파일 저장 성공");
		} catch (Exception e) {
			System.err.println("파일 저장 실패");
		}
	}
}
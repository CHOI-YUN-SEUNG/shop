package shop;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

//유저정보
//

public class FileManager {
	private File file;
	private String fileName;
	private FileWriter fileWriter;
	private FileReader fileReader;
	private BufferedReader bufferedReader;

	private FileManager() {
		fileName = "shopData.txt";
	}

	private static FileManager instance = new FileManager();

	public static FileManager getInstance() {
		return instance;
	}

	private void loadData() {
		if (file.exists()) {
			String data = "";
			try {
				fileReader = new FileReader(file);
				bufferedReader = new BufferedReader(fileReader);

				while (bufferedReader.ready()) {
					data += bufferedReader.readLine() + "\n";
				}

				bufferedReader.close();
				fileReader.close();

				parseLoadData(data);
				System.out.println("파일 로드 성공");

			} catch (Exception e) {
				System.err.println("파일 로드 실패");
			}
		}
	}

	private void parseLoadData(String data) {

	}

	private void saveData() {
		String data = "";
		try {
			fileWriter = new FileWriter(file);
			System.out.println("파일 저장 성공");
		} catch (Exception e) {
			System.err.println("파일 저장 실패");
		}
	}
}
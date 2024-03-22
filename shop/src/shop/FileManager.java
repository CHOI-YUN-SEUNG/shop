package shop;

public class FileManager {	
	//load
	//save
	
	private FileManager() {
		
	}

	private static FileManager instance = new FileManager();

	public static FileManager getInstance() {
		return instance;
	}
}

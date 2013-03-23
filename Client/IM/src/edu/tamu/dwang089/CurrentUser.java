package edu.tamu.dwang089;

/**
 * Singleton class that represents the user who is using the app
 * This is different from the other User class
 * @author dwang089
 *
 */
public class CurrentUser {
	private static CurrentUser instance;
	private String currentUser;
	
	private CurrentUser() {
		
	}
	
	public static CurrentUser getInstance() {
		if (instance == null)
			instance = new CurrentUser();
		return instance;
	}
	
	public String getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(String user) {
		currentUser = user;
	}
	

}

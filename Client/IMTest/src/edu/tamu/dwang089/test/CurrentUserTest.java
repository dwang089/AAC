package edu.tamu.dwang089.test;

import edu.tamu.dwang089.CurrentUser;
import junit.framework.TestCase;

public class CurrentUserTest extends TestCase {
	private CurrentUser user;
	
	public void setUp() {
		user = CurrentUser.getInstance();
	}
	
	public void testSetUser() {
		user.setCurrentUser("daoqi");
		assertEquals("daoqi", user.getCurrentUser());
	}
	
	
}

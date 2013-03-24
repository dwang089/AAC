package edu.tamu.dwang089.test;

import edu.tamu.dwang089.User;
import junit.framework.TestCase;

public class UserTest extends TestCase {
	private User user;
	
	public void setUp() {
		user = new User("daoqi", "1234", "Daoqi Wang", "daoqi@gmail.com");
	}
	
	public void testGetUserName() {
		assertEquals(user.getUserName(), "daoqi");
	}
	
	public void testGetPassword() {
		assertEquals(user.getPassword(), "1234");
	}
	
	public void testGetName() {
		assertEquals(user.getName(), "Daoqi Wang");
	}
	
	public void testGetEmail() {
		assertEquals(user.getEmail(), "daoqi@gmail.com");
	}
}

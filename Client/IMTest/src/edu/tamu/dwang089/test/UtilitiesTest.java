package edu.tamu.dwang089.test;

import java.util.List;

import edu.tamu.dwang089.Utilities;
import junit.framework.TestCase;

public class UtilitiesTest extends TestCase {
	public UtilitiesTest(String name) {
		super(name);
	}
	
	public void testIsExistingUserName() {
		assertTrue(Utilities.isExistingUserName("daoqi"));
		assertFalse(Utilities.isExistingUserName("random"));
	}
	
	public void testIsValidUserNameAndPassword() {
    	assertTrue(Utilities.isValidUserNameAndPassword("daoqi", "1234"));
    	assertFalse(Utilities.isValidUserNameAndPassword("daoqi", "1235"));
    }
	
	public void testParseMessageFieldsFromMessageHtml() {
		String document = "" +
				"<!DOCTYPE html> " +
				"<html> " +
					"<head> " +
						"<title>Messages</title> " +
					"</head> " +					
					"<body> " +		
							"<div class=\"message\"> " +
								"<div class=\"message-sender\">bob</div>(sender) " +
								"<div class=\"message-recipient\">daoqi</div>(recipient) " +
								"<div class=\"message-content\">hello daoqi</div>(content) " +
								"<div class=\"message-type\">text</div>(type) " +
								"<div class=\"message-created\">2012-07-03 06:06:02.354150</div>(created) " +
							"</div> " +
							"<br> " +			
							"<div class=\"message\"> " +
								"<div class=\"message-sender\">daoqi</div>(sender) " +
								"<div class=\"message-recipient\">bob</div>(recipient) " +
								"<div class=\"message-content\">hi bob</div>(content) " +
								"<div class=\"message-type\">text</div>(type) " +
								"<div class=\"message-created\">2012-07-03 06:01:08.973930</div>(created) " +
							"</div> " +
							"<br> " +				
					"</body> " +			
				"</html>";
		
		List<List<String>> messageFieldsList = Utilities.parseMessageFieldsFromMessageHtml(document);
		assertEquals(messageFieldsList.size(), 2);
		assertEquals(messageFieldsList.get(0).get(0), "bob");
		assertEquals(messageFieldsList.get(0).get(1), "hello daoqi");
		assertEquals(messageFieldsList.get(0).get(2), "2012-07-03 06:06:02");
	}
}

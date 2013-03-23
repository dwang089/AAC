package edu.tamu.dwang089.test;

import java.util.List;

import edu.tamu.dwang089.Message;
import edu.tamu.dwang089.TextMessage;
import edu.tamu.dwang089.Utilities;
import junit.framework.TestCase;

public class TextMessageTest extends TestCase {
	private Message message;
	
	public void setUp() {
		message = new TextMessage("daoqi", "hi");
	}
	
	public void testInitializeMessagePostParameters() {
		assertEquals(message.getMessagePostParameterList().get(1).getValue(), "daoqi");
		assertEquals(message.getMessagePostParameterList().get(2).getValue(), "hi");
	}
	
	public void testCreateTextMessageListForDisplay() {
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
		List<Message> textMessageList = TextMessage.createTextMessageListForDisPlay(messageFieldsList);
		assertEquals(textMessageList.get(0).displaySenderAndContent(), "bob: hello daoqi");
	}
}

package edu.tamu.dwang089;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

/**
 * This class represents the text message
 * 
 * @author Daoqi Wang
 *
 */
public class TextMessage extends Message {	
	
	//Constructor for sending
	public TextMessage(String recipient, String content) {
		super(recipient, content);
		type = "text";
		messagePostParameterList.add(new BasicNameValuePair("type", type));
	}
	
	//Constructor for receiving and display
	public TextMessage(String sender, String content, String timeCreated) {
		super(sender, content, timeCreated);
		type = "text";
	}
	
	//inflexible structure that depends on response from httpget parsing in utilities
	public static List<Message> createTextMessageListForDisPlay(final List<List<String>> messageFieldsList) {
		List<Message> textMessages = new ArrayList<Message>();
		
		for (int i = 0; i < messageFieldsList.size(); i++) {
			List<String> messageFields = messageFieldsList.get(i);
			String messageSender = messageFields.get(0);
			String messageContent = messageFields.get(1);
			String messageTimeCreated = messageFields.get(2);
			
			Message textMessage = new TextMessage(messageSender, messageContent, messageTimeCreated);
			textMessages.add(textMessage);
		}
		return textMessages;
	}
}


package edu.tamu.dwang089;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public abstract class Message {
	protected String sender;
	protected String recipient;
	protected String content;
	protected String type;
	protected String timeCreated;
	
	//Encapsulate parameter creation for post method in Utilities
	protected List<NameValuePair> messagePostParameterList;
	
	//Constructor for sending
	public Message(String recipient, String content) {
		String user = CurrentUser.getInstance().getCurrentUser();
		this.sender = user;
		this.recipient = recipient;
		this.content = content;
		this.type = "text"; //Text for now, need to add image and sound in the future
		initializeMessagePostParameterList();
		
	}
	
	//Constructor for receiving and display
	public Message(String sender, String content, String timeCreated) {
		this.sender = sender;
		String user = CurrentUser.getInstance().getCurrentUser();
		this.recipient = user;
		this.content = content;
		this.timeCreated = timeCreated;
	}
	
	private void initializeMessagePostParameterList() {
		messagePostParameterList = new ArrayList<NameValuePair>();
		messagePostParameterList.add(new BasicNameValuePair("sender", sender));
		messagePostParameterList.add(new BasicNameValuePair("recipient", recipient));
		messagePostParameterList.add(new BasicNameValuePair("content", content));
	}
	
	public List<NameValuePair> getMessagePostParameterList() {
		return messagePostParameterList;
		
	}
	
	public String displaySenderAndContent() {
		return sender + ": " + content;
	}
	
	public String getTimeCreated() {
		return timeCreated;
	}
	
	public String getSender() {
		return sender;
	}
}

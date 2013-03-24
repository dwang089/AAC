package edu.tamu.dwang089;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * This class contains user information
 * 
 * @author Daoqi Wang
 *
 */
public class User {
	private String userName;
	private String password;
	private String name;
	private String email;
	
	private List<NameValuePair> userPostParameterList;
	
	public User(String userName, String password,
			String name, String email) {
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public List<NameValuePair> getUserPostParameterList() {
		initializeParameterList();
		return userPostParameterList;
	}
	
	private void initializeParameterList() { 
		userPostParameterList = new ArrayList<NameValuePair>();
    	userPostParameterList.add(new BasicNameValuePair("username", userName));
    	userPostParameterList.add(new BasicNameValuePair("password", password));
    	userPostParameterList.add(new BasicNameValuePair("name", name));
    	userPostParameterList.add(new BasicNameValuePair("email", email));
	}
	
	
	
	
}

package edu.tamu.dwang089;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;


/**
 * This class contains utility methods for the client to communicate
 * with the server through HTTP GET/POST
 * 
 * @author Daoqi Wang
 *
 */
public class Utilities {
	private static final String messageUrl = "https://daoqiwang89.appspot.com"; 
	private static final String userUrl = "https://daoqiwang3.appspot.com"; 
	private static final String friendsUrl = "https://daoqiwang4.appspot.com";
	
	//noninstantiality
	private Utilities() {
		
	}
	
	//free google app engine account, subject to quota
	public static void postMessageToGAE(Message message) {
		List<NameValuePair> parameterListForPost = message.getMessagePostParameterList();
		postContentToGAE(messageUrl, parameterListForPost);
	}
	
    public static void postNewUserToGAE(User newUser) {
    	List<NameValuePair> parameterListForPost = newUser.getUserPostParameterList();
    	postContentToGAE(userUrl, parameterListForPost);
    }
    
    public static void postNewFriendToGAE(String friend) {
    	List<NameValuePair> parameterListForPost = new ArrayList<NameValuePair>();
    	parameterListForPost.add(new BasicNameValuePair("user", CurrentUser.getInstance().getCurrentUser()));
    	parameterListForPost.add(new BasicNameValuePair("friend", friend));
    	postContentToGAE(friendsUrl, parameterListForPost);
    }
	
	private static void postContentToGAE(String url, List<NameValuePair> parameterListForPost) {
    	HttpPost post = new HttpPost(url);
    	HttpClient client = new DefaultHttpClient();

    	//Set SSL certificate verifier for HTTPS servers
    	HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
    	HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
    	
    	try {
    		//List<NameValuePair> parameterListForPost = message.getMessagePostParameterList();
    		post.setEntity(new UrlEncodedFormEntity(parameterListForPost));
    		client.execute(post);
    	} catch (ClientProtocolException e) {
    		Log.w("ClientProtocolException", e);
    	} catch (IOException e) {
    		Log.w("IOException", e);
    	}
	}
	
	public static String getMessagesFromGAE() {
		String urlWithRecipient = messageUrl + "?recipient=" + CurrentUser.getInstance().getCurrentUser(); 
		return getHtmlDocumentFromGAE(urlWithRecipient);
	}
	
	private static String getUserFromGAE(String userName) {
		String urlWithUserName = userUrl + "?username=" + userName; 
		return getHtmlDocumentFromGAE(urlWithUserName);
	}
	
	public static List<User> getFriendsFromGAE() {
		String user = CurrentUser.getInstance().getCurrentUser();
		//Log.i("current user", user);
		String urlWithUser = friendsUrl + "?user=" + user;
		String html = getHtmlDocumentFromGAE(urlWithUser);
		return parseFriendsFromFriendsHtml(html);
	}
	
	private static String getHtmlDocumentFromGAE(String url) {
		String htmlDocument = "";
		try {
	    	HttpClient client = new DefaultHttpClient();
	    	HttpGet request = new HttpGet(url);
	    	HttpResponse response = client.execute(request);
	
	    	BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	    			
	    	String line = "";
	    	while ((line = rd.readLine()) != null) {
	    		//Log.i("DisplayTextActivity", line);
	    		htmlDocument += line;
	    	}
	    	
	    	//Log.i("document", document);
    	} catch (ClientProtocolException e) {
    		Log.w("ClientProtocolException", e);
    	} catch (IOException e) {
    		Log.w("IOException", e);
    	} catch (IllegalStateException e) {
    		Log.w("IllegalStateException", e);
    	}
		return htmlDocument;
	}
	
	/**
	 * 
	 * This method parses messages from the HTML document from HTTP GET
	 * 
	 * @param document
	 * @return
	 */
    public static List<List<String>> parseMessageFieldsFromMessageHtml(String htmlDocument) {
    	Document doc = Jsoup.parse(htmlDocument);
    	Elements senders = doc.select("div.message-sender");
    	Elements contents = doc.select("div.message-content");
    	Elements times = doc.select("div.message-created");
    	
    	List<String> senderList = new ArrayList<String>();
    	List<String> contentList = new ArrayList<String>();
    	List<String> timeList = new ArrayList<String>();
    	List<List<String>> messageFieldsList = new ArrayList<List<String>>();
    	
    	for (Element sender : senders) {
    		senderList.add(sender.text());   		
    	}
    	
    	for (Element content : contents) {
	    	contentList.add(content.text());
    	}
    	
    	for (Element time : times) {
    		timeList.add(time.text().substring(0, 19)); 
    		//delete partial seconds from server
    		//format: yyyy-mm-dd hr:mn:sc
    	}
    	
    	//need to pay attention if the structure of the message changes otherwise 
    	//elements (sender, content ...) might not belong to the same message 
    	//bad implementation here...
    	for (int i = 0; i < senderList.size(); i++) {
    		String messageSender = senderList.get(i);
    		String messageContent = contentList.get(i);
    		String messageTimeCreated = timeList.get(i);
    		
    		List<String> messageFields = new ArrayList<String>();
    		messageFields.add(messageSender);
    		messageFields.add(messageContent);
    		messageFields.add(messageTimeCreated);
    		
    		messageFieldsList.add(messageFields);
    	}
    	
    	return messageFieldsList;
    }
	
	public static boolean isExistingUserName(String userName) {
		String htmlDocument = getUserFromGAE(userName);
		String user = parseUserNameFromUserHtml(htmlDocument);
		return user.equals(userName);
	}
	
    public static boolean isValidUserNameAndPassword(String userName, String password) {
    	String htmlDocument = getUserFromGAE(userName);
		String uname = parseUserNameFromUserHtml(htmlDocument);
    	String passwd = parsePasswordFromUserHtml(htmlDocument);
    	return uname.equals(userName) && passwd.equals(password);
    }

    private static String parseUserNameFromUserHtml(String htmlDocument) {
    	Document doc = Jsoup.parse(htmlDocument);
    	Element user = doc.select("div.user-username").first();
    	
    	//Should return at most one user
    	if (user != null) {
	    	String userName = user.text();
	    	return userName;
    	}
    	return "";
    }
    
    private static String parsePasswordFromUserHtml(String htmlDocument) {
    	Document doc = Jsoup.parse(htmlDocument);
    	Element passwd = doc.select("div.user-password").first();
    	
    	//Should return at most one user
    	if (passwd != null) {
	    	String password = passwd.text();
	    	return password;
    	}
    	return "";	
    }
    
    private static String parseNameFromUserHtml(String htmlDocument) {
    	Document doc = Jsoup.parse(htmlDocument);
    	Element name = doc.select("div.user-name").first();
    	
    	//Should return at most one user
    	if (name != null) {
	    	return name.text();
    	}
    	return "";
    }
    
    private static String parseEmailFromUserHtml(String htmlDocument) {
    	Document doc = Jsoup.parse(htmlDocument);
    	Element email = doc.select("div.user-email").first();
    	
    	//Should return at most one user
    	if (email != null) {
	    	return email.text();
    	}
    	return "";
    }
    
    private static List<User> parseFriendsFromFriendsHtml(String htmlDocument) {
    	Document doc = Jsoup.parse(htmlDocument);
    	Elements friends = doc.select("div.friend-friend");
    	
    	if (friends == null) 
    		return null;
    		
    	List<User> friendList = new ArrayList<User>();
    	
    	for (Element friend : friends) {
    		//Log.i("friend", friend.text());
    		String userHtml = getUserFromGAE(friend.text());
    		String userName = parseUserNameFromUserHtml(userHtml);
    		//Log.i("userName", userName);
    		String name = parseNameFromUserHtml(userHtml);
    		//Log.i("name", name);
    		String email = parseEmailFromUserHtml(userHtml);
    		//Log.i("email", email);
    		
    		User user = new User(userName, "", name, email);
    		friendList.add(user);
    	}
    	
    	return friendList;
    }
}

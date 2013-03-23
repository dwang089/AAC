package edu.tamu.dwang089;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class DisplayTextActivity extends Activity {
	private Button backButton;
	private ListView messageListView;
	private List<Message> textMessageList;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaytext);
        
        initializeWidgets();
    }
    
    private void initializeWidgets() {
    	initializeBackButton();
    	initializeMessageListView();
    }
    
    private void initializeBackButton() {
    	backButton = (Button) findViewById(R.id.displayTextBackButton);
    	
    	backButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View view) {
	    		Intent myIntent = new Intent(view.getContext(), MainMenuActivity.class);
	            startActivityForResult(myIntent, 0);
	        }	
	    });
    }
    
    private void initializeMessageListView() {
    	messageListView = (ListView) findViewById(R.id.displayTextMessagelist);
    	textMessageList = generateTextMessageList();
    	List<String> textMessages = generateTextMessagesForDisplay();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        	android.R.layout.simple_list_item_1, android.R.id.text1, textMessages);
        messageListView.setAdapter(adapter);
        
        createMessageListViewEvent();
    }

    private List<Message> generateTextMessageList() {
    	String htmlDocument = Utilities.getMessagesFromGAE();
		List<List<String>> messageFieldsList = Utilities.parseMessageFieldsFromMessageHtml(htmlDocument);
		List<Message> textMessages = TextMessage.createTextMessageListForDisPlay(messageFieldsList);
		return textMessages;
    }
    
    private List<String> generateTextMessagesForDisplay() {
    	List<String> textMessagesForDisplay = new ArrayList<String>();
    	for (int i = 0; i < textMessageList.size(); i++) {
    		String textMessage = textMessageList.get(i).displaySenderAndContent();
    		textMessagesForDisplay.add(textMessage);
    	}
    	return textMessagesForDisplay;
    }
    
    private void createMessageListViewEvent() {
    	messageListView.setOnItemClickListener(new OnItemClickListener() {
    		public void onItemClick(AdapterView<?> parent, View view,
    				int position, long id) {
    			promptReplyDialog(position);
    		}
    	});
    }
    
    private void promptReplyDialog(int position) {
    	Message message = textMessageList.get(position);
    	String displayTime = message.getTimeCreated();
    	final String recipient = message.getSender();
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Sent at " + displayTime);
    	
    	builder.setPositiveButton("Reply", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	Intent i = new Intent(getApplicationContext(), SendTextActivity.class);
            	i.putExtra("recipient", recipient);
            	startActivity(i);
            }
     	});
    	
    	builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
           }
    	});

    	AlertDialog alert = builder.create();
    	alert.show();
    }
}
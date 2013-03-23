package edu.tamu.dwang089;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SendTextActivity extends Activity {
	private EditText recipientInput;
	private EditText contentInput;
	private Button sendButton;
	private Button backButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendtext);
        
        initializeWidgets();
        
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String recipient = extras.getString("recipient");
            recipientInput.setText(recipient);
        }
    }
    
    private void initializeWidgets() {
    	initializeRecipientInput();
    	initializeMessageInput();
    	initializeSendButton();
    	initializeBackButton();
    }
    
    private void initializeRecipientInput() {
    	recipientInput = (EditText) findViewById(R.id.sendTextRecipientInput);
    }
    
    private void initializeMessageInput() {
    	contentInput = (EditText) findViewById(R.id.sendTextContentInput);
    }
    
    private void initializeSendButton() {
    	sendButton = (Button) findViewById(R.id.sendTextSendButton);
    	createSendButtonEvent();
    }
    
    private void initializeBackButton() {
    	backButton = (Button) findViewById(R.id.sendTextBackButton);
    	
    	backButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View view) {
	    		final Intent myIntent = new Intent(view.getContext(), MainMenuActivity.class);
	            startActivityForResult(myIntent, 0);
	        }	
	    });
    }
    
    private void createSendButtonEvent() {
    	sendButton.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				if (isValidInputFields()) {
					Message message = generateMessage();
					Utilities.postMessageToGAE(message);
					clearInputFields();
					promptDialog("Message Sent");
				}
				else {
					promptDialog("Incorrect recipient or empty message!");
				}
			}
		});
    }
    
    private boolean isValidInputFields() {
    	String recipient = recipientInput.getText().toString().trim();
    	return Utilities.isExistingUserName(recipient) && !contentInput.getText().toString().trim().equals("");
    }

    
    private Message generateMessage() {
    	String recipient = recipientInput.getText().toString().trim();
		String content = contentInput.getText().toString().trim();
		Message message = new TextMessage(recipient, content);
		return message;
    }
    
    private void clearInputFields() {
    	recipientInput.setText("");
    	contentInput.setText("");
    }
    
    private void promptDialog(String string) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage(string);
    	
    	builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
           }
    	});
    	
    	AlertDialog alert = builder.create();
    	alert.show();
    }
}

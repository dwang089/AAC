package edu.tamu.dwang089;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * This is the main activity
 * 
 * @author Daoqi Wang
 *
 */
public class MainMenuActivity extends Activity {
	private Button readTextButton;
	private Button sendTextButton;
	private Button friendsButton;
	private Button signOffButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        
        initializeWidgets();
    }
    
    private void initializeWidgets() {
    	initializeReadTextButton();
    	initializeSendTextButton();
    	initializeFriendsButton();
    	initializeSignOffButton();
    }
    
    private void initializeReadTextButton() {
    	readTextButton = (Button) findViewById(R.id.mainMenuReadTextButton);
    	
    	readTextButton.setOnClickListener(new View.OnClickListener() {
 	    	public void onClick(View view) {
 	    		Intent myIntent = new Intent(view.getContext(), DisplayTextActivity.class);
 	           startActivityForResult(myIntent, 0);
 	        }	
 	    });
    }
    
    private void initializeSendTextButton() {
    	sendTextButton = (Button) findViewById(R.id.mainMenuSendTextButton);
    	
    	sendTextButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), SendTextActivity.class);
		        startActivityForResult(myIntent, 0);
			}
		});
    }
    
    private void initializeFriendsButton() {
    	friendsButton = (Button) findViewById(R.id.mainMenuFriendsButton);
    	
    	friendsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), DisplayFriendActivity.class);
		        startActivityForResult(myIntent, 0);
			}
		});
    }
    
    private void initializeSignOffButton() {
    	signOffButton = (Button) findViewById(R.id.mainMenuSignOffButton);
    	
    	signOffButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				final Intent myIntent = new Intent(view.getContext(), SignInActivity.class);
		        startActivityForResult(myIntent, 0);
			}
		});
    }
}


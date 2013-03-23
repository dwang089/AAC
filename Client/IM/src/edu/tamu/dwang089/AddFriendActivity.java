package edu.tamu.dwang089;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddFriendActivity extends Activity {
	private EditText friendInput;
	private Button enterButton;
	private Button backButton;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfriend);
        
        initializeWidgets();
    }

	private void initializeWidgets() {
		initializeFriendInput();
		initializeEnterButton();
		initializeBackButton();
	}

	private void initializeFriendInput() {
		friendInput = (EditText) findViewById(R.id.addFriendFriendInput);
	}
	
	private void initializeEnterButton() {
		enterButton = (Button) findViewById(R.id.addFriendEnterButton);
		
		enterButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View view) {
	    		String friend = friendInput.getText().toString();
	    		if (friend.equals(CurrentUser.getInstance().getCurrentUser())) {
		    		promptWarningDialog("You cannot add yourself as a friend");
		    		return;
	    		}
	    		else if (!isValidFriend(friend)) {
	    			promptWarningDialog("The friend you are trying to add is not a valid user");
		    		return;
	    		}
	    		else if (isExistingFriend(friend)) {
	    			promptWarningDialog("He/she is already your friend");
		    		return;
	    		}
	    		else {
	    			Utilities.postNewFriendToGAE(friend);
	    			promptWarningDialog("You successfully added a friend!");
		    		friendInput.setText("");
	    		}
	        }	
	    });
	}
	
	private void promptWarningDialog(String message) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage(message);
    	
    	builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
           }
    	});
    	
    	AlertDialog alert = builder.create();
    	alert.show();
    } 
	
	private boolean isValidFriend(String friend) {
		return Utilities.isExistingUserName(friend);
	}
	
	private boolean isExistingFriend(String friend) {
		return false;
	}
	
	private void initializeBackButton() {
		backButton = (Button) findViewById(R.id.addFriendBackButton);
		
		backButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View view) {
	    		Intent myIntent = new Intent(view.getContext(), DisplayFriendActivity.class);
	            startActivityForResult(myIntent, 0);
	        }	
	    });
	}
}

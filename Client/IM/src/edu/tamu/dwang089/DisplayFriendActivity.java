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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class DisplayFriendActivity extends Activity {
	private Button backButton;
	private Button addButton;
	private ListView friendListView;
	private List<User> friendList;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayfriend);
        
        initializeWidgets();
    }
	
	private void initializeWidgets() {
		initializeBackButton();
		initializeAddButton();
    	initializeFriendListView();
	}
	
	private void initializeBackButton() {
		backButton = (Button) findViewById(R.id.displayFriendBackButton);
    	
    	backButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View view) {
	    		Intent myIntent = new Intent(view.getContext(), MainMenuActivity.class);
	            startActivityForResult(myIntent, 0);
	        }	
	    });
	}
	
	private void initializeAddButton() {
		addButton = (Button) findViewById(R.id.displayFriendAddButton);
    	
    	addButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View view) {
	    		Intent myIntent = new Intent(view.getContext(), AddFriendActivity.class);
	            startActivityForResult(myIntent, 0);
	        }	
	    });
	}
	
	private void initializeFriendListView() {
		friendListView = (ListView) findViewById(R.id.displayFriendListView);		
		friendList = Utilities.getFriendsFromGAE();

		List<String> friendsNameList = new ArrayList<String>();
		for (User friend : friendList) {
			String name = friend.getUserName();
			friendsNameList.add(name);
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	        	android.R.layout.simple_list_item_1, android.R.id.text1, friendsNameList);
		friendListView.setAdapter(adapter);
	        
	    createFriendsListViewEvent();
	}

	private void createFriendsListViewEvent() {
		friendListView.setOnItemClickListener(new OnItemClickListener() {
    		public void onItemClick(AdapterView<?> parent, View view,
    				int position, long id) {
    			promptFriendDialog(position);
    		}
    	});	
	}
	
	private void promptFriendDialog(int position) {
		final User friend = friendList.get(position);
    	String name = friend.getName();
    	String email = friend.getEmail();
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Name: " + name + ", Email: " + email);
    	
//    	builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {               
//    				//implement this in the future
//    				//add a delete parameter in the http post method
//    				deleteFriend(friend.getUserName());
//            }
//     	});
    	
    	builder.setPositiveButton("Send a message", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int id) {
    			Intent i = new Intent(getApplicationContext(), SendTextActivity.class);
            	i.putExtra("recipient", friend.getUserName());
            	startActivity(i);
    		}
    	});
    	
    	builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
           }
    	});

    	AlertDialog alert = builder.create();
    	alert.show();
	}	
}

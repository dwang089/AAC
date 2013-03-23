package edu.tamu.dwang089;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends Activity {
	private EditText userNameInput;
	private EditText passwordInput;
	private Button enterButton;
	private Button signUpButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initializeWidgets();
        
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String userName = extras.getString("userName");
            String password = extras.getString("password");
            userNameInput.setText(userName);
            passwordInput.setText(password);
        }
    }
    
    private void initializeWidgets() {
    	initializeUserNameInput();
    	initializePasswordInput();
    	initializeEnterButton();
    	initializeSignUpButton();
    }
    
    private void initializeUserNameInput() {
    	userNameInput = (EditText) findViewById(R.id.signInUserNameInput);
    }
    
    private void initializePasswordInput() {
    	passwordInput = (EditText) findViewById(R.id.signInPasswordInput);
    }
    
    private void initializeEnterButton() {
    	enterButton = (Button) findViewById(R.id.signInEnterButton);
    	createEnterButtonEvent();
    }
    
    private void createEnterButtonEvent() {
	    enterButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View view) {
	    		if (isValidUserNameAndPassword()) {
	    			setUserName();
	    			enterMenuActivity(view);
	    		}
	    		else {
	    			promptWarningDialog();
	    		}
	        }	
	    });
    }
    
    //retrieve user info from app engine in the future
    private boolean isValidUserNameAndPassword() {
    	String userName = userNameInput.getText().toString().trim();
    	String password = passwordInput.getText().toString().trim();
    	return Utilities.isValidUserNameAndPassword(userName, password);
    }
    
    private void setUserName() {
    	String user = userNameInput.getText().toString().trim();
    	CurrentUser.getInstance().setCurrentUser(user);
    }
    
    private void enterMenuActivity(View view) {
    	Intent myIntent = new Intent(view.getContext(), MainMenuActivity.class);
        startActivityForResult(myIntent, 0);
    }
    
    private void promptWarningDialog() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Incorrect Username or Password!");
    	
    	builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
           }
    	});
    	
    	AlertDialog alert = builder.create();
    	alert.show();
    }
    
    private void initializeSignUpButton() {
    	signUpButton = (Button) findViewById(R.id.signInSignUpButton);
    	
    	signUpButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View view) {
	    		Intent myIntent = new Intent(view.getContext(), SignUpActivity.class);
	            startActivityForResult(myIntent, 0);
	        }	
	    });
    }
}
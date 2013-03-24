package edu.tamu.dwang089;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * This class represents the activity for user sign up
 * 
 * @author Daoqi Wang
 *
 */
public class SignUpActivity extends Activity {
	private EditText userNameInput;
	private EditText passwordInput;
	private EditText nameInput;
	private EditText emailInput;
	private Button enterButton;
	private Button cancelButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        
        initializeWidgets();
    }
    
    private void initializeWidgets() {
    	initializeUserNameInput();
    	initializePasswordInput();
    	initializeNameInput();
    	initializeEmailInput();
    	initializeEnterButton();
    	initializeCancelButton();
    }
    
    private void initializeUserNameInput() {
    	userNameInput = (EditText) findViewById(R.id.signUpUserNameInput);
    }
    
    private void initializePasswordInput() {
    	passwordInput = (EditText) findViewById(R.id.signUpPasswordInput);
    }
    
    private void initializeNameInput() {
    	nameInput = (EditText) findViewById(R.id.signUpNameInput);
    }
    
    private void initializeEmailInput() {
    	emailInput = (EditText) findViewById(R.id.signUpEmailInput);
    }
    
    private void initializeEnterButton() {
    	enterButton = (Button) findViewById(R.id.signUpEnterButton);

    	enterButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View view) {
	    		if (isValidateInputs()) {
	    			User newUser = generateNewUser();
	    			Utilities.postNewUserToGAE(newUser);
	    			promptSignUpSuccessDialog();
	    		}
	        }	
	    });
    }
    
    private boolean isValidateInputs() {
    	return isValidUserName() && isValidPassword() && isValidName() && isValidEmail();
    }
    
    private boolean isValidUserName() { 
    	String userName = userNameInput.getText().toString().trim();
    	
    	if (userName.length() < 4 || !isAlphaNumeric(userName)) {
    		promptWarningDialog("User name needs to have at least 4 characters" +
    				"and can only contain numbers or characters");
    		return false;
    	}
    	else if (Utilities.isExistingUserName(userName)) {
    		promptWarningDialog("This user name already exists");
    		return false;
    	}
    	return true;
    }
    
    //user regex in the future
    private boolean isAlphaNumeric(String string) {
    	String AlphaNumericPattern= "^[a-zA-Z0-9_]*$";
    	return string.matches(AlphaNumericPattern);
    }
    
    private void promptWarningDialog(String string) {
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
    
    private boolean isValidPassword() {
    	String password = passwordInput.getText().toString().trim();
    	
    	if (password.length() < 4 || !isAlphaNumeric(password)) {
    		promptWarningDialog("Password needs to have at least 4 charaters " +
    				"and can only contain numbers or alphabets");
    		return false;
    	}
    	return true;
    }
    
    private boolean isValidName() {
    	if (nameInput.getText().toString().trim().length() == 0) {
    		promptWarningDialog("You need to register with a name");
    		return false;
    	}
    	return true;
    }
    
    private boolean isValidEmail() {
    	String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@" + 
                "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    	
    	if (!emailInput.getText().toString().trim().matches(emailPattern)) {
    		promptWarningDialog("You need to register with a valid Email address");
    		return false;
    	}
    	return true;
    }
    
    private User generateNewUser() {
    	String userName = userNameInput.getText().toString().trim();
    	String password = passwordInput.getText().toString().trim();
    	String name = nameInput.getText().toString().trim();
    	String email = emailInput.getText().toString().trim();
    	
    	User newUser = new User(userName, password, name, email);
    	return newUser;
    }
	
	private void  promptSignUpSuccessDialog() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Sign Up Success");
    	
    	builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                String userName = userNameInput.getText().toString().trim();
            	String password = passwordInput.getText().toString().trim();
            	enterSignInActivityAfterSignUp(userName, password);
           }
    	});
    	
    	AlertDialog alert = builder.create();
    	alert.show();
	}
    
    private void enterSignInActivityAfterSignUp(String userName, String password) {
    	Intent i = new Intent(getApplicationContext(), SignInActivity.class);
    	i.putExtra("userName", userName);
    	i.putExtra("password", password);
    	startActivity(i);
    }
    
    private void initializeCancelButton() {
    	cancelButton = (Button) findViewById(R.id.signUpCancelButton);
    
    	cancelButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View view) {
	    		Intent myIntent = new Intent(view.getContext(), SignInActivity.class);
	            startActivityForResult(myIntent, 0);
	        }	
	    });
    }
}

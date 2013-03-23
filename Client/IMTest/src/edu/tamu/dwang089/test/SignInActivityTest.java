package edu.tamu.dwang089.test;

import edu.tamu.dwang089.SignInActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignInActivityTest extends ActivityInstrumentationTestCase2<SignInActivity> {
	private SignInActivity mActivity;
	private TextView userNameLabel;
	private TextView passwordLabel;
	private EditText userNameInput;
	private EditText passwordInput;
	private Button enterButton;
	private Button signUpButton;
	
	public SignInActivityTest() {
		super("edu.tamu.dwang089", SignInActivity.class);
	}

	protected void setUp() throws Exception {
		mActivity = this.getActivity();
		userNameLabel = (TextView) mActivity.findViewById(edu.tamu.dwang089.R.id.signInUserNameLabel);
		userNameInput = (EditText) mActivity.findViewById(edu.tamu.dwang089.R.id.signInUserNameInput);
		passwordLabel = (TextView) mActivity.findViewById(edu.tamu.dwang089.R.id.signInPasswordLabel);
		passwordInput = (EditText) mActivity.findViewById(edu.tamu.dwang089.R.id.signInPasswordInput);
		enterButton = (Button) mActivity.findViewById(edu.tamu.dwang089.R.id.signInEnterButton);
		signUpButton = (Button) mActivity.findViewById(edu.tamu.dwang089.R.id.signInSignUpButton);
	}
	
	public void testPreconditions() {
		assertNotNull(mActivity);
		assertNotNull(userNameLabel);
		assertNotNull(passwordLabel);
		assertNotNull(userNameInput);
		assertNotNull(passwordInput);
		assertNotNull(enterButton);
	}
	
	public void testUserNameLabel() {
		assertEquals(userNameLabel.getText(), "User Name:");
	}
	
	public void testPasswordLabel() {
		assertEquals(passwordLabel.getText(), "Password:");
	}
	
	public void testEnterButton() {
		assertEquals(enterButton.getText(), "Enter");
	}
	
	public void testSignUpButton() {
		assertEquals(signUpButton.getText(), "Sign Up");
	}
}

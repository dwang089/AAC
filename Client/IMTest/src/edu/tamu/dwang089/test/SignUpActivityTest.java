package edu.tamu.dwang089.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import edu.tamu.dwang089.SignUpActivity;

public class SignUpActivityTest extends ActivityInstrumentationTestCase2<SignUpActivity> {
	private SignUpActivity mActivity;
	private TextView userNameLabel;
	private EditText userNameInput;
	private TextView passwordLabel;
	private EditText passwordInput;
	private TextView nameLabel;
	private EditText nameInput;
	private TextView emailLabel;
	private EditText emailInput;
	private Button enterButton;
	private Button cancelButton;
	
	public SignUpActivityTest() {
		super("edu.tamu.dwang089", SignUpActivity.class);
	}
	
	protected void setUp() throws Exception {
		mActivity = this.getActivity();
		userNameLabel = (TextView) mActivity.findViewById(edu.tamu.dwang089.R.id.signUpUserNameLabel);
		userNameInput = (EditText) mActivity.findViewById(edu.tamu.dwang089.R.id.signUpUserNameInput);
		passwordLabel = (TextView) mActivity.findViewById(edu.tamu.dwang089.R.id.signUpPasswordLabel);
		passwordInput = (EditText) mActivity.findViewById(edu.tamu.dwang089.R.id.signUpPasswordInput);
		nameLabel = (TextView) mActivity.findViewById(edu.tamu.dwang089.R.id.signUpNameLabel);
		nameInput = (EditText) mActivity.findViewById(edu.tamu.dwang089.R.id.signUpNameInput);
		emailLabel = (TextView) mActivity.findViewById(edu.tamu.dwang089.R.id.signUpEmailLabel);
		emailInput = (EditText) mActivity.findViewById(edu.tamu.dwang089.R.id.signUpEmailInput);
		enterButton = (Button) mActivity.findViewById(edu.tamu.dwang089.R.id.signUpEnterButton);
		cancelButton = (Button) mActivity.findViewById(edu.tamu.dwang089.R.id.signUpCancelButton);
	}
	
	public void testPreconditions() {
		assertNotNull(mActivity);
		assertNotNull(userNameLabel);
		assertNotNull(userNameInput);
		assertNotNull(passwordLabel);
		assertNotNull(passwordInput);
		assertNotNull(nameLabel);
		assertNotNull(nameInput);
		assertNotNull(emailLabel);
		assertNotNull(emailInput);
		assertNotNull(enterButton);
		assertNotNull(cancelButton);
	}
	
}

package edu.tamu.dwang089.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import edu.tamu.dwang089.AddFriendActivity;

public class AddFriendActivityTest extends ActivityInstrumentationTestCase2<AddFriendActivity> {
	private AddFriendActivity mActivity;
	private TextView friendLabel;
	private EditText friendInput;
	private Button enterButton;
	private Button backButton;
	
	public AddFriendActivityTest() {
		super("edu.tamu.dwang089", AddFriendActivity.class);
	}
	
	protected void setUp() throws Exception {
		mActivity = this.getActivity();
		friendLabel = (TextView) mActivity.findViewById(edu.tamu.dwang089.R.id.addFriendFriendLabel);
		friendInput = (EditText) mActivity.findViewById(edu.tamu.dwang089.R.id.addFriendFriendInput);
		enterButton = (Button) mActivity.findViewById(edu.tamu.dwang089.R.id.addFriendEnterButton);
		backButton = (Button) mActivity.findViewById(edu.tamu.dwang089.R.id.addFriendBackButton);
	}
	
	public void testPreconditions() {
		assertNotNull(mActivity);
		assertNotNull(friendLabel);
		assertNotNull(friendInput);
		assertNotNull(enterButton);
		assertNotNull(backButton);
	}
	
	public void testFriendLabel() {
		assertEquals(friendLabel.getText(), "Friend:");
	}
	
	public void testEnterButton() {
		assertEquals(enterButton.getText(), "Enter");
	}
	
	public void testBackButton() {
		assertEquals(backButton.getText(), "Back");
	}
}

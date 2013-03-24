package edu.tamu.dwang089.test;

import edu.tamu.dwang089.MainMenuActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

public class MainMenuActivityTest extends ActivityInstrumentationTestCase2<MainMenuActivity> {
	private MainMenuActivity mActivity;
	private Button readTextButton;
	private Button sendTextButton;
	private Button signOffButton;
	
	public MainMenuActivityTest() {
		super("edu.tamu.dwang089", MainMenuActivity.class);
	}

	protected void setUp() throws Exception {
		mActivity = this.getActivity();
		readTextButton = (Button) mActivity.findViewById(edu.tamu.dwang089.R.id.mainMenuReadTextButton);
		sendTextButton = (Button) mActivity.findViewById(edu.tamu.dwang089.R.id.mainMenuSendTextButton);
		signOffButton = (Button) mActivity.findViewById(edu.tamu.dwang089.R.id.mainMenuSignOffButton);
	}
	
	public void testPreconditions() {
		assertNotNull(mActivity);
		assertNotNull(readTextButton);
		assertNotNull(sendTextButton);
		assertNotNull(signOffButton);
	}
	
	public void testReadTextButton() {
		assertEquals(readTextButton.getText(), "Read Text Messages");
	}
	
	public void testSendTextButton() {
		assertEquals(sendTextButton.getText(), "Send a Text Message");
	}
	
	public void testSignOffButton() {
		assertEquals(signOffButton.getText(), "Sign Off");
	}

}

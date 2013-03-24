package edu.tamu.dwang089.test;

//import edu.tamu.dwang089.MenuActivity;
import edu.tamu.dwang089.SendTextActivity;
//import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
//import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SendTextActivityTest extends ActivityInstrumentationTestCase2<SendTextActivity> {
	private SendTextActivity mActivity;
	private TextView recipientLabel;
	private EditText recipientInput;
	private TextView contentLabel;
	private EditText contentInput;
	private Button sendButton;
	private Button backButton;
	
//	private Activity nextActivity;
	
	public SendTextActivityTest() {
		super("edu.tamu.dwang089", SendTextActivity.class);
	}
	
	public void setUp() throws Exception {
		mActivity = this.getActivity();
		recipientLabel = (TextView) mActivity.findViewById(edu.tamu.dwang089.R.id.sendTextRecipientLabel);
		recipientInput = (EditText) mActivity.findViewById(edu.tamu.dwang089.R.id.sendTextRecipientInput);
		contentLabel = (TextView) mActivity.findViewById(edu.tamu.dwang089.R.id.sendTextContentLabel);
		contentInput = (EditText) mActivity.findViewById(edu.tamu.dwang089.R.id.sendTextContentInput);
		sendButton = (Button) mActivity.findViewById(edu.tamu.dwang089.R.id.sendTextSendButton); 
		backButton = (Button) mActivity.findViewById(edu.tamu.dwang089.R.id.sendTextBackButton); 
	}
	
	public void testPreconditions() {
		assertNotNull(mActivity);
		assertNotNull(recipientLabel);
		assertNotNull(recipientInput);
		assertNotNull(contentLabel);
		assertNotNull(contentInput);
		assertNotNull(sendButton);
		assertNotNull(backButton);
	}

	public void testRecipientLabel() {
		assertEquals(recipientLabel.getText(), "Send to:");
	}
	
	public void testContentLabel() {
		assertEquals(contentLabel.getText(), "Message:");
	}
	
	public void testSendButton() {
		assertEquals(sendButton.getText(), "Send");
	}
	
	public void testBackButton() {
		assertEquals(backButton.getText(), "Main");
	}
}

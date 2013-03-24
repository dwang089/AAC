package edu.tamu.dwang089.test;

import edu.tamu.dwang089.DisplayTextActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ListView;

public class DisplayTextActivityTest extends ActivityInstrumentationTestCase2<DisplayTextActivity> {
	private DisplayTextActivity mActivity;
	private Button backButton;
	private ListView messageListView; 
	
	public DisplayTextActivityTest() {
		super("edu.tamu.dwang089", DisplayTextActivity.class);
	}
	
	protected void setUp() throws Exception {
		mActivity = this.getActivity();
		backButton = (Button) mActivity.findViewById(edu.tamu.dwang089.R.id.displayTextBackButton);
		messageListView = (ListView) mActivity.findViewById(edu.tamu.dwang089.R.id.displayTextMessagelist);
	}
	
	public void testPreconditions() {
		assertNotNull(mActivity);
		assertNotNull(backButton);
		assertNotNull(messageListView);
	}
	
	public void testBackButton() {
		assertEquals(backButton.getText(), "Back");
	}

}
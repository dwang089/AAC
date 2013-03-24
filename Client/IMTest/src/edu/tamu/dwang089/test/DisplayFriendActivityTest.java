package edu.tamu.dwang089.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ListView;
import edu.tamu.dwang089.DisplayFriendActivity;

public class DisplayFriendActivityTest extends ActivityInstrumentationTestCase2<DisplayFriendActivity> {
	private DisplayFriendActivity mActivity;
	private Button backButton;
	private Button addButton;
	private ListView friendListView;

	public DisplayFriendActivityTest() {
		super("edu.tamu.dwang089", DisplayFriendActivity.class);
	}
	
	protected void setUp() throws Exception {
		mActivity = this.getActivity();
		backButton = (Button) mActivity.findViewById(edu.tamu.dwang089.R.id.displayFriendBackButton);
		addButton = (Button) mActivity.findViewById(edu.tamu.dwang089.R.id.displayFriendAddButton);
		friendListView = (ListView) mActivity.findViewById(edu.tamu.dwang089.R.id.displayFriendListView);
	}
	
	public void testPreconditions() {
		assertNotNull(mActivity);
		assertNotNull(backButton);
		assertNotNull(addButton);
		assertNotNull(friendListView);
	}
	
	public void testBackButton() {
		assertEquals(backButton.getText(), "Back");
	}
	
	public void testAddButton() {
		assertEquals(addButton.getText(), "Add Friends");
	}
}

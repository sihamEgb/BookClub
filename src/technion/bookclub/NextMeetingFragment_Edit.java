package technion.bookclub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NextMeetingFragment_Edit extends Fragment {
//	public String bookId;
	public NextMeetingFragment_Edit() {
//		this.bookId=bookId;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.next_meeting_edit, container,
				false);

		return rootView;
	}
	
}

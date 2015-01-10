package technion.bookclub;

import android.os.Bundle;
//import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.view.ViewGroup;
import android.support.v4.app.Fragment;
//import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class NextMeetingFragment extends Fragment {

	public NextMeetingFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.next_meeting, container,
				false);
		return rootView;
	}
	
	
}

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
public class ClubPageFragment extends Fragment {

	public ClubPageFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.club_page, container,
				false);
		return rootView;
	}
	
	public static Fragment newInstance() {
		NextMeetingFragment fragment = new NextMeetingFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

}

package technion.bookclub;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
//import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
//import android.view.ViewGroup;
import android.support.v4.app.Fragment;
//import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class NextMeetingFragment extends Fragment {

	public NextMeetingFragment() {
		// Required empty public constructor.
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.next_meeting, container,
				false);
		setHasOptionsMenu(true);
		return rootView;
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = this.getActivity().getMenuInflater();
		inflater.inflate(R.menu.club_next_meeting, menu);
		//return getActivity().super.onCreateOptionsMenu(menu, inflater);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		return true;
	}
}

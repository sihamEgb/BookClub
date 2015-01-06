package technion.bookclub;

import technion.bookclub.entities.Club;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SplashFragment extends Fragment {

	public SplashFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//listView=(ExpandableListView) findViewById(R.id.club_info_list);
		View rootView = inflater.inflate(R.layout.splash, container,
				false);

		return rootView;
	}

	public static Fragment newInstance() {
		SplashFragment fragment = new SplashFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}	
}

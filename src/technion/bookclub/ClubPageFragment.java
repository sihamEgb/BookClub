package technion.bookclub;


import technion.bookclub.entities.Club;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

public class ClubPageFragment extends Fragment {
	//ExpandableListView listView;

	public ClubPageFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//listView=(ExpandableListView) findViewById(R.id.club_info_list);
		View rootView = inflater.inflate(R.layout.club_page, container,
				false);

		return rootView;
	}

	public static Fragment newInstance(Club newClub) {
		ClubPageFragment fragment = new ClubPageFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}
}

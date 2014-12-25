package technion.bookclub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ClubsResultsFragment extends ListFragment {
	ListView myListView;
	ClubListAdapter adapter;

	public ClubListAdapter getAdapter() {
		return adapter;
	}

	// Store instance variables based on arguments passed
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.clubs_results, container, false);

		adapter = new ClubListAdapter(getActivity());

		setListAdapter(adapter);
		return v;
	}

	public static Fragment newInstance(int newColor) {
		ClubsResultsFragment fragment = new ClubsResultsFragment();
		Bundle args = new Bundle();
		// args.putInt("someInt", i);
		// args.putString("someTitle", string);
		fragment.setArguments(args);
		return fragment;
	}

}

package technion.bookclub;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ClubsResultsFragment extends ListFragment {
	ListView myListView;
	ClubListAdapter adapter;
	private static String data;

	public ClubsResultsFragment(String searchResult) {
		data = searchResult;
	}

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

		adapter = new ClubListAdapter(getActivity(), data);

		setListAdapter(adapter);
		return v;
	}

}

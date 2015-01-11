package technion.bookclub;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class ClubsResultsFragment extends ListFragment {
	ListView myListView;
	ClubListAdapter adapter;
	private static String data;
	private String location;

	public ClubsResultsFragment(String searchResult, String clubLocation) {
		data = searchResult;
		location = clubLocation;
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

		TextView v2 = (TextView) v.findViewById(
				R.id.club_results_id);
		v2.setText("Book clubs in:" + location);
		adapter = new ClubListAdapter(getActivity(), data);

		setListAdapter(adapter);
		return v;
	}

}

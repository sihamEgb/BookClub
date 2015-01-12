package technion.bookclub;

import technion.bookclub.entities.Club;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

		TextView v2 = (TextView) v.findViewById(R.id.club_results_id);
		v2.setText("Book clubs in:" + location);
		adapter = new ClubListAdapter(getActivity(), data);

		setListAdapter(adapter);

		return v;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Do your stuff..

		Club myClub = (Club) adapter.getItem(position);
		Intent in = new Intent(getActivity().getApplicationContext(),
				ClubPageActivity.class);
		in.putExtra("clubId", myClub.getClubId());
		in.putExtra("adminId", myClub.getAdminId());
		in.putExtra("name", myClub.getName());
		in.putExtra("location", myClub.getLocation());
		in.putExtra("description", myClub.getDescription());
		in.putExtra("imageUrl", myClub.getImageUrl());
		in.putExtra("memeberNum", myClub.getMemeberNum());
		startActivity(in);

		// Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();

	}

}

package technion.bookclub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

public class ClubSearchFragment extends Fragment {

	//public static final String ARG_PLANET_NUMBER = "planet_number";
	
	private AutoCompleteTextView autoComplete;
	private ArrayAdapter<String> adapter;
	

	public ClubSearchFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.clubs_search, container,
				false);
		//int i = getArguments().getInt(ARG_PLANET_NUMBER);
		//TODO - 1 from where??
		String index = getResources().getStringArray(R.array.drawer_options)[1];

		// get the defined string-array
		String[] location = getResources().getStringArray(
				R.array.location_array);

		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, location);

		autoComplete = (AutoCompleteTextView) rootView
				.findViewById(R.id.location_spinner);

		// set adapter for the auto complete fields
		autoComplete.setAdapter(adapter);

		// specify the minimum type of characters before drop-down list is shown
		autoComplete.setThreshold(1);

		// comma to separate the different colors
		// when the user clicks an item of the drop-down list

		;
		
		Button button = (Button) rootView.findViewById(R.id.searchClub);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment fragment = new ClubsResultsFragment();
				Bundle args = new Bundle();
				// args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
				fragment.setArguments(args);

				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, fragment).commit();
				// DoIt(v);
			}
		});

		// TextView tv = (TextView) findViewById(R.id.rate);
		// textViewToChange.setText(
		// "The new text that I'd like to display now that the user has pushed a button.");

		// int imageId = getResources().getIdentifier(
		// index.toLowerCase(Locale.getDefault()), "drawable",
		// getActivity().getPackageName());
		// ((ImageView) rootView.findViewById(R.id.image))
		// .setImageResource(imageId);
		getActivity().setTitle(index);
		return rootView;
	}

	public static Fragment newInstance(int newColor) {
		ClubSearchFragment fragment = new ClubSearchFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

}

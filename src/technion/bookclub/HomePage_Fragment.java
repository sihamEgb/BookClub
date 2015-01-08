package technion.bookclub;

import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomePage_Fragment extends Fragment {
	public static final String ARG_PLANET_NUMBER = "planet_number";

	public HomePage_Fragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.homepage_fragment, container,
				false);
		int i = getArguments().getInt(ARG_PLANET_NUMBER);
		String planet = getResources().getStringArray(R.array.drawer_options)[i];

		int imageId = getResources().getIdentifier(
				planet.toLowerCase(Locale.getDefault()), "drawable",
				getActivity().getPackageName());
		// ((ImageView) rootView.findViewById(R.id.image))
		// .setImageResource(imageId);
		getActivity().setTitle(planet);

		final Button button = (Button) rootView.findViewById(R.id.button_id);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), HomePage_AddNewClub.class);
				startActivity(i);
			}
		});

		final Button button2 = (Button) rootView.findViewById(R.id.newbook);
		button2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), HomePage_AddNewBook.class);
				startActivity(i);
			}
		});

		return rootView;
	}

	public static Fragment newInstance(int newColor) {
		HomePage_Fragment fragment = new HomePage_Fragment();
		Bundle args = new Bundle();
		// args.putInt("someInt", i);
		// args.putString("someTitle", string);
		fragment.setArguments(args);
		return fragment;
	}
}
package technion.bookclub;

import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PlanetFragment extends Fragment {
	public static final String ARG_PLANET_NUMBER = "planet_number";

	public PlanetFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_planet, container,
				false);
		int i = getArguments().getInt(ARG_PLANET_NUMBER);
		String planet = getResources().getStringArray(R.array.drawer_options)[i];

		int imageId = getResources().getIdentifier(
				planet.toLowerCase(Locale.getDefault()), "drawable",
				getActivity().getPackageName());
		((ImageView) rootView.findViewById(R.id.image))
				.setImageResource(imageId);
		getActivity().setTitle(planet);
		return rootView;
	}

	public static Fragment newInstance(int newColor) {
		PlanetFragment fragment = new PlanetFragment();
		Bundle args = new Bundle();
		// args.putInt("someInt", i);
		// args.putString("someTitle", string);
		fragment.setArguments(args);
		return fragment;
	}
}
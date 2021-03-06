package technion.bookclub;

import org.json.JSONObject;

import com.loopj.android.http.*;
import org.apache.http.Header;

//import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.AsyncHttpResponseHandler;
//import com.loopj.android.http.JsonHttpResponseHandler;
//import com.loopj.android.http.RequestParams;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ClubSearchFragment extends Fragment {

	private AutoCompleteTextView autoCompleteLocation;
	private ArrayAdapter<String> adapter;
	private ProgressBar spinner;

	public ClubSearchFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.clubs_search, container,
				false);

		String index = getResources().getStringArray(R.array.drawer_options)[0];

		// location auto complete
		String[] location = getResources().getStringArray(
				R.array.location_array);
		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, location);
		autoCompleteLocation = (AutoCompleteTextView) rootView
				.findViewById(R.id.location_spinner);
		autoCompleteLocation.setAdapter(adapter);
		autoCompleteLocation.setThreshold(1);

		// spinner status
		spinner = (ProgressBar) rootView.findViewById(R.id.progressBar1);
		spinner.setVisibility(View.GONE);

	
		// button
		final Button button = (Button) rootView.findViewById(R.id.searchClub);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			    in.hideSoftInputFromWindow(v.getWindowToken(), 0);
			    
				spinner.setVisibility(View.VISIBLE);
				Context context = getActivity();
				int duration = Toast.LENGTH_SHORT;

				final String clubLocation = autoCompleteLocation.getText().toString();

				if (clubLocation.equals("")) {
					Toast toast = Toast.makeText(context,
							"Please choose a location from the list", duration);
					toast.show();

				} else {

					AsyncHttpClient client = new AsyncHttpClient();
					RequestParams params = new RequestParams();
					params.put("location", clubLocation);

					
					client.get("http://bookclub-server.appspot.com/searchclub",
							params, new AsyncHttpResponseHandler() {

								@Override
								public void onSuccess(int statusCode,
										Header[] headers, byte[] response) {
									String s = new String(response);
									System.out.println("sucees" + s);

									spinner.setVisibility(View.GONE);
									Fragment fragment = new ClubsResultsFragment(
											s,clubLocation);
									Bundle args = new Bundle();
									fragment.setArguments(args);
									FragmentManager fragmentManager = getFragmentManager();
									fragmentManager
											.beginTransaction()
											.replace(R.id.content_frame,
													fragment).commit();

								}

								@Override
								public void onFailure(int arg0, Header[] arg1,
										byte[] arg2, Throwable arg3) {
									System.out.println("failed");
									System.out.println(arg1);
									System.out.println(arg2);
									System.out.println(arg3);

								}

							});

				}
			}
		});

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

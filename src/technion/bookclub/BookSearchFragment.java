package technion.bookclub;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Context;
//import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
//import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

//import android.widget.Toast;

public class BookSearchFragment extends Fragment {

	private EditText titleQuery;
	private AutoCompleteTextView autoCompleteLocation;
	private ArrayAdapter<String> autoCompleteAdapter;
	private Spinner languageSpinner;
	private ProgressBar spinner;

	public BookSearchFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.books_search, container,
				false);
		String index = getResources().getStringArray(R.array.drawer_options)[2];

		// book title query
		titleQuery = (EditText) rootView.findViewById(R.id.bookNameQuery);

		// language
		languageSpinner = (Spinner) rootView
				.findViewById(R.id.language_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this.getActivity(), R.array.language_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		languageSpinner.setAdapter(adapter);

		// location auto complete
		String[] location = getResources().getStringArray(
				R.array.location_array);

		autoCompleteAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, location);
		autoCompleteLocation = (AutoCompleteTextView) rootView
				.findViewById(R.id.location_spinner);
		autoCompleteLocation.setAdapter(autoCompleteAdapter);
		autoCompleteLocation.setThreshold(1);

		// spinner status
		spinner = (ProgressBar) rootView.findViewById(R.id.progressBar1);
		spinner.setVisibility(View.GONE);

		// button
		final Button button = (Button) rootView.findViewById(R.id.searchBook);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				InputMethodManager in = (InputMethodManager) getActivity()
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				in.hideSoftInputFromWindow(v.getWindowToken(), 0);

				// Context context = getActivity();
				// int duration = Toast.LENGTH_SHORT;
				spinner.setVisibility(View.VISIBLE);

				final String bookTitle = titleQuery.getText().toString().trim();
				final String bookLanguage = languageSpinner.getSelectedItem()
						.toString();
				final String clubLocation = autoCompleteLocation.getText()
						.toString();

				// TODO - change logica
				if (clubLocation.equals("")) {
					// clubLocation = "Haifa";
					// Toast toast = Toast.makeText(context,
					// "Please choose a location from the list", duration);
					// toast.show();

				}
				if (bookTitle.equals("")) {
					// bookTitle = "Frozen";
					// Toast toast = Toast.makeText(context,
					// "Please choose a location from the list", duration);
					// toast.show();

				}
				if (bookLanguage.equals("")) {
					// bookLanguage = "Arabic";
					// Toast toast = Toast.makeText(context,
					// "Please choose a location from the list", duration);
					// toast.show();

				}

				AsyncHttpClient client = new AsyncHttpClient();
				RequestParams params = new RequestParams();
				params.put("location", clubLocation);
				params.put("language", bookLanguage);
				params.put("title", bookTitle);

				client.get("http://jalees-bookclub.appspot.com/searchbook",
						params, new AsyncHttpResponseHandler() {

							@Override
							public void onSuccess(int statusCode,
									Header[] headers, byte[] response) {
								String s = new String(response);

								System.out.println("sucees" + s);
								spinner.setVisibility(View.GONE);

								Fragment fragment = new BooksResultsFragment(s,
										bookTitle, bookLanguage, clubLocation);
								Bundle args = new Bundle();
								fragment.setArguments(args);
								FragmentManager fragmentManager = getFragmentManager();
								fragmentManager.beginTransaction()
										.replace(R.id.content_frame, fragment)
										.commit();

							}

							@Override
							public void onFailure(int arg0, Header[] arg1,
									byte[] arg2, Throwable arg3) {
								System.out.println("failed");
								// TODO Auto-generated method stub

							}

						});

			}
		});

		getActivity().setTitle(index);
		return rootView;
	}

	public static Fragment newInstance(int newColor) {
		BookSearchFragment fragment = new BookSearchFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

}
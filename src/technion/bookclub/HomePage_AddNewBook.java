package technion.bookclub;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HomePage_AddNewBook extends Activity {

	EditText titleEditText;
	EditText authorEditText;
	Spinner locationSpinner;
	Spinner languageSpinner;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.homepage_add_newbook);

		Spinner location_spinner = (Spinner) findViewById(R.id.homepage_addingbook_location_spinner);
		ArrayAdapter<CharSequence> location_spinner_adapter = ArrayAdapter
				.createFromResource(this, R.array.location_array,
						android.R.layout.simple_spinner_dropdown_item);
		location_spinner.setAdapter(location_spinner_adapter);
		location_spinner_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		Spinner language_spinner = (Spinner) findViewById(R.id.homepage_addingbook_language_spinner);
		ArrayAdapter<CharSequence> language_spinner_adapter = ArrayAdapter
				.createFromResource(this, R.array.language_array,
						android.R.layout.simple_spinner_dropdown_item);
		language_spinner.setAdapter(language_spinner_adapter);
		language_spinner_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		titleEditText = (EditText) findViewById(R.id.homepage_addingbook_bookname);
		authorEditText = (EditText) findViewById(R.id.homepage_addingbook_bookauthor);

		locationSpinner = location_spinner;
		languageSpinner = language_spinner;

	}

	/*
	 * @Override public View onCreateView(LayoutInflater inflater, ViewGroup
	 * container, Bundle savedInstanceState) { View v =
	 * inflater.inflate(R.layout.homepage_add_newbook, container, false); return
	 * v; }
	 */

	/*
	 * @Override public void onCreateOptionsMenu(Menu menu, MenuInflater
	 * inflater) { super.onCreateOptionsMenu(menu, inflater); if (menu != null)
	 * { menu.add(0, 1, 0, "HomePageAddBook")
	 * .setIcon(R.drawable.homepage_ic_action_send_now) .setTitle("AddBook")
	 * .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS); //
	 * menu.add("CreateClub"
	 * ).setTitle("Create >>").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
	 * } }
	 */
	// TODO
	public void addNewBook(View view) {

		//Context context = getApplicationContext();

		String bookTitle = titleEditText.getText().toString().trim();
		String author = authorEditText.getText().toString().trim();
		String location = locationSpinner.getSelectedItem().toString();
		String language = languageSpinner.getSelectedItem().toString();

		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("title", bookTitle);
		params.put("author", author);
		params.put("location", location);
		params.put("language", language);

		client.get("http://jalees-bookclub.appspot.com/addbook", params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onFailure(int arg0,
							org.apache.http.Header[] arg1, byte[] arg2,
							Throwable arg3) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(int arg0,
							org.apache.http.Header[] arg1, byte[] arg2) {
						// TODO Auto-generated method stub

					}
				});

	}
}

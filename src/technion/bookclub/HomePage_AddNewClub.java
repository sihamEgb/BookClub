package technion.bookclub;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HomePage_AddNewClub extends Activity {

	EditText nameEditText;
	EditText descEditText;
	EditText imageUrlEditText;

	// Spinner locationSpinner;
	private AutoCompleteTextView autoCompleteLocation;
	private ArrayAdapter<String> autoCompleteAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage_add_newclub);

		// location auto complete
		String[] location = getResources().getStringArray(
				R.array.location_array);

		autoCompleteAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, location);
		autoCompleteLocation = (AutoCompleteTextView) findViewById(R.id.location_spinner);
		autoCompleteLocation.setAdapter(autoCompleteAdapter);
		autoCompleteLocation.setThreshold(1);

		imageUrlEditText = (EditText) findViewById(R.id.homepage_adding_club_imageUrl_text);
		nameEditText = (EditText) findViewById(R.id.club_name);
		descEditText = (EditText) findViewById(R.id.club_description);

		// getSupportActionBar().setDisplayShowTitleEnabled(false);

		// final Button button = (Button) findViewById(R.id.addClub);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.creating_club_activity_main, menu);
		return true;
		// menu.add(0, 0, 0,
		// "History").setIcon(R.drawable.ic_launcher).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		// menu.add(0, 1, 0,
		// "Settings").setIcon(R.drawable.ic_launcher).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		// menu.add("Add Club +").setTitle("Add").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

		// menu.add(0, 1, 0,
		// "CreatClub").setIcon(R.drawable.gray_book_group).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		// menu.add("CreateClub").setTitle("Create >>").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

		// return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}

		// if (id == R.id.action_request_creating_club) {
		/*
		 * if(clubDesc.length()<=0){ Toast toast = Toast.makeText(context,
		 * "Are you sure you dont want to add a description for your club!!" ,
		 * duration); toast.show(); return true; }
		 */

		// TODO: ADD REDIRECTION TO THE CREATED CLUB PAGE INSTEAD OF THE
		// NEXT TWO LINES

		// return true;

		// if (id == R.id.action_cancel_creating_club_request) {
		// TODO: ADD REDIRECTION To the previous activity
		// return true;
		// }

		return super.onOptionsItemSelected(item);
	}

	public void addNewClub(View view) {

		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;

		String clubName = nameEditText.getText().toString().trim();
		String clubDesc = descEditText.getText().toString().trim();
		String clubLocation = autoCompleteLocation.getText().toString();
		String imageUrl = imageUrlEditText.getText().toString();

		if (imageUrl == "")
			imageUrl = "http://www.viduman.com/dosya/default.jpg";

		if (clubName == null || clubDesc == null || clubLocation == null) {
			Toast toast = Toast.makeText(context, "Error Can Not Create Club",
					duration);
			toast.show();

		}
		if (clubName.length() <= 0) {
			Toast toast = Toast.makeText(context,
					"Insert A Name For Your Club Please!!", duration);
			toast.show();

		}
		Toast toast = Toast.makeText(context, clubName + " " + clubLocation
				+ " " + clubDesc, duration);
		toast.show();
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("name", clubName);
		params.put("location", clubLocation);
		params.put("description", clubDesc);
		params.put("adminId", "adminTemp");
		params.put("imageUrl", imageUrl);

		client.get("http://jalees-bookclub.appspot.com/addclub", params,
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

package technion.bookclub;

import org.apache.http.Header;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

		imageUrlEditText = (EditText) findViewById(R.id.homepage_adding_club_imageUrl);
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

		String imageUrl = "http://www.viduman.com/dosya/default.jpg";

		final String clubName = nameEditText.getText().toString().trim();
		final String clubDesc = descEditText.getText().toString().trim();
		final String clubLocation = autoCompleteLocation.getText().toString();
		imageUrl = imageUrlEditText.getText().toString();

		if (imageUrl.equals(""))
			imageUrl = "http://www.viduman.com/dosya/default.jpg";

		if (imageUrl == null)
			imageUrl = "http://www.viduman.com/dosya/default.jpg";

		if (clubName == null || clubDesc == null || clubLocation == null) {
			Toast toast = Toast.makeText(context, "Error Cannot Create Club",
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
		params.put("adminId", UserInfo.getId());
		params.put("imageUrl", imageUrl);

		final String urlStam = imageUrl;
		client.get("http://bookclub-server.appspot.com/addclub", params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] response) {
						String s = new String(response);
						System.out.println("sucees" + s);

						// s is the new clubId
						// System.out.println("sucees" + s);

						Intent in = new Intent(getApplicationContext(),
								ClubPageActivity.class);
						in.putExtra("clubId", s);
						in.putExtra("adminId", UserInfo.getId());
						in.putExtra("name", clubName);
						in.putExtra("location", clubLocation);
						in.putExtra("description", clubDesc);

						in.putExtra("imageUrl", urlStam);
						in.putExtra("memeberNum", 1);
						startActivity(in);

					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						System.out.println("failed");
						System.out.println(arg1);
						System.out.println(arg2);
						System.out.println(arg3);

					}

				});

	}

}

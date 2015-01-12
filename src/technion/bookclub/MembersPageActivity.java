package technion.bookclub;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import technion.bookclub.entities.Club;
import technion.bookclub.entities.User;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MembersPageActivity extends ListActivity {

	ListView myListView;
	UserListAdapter adapter;
	private static String data;
	private String name;
	private Context context;

	

	public UserListAdapter getAdapter() {
		return adapter;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//data = searchResult;
		//name = clubName;

		context = this;
		setContentView(R.layout.members_activity);

		Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();

		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("clubId", "6205504040730624");

		client.get("http://jalees-bookclub.appspot.com/getmembers", params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] response) {
						String s = new String(response);
						System.out.println("sucees" + s);
						adapter = new UserListAdapter(context, s);
						TextView v2 = (TextView) findViewById(R.id.members_results_id);
						v2.setText("Members in this club:");
						
						setListAdapter(adapter);

					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
					}

				});

		

	}

}

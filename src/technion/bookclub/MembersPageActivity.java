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
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MembersPageActivity extends ListFragment {

	ListView myListView;
	UserListAdapter adapter;
	private static String data;
	private String name;
	private Context context;
	public View view;
	public String clubId;

	public UserListAdapter getAdapter() {
		return adapter;
	}
	
	
	public MembersPageActivity(String clubId) {
		this.clubId=clubId;
		// Required empty public constructor
	}

//	// Store instance variables based on arguments passed
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.members_activity, container, false);

		//adapter = new ClubListAdapter(getActivity(), data);

		//setListAdapter(adapter);

		context = view.getContext();
//		setContentView(R.layout.members_activity);

//		Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();

		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("clubId", clubId);

		client.get("http://jalees-bookclub.appspot.com/getmembers", params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] response) {
						String s = new String(response);
						System.out.println("sucees" + s);
						adapter = new UserListAdapter(context, s);
						TextView v2 = (TextView) view.findViewById(R.id.members_results_id);
						v2.setText("Members in this club:");
						
						setListAdapter(adapter);

					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
					}

				});
		return view;
	}



}

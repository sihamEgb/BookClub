package technion.bookclub;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;

import technion.bookclub.entities.Club;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class SplashFragment extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Intent intent = getIntent();
		setContentView(R.layout.splash);
		// enable ActionBar app icon to behave as action to toggle nav drawer
//		getActionBar().setDisplayHomeAsUpEnabled(true);
	//	getActionBar().setHomeButtonEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// Called whenever we call invalidateOptionsMenu() 
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		//boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_websearch).setVisible(true);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		//if (mDrawerToggle.onOptionsItemSelected(item)) {
		//	return true;
		//}
		// Handle action buttons
		switch (item.getItemId()) {
		case R.id.action_websearch:
			// create intent to perform web search for this planet
			Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
			intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
			// catch event that there's no activity to handle intent
			if (intent.resolveActivity(getPackageManager()) != null) {
				startActivity(intent);
			} else {
				Toast.makeText(this, R.string.app_not_available,
						Toast.LENGTH_LONG).show();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
/*
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//listView=(ExpandableListView) findViewById(R.id.club_info_list);
		View rootView = inflater.inflate(R.layout.splash, container,
				false);

		return rootView;
	}
	
	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
		Context context = this.getActivity().getApplicationContext();
		CharSequence text = "Hello toast!";
		CharSequence text2 = "bye toast!";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		Toast toast2 = Toast.makeText(context, text2, duration);
		//toast.show();
		
	    if (state.isOpened()) {
	    	toast.show();
	    } else if (state.isClosed()) {
	        //Log.i(TAG, "Logged out...");
	    	toast2.show();
	    }
	}
	
	//private static final String TAG = "MainFragment";

	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	private UiLifecycleHelper uiHelper;

	
	public static Fragment newInstance() {
		SplashFragment fragment = new SplashFragment();
		Bundle args = new Bundle();

		fragment.setArguments(args);
		return fragment;
	}	*/
}

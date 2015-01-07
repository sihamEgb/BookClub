package technion.bookclub;


//import technion.bookclub.MainActivity.DrawerItemClickListener;
import technion.bookclub.entities.Club;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

public class ClubPageFragment extends FragmentActivity {
	//ExpandableListView listView;

	
/*
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//listView=(ExpandableListView) findViewById(R.id.club_info_list);
		View rootView = inflater.inflate(R.layout.club_page, container,
				false);
		
        final Button button = (Button) this.getActivity().findViewById(R.id.Join_Club);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Fragment currFragment = SplashFragment.newInstance();
    			// fragment = new ClubSearchFragment();
    			//System.out.println(position);
    			System.out.println(currFragment);
            }
        });

		return rootView;
	}

	public static Fragment newInstance(Club newClub) {
		ClubPageFragment fragment = new ClubPageFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}
	*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Intent intent = getIntent();
		setContentView(R.layout.club_page);
		// enable ActionBar app icon to behave as action to toggle nav drawer
//		getActionBar().setDisplayHomeAsUpEnabled(true);
	//	getActionBar().setHomeButtonEnabled(true);

	}

	public void JoinClub(View view) {
		
		Intent myIntent = new Intent(this, SplashFragment.class);
		//myIntent.putExtra("key", value); //Optional parameters
		startActivityForResult(myIntent,0);
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
}

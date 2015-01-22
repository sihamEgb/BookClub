package technion.bookclub;

import org.apache.http.Header;

import technion.bookclub.entities.Book;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import technion.bookclub.entities.Club;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mDrawerOptionsTitles;

	static Fragment currFragment;
	
	//Facebook:
	public String fbUserId="";
	public String serverUserId;
	public String fbUserName="";
	private static final int SPLASH = 0;
	private static final int SELECTION = 1;
	private static final int FRAGMENT_COUNT = SELECTION +1;
	private static final int REAUTH_ACTIVITY_CODE = 100;
	private Fragment[] fragments = new Fragment[FRAGMENT_COUNT];
	private boolean isResumed = false;
	private UiLifecycleHelper uiHelper;
	private boolean isLogedIn;
	public SelectionFragment selectionF;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Facebook
        uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);
	    Session session = Session.getActiveSession();
	    if (session != null && session.isOpened()) {
	        // Get the user's data
	    	isLogedIn=true;
	    	UserInfo.logIn(true);
	        makeMeRequest(session);
	        
	    } else{
	    	isLogedIn=false;
	    	UserInfo.logIn(false);
	    }
        
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();
		
		// getActionBar().setLogo(R.drawable.bookshelf_icon);

		mDrawerOptionsTitles = getResources().getStringArray(
				R.array.drawer_options);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mDrawerOptionsTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open, 0) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();

			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();

			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
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
		case R.id.get_users:
	
			Intent in = new Intent(this, MembersPageActivity.class);
			//in.putExtra("data", );
			in.putExtra("name", "bla bla club");
			
			startActivity(in);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {
		// update the main content by replacing fragments
		// Fragment fragment = null;
		switch (position) {
		case 0:
			currFragment = ClubSearchFragment.newInstance(0);
			// fragment = new ClubSearchFragment();
			System.out.println(position);
			System.out.println(currFragment);
			break;

		case 1:
            if(UserInfo.isLoggedIn()){
    			String user_id = UserInfo.getId();
    			Intent home_page_intent = new Intent(this, HomePageActivity.class);
    			home_page_intent.putExtra("userId", user_id);
    			this.startActivity(home_page_intent);
    			return;
            }else{
                currFragment = SplashFragment.newInstance();
                position =3;
                break;
            }
            
		case 2:
			currFragment = BookSearchFragment.newInstance(0);
			// fragment = new ClubSearchFragment();
			System.out.println(position);
			System.out.println(currFragment);
			break;

		case 3:
			currFragment = SplashFragment.newInstance();
			// fragment = new ClubSearchFragment();
			System.out.println(position);
			System.out.println(currFragment);


			// break;

			// case 2:
			// fragment = new HomePage_ClubCreationFragment();
			// System.out.println(position);
			// System.out.println(currFragment);
			// break;
		//default:
		//	currFragment = PlanetFragment.newInstance(0);
		}
		//Bundle args = new Bundle();
		//args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
		//currFragment.setArguments(args);

		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, currFragment).commit();

		// update selected item and title, then close the drawer
		mDrawerList.setItemChecked(position, true);
		setTitle(mDrawerOptionsTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	/////**********************************************************************************/////
	///*******************************   Facebook  *****************************************////
	
  


@Override
public void onResume() {
    super.onResume();
    uiHelper.onResume();
    isResumed = true;
}

@Override
public void onPause() {
    super.onPause();
    uiHelper.onPause();
    isResumed = false;
}

private void makeMeRequest(final Session session) {
    // Make an API call to get user data and define a 
    // new callback to handle the response.
    Request request = Request.newMeRequest(session, 
            new Request.GraphUserCallback() {

		@Override
		public void onCompleted(GraphUser user, Response response) {
            // If the response is successful
            if (session == Session.getActiveSession()) {
                if (user != null) {
                    // Set the id for the ProfilePictureView
                    // view that in turn displays the profile picture.
                    // Set the Textview's text to the user's name.
                	fbUserName=user.getName();
                	fbUserId=user.getId();
                	UserInfo.setId(fbUserId);
                    
        			AsyncHttpClient client = new AsyncHttpClient();
       		     RequestParams params = new RequestParams();
       		  params.put("name", fbUserName);
       		     params.put("email", fbUserId);
       		     
       		     client.get("http://bookclub-server.appspot.com/adduser",params, new AsyncHttpResponseHandler() {
       					@Override
       					public void onSuccess(int statusCode,
       							Header[] headers, byte[] response) {
       						String res=new String(response);
       						System.out.println(res);
       					}

       					@Override
       					public void onFailure(int arg0, Header[] arg1,
       							byte[] arg2, Throwable arg3) {
       					}

       				});
                }
            }
            if (response.getError() != null) {
                // Handle errors, will do so later.
            }				
		}
    });
    request.executeAsync();
} 

private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	System.out.println("here on session changed");
    // Only make changes if the activity is visible
    if (session != null && session.isOpened()) {
        // Get the user's data
        makeMeRequest(session);
        System.out.println("facebook user details:");
        System.out.println(fbUserId);
        System.out.println(fbUserName);
//        return;
    }
    System.out.println("here on session changed2");
    if (isResumed) {

        if (state.isOpened()) {
        	isLogedIn=true;
        	UserInfo.logIn(true);
	        System.out.println("here!!");
        } else if (state.isClosed()) {
        	isLogedIn=false;
        	UserInfo.logIn(false);
        }
    }
}


@Override
protected void onResumeFragments() {
	System.out.println("here On resume!!");
    super.onResumeFragments();
    Session session = Session.getActiveSession();

    if (session != null && session.isOpened()) {
    	isLogedIn=true;
    	UserInfo.logIn(true);
//    	selectionF=new SelectionFragment();
            // Get the user's data.
    	System.out.println("here On resume1.22!!");
            makeMeRequest(session);
            System.out.println("here On resume2!!");
            System.out.println("facebook user details after resume:");
            System.out.println(fbUserId);
            System.out.println(fbUserName);
//		 FragmentManager fragmentManager = getSupportFragmentManager();
//		 fragmentManager.popBackStack();
    } else {
    	isLogedIn=false;
    	UserInfo.logIn(false);
    }
}


private Session.StatusCallback callback = 
    new Session.StatusCallback() {
    @Override
    public void call(Session session, 
            SessionState state, Exception exception) {
        onSessionStateChange(session, state, exception);
    }
};

@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    uiHelper.onActivityResult(requestCode, resultCode, data);
//    if (requestCode == REAUTH_ACTIVITY_CODE) {
//        uiHelper.onActivityResult(requestCode, resultCode, data);
//    }
}

@Override
public void onDestroy() {
    super.onDestroy();
    uiHelper.onDestroy();
}


@Override
protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    uiHelper.onSaveInstanceState(outState);
}


private void showFragment(int fragmentIndex, boolean addToBackStack) {
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction transaction = fm.beginTransaction();
    for (int i = 0; i < fragments.length; i++) {
        if (i == fragmentIndex) {
            transaction.show(fragments[i]);
        } else {
            transaction.hide(fragments[i]);
        }
    }
    if (addToBackStack) {
        transaction.addToBackStack(null);
    }
    transaction.commit();
}

}
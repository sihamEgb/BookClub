
package technion.bookclub;


//import technion.bookclub.MainActivity.DrawerItemClickListener;
//import com.facebook.Session;

import technion.bookclub.entities.Club;
import android.app.Activity;
//import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
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
import android.widget.RadioButton;
import android.widget.Toast;

public class ClubPageActivity extends FragmentActivity {
	public int suggestedBooks=2;
	Club club;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.club_activity);
		

		 FragmentManager fragmentManager = getSupportFragmentManager();
		 ClubPageFragment fragment =new ClubPageFragment();
		
		fragmentManager.beginTransaction().replace(R.id.Club_Act, fragment).commit();
		// enable ActionBar app icon to behave as action to toggle nav drawer
//		getActionBar().setDisplayHomeAsUpEnabled(true);
	//	getActionBar().setHomeButtonEnabled(true);

	}

	public boolean isLoggedIn() {
	 //   Session session = Session.getActiveSession();
	   // return (session != null && session.isOpened());
		return true;
	}
	
	public void joinClub(View view) {
		if (!isLoggedIn()){
			//if(isSessionValid()){
			Intent myIntent = new Intent(this, SplashFragment.class);
			//myIntent.putExtra("key", value); //Optional parameters
			startActivityForResult(myIntent,0);
			//}
		}
	}

	public void getParticipants(View view) {
	}	
	
	
	public void getNextMeeting(View view) {
		 FragmentManager fragmentManager = getSupportFragmentManager();
		//android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		NextMeetingFragment fragment =new NextMeetingFragment();
		
		fragmentManager.beginTransaction().replace(R.id.Club_Act, fragment).commit();
		
	}
	
	public void getSeggestedBooks(View view) {
		 FragmentManager fragmentManager = getSupportFragmentManager();
		//android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		 SuggestedBooksFragment fragment =new SuggestedBooksFragment();
		
		fragmentManager.beginTransaction().replace(R.id.Club_Act, fragment).commit();
		
	}	
	

	/*
	public void SuggestedBooksFragment(View view) {
		//if (!isLoggedIn()){
			//if(isSessionValid()){
			Intent myIntent = new Intent(this, SplashFragment.class);
			//myIntent.putExtra("key", value); //Optional parameters
			startActivityForResult(myIntent,0);
			//}
		//}
	}*/
	
	public void NewBookSuggestion(View view) {
		if(suggestedBooks >=5){
			return;
		}
		RadioButton button;
		suggestedBooks++;
		switch (suggestedBooks){
		case 1:
			button=(RadioButton)findViewById(R.id.book1);
			button.setText("Book 1");
			button.setVisibility(1);
			break;
		case 2:
			button=(RadioButton)findViewById(R.id.book2);
			button.setText("Book 2");
			button.setVisibility(1);
			break;
		case 3:
			button=(RadioButton)findViewById(R.id.book3);
			button.setText("Book 3");
			button.setVisibility(1);
			break;
		case 4:
			button=(RadioButton)findViewById(R.id.book4);
			button.setText("Book 4");
			button.setVisibility(1);
			break;
		case 5:
			button=(RadioButton)findViewById(R.id.book5);
			button.setText("Book 5");
			button.setVisibility(1);
		default:
		}
	}
	
	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.book1:
	            if (checked)
	                // Pirates are the best
	            break;
	        case R.id.book2:
	            if (checked)
	                // Ninjas rule
	            break;
	        case R.id.book3:
	            if (checked)
	                // Pirates are the best
	            break;
	        case R.id.book4:
	            if (checked)
	                // Ninjas rule
	            break;
	        case R.id.book5:
	            if (checked)
	                // Pirates are the best
	            break;           
	    }
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

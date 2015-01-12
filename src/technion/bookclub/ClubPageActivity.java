
package technion.bookclub;


//import technion.bookclub.MainActivity.DrawerItemClickListener;
//import com.facebook.Session;

import java.util.Arrays;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import technion.bookclub.entities.Club;
import technion.bookclub.entities.SuggestedBook;
import android.app.Activity;
//import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ClubPageActivity extends FragmentActivity {
	public View currentView;
	public int selectedBook=0;
	public int suggestedBooks=2;
	public Club club;
	public SuggestedBook[] books;
	public String description;
	public String memeberNum;
	public String imageURL;
	public String clubId;
	public Bundle b;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.club_activity);
		
		b = getIntent().getExtras();
		String name = b.getString("name");
		setTitle(name);
		description=b.getString("description");
		memeberNum=b.getString("memeberNum");
		imageURL=b.getString("imageUrl");
		clubId=b.getString("clubId");
		
		
		 FragmentManager fragmentManager = getSupportFragmentManager();
		 ClubPageFragment fragment =new ClubPageFragment(description,memeberNum, imageURL);
		 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		 fragmentTransaction.replace(R.id.Club_Act, fragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();

	}

	public boolean isLoggedIn() {
	 //   Session session = Session.getActiveSession();
	   // return (session != null && session.isOpened());
		return true;
	}
	
	public void joinClub(View view) {
		currentView=view.getRootView();
		if (!isLoggedIn()){
			//if(isSessionValid()){
			Intent myIntent = new Intent(this, SplashFragment.class);
			//myIntent.putExtra("key", value); //Optional parameters
			startActivityForResult(myIntent,0);
			//}
		} else{
//			((Button)currentView).h;
			AsyncHttpClient client = new AsyncHttpClient();
		     RequestParams params = new RequestParams();
		     //TODO get user id
//		     params.put("clubId", "5795051464556544");
		     params.put("clubId", clubId);
		     params.put("userId", "5278093363118080");
		     params.put("op", "join");
		     client.get("http://jalees-bookclub.appspot.com/joinclub",params, new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode,
							Header[] headers, byte[] response) {
						System.out.println(response.toString());
						Integer num=Integer.parseInt(memeberNum);
						num++;
						memeberNum=num.toString();
						TextView memebers=(TextView)currentView.findViewById(R.id.participants_num);
						memebers.setText(memeberNum);
//						Button button=(Button)currentView.findViewById(R.id.Join_Club);
//						((Button)currentView).setVisibility(1);
					}

					@Override
					public void onFailure(int arg0, Header[] arg1,
							byte[] arg2, Throwable arg3) {
						System.out.println(clubId.toCharArray());
						// TODO Auto-generated method stub

					}

				});
		}
	}
	
	

	public void getParticipants(View view) {
		 FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//		setTitle("Next Meeting");
		MembersPageActivity fragment =new MembersPageActivity(clubId);
		
		fragmentTransaction.replace(R.id.Club_Act, fragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();				
	}	
	
///////////////////////////////// Next Meeting /////////////////////////////////////
	
	public void getNextMeeting(View view) {
		 FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//		setTitle("Next Meeting");
		NextMeetingFragment fragment =new NextMeetingFragment();
		
		fragmentTransaction.replace(R.id.Club_Act, fragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();		
	}
	
	

	public void joinMeeting(View view){
		
	}
	
	public void editMeeting(View view){
		
	}	
	
///////////////////////////////// Suggested Books /////////////////////////////////////	
	
	public void getSeggestedBooks(View view) {
		//TODO: 				
				AsyncHttpClient client = new AsyncHttpClient();
			     RequestParams params = new RequestParams();
			     params.put("clubId", "6205504040730624");
			     client.get("http://jalees-bookclub.appspot.com/getsuggestedbooks",params, new AsyncHttpResponseHandler() {

						@Override
						public void onSuccess(int statusCode,
								Header[] headers, byte[] response) {
						}

						@Override
						public void onFailure(int arg0, Header[] arg1,
								byte[] arg2, Throwable arg3) {
//							System.out.println("failed");
//							 TODO Auto-generated method stub
						}
					});
			     //setTitle("Suggested Books");
				 FragmentManager fragmentManager = getSupportFragmentManager();
				 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				 SuggestedBooksFragment fragment =new SuggestedBooksFragment();				
				 fragmentTransaction.replace(R.id.Club_Act, fragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
			}	
	
	public void Vote(View view){
		if(selectedBook==0){
			return;
		}
		
		AsyncHttpClient client = new AsyncHttpClient();
	     RequestParams params = new RequestParams();
	     params.put("suggestedBookId", "Sense and Sensibility");
	     client.get("http://jalees-bookclub.appspot.com/votetosuggestedbook",params, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(int statusCode,
						Header[] headers, byte[] response) {
				}

				@Override
				public void onFailure(int arg0, Header[] arg1,
						byte[] arg2, Throwable arg3) {
//					System.out.println("failed");
//					 TODO Auto-generated method stub
				}
			});		
		//TODO add vote to server
	//	books[selectedBook].addVote();
		Arrays.sort(books);
	}
	/*
	public void Suggest(View view){
		EditText edit=(EditText)findViewById(R.id.Edit_Location);
		edit.setVisibility(1);
		Button button=(Button)findViewById(R.id.Suggest);
		button.setVisibility(1);

	}
	*/	
	public void AddBookToServer(String bookName){
		AsyncHttpClient client = new AsyncHttpClient();
	    RequestParams params = new RequestParams();
	    params.put("title", bookName);
	    params.put("clubId", "6205504040730624");
	    client.get("http://jalees-bookclub.appspot.com/addsuggestedbook",params, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(int statusCode,
						Header[] headers, byte[] response) {
				}

				@Override
				public void onFailure(int arg0, Header[] arg1,
						byte[] arg2, Throwable arg3) {
				}
			});
	}
	
	
	public void NewBookSuggestion(View view) {
		if(suggestedBooks >=5){
			return;
		}
		RadioButton button;
		EditText edit=(EditText)findViewById(R.id.Edit_Location);
		String bookName=edit.getText().toString().trim();
		if(bookName.equals("")){
			return;
		}
		AddBookToServer(bookName);
		suggestedBooks++;
		//books[1];
		switch (suggestedBooks){
		case 1:
			button=(RadioButton)findViewById(R.id.book1);
			button.setText(bookName);
			button.setVisibility(1);
			break;
		case 2:
			button=(RadioButton)findViewById(R.id.book2);
			button.setText(bookName);
			button.setVisibility(1);
			break;
		case 3:
			button=(RadioButton)findViewById(R.id.book3);
			button.setText(bookName);
			button.setVisibility(1);
			break;
		case 4:
			button=(RadioButton)findViewById(R.id.book4);
			button.setText(bookName);
			button.setVisibility(1);
			break;
		case 5:
			button=(RadioButton)findViewById(R.id.book5);
			button.setText(bookName);
			button.setVisibility(1);
		default:
		}
/*		EditText edit=(EditText)findViewById(R.id.Edit_Location);
		edit.setVisibility(0);
		Button button2=(Button)findViewById(R.id.Suggest);
		button2.setVisibility(0);*/
	}
	
	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.book1:
	            if (checked)
	            	selectedBook=1;
	            break;
	        case R.id.book2:
	            if (checked)
	            	selectedBook=2;
	            break;
	        case R.id.book3:
	            if (checked)
	            	selectedBook=3;
	            break;
	        case R.id.book4:
	            if (checked)
	            	selectedBook=4;
	            break;
	        case R.id.book5:
	            if (checked)
	            	selectedBook=5;
	            break;           
	    }
	}
	
	
	/////////////////////////////// end of suggested books ///////////////////////////////////
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.club, menu);
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
		case R.id.Edit_Meeting:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public void setTitle(CharSequence title) {
		getActionBar().setTitle(title);
	}
}

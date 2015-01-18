
package technion.bookclub;


//import technion.bookclub.MainActivity.DrawerItemClickListener;
//import com.facebook.Session;

import java.util.ArrayList;
import android.support.v4.app.ListFragment;

import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.Session;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import technion.bookclub.entities.Club;
import technion.bookclub.entities.Meeting;
import technion.bookclub.entities.SuggestedBook;
import android.app.Activity;
//import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
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
import android.view.Gravity;
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
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ClubPageActivity extends FragmentActivity {
	public View currentView;
	public int selectedBook=0;
	public int suggestedBooks;
	public Club club;
	public SuggestedBook[] books;
	public String description;
	public String memeberNum;
	public String imageURL;
	public String clubId;
	public Bundle b;
	public ClubPageFragment clubFragment;
	public SuggestedBooksFragment booksFragment;
	public NextMeetingFragment_Edit editMeetingF;
	public NextMeetingFragment meetingFragment;
	public SplashFragment splashFragment;
	public boolean voteIsDone;
	private PopupWindow pwindo;
	public UserListAdapter adapter;
	
	public String bookName;
	public String location;
	public String date;
	public Meeting meeting;
	public String user="5278093363118080";
	public  boolean result;
	
	
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
		voteIsDone=false;
		bookName=new String("");
		location=new String("");
		date=new String("");
		meeting=new Meeting();
		
		AsyncHttpClient client = new AsyncHttpClient();
	     RequestParams params = new RequestParams();
	     params.put("clubId", clubId);
	     client.get("http://jalees-bookclub.appspot.com/getclubmeeting",params, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(int statusCode,
						Header[] headers, byte[] response) {
//					JSONArray jsonItems;
					String result=new String(response);
//					System.out.println(result);
                   meeting= (Meeting.constructFromJson(result));
                   
                   bookName=meeting.getTitle();
                   date=meeting.getDate();
                   location=meeting.getLocation();
                   if(!date.isEmpty()){
                		 FragmentManager fragmentManager = getSupportFragmentManager();
                		 clubFragment =new ClubPageFragment(description,memeberNum, imageURL,date);
                		 
                		 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                		 fragmentTransaction.replace(R.id.Club_Act, clubFragment);
//                		fragmentTransaction.addToBackStack(null);
                		fragmentTransaction.commit();
                   }
                   
				}

				@Override
				public void onFailure(int arg0, Header[] arg1,
						byte[] arg2, Throwable arg3) {
//					System.out.println("failed");
//					 TODO Auto-generated method stub
				}
			});

  		 FragmentManager fragmentManager = getSupportFragmentManager();
		 clubFragment =new ClubPageFragment(description,memeberNum, imageURL,date);
		 
		 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		 fragmentTransaction.replace(R.id.Club_Act, clubFragment);
//		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

	public boolean isLoggedIn() {
	 //   Session session = Session.getActiveSession();
	   // return (session != null && session.isOpened());
		return true;
	}
	
	public void joinClub(View view) {
		
		currentView=view.getRootView();
	    Session session = Session.getActiveSession();
		if (session == null || session.isClosed() ){
			 FragmentManager fragmentManager = getSupportFragmentManager();
			 splashFragment =new SplashFragment();
			 
			 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			 fragmentTransaction.replace(R.id.Club_Act, splashFragment);
//			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
		} else{
			AsyncHttpClient client = new AsyncHttpClient();
		     RequestParams params = new RequestParams();
		     //TODO get user id
		     params.put("clubId", clubId);
		     params.put("userId", user);
		     params.put("op", "join");
		     client.get("http://jalees-bookclub.appspot.com/joinclub",params, new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode,
							Header[] headers, byte[] response) {
						String res=new String(response);
						System.out.println(res);
						if(res.trim().equals("member already joined this club".trim())){
							result=false;
							return;
						} else{
							Integer num=Integer.parseInt(memeberNum);
							num++;
							memeberNum=num.toString();
							clubFragment.setMembers(memeberNum);
							TextView memebers=(TextView)currentView.findViewById(R.id.participants_num);
							memebers.setText(memeberNum);

						}
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
		     if(!result){
				Toast.makeText(this, "Already joined this club",
						Toast.LENGTH_LONG).show();
		     } else {
				Toast.makeText(this, "Joined Successfully",
						Toast.LENGTH_LONG).show();
		     }
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
		
/*		
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View viewtemp = inflater.inflate(R.layout.members_activity, (ViewGroup) findViewById(R.id.members_layout));
		//adapter = new ClubListAdapter(getActivity(), data);
		pwindo = new PopupWindow(viewtemp, 300, 370, true);
		pwindo.showAtLocation(viewtemp, Gravity.CENTER, 0, 0);
		//setListAdapter(adapter);

		final Context context=this;
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
						TextView v2 = (TextView) viewtemp.findViewById(R.id.members_results_id);
						v2.setText("Members in this club:");
						
						MembersPageActivity t=new MembersPageActivity("clubId");
						t.setListAdapter(adapter);

					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
					}

				});
//		return view;
	*/
	}	
	
///////////////////////////////// Next Meeting /////////////////////////////////////
	
	public void getNextMeeting(View view) {
		
		AsyncHttpClient client = new AsyncHttpClient();
	     RequestParams params = new RequestParams();
	     params.put("clubId", clubId);
	     client.get("http://jalees-bookclub.appspot.com/getclubmeeting",params, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(int statusCode,
						Header[] headers, byte[] response) {
//					JSONArray jsonItems;
					String result=new String(response);
//					System.out.println(result);
                    meeting= (Meeting.constructFromJson(result));
                    
                    bookName=meeting.getTitle();
                    date=meeting.getDate();
                    location=meeting.getLocation();
                    
           		 FragmentManager fragmentManager = getSupportFragmentManager();
         		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//         		setTitle("Next Meeting");
         		meetingFragment =new NextMeetingFragment(bookName,date,location);
         		
         		fragmentTransaction.replace(R.id.Club_Act, meetingFragment);
         		fragmentTransaction.addToBackStack(null);
         		fragmentTransaction.commit();
				}

				@Override
				public void onFailure(int arg0, Header[] arg1,
						byte[] arg2, Throwable arg3) {
//					System.out.println("failed");
//					 TODO Auto-generated method stub
				}
			});
		
	}
	
	
	public void submitMeeting(View view){
		//TODO add to server
//		currentView=view.getRootView();
		EditText book=(EditText) findViewById(R.id.Book_Name_edit);
		EditText date=(EditText) findViewById(R.id.Edit_Date2);
		EditText location=(EditText) findViewById(R.id.Edit_Location2);
		if((book.getText()).toString().trim().equals("") || (date.getText()).toString().trim().equals("") ||
				(location.getText()).toString().trim().equals("")){
			return;
		}
		bookName=new String((book.getText()).toString().trim());
		this.location=new String ((location.getText()).toString().trim());
		this.date=new String((date.getText()).toString().trim());
		TextView meetingDate=(TextView) findViewById(R.id.meetingDate);
		meetingDate.setText(this.date);
//		meetingDate
//		deletesuggestedbook
		AsyncHttpClient client = new AsyncHttpClient();
	     RequestParams params = new RequestParams();
	     params.put("clubId", clubId);
	     params.put("bookId",bookName);
	     client.get("http://jalees-bookclub.appspot.com/deletesuggestedbook",params, new AsyncHttpResponseHandler() {

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
	     
		
		System.out.println(suggestedBooks);
		if(!((book.getText()).toString().trim().equals("") )){
//			remove book[0]
			for(int i=1;i<suggestedBooks;i++){
				books[i-1]=books[i];
			}
			suggestedBooks=suggestedBooks-1;
			books[suggestedBooks]=new SuggestedBook("00","00","00");
		}


//		addmeeting
		AsyncHttpClient client2 = new AsyncHttpClient();
	     RequestParams params2 = new RequestParams();
	     params2.put("clubId", clubId);
	     params2.put("title",bookName);
	     params2.put("location", this.location);
	     params2.put("date",this.date);
	     client2.get("http://jalees-bookclub.appspot.com/addmeeting",params2, new AsyncHttpResponseHandler() {

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
		
		 FragmentManager fragmentManager = getSupportFragmentManager();
		 fragmentManager.popBackStack();
		 fragmentManager.popBackStack();
		 
		 
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		meetingFragment =new NextMeetingFragment(bookName,this.date,this.location);
//		meetingFragment.setPrms();
		
		fragmentTransaction.replace(R.id.Club_Act, meetingFragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();		
		 

		 
		 
		
		 //TODO: add meeting to server
	
	}

	public void joinMeeting(View view){
		AsyncHttpClient client = new AsyncHttpClient();
	     RequestParams params = new RequestParams();
	     params.put("meetingId", meeting.getMeetingId());
	     params.put("userId",user);
	     client.get("http://jalees-bookclub.appspot.com/joinMeeting",params, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(int statusCode,
						Header[] headers, byte[] response) {
//					JSONArray jsonItems;
				}
				@Override
				public void onFailure(int arg0, Header[] arg1,
						byte[] arg2, Throwable arg3) {
//					System.out.println("failed");
//					 TODO Auto-generated method stub
				}
			});
			Toast.makeText(this, "Joined Successfully",
					Toast.LENGTH_LONG).show();
	}
	
	public void editMeeting(View view){
		final View currentView=view.getRootView();
		 FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//		setTitle("Next Meeting");
			editMeetingF =new NextMeetingFragment_Edit();
			fragmentTransaction.replace(R.id.Club_Act, editMeetingF);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();	
		
		AsyncHttpClient client = new AsyncHttpClient();
	     RequestParams params = new RequestParams();
	     params.put("clubId", clubId);
	     client.get("http://jalees-bookclub.appspot.com/getsuggestedbooks",params, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(int statusCode,
						Header[] headers, byte[] response) {
					JSONArray jsonItems;
					String result=new String(response);
					System.out.println(result);
					try {
						JSONObject json = new JSONObject(result);
						jsonItems=json.getJSONArray("results");
						suggestedBooks=jsonItems.length()-1;
						if(suggestedBooks <= 0){
							suggestedBooks=0;
//							return;
						}
						String[] title=new String[suggestedBooks];
						String[] numOfLikes=new String[suggestedBooks];
						books=new SuggestedBook[5];
		//
						for(int i=0; i<suggestedBooks;i++){
							json = jsonItems.getJSONObject(i);
							title[i]=json.getString("title");
							numOfLikes[i]=json.getString("numOfLikes");
							books[i]= new SuggestedBook(clubId,title[i],numOfLikes[i]);
						}
						for(int i=suggestedBooks; i<5;i++){
							Integer like=-i;
							books[i]= new SuggestedBook("00","00",like.toString());
						}
						
					} catch (JSONException e) {
						System.out.println("failed");
						e.printStackTrace();
					}
					Arrays.sort(books);

				     if(suggestedBooks != 0){
				    	 EditText des=(EditText) currentView.findViewById(R.id.Book_Name_edit);
				    	 des.setText(books[0].getSuggestedBookId());
				     }
				}

				@Override
				public void onFailure(int arg0, Header[] arg1,
						byte[] arg2, Throwable arg3) {
//					System.out.println("failed");
//					 TODO Auto-generated method stub
				}
			});
	     //setTitle("Suggested Books");

	}	
	
///////////////////////////////// Suggested Books /////////////////////////////////////	
	
	public void getSeggestedBooks(View view) {
		//TODO: 				
				AsyncHttpClient client = new AsyncHttpClient();
			     RequestParams params = new RequestParams();
			     params.put("clubId", clubId);
			     client.get("http://jalees-bookclub.appspot.com/getsuggestedbooks",params, new AsyncHttpResponseHandler() {

						@Override
						public void onSuccess(int statusCode,
								Header[] headers, byte[] response) {
							JSONArray jsonItems;
							String result=new String(response);
							System.out.println(result);
							try {
								JSONObject json = new JSONObject(result);
								jsonItems=json.getJSONArray("results");
								suggestedBooks=jsonItems.length()-1;
								if(suggestedBooks <= 0){
									suggestedBooks=0;
//									return;
								}
								System.out.println(suggestedBooks);
								String[] title=new String[suggestedBooks];
								String[] numOfLikes=new String[suggestedBooks];
								books=new SuggestedBook[5];
				//
								for(int i=0; i<suggestedBooks;i++){
									json = jsonItems.getJSONObject(i);
									title[i]=json.getString("title");
									numOfLikes[i]=json.getString("numOfLikes");
									books[i]= new SuggestedBook(clubId,title[i],numOfLikes[i]);
									System.out.println(title[i]);
								}
								for(int i=suggestedBooks; i<5;i++){
									Integer like=-i;
									books[i]= new SuggestedBook("00","00",like.toString());
								}
								
							} catch (JSONException e) {
								System.out.println("failed");
								e.printStackTrace();
							}
							Arrays.sort(books);
							 FragmentManager fragmentManager = getSupportFragmentManager();
							 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
							 booksFragment =new SuggestedBooksFragment(books,suggestedBooks);				
							 fragmentTransaction.replace(R.id.Club_Act, booksFragment);
							fragmentTransaction.addToBackStack(null);
							fragmentTransaction.commit();
						}

						@Override
						public void onFailure(int arg0, Header[] arg1,
								byte[] arg2, Throwable arg3) {
//							System.out.println("failed");
//							 TODO Auto-generated method stub
						}
					});
			     //setTitle("Suggested Books");

			}	
	
	public void Vote(View view){
		if(selectedBook==0 || voteIsDone==true){
			return;
		}
		System.out.println(selectedBook);
		AsyncHttpClient client = new AsyncHttpClient();
	     RequestParams params = new RequestParams();
	     params.put("title", books[selectedBook-1].getSuggestedBookId());
	     params.put("clubId", clubId);
	     params.put("op", "like");
	     client.get("http://jalees-bookclub.appspot.com/votetosuggestedbook",params, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(int statusCode,
						Header[] headers, byte[] response) {
					System.out.println("important!!!");
					System.out.println(selectedBook);
					books[selectedBook-1].addLike();
					Arrays.sort(books);
					RadioButton button;
//					selectedBook=0;
					for(int i=1; i<=suggestedBooks; i++){
						switch (i){
						case 1:
							button=(RadioButton)findViewById(R.id.book1);
							button.setText(books[i-1].getSuggestedBookId());
							button.setChecked(false);
//							button.setClickable(false);
							button.setVisibility(0);
							break;
						case 2:
							button=(RadioButton)findViewById(R.id.book2);
							button.setText(books[i-1].getSuggestedBookId());
							button.setChecked(false);
//							button.setClickable(false);
							button.setVisibility(0);
							break;
						case 3:
							button=(RadioButton)findViewById(R.id.book3);
							button.setText(books[i-1].getSuggestedBookId());
							button.setChecked(false);
//							button.setClickable(false);
							button.setVisibility(0);
							break;
						case 4:
							button=(RadioButton)findViewById(R.id.book4);
							button.setText(books[i-1].getSuggestedBookId());
							button.setChecked(false);
//							button.setClickable(false);
							button.setVisibility(0);
							break;
						case 5:
							button=(RadioButton)findViewById(R.id.book5);
							button.setText(books[i-1].getSuggestedBookId());
							button.setChecked(false);
//							button.setClickable(false);
							button.setVisibility(0);
						default:
							break;
						}
					}
					voteIsDone=true;
				}

				@Override
				public void onFailure(int arg0, Header[] arg1,
						byte[] arg2, Throwable arg3) {
					System.out.println("failed");
//					 TODO Auto-generated method stub
				}
			});		
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
	    params.put("clubId", clubId);
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
		EditText edit=(EditText)findViewById(R.id.Edit_title);
		String bookName=edit.getText().toString().trim();
		if(bookName.equals("")){
			return;
		}
		AddBookToServer(bookName);
		books[suggestedBooks]=new SuggestedBook(clubId,bookName,"0");
		suggestedBooks++;
		
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
	    System.out.println(view.getId());
	    System.out.println(checked);
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.book1:
	        	System.out.println("1");
	            if (checked)
	            	selectedBook=1;
	            break;
	        case R.id.book2:
	        	System.out.println("2");
	            if (checked)
	            	selectedBook=2;
	            break;
	        case R.id.book3:
	        	System.out.println("3");
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

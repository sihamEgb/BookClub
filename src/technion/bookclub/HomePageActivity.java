package technion.bookclub;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import technion.bookclub.entities.Club;
import technion.bookclub.entities.Meeting;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
 
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;

public class HomePageActivity extends FragmentActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener, HomePageInterface {
	//"6583832140578816"
	//"6583832140578816"
	private HomePageMeetingsListAdapter meetingsAdapter;
	private HomePageClubsListAdapter clubsAdapter;
	private HomePageBooksListAdapter booksAdapter;
	
	private String clubs_string;
   // private HashMap<String,String> clubs_ids;
    private HashMap<String,String> clubs_titles;
    private HashMap<String,String> club_meetings; 
    
	public static String userId;//="5278093363118080";
    private TabHost mTabHost;
    private ViewPager mViewPager;

    private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, HomePageActivity.TabInfo>();
    private HomePagePagerAdapter mPagerAdapter;
    /**
     * Maintains extrinsic info of a tab's construct
     */
    private class TabInfo {
         private String tag;
         private Class<?> clss;
         private Bundle args;
         private Fragment fragment;
         TabInfo(String tag, Class<?> clazz, Bundle args) {
             this.tag = tag;
             this.clss = clazz;
             this.args = args;
         }
 
    }

    class TabFactory implements TabContentFactory {
 
        private final Context mContext;
 
        /**
         * @param context
         */
        public TabFactory(Context context) {
            mContext = context;
        }
 

        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }
 
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout
        setContentView(R.layout.tabs_viewpager_layout);                
		if(this.getIntent().hasExtra("userId")){
			Bundle b = getIntent().getExtras();
			userId= b.getString("userId");
		}
		clubs_titles = new HashMap<String,String>();
		club_meetings = new HashMap<String,String>();
		getUserClubsInfoListFromServer();
        // Initialize the TabHost
        this.initialiseTabHost(savedInstanceState);
        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab")); //set the tab as per the saved state
        }
        // Intialise ViewPager
        this.intialiseViewPager();
        
    }
 

    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("tab", mTabHost.getCurrentTabTag()); //save the tab selected
        super.onSaveInstanceState(outState);
    }
 
    /**
     * Initialise ViewPager
     */
    private void intialiseViewPager() {
 
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, HomePageBooksListFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, HomePageClubsGridFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, HomePageMeetingsListFragment.class.getName()));
        this.mPagerAdapter  = new HomePagePagerAdapter(super.getSupportFragmentManager(), fragments);
        //
        this.mViewPager = (ViewPager)super.findViewById(R.id.viewpager);
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mViewPager.setOnPageChangeListener(this);
    }
 
    /**
     * Initialise the Tab Host
     */
    private void initialiseTabHost(Bundle args) {
        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();
        TabInfo tabInfo = null;
        HomePageActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Books").setIndicator("Books"), ( tabInfo = new TabInfo("Books", HomePageBooksListFragment.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        HomePageActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Clubs").setIndicator("Clubs"), ( tabInfo = new TabInfo("Clubs", HomePageClubsGridFragment.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        HomePageActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Meetings").setIndicator("Meetings"), ( tabInfo = new TabInfo("Meetings", HomePageMeetingsListFragment.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        // Default to first tab
        //this.onTabChanged("Tab1");
        //
        mTabHost.setOnTabChangedListener(this);
    }
 
    /**
     * Add Tab content to the Tabhost
     * @param activity
     * @param tabHost
     * @param tabSpec
     * @param clss
     * @param args
     */
    private static void AddTab(HomePageActivity activity, TabHost tabHost, TabHost.TabSpec tabSpec, TabInfo tabInfo) {
        // Attach a Tab view factory to the spec
        tabSpec.setContent(activity.new TabFactory(activity));
        tabHost.addTab(tabSpec);
    }

    public void onTabChanged(String tag) {
        //TabInfo newTab = this.mapTabInfo.get(tag);
        int pos = this.mTabHost.getCurrentTab();
        this.mViewPager.setCurrentItem(pos);
    }
 
    @Override
    public void onPageScrolled(int position, float positionOffset,
            int positionOffsetPixels) {
        // TODO Auto-generated method stub
 
    }

    @Override
    public void onPageSelected(int position) {
        // TODO Auto-generated method stub
        this.mTabHost.setCurrentTab(position);
    }
 
    @Override
    public void onPageScrollStateChanged(int state) {
        // TODO Auto-generated method stub
 
    }
    
    public String getUserId(){
    	return userId;
    }
    

	 private void getUserClubsInfoListFromServer(){
		  AsyncHttpClient client = new AsyncHttpClient();
         RequestParams params = new RequestParams();
         params.put("userId", userId);
         client.get("http://jalees-bookclub.appspot.com/getmyclubs",params, new AsyncHttpResponseHandler() {
                     @Override
                     public void onSuccess(int statusCode,Header[] headers, byte[] response) {
                   	  clubs_string = new String(response);
                   	  getClubsIdsFromJson(clubs_string);
                     }
                     
                     @Override
                     public void onFailure(int arg0, Header[] arg1,
                             byte[] arg2, Throwable arg3) {
                     }
                 });
	 }
    
    
		private void getClubsIdsFromJson(String result) {
			
			try {
				JSONObject obj = new JSONObject(result);
				JSONArray jsonArr = new JSONArray(obj.getString("results"));
				int numOfItems = jsonArr.length();
				JSONObject json;
				for (int i = 0; i < numOfItems; i++) {
					Club club = new Club();
					json = jsonArr.getJSONObject(i);
					club = Club.constructFromJson(json.toString());
					clubs_titles.put(club.getClubId(),club.getName());
					getClubMeetingFromServer(club.getClubId());
				}
				// return results;
			} catch (Exception e) {

			}
		}
    
		private void getClubMeetingFromServer(final String club_id){
			  AsyncHttpClient client = new AsyncHttpClient();
		         RequestParams params = new RequestParams();
		         params.put("clubId", club_id);
		         client.get("http://jalees-bookclub.appspot.com/getclubmeeting",params, new AsyncHttpResponseHandler() {
		                     @Override
		                     public void onSuccess(int statusCode,Header[] headers, byte[] response) {
		                         String s = new String(response);
		                         System.out.println("HomePage Meetings:"+s);
		                         club_meetings.put(club_id, s);
		                     }
		                     
		                     @Override
		                     public void onFailure(int arg0, Header[] arg1,
		                             byte[] arg2, Throwable arg3) {
		                    	 System.out.println("HomePage NO Meetings:");
		                    	 club_meetings.put(club_id, " ");
		                     }
		                 });
		}
    
    public void removeClubFromLists(String club_id){
    	if(club_meetings.containsKey(club_id)){
    		club_meetings.remove(club_id);
    	}
    	if(clubs_titles.containsKey(club_id)){
    		clubs_titles.remove(club_id);
    	}
    }
    
    public String getClubsString(){
    	return clubs_string;
    }
    public HashMap<String,String> getClubsTitles(){
    	return clubs_titles;
    }

    public HashMap<String,String> getClubMeetings(){
    	return club_meetings;
    }
    
    public HomePageMeetingsListAdapter getMeetingsAdapter(){
    	return meetingsAdapter;
    }


	@Override
	public void setClubsAdapter(HomePageClubsListAdapter adp) {
		clubsAdapter = adp;
	}


	@Override
	public void setBooksAdapter(HomePageBooksListAdapter adp) {
		booksAdapter = adp;
	}


	@Override
	public void setMeetingsAdapter(HomePageMeetingsListAdapter adp) {
		meetingsAdapter = adp;
	}


	@Override
	public HomePageBooksListAdapter getBooksAdapter() {
		return booksAdapter;
	}


	@Override
	public HomePageClubsListAdapter getClubsAdapter() {
		return clubsAdapter;
	}
}
package technion.bookclub;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
 
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
}
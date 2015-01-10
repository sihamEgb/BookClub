package technion.bookclub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomePageFragment extends Fragment {

	ViewPager vpPager;
	private HomePage_MyPagerAdapter adapterViewPager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.homepage_fragment_xml, container,
				false);

		vpPager = (ViewPager) v.findViewById(R.id.homepage_vpPager);
		adapterViewPager = new HomePage_MyPagerAdapter(this.getActivity()
				.getSupportFragmentManager());
		vpPager.setAdapter(adapterViewPager);

		vpPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// When swiping between pages, select the
				// corresponding tab.
				// actionBar.setSelectedNavigationItem(position);
			}
		});

		return v;
	}

	public class HomePage_MyPagerAdapter extends FragmentPagerAdapter {
		private static final int NUM_ITEMS = 3;

		public HomePage_MyPagerAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		// Returns total number of pages
		@Override
		public int getCount() {
			return NUM_ITEMS;
		}

		// Returns the fragment to display for that page
		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0: // Fragment # 0 - This will show FirstFragment
				return new HomePageBooksListFragment();
				// return new textFragment();
			case 1: // Fragment # 0 - This will show FirstFragment different
					// title
				return new HomePageClubsGridFragment();// ClubsListFragment();
			case 2: {// Fragment # 1 - This will show SecondFragment
				return new HomePageMeetingsListFragment();
			}
			default:
				return null;
			}
		}

		// Returns the page title for the top indicator
		@Override
		public CharSequence getPageTitle(int position) {
			return "Page " + position;
		}
	}

}

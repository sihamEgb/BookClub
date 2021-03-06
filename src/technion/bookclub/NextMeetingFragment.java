package technion.bookclub;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
//import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
//import android.view.ViewGroup;
import android.support.v4.app.Fragment;
//import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class NextMeetingFragment extends Fragment {

	public String bookName;
	public String location;
	public String date;
	
	public NextMeetingFragment(){}
	
	public NextMeetingFragment(String bookName, String date, String location) {
		this.bookName=bookName;
		this.date=date;
		this.location=location;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.next_meeting, container,
				false);
		setHasOptionsMenu(true);
		TextView bookT=(TextView) rootView.findViewById(R.id.Book_Name);
		TextView dateT=(TextView) rootView.findViewById(R.id.textView1);
		TextView locationT=(TextView) rootView.findViewById(R.id.textView2);
		bookT.setText(bookName);
		dateT.setText(this.date);
		locationT.setText(this.location);
		return rootView;
	}
	
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.club_next_meeting, menu);
//        menu.findItem(R.id.New_Meeting).setOnMenuItemClickListener(new menuItemClickListener)
    }
//    public boolean onMenuItemSelected(int featureId, MenuItem item) {
//		switch (item.getItemId()) {
//
//		case R.id.New_Meeting:
//			((ClubPageActivity)this.getActivity()).editMeeting();
//			return true;
//
//		default:
//			return ((ClubPageActivity)this.getActivity()).onOptionsItemSelected(item);
//		}
//    }
//    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//
//		case R.id.New_Meeting:
//			((ClubPageActivity)this.getActivity()).editMeeting();
//			return true;
//
//		default:
//			return ((ClubPageActivity)this.getActivity()).onOptionsItemSelected(item);
//		}
		return true;
	}
}

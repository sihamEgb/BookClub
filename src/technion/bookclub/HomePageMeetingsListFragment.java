package technion.bookclub;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

public class HomePageMeetingsListFragment extends ListFragment{
	private HomePageMeetingsListAdapter listAdapter;
    
	@Override
	  public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listAdapter = new HomePageMeetingsListAdapter(getActivity());
		setListAdapter(listAdapter);
    }
}

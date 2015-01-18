package technion.bookclub;

import java.util.HashMap;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomePageMeetingsListFragment extends ListFragment{
	private HomePageMeetingsListAdapter listAdapter;
	Context context;
    private String user_id;
    private HashMap<String,String> club_meetings ;//= new ArrayList<Meeting>();
    private HashMap<String,String> club_titles;// = new ArrayList<String>();

    @Override
	  public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		context = getActivity();
	    club_meetings = ((HomePageInterface)context).getClubMeetings();
	    club_titles = ((HomePageInterface)context).getClubsTitles();
		user_id = ((HomePageInterface)context).getUserId();
        listAdapter = new HomePageMeetingsListAdapter(getActivity(),club_meetings,club_titles);
	    setListAdapter(listAdapter);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	
}

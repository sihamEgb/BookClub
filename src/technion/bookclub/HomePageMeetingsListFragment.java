package technion.bookclub;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.http.Header;

import technion.bookclub.entities.Meeting;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
    private LinkedHashMap<String,Meeting> club_meetings ;//= new ArrayList<Meeting>();
    private LinkedHashMap<String,String> club_titles;// = new ArrayList<String>();

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

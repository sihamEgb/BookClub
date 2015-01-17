package technion.bookclub;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import technion.bookclub.entities.Club;
import technion.bookclub.entities.Meeting;

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
    public ArrayList<Meeting> user_meetings ;//= new ArrayList<Meeting>();
    public ArrayList<String> club_ids ;//= new ArrayList<String>();
    public ArrayList<String> club_titles;// = new ArrayList<String>();
    
	@Override
	  public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		context = getActivity();
	    user_meetings = new ArrayList<Meeting>();
	    club_ids = new ArrayList<String>();
	    club_titles = new ArrayList<String>();
		user_id = ((HomePageInterface)this.getActivity()).getUserId();
        listAdapter = new HomePageMeetingsListAdapter(getActivity(),user_meetings,club_titles);
		setListAdapter(listAdapter);
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	
	 private void getMeetings(){
		 System.out.println("W-W-W-W-W-W-W-W"+new String(((HomePageInterface)context).getMyClubsResponse()));
		 getClubIdsListFromJson(new String(((HomePageInterface)context).getMyClubsResponse()));
		 for(String c_id : club_ids){
			 AddMeetingFromServer(c_id);
		 }
	 }
	 
	 
		private void getClubIdsListFromJson(String result) {
			try {
				JSONObject obj = new JSONObject(result);
				JSONArray jsonArr = new JSONArray(obj.getString("results"));
				int numOfItems = jsonArr.length();
				JSONObject json;
				for (int i = 0; i < numOfItems; i++) {
					Club newClub = new Club();
					json = jsonArr.getJSONObject(i);
					newClub = Club.constructFromJson(json.toString());
					club_ids.add(newClub.getClubId());
					club_titles.add(newClub.getName());
				}
				// return results;
			} catch (Exception e) {
				System.out
						.println("homepageclubs adapter error :getting Clubs from string FAILED");
				// e.printStackTrace();
			}
		}
	
	
	private void AddMeetingFromServer(String club_id){
		  AsyncHttpClient client = new AsyncHttpClient();
	         RequestParams params = new RequestParams();
	         params.put("clubId", club_id);
	         client.get("http://jalees-bookclub.appspot.com/getclubmeeting",params, new AsyncHttpResponseHandler() {
	                     @Override
	                     public void onSuccess(int statusCode,Header[] headers, byte[] response) {
	                         String s = new String(response);
	                         user_meetings.add(Meeting.constructFromJson(s));
	                     }
	                     
	                     @Override
	                     public void onFailure(int arg0, Header[] arg1,
	                             byte[] arg2, Throwable arg3) {
	                     }
	                 });
	}
	
	
}

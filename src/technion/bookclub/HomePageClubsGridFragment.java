package technion.bookclub;

import org.apache.http.Header;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class HomePageClubsGridFragment extends Fragment{

    private String userClubsString;
    private String user_id=" 6583832140578816";
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		//return inflater.inflate(R.layout.homepage_clubs_fragment_xml, container, false); 
		
		//return super.onCreateView(inflater, container, savedInstanceState);
		
		View view = inflater.inflate(R.layout.homepage_clubs_fragment_xml, container, false);
		GridView gView = (GridView)view.findViewById(R.id.homepage_clubs_gridview);
		getUserClubsListFromServer();
		gView.setAdapter(new HomePageClubsListAdapter(this.getActivity(),userClubsString));
        return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
     
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	 private void getUserClubsListFromServer(){
		  AsyncHttpClient client = new AsyncHttpClient();
          RequestParams params = new RequestParams();
          params.put("userId", user_id);
          client.get("http://jalees-bookclub.appspot.com/getmyclubs",params, new AsyncHttpResponseHandler() {
                      @Override
                      public void onSuccess(int statusCode,Header[] headers, byte[] response) {
                          userClubsString = new String(response);
                          //String s =  new String(response);
                          System.out.println("SUCCESS - GETTING USER CLUBS FROM SERVER :" + userClubsString);
                          //System.out.println("SUCCESS - GETTING USER CLUBS FROM SERVER (s):" + userClubsString);
                      }
                      
                      @Override
                      public void onFailure(int arg0, Header[] arg1,
                              byte[] arg2, Throwable arg3) {
                    	  System.out.println("FAILED:GETTING CLUBS FROM SERVER ");
                      }
                  });
	 }
	
}

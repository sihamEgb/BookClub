package technion.bookclub;

import org.apache.http.Header;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

public class HomePageClubsGridFragment extends Fragment{

    public String userClubsString;
    private String user_id;
    GridView gView ;
    Context context;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		context = getActivity();
		View view = inflater.inflate(R.layout.homepage_clubs_fragment_xml, container, false);
		gView = (GridView)view.findViewById(R.id.homepage_clubs_gridview);
		user_id = ((HomePageInterface)this.getActivity()).getUserId();
		getUserClubsListFromServer();
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
                          System.out.println("SUCCESS - GETTING USER CLUBS FROM SERVER :" + userClubsString);
                    	  gView.setAdapter(new HomePageClubsListAdapter(context,userClubsString));    
                          ((HomePageInterface)context).setMyClubsResponse(response);
                      }
                      
                      @Override
                      public void onFailure(int arg0, Header[] arg1,
                              byte[] arg2, Throwable arg3) {
                    	  System.out.println("FAILED:GETTING CLUBS FROM SERVER ");
                    	  gView.setAdapter(new HomePageClubsListAdapter(context,""));
                    	 // Toast.makeText(context, "failed loading data" , Toast.LENGTH_SHORT).show();
                      }
                  });
	 }
	 
	
}

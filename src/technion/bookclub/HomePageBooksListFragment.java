package technion.bookclub;

import org.apache.http.Header;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;

public class HomePageBooksListFragment extends ListFragment{
	private HomePageBooksListAdapter listAdapter;
	//String[] options = {"Edit","Delete","Set As not Available"};
	String[] menuItems = {"Edit","Delete","Mark As Available"};
    private String userBooksString="";
    private String user_id="6583832140578816";
    
	@Override
	  public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getUserBooksListFromServer();
        listAdapter = new HomePageBooksListAdapter(getActivity(),userBooksString);
		setListAdapter(listAdapter);
		//getListView().setDivider(null);
		//this.getListView().setDividerHeight(0);// getListView().setDividerHeight(10); 
    }

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onActivityCreated(savedInstanceState);
		getListView().setDivider(null);
		getListView().setDividerHeight(0);
	}
	
    private void getUserBooksListFromServer(){
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("userId", user_id);

		client.get("http://jalees-bookclub.appspot.com/getmybooks",
				params, new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode,
							Header[] headers, byte[] response) {
						String s = new String(response);
						userBooksString=s;
						System.out.println("SUCCESS - GETTING USER BOOKS FROM SERVER" + s);
					}

					@Override
					public void onFailure(int arg0, Header[] arg1,
							byte[] arg2, Throwable arg3) {
						System.out.println("FAILED:GETTING BOOKS FROM SERVER ");
						}

				});
    }
	
}

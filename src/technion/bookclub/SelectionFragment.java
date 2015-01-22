package technion.bookclub;

import org.apache.http.Header;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.ProfilePictureView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SelectionFragment extends Fragment{
	
	private ProfilePictureView profilePictureView;
	private TextView userNameView;
	private TextView selection_user_id;
	private UiLifecycleHelper uiHelper;
	
	public String userName;
	public String userId;
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(final Session session, final SessionState state, final Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	
	private static final int REAUTH_ACTIVITY_CODE = 100;
	
	private static final String TAG = "SelectionFragment";
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, Bundle savedInstanceState) {
	    super.onCreateView(inflater, container, savedInstanceState);
	    View view = inflater.inflate(R.layout.selection, 
	            container, false);
	 // Find the user's profile picture custom view
	    profilePictureView = (ProfilePictureView) view.findViewById(R.id.selection_profile_pic);
	    profilePictureView.setCropped(true);

	    // Find the user's name view
	    userNameView = (TextView) view.findViewById(R.id.selection_user_name);
	   
	    selection_user_id = (TextView) view.findViewById(R.id.selection_user_id);
	    Session session = Session.getActiveSession();
	    if (session != null && session.isOpened()) {
	        // Get the user's data
	        makeMeRequest(session);
	    }
	    return view;
	}
	
	private void makeMeRequest(final Session session) {
	    // Make an API call to get user data and define a 
	    // new callback to handle the response.
	    Request request = Request.newMeRequest(session, 
	            new Request.GraphUserCallback() {

			@Override
			public void onCompleted(GraphUser user, Response response) {
	            // If the response is successful
	            if (session == Session.getActiveSession()) {
	                if (user != null) {
	                    // Set the id for the ProfilePictureView
	                    // view that in turn displays the profile picture.
	                    profilePictureView.setProfileId(user.getId());
	                    // Set the Textview's text to the user's name.
	                    userNameView.setText(user.getName());
	                    selection_user_id.setText(user.getId());
	                    userName=user.getName();
	                    userId=user.getId();
	                    
	                    
	        			AsyncHttpClient client = new AsyncHttpClient();
	       		     RequestParams params = new RequestParams();
	       		  params.put("name", userName);
	       		     params.put("email", userId);
	       		     
	       		     client.get("http://bookclub-server.appspot.com/adduser",params, new AsyncHttpResponseHandler() {
	       					@Override
	       					public void onSuccess(int statusCode,
	       							Header[] headers, byte[] response) {
	       						String res=new String(response);
	       						System.out.println(res);
	       					}

	       					@Override
	       					public void onFailure(int arg0, Header[] arg1,
	       							byte[] arg2, Throwable arg3) {
	       					}

	       				});
	                }
	            }
	            if (response.getError() != null) {
	                // Handle errors, will do so later.
	            }				
			}
	    });
	    request.executeAsync();
	} 
	
	private void onSessionStateChange(final Session session, SessionState state, Exception exception) {
	    if (session != null && session.isOpened()) {
	        // Get the user's data.
	        makeMeRequest(session);
	    }
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    if (requestCode == REAUTH_ACTIVITY_CODE) {
	        uiHelper.onActivityResult(requestCode, resultCode, data);
	    }
	}
	@Override
	public void onResume() {
	    super.onResume();
	    uiHelper.onResume();
	}

	@Override
	public void onSaveInstanceState(Bundle bundle) {
	    super.onSaveInstanceState(bundle);
	    uiHelper.onSaveInstanceState(bundle);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}
}

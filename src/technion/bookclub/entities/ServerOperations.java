package technion.bookclub.entities;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ServerOperations {
	
	public String addsuggestedbook(String title, String clubId){
		AsyncHttpClient client = new AsyncHttpClient();
	    RequestParams params = new RequestParams();
	    params.put("title", title);
	    params.put("clubId", clubId);
	    client.get("http://jalees-bookclub.appspot.com/addsuggestedbook",params, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(int statusCode,
						Header[] headers, byte[] response) {
					//TODO return "book added";
				}

				@Override
				public void onFailure(int arg0, Header[] arg1,
						byte[] arg2, Throwable arg3) {
					//TODO throw exception
				}
			});
	    return "book added";
	}

	//TODO change return value
	public Integer votetosuggestedbook(String suggestedBookId){
		AsyncHttpClient client = new AsyncHttpClient();
	    RequestParams params = new RequestParams();
	    params.put("suggestedBookId", suggestedBookId);
	    client.get("http://jalees-bookclub.appspot.com/votetosuggestedbook",params, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(int statusCode,
						Header[] headers, byte[] response) {
					//TODO return response;
				}

				@Override
				public void onFailure(int arg0, Header[] arg1,
						byte[] arg2, Throwable arg3) {
					//TODO throw exception
				}
			});
	    return 1;
	}
	
	
	//TODO change return value
	public SuggestedBook[] getsuggestedbooks(String clubId){
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("clubId", clubId);
		client.get("http://jalees-bookclub.appspot.com/getsuggestedbooks",params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode,
					Header[] headers, byte[] response) {
				//TODO return "book added";
			}

			@Override
			public void onFailure(int arg0, Header[] arg1,
					byte[] arg2, Throwable arg3) {
				//TODO throw exception
			}
		});
		return new SuggestedBook[2];
	}

	public String joinMeeting(String meetingId, String userId){
		AsyncHttpClient client = new AsyncHttpClient();
	    RequestParams params = new RequestParams();
	    params.put("meetingId", meetingId);
	    params.put("userId", userId);
	    client.get("http://jalees-bookclub.appspot.com/joinMeeting",params, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(int statusCode,
						Header[] headers, byte[] response) {
					//TODO return "book added";
				}

				@Override
				public void onFailure(int arg0, Header[] arg1,
						byte[] arg2, Throwable arg3) {
					//TODO throw exception
				}
			});
	    return "member joined meeting";
	}
	
	//op=join/leave
	public String joinclub(String clubId, String userId, String op){
		AsyncHttpClient client = new AsyncHttpClient();
	    RequestParams params = new RequestParams();
	    params.put("clubId", clubId);
	    params.put("userId", userId);
	    params.put("op", op);
	    client.get("http://jalees-bookclub.appspot.com/joinclub",params, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(int statusCode,
						Header[] headers, byte[] response) {
					//TODO return "book added";
				}

				@Override
				public void onFailure(int arg0, Header[] arg1,
						byte[] arg2, Throwable arg3) {
					//TODO throw exception
				}
			});
	    return "member joined club” OR “member leaved club";
	}
}
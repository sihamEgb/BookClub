package technion.bookclub.entities;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ServerOperations {

	private static ServerOperations Instance;
	private static String baseUrl;

	// private static String res;

	private ServerOperations() {
		Instance = null;
		baseUrl = "http://jalees-bookclub.appspot.com/";
	}

	/*
	 * addclub description, adminId, imageUrl return: “club added”
	 * 
	 * /deleteclub parameters: clubId return: success or fail
	 * 
	 * /searchclub - get all clubs in this location parameters: location return:
	 * json array
	 * 
	 * /joinclub parameters: clubId, userId, op=join/leave return: “member
	 * joined club” OR “member leaved club”
	 * 
	 * /addbook - add a new book to my/ collection of books parameters: title,
	 * location, author, language, ownerId, imageUrl return: “book added”
	 * 
	 * /deletebook - delete a book by id parameters: bookId return: success or
	 * fail
	 * 
	 * /searchbook parameters: title, location, language return: json array
	 * 
	 * /changebookstatus parameters: bookId return: new status (true/false)
	 * /addmeeting parameters: suggestedBookId, date, location return: “meeting
	 * added”
	 * 
	 * /joinMeeting parameters: meetingId, userId return: “member joined
	 * meeting”
	 * 
	 * /getmemebers parameters: clubId return: json array of the users
	 * 
	 * 
	 * /getsuggestedbooks parameters: clubId return: json array
	 * 
	 * /votetosuggestedbook - add a new vote for the suggested book (without
	 * user) parameters:suggestedBookId return: number of the likes
	 * 
	 * 
	 * /getnumberofsharedbooks
	 * 
	 * /getMyClubs parameters:userId return: json array of clubs /getMyMeetings
	 * parameters:userId return: number of the likes /getmybooks
	 * parameters:userId return: json array of books
	 */

	public static ServerOperations ServerOperationsFactory() {
		if (Instance == null)
			Instance = new ServerOperations();
		return (Instance);
	}

	public void addsuggestedbook(String title, String clubId) {

		RequestParams params = new RequestParams();
		params.put("title", title);
		params.put("clubId", clubId);
		AsyncHttpClient client = new AsyncHttpClient();

		client.get(baseUrl + "addsuggestedbook", params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] response) {
						final String res = new String(response);
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {

						// TODO throw exception
					}
				});
		// return res;
		// return "book added";
	}

	// TODO change return value
	public Integer votetosuggestedbook(String suggestedBookId) {
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("suggestedBookId", suggestedBookId);
		client.get("http://jalees-bookclub.appspot.com/votetosuggestedbook",
				params, new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] response) {
						// TODO return response;
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						// TODO throw exception
					}
				});
		return 1;
	}

	// TODO change return value
	public SuggestedBook[] getsuggestedbooks(String clubId) {
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("clubId", clubId);
		client.get("http://jalees-bookclub.appspot.com/getsuggestedbooks",
				params, new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] response) {
						// TODO return "book added";
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						// TODO throw exception
					}
				});
		return new SuggestedBook[2];
	}

	public String joinMeeting(String meetingId, String userId) {
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("meetingId", meetingId);
		params.put("userId", userId);
		client.get("http://jalees-bookclub.appspot.com/joinMeeting", params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] response) {
						// TODO return "book added";
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						// TODO throw exception
					}
				});
		return "member joined meeting";
	}

	// op=join/leave
	public String joinclub(String clubId, String userId, String op) {
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("clubId", clubId);
		params.put("userId", userId);
		params.put("op", op);
		client.get("http://jalees-bookclub.appspot.com/joinclub", params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] response) {
						// TODO return "book added";
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						// TODO throw exception
					}
				});
		return "member joined club” OR “member leaved club";
	}
}
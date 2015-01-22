package technion.bookclub;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class UserInfo {
	private static String id = "5278093363118080";//"5278093363118080";
	private static boolean loggedIn=false;
	//String id = "4859722209427456";
	// Fatima 5222194095325184
	// Ekram 6583832140578816
	// Siham 4859722209427456
	// Salam 5278093363118080
	// Bob 4824175986343936
	public static String getId(){
		return id;		
	}
	public static void setId(String fbUserId){
		AsyncHttpClient client = new AsyncHttpClient();
	     RequestParams params = new RequestParams();
	     params.put("email", fbUserId);
	     client.get("http://bookclub-server.appspot.com/getuser",params, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(int statusCode,
						Header[] headers, byte[] response) {
					String res=new String(response);
					System.out.println(res);
					id=res;
				}

				@Override
				public void onFailure(int arg0, Header[] arg1,
						byte[] arg2, Throwable arg3) {
				}

			});

	}
	public static boolean isLoggedIn(){
		return loggedIn;		
	}
	public static void logIn(boolean state){
		loggedIn=state;		
	}
	
}

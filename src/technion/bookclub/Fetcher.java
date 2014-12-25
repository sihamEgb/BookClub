package technion.bookclub;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Fetcher {

	private Context myContext;

	public Fetcher(Context myContext) {
		this.myContext = myContext;

	}

	private Boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) myContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null
				&& activeNetworkInfo.isConnectedOrConnecting();
	}
	
	

}
/*
 * import org.apache.http.Header; import technion.bookclub.entities.Club; import
 * android.content.Context; import android.widget.Toast;
 * 
 * 
 * 
 * // http://jalees-bookclub.appspot.com/getclub?name=sabih private static final
 * String BASE_URL = "http://api.twitter.com/1/";
 * 
 * private static AsyncHttpClient client = new AsyncHttpClient();
 * 
 * 
 * 
 * public static void addClub(Club newClub) {
 * 
 * }
 * 
 * public static void get(String url, RequestParams params,
 * AsyncHttpResponseHandler responseHandler) {
 * 
 * client.get(getAbsoluteUrl(url), params, responseHandler); }
 * 
 * public static void post(String url, RequestParams params,
 * AsyncHttpResponseHandler responseHandler) { client.post(getAbsoluteUrl(url),
 * params, responseHandler); }
 * 
 * private static String getAbsoluteUrl(String relativeUrl) { return BASE_URL +
 * relativeUrl; }
 * 
 * protected void onPostExecute(String result) {
 * 
 * Toast.makeText(myContext, "club added successfully", Toast.LENGTH_SHORT)
 * .show();
 * 
 * } }
 */

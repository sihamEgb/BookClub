package technion.bookclub.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import technion.bookclub.entities.Club;
import android.content.Context;

public class DataLoader {

	public static String loadJsonFromAsset(Context myContext, String fileName) {
		String json = null;
		try {

			InputStream is = myContext.getAssets().open(fileName);
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json = new String(buffer, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;
	}

	/*
	 * public static void initUsers(Context context, ArrayList<User> usersList)
	 * {
	 * 
	 * String data = loadJsonFromAsset(context, "usersDataSet.json"); try {
	 * JSONArray jsonArr = new JSONArray(data); int numOfItems =
	 * jsonArr.length(); JSONObject json; for (int i = 0; i < numOfItems; i++) {
	 * User newUser = new User(); json = jsonArr.getJSONObject(i);
	 * newUser.fromJson(json); usersList.add(newUser); } } catch (Exception e) {
	 * e.printStackTrace(); } }
	 * 
	 * public static void initBooks(Context context, ArrayList<Book> booksList)
	 * {
	 * 
	 * String data = loadJsonFromAsset(context, "booksDataSet.json"); try {
	 * JSONArray jsonArr = new JSONArray(data); int numOfItems =
	 * jsonArr.length(); JSONObject json; for (int i = 0; i < numOfItems; i++) {
	 * Book newBook = new Book(); json = jsonArr.getJSONObject(i);
	 * newBook.fromJson(json); booksList.add(newBook); } } catch (Exception e) {
	 * e.printStackTrace(); } }
	 */
	public static void initClubs(Context context, ArrayList<Club> clubsList) {

		String data = loadJsonFromAsset(context, "clubsDataSet.json");
		try {
			JSONArray jsonArr = new JSONArray(data);
			int numOfItems = jsonArr.length();
			JSONObject json;
			for (int i = 0; i < numOfItems; i++) {
				Club newClub = new Club();
				json = jsonArr.getJSONObject(i);
				newClub.fromJson(json);
				clubsList.add(newClub);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}

package technion.bookclub.entities;

import java.util.Set;

import com.google.gson.Gson;

public class SuggestedBook {
	private String suggestedBookId;
	private String title;
	// private String goodReadsUrl;
	private Number numOfLikes;
	private String clubId;

	// private Set<String> usersId;

	

	public String getSuggestedBookId() {
		return suggestedBookId;
	}

	public void setSuggestedBookId(String suggestedBookId) {
		this.suggestedBookId = suggestedBookId;
	}

	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	public static SuggestedBook constructFromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, SuggestedBook.class);
	}

	/*
	 * public boolean addLike(String userId) { if (usersId.contains(userId) ==
	 * true) return false; numOfLikes++; usersId.add(userId); return true; }
	 * 
	 * public boolean removeLike(String userId) { if (usersId.contains(userId)
	 * == false) return false; numOfLikes--; usersId.remove(userId); return
	 * true; }
	 */

}

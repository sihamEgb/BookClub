package technion.bookclub.entities;

import java.util.Set;

import com.google.gson.Gson;

public class SuggestedBook implements Comparable<SuggestedBook>{
	private String suggestedBookId;
	private String title;
	// private String goodReadsUrl;
	private Number numOfLikes;
	private String clubId;

	// private Set<String> usersId;

	
	public SuggestedBook(String clubId,String title,String numOfLikes){
		suggestedBookId=title;
		this.title=title;
		this.numOfLikes=Integer.parseInt(numOfLikes);
		this.clubId=clubId;
	}
	public String getTitle() {
		return title;
	}
	public String getSuggestedBookId() {
		return suggestedBookId;
	}
	public void addLike() {
		this.numOfLikes=numOfLikes.intValue()+1;
	}
	public int getLikes() {
		return numOfLikes.intValue();
	}
	public void setTitle(String title) {
		this.title = title;
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
	@Override
	public int compareTo(SuggestedBook another) {
		return another.getLikes() - this.numOfLikes.intValue() ;
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

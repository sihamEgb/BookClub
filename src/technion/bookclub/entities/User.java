package technion.bookclub.entities;

import com.google.gson.Gson;

public class User {

	private String userId;
	private String name;
	private String email;

	// private Set<String> ownedClubs; 
	// private Set<String> myJoinedClubs;
	// private Set<String> myBooks;
	// meetings
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	public static User constructFromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, User.class);
	}

}

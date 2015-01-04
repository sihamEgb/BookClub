package technion.bookclub.entities;

import java.util.Set;

import org.json.JSONObject;

public class Club {

	private String clubId ;
	private String adminId;// = "admin";
	private String name;
	private String location;
	private String category;
	private String description;
	private Set<String> members;
	private String imageUrl = "http://subheksha.tk/wp-content/themes/creativemag/images/default.png";
	private Meeting nextMeeting;

	public Meeting getNextMeeting() {
		return nextMeeting;
	}

	
	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<String> getMemberId() {
		return members;
	}

	public void addMemeber(String newMemeber) {
		this.members.add(newMemeber);
	}


	
	/*
	 * returns number of members in this club
	 */
	public int getNumber() {
		return members.size();
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Club fromJson(JSONObject json) {

		// TODO
		// Set<String> members;
		// String imageUrl;

		try {
		//	if(json.getString("clubId") != null)
		//		this.setClubId(json.getString("clubId"));
			this.setAdminId(json.getString("adminId"));
			this.setName(json.getString("name"));
			this.setLocation(json.getString("location"));
			this.setCategory(json.getString("category"));
			this.setDescription(json.getString("description"));

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return this;
	}

}

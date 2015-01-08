package technion.bookclub.entities;

import com.google.gson.Gson;

public class Club {

	private String clubId;
	private String adminId;
	private String name;
	private String location;
	private String description;
	private String imageUrl;
	private String memeberNum;

	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getMemeberNum() {
		return memeberNum;
	}

	public void setMemeberNum(String memeberNum) {
		this.memeberNum = memeberNum;
	}

	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	public static Club constructFromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, Club.class);
	}

}

package technion.bookclub.entities;

import com.google.gson.Gson;

public class Meeting {

	// private String book;
	// private String date;
	// private Set<String> participants;

	private String meetingId;
	private String title;
	 private String date;
	private String location;
	private String clubId;

	/*
	 * public String getBook() { return book; }
	 * 
	 * public String getDate() { return date; }
	 * 
	 * public Integer getParticipantsNumber() { return participants.size(); }
	 * 
	 * public void addParticipant(String participant) {
	 * participants.add(participant); }
	 */
	public String getDate(){
		return date;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getMeetingId(){
		return meetingId;
	}
	
	public String getLocation(){
		return location;
	}
	
	public String getClubId(){
		return clubId;
	}
	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	public static Meeting constructFromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, Meeting.class);
	}

}
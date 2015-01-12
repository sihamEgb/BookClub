package technion.bookclub.entities;

import java.util.Set;

public class Meeting {
	private String book;
	private String date;
	private Set<String> participants;

	public String getBook() {
		return book;
	}

	public String getDate() {
		return date;
	}

	public Integer getParticipantsNumber() {
		return participants.size();
	}

	public void addParticipant(String participant) {
		participants.add(participant);
	}

}
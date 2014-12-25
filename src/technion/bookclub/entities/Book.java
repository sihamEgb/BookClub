package technion.bookclub.entities;

import org.json.JSONObject;

public class Book {
	private String bookId;
	private String ownerId;
	private String title;
	private String author;
	private String location;
	private String language;
	private String imageUrl; // or default image
	private boolean isAvailable;

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", ownerId=" + ownerId + ", title="
				+ title + ", isAvailable=" + isAvailable + "]";
	}

	public Book fromJson(JSONObject json) {

		try {
			this.setBookId(json.getString("bookId"));
			this.setOwnerId(json.getString("ownerId"));
			this.setTitle(json.getString("title"));
			this.setAuthor(json.getString("author"));
			this.setLocation(json.getString("location"));
			this.setLanguage(json.getString("language"));
			this.setImageUrl(json.getString("imageUrl"));
			if (json.getString("isAvailable").equals("true"))
				this.isAvailable = true;
			else
				this.isAvailable = false;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return this;
	}

}

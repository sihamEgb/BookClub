package technion.bookclub.entities;

public class SuggestedBook implements Comparable<SuggestedBook>{
	 
	private String bookName;
	private String bookDesc;
	private int votes;
 
	public SuggestedBook(String bookName, String bookDesc, int votes) {
		super();
		this.bookName = bookName;
		this.bookDesc = bookDesc;
		this.votes = votes;
	}
 
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookDesc() {
		return bookDesc;
	}
	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}
	public int getVotes() {
		return votes;
	}
	public void addVote() {
		this.votes++;
	}
 
	public int compareTo(SuggestedBook book) {
 
		int numberOfVotes = ((SuggestedBook) book).getVotes(); 
 
		//ascending order
		//return this.votes - numberOfVotes;
 
		//descending order
		return numberOfVotes - this.votes;
 
	}	
}
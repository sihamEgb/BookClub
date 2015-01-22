package technion.bookclub;

import java.util.LinkedHashMap;

import technion.bookclub.entities.Club;
import technion.bookclub.entities.Meeting;

public interface HomePageInterface {
	public String getUserId();
    
	public String getClubsString();
	public LinkedHashMap<String,Club> getClubs();
    public LinkedHashMap<String,String> getClubsTitles();
    public LinkedHashMap<String,Meeting> getClubMeetings();
    
	public void setClubsAdapter(HomePageClubsListAdapter adp);
	public void setBooksAdapter(HomePageBooksListAdapter adp);
	public void setMeetingsAdapter(HomePageMeetingsListAdapter adp);
	
	public HomePageBooksListAdapter getBooksAdapter();
	public HomePageClubsListAdapter getClubsAdapter();
	public HomePageMeetingsListAdapter getMeetingsAdapter();
	
	public void removeClubFromLists(String club_id);
}

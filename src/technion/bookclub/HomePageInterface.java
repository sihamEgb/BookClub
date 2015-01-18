package technion.bookclub;

import java.util.HashMap;

public interface HomePageInterface {
	public String getUserId();
    
	public String getClubsString();
    public HashMap<String,String> getClubsTitles();
    public HashMap<String,String> getClubMeetings();
    
	public void setClubsAdapter(HomePageClubsListAdapter adp);
	public void setBooksAdapter(HomePageBooksListAdapter adp);
	public void setMeetingsAdapter(HomePageMeetingsListAdapter adp);
	
	public HomePageBooksListAdapter getBooksAdapter();
	public HomePageClubsListAdapter getClubsAdapter();
	public HomePageMeetingsListAdapter getMeetingsAdapter();
	
	public void removeClubFromLists(String club_id);
}
